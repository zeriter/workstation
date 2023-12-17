package com.workstation.auth.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.IdUtil;
import com.workstation.auth.service.IAuthService;
import com.workstation.common.constant.CacheConstants;
import com.workstation.common.constant.Constants;
import com.workstation.common.properties.SystemProperties;
import com.workstation.common.utils.RedisUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 认证相关服务类
 * @date 2023/12/4 20:32 周一
 */
@Service
public class AuthServiceImpl implements IAuthService {
    @Resource
    private SystemProperties properties;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public Dict captcha() {
        if (Constants.CAPTCHA_MATH.equalsIgnoreCase(properties.getCaptchaType())) {
            RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
            LineCaptcha captcha = CaptchaUtil.createLineCaptcha(160, 60);
            captcha.setGenerator(randomGenerator);
            captcha.createCode();
            String code = captcha.getCode();
            // 保存验证码信息
            String uuid = IdUtil.randomUUID();
            String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;
            redisUtil.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
            return Dict.create().set("uuid", uuid).set("img", "data:image/gif;base64," + captcha.getImageBase64());
        } else {
            ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(160, 60, 4, 4);
            String code = captcha.getCode();
            String uuid = IdUtil.randomUUID();
            String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;
            redisUtil.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
            return Dict.create().set("uuid", uuid).set("img", "data:image/gif;base64," + captcha.getImageBase64());
        }
    }
}
