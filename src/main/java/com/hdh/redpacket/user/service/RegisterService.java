package com.hdh.redpacket.user.service;

import com.hdh.redpacket.core.utils.InviteCodeUtil;
import com.hdh.redpacket.core.utils.SecrityUtils;
import com.hdh.redpacket.system.service.VerificationService;
import com.hdh.redpacket.user.mapper.UserMapper;
import com.hdh.redpacket.user.dto.RegisterDto;
import com.hdh.redpacket.user.exception.UserException;
import com.hdh.redpacket.user.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VerificationService verificationService;

    /**
     * 用户注册
     * @param registerDto
     */
    public void register(RegisterDto registerDto){
        if(registerDto == null || StringUtils.isBlank(registerDto.getPassword()) ||
                (StringUtils.isBlank(registerDto.getEmail()) && StringUtils.isBlank(registerDto.getName()) ||
                StringUtils.isBlank(registerDto.getVerifyCode()))){
            throw UserException.PARAMS_ERROR;
        }

        //  注册通用校验
        this.commonRegisterVerify(registerDto);

        // 检验图形验证码
        verificationService.checkVerfyCode(registerDto.getBindKey(),registerDto.getVerifyCode());

        // 初始化用户数据
        User newUser = initNewUserDefaultData(registerDto);

        // 进行注册
        userMapper.insert(newUser);
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
        user.setEmail(registerDto.getEmail());
        user.setGender(registerDto.getGender());
        user.setName(registerDto.getName());
        String salt = RandomStringUtils.randomAlphabetic(8);
        user.setSalt(salt);
        user.setPassword(SecrityUtils.md5Pwd(salt,registerDto.getPassword()));
        user.setTel(registerDto.getTel());
        user.setPlatform(registerDto.getPlatform());
        long count = userMapper.getMaxId();
        user.setInviteCode(InviteCodeUtil.idToCode(count+1));
        return user;
    }
}
