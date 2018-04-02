package com.hdh.redpacket.user.service;

import com.hdh.redpacket.core.utils.DictUtils;
import com.hdh.redpacket.core.utils.InviteCodeUtil;
import com.hdh.redpacket.core.utils.SecrityUtils;
import com.hdh.redpacket.core.utils.UuidUtil;
import com.hdh.redpacket.system.constant.DictEnum;
import com.hdh.redpacket.system.constant.PropertyTypeEnum;
import com.hdh.redpacket.system.model.ConfigDic;
import com.hdh.redpacket.system.service.VerificationService;
import com.hdh.redpacket.user.mapper.UserMapper;
import com.hdh.redpacket.user.dto.RegisterDto;
import com.hdh.redpacket.user.exception.UserException;
import com.hdh.redpacket.user.model.User;
import com.hdh.redpacket.user.model.UserProperty;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class RegisterService {

    // 默认邀请一个人增加的概率
    private static final BigDecimal INVITE_ADD_PROD_DEFAULT = new BigDecimal(0.02);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private UserPropertyService userPropertyService;

    @Autowired
    private PropertyLogService propertyLogService;

    /**
     * 用户注册
     * @param registerDto
     */
    @Transactional
    public void register(RegisterDto registerDto){
        if(registerDto == null || StringUtils.isBlank(registerDto.getPassword()) ||
                (StringUtils.isBlank(registerDto.getEmail()) && StringUtils.isBlank(registerDto.getName()))){
            throw UserException.PARAMS_ERROR;
        }

        //  注册通用校验
        this.commonRegisterVerify(registerDto);

        // 检验图形验证码
//        verificationService.checkVerfyCode(registerDto.getBindKey(),registerDto.getVerifyCode());

        // 是否有邀请码，有则进行相应的操作
        if(StringUtils.isNotBlank(registerDto.getInviteCode())){
            addPropertyByInvite(registerDto.getInviteCode());
        }

        // 初始化用户数据
        User newUser = initNewUserDefaultData(registerDto);

        // 进行注册
        userMapper.insert(newUser);

        // 初始化用户属性
        initUserProperty(newUser.getId());

    }

    /**
     * 初始化用户属性
     * @param userId
     */
    private void initUserProperty(String userId){
        if(StringUtils.isBlank(userId)){
            throw UserException.PARAMS_ERROR;
        }

        UserProperty userProperty = new UserProperty();
        userProperty.setUserId(userId);
        userProperty.setCreateTime(new Date());
        userProperty.setDiamond(0);
        userProperty.setInviteNum(0);
        userProperty.setLevel(1);
        userProperty.setPoint(0);
        userProperty.setProbability(new BigDecimal(20));
        userProperty.setWinProbability(BigDecimal.ZERO);
        userProperty.setRechargeAmount(BigDecimal.ZERO);
        userPropertyService.addUserProperty(userProperty);
    }

    /**
     * 通用注册校验
     * @param registerDto
     */
    private void commonRegisterVerify(RegisterDto registerDto){
        // 检验用户名是否存在
        if(StringUtils.isNotBlank(registerDto.getName())){
            User user = userMapper.getByName(registerDto.getName());
            if(user != null){
                throw UserException.NAME_ISEXIST_ERROR;
            }
        }
        // 检验邮箱是否已经存在
        if(StringUtils.isNotBlank(registerDto.getEmail())){
            User user = userMapper.getByEmail(registerDto.getEmail());
            if(user != null){
                throw UserException.EMAIL_ISEXIST_ERROR;
            }
        }
    }

    /**
     * 初始化新用户信息
     * @param registerDto
     * @return
     */
    private User initNewUserDefaultData(RegisterDto registerDto){
        User user = new User();
        user.setId(UuidUtil.genUuidNoLine());
        user.setEmail(registerDto.getEmail());
        user.setGender(registerDto.getGender());
        user.setName(registerDto.getName());
        String salt = RandomStringUtils.randomAlphabetic(8);
        user.setSalt(salt);
        user.setPassword(SecrityUtils.md5Pwd(salt,registerDto.getPassword()));
        user.setTel(registerDto.getTel());
        user.setPlatform(registerDto.getPlatform());
        user.setState(1);
        long count = userMapper.getCount();
        user.setInviteCode(InviteCodeUtil.idToCode(count+1));
        return user;
    }

    /**
     * 增加用户属性根据邀请人
     * @param invideCode
     */
    private void addPropertyByInvite(String invideCode){
        if(StringUtils.isBlank(invideCode)){
            throw UserException.PARAMS_ERROR;
        }

        User user = userMapper.getByInviteCode(invideCode);
        if(user == null){
            throw UserException.INVITECODE_USER_NOT_EXIST;
        }

        // 进行加邀请人数及概率操作
        UserProperty userProperty = userPropertyService.getUserProperties(user.getId());
        BigDecimal oldProbability = userProperty.getProbability();
        userProperty.setInviteNum(userProperty.getInviteNum()+1);
        ConfigDic configDic = DictUtils.getDic(DictEnum.INVITE_ADD_PROD.getCode());
        if(configDic != null){
            userProperty.setProbability(userProperty.getProbability().add(BigDecimal.ONE));
        }else{
            userProperty.setProbability(userProperty.getProbability().add(INVITE_ADD_PROD_DEFAULT));
        }
        userPropertyService.updateUserProperty(userProperty);

        // 插入概率变化记录表
        propertyLogService.addPropertyLog(user.getId(), PropertyTypeEnum.PROBABILITY.name(),oldProbability,userProperty.getProbability(),1,new Date());
        // 插入邀请人数变化记录表
        propertyLogService.addPropertyLog(user.getId(), PropertyTypeEnum.INVITE_NUM.name(),new BigDecimal(userProperty.getInviteNum()-1),new BigDecimal(userProperty.getInviteNum()),1,new Date());
    }

}
