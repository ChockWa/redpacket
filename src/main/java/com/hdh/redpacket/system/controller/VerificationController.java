package com.hdh.redpacket.system.controller;

import com.hdh.redpacket.core.annotation.MustLogin;
import com.hdh.redpacket.core.model.Result;
import com.hdh.redpacket.core.utils.CaptchaGenerateor;
import com.hdh.redpacket.core.utils.UuidUtil;
import com.hdh.redpacket.system.service.VerificationService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class VerificationController {

    private Logger logger = LoggerFactory.getLogger(VerificationController.class);

    @Autowired
    private VerificationService verificationService;

    /**
     * 生成图形验证码
     * @return
     */
    @RequestMapping("/getVerifyCode")
    @MustLogin(false)
    @ResponseBody
    public Result verifyCodeGenBaseDivide(){
        Result result = Result.SUCCESS();
        int codeLen = 4; // 验证码长度
        String bindKey = UuidUtil.genUuidNoLine(); // 图形验证码绑定的临时值
        CaptchaGenerateor captcha = new CaptchaGenerateor(verificationService.verifyCodeGeneration(codeLen, bindKey));
        RenderedImage buffImg = captcha.generate();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(buffImg, "jpeg", outStream);
            result.setData("verifyImage", "data:image/jpg;base64," + new String(Base64.encodeBase64(outStream.toByteArray())));
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                outStream.close();
            } catch (IOException e) {
                logger.error("close outStream fail", e);
                e.printStackTrace();
            }
        }
        return result;
    }
}
