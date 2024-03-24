package com.workstation.auth.service.impl;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
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
        AbstractCaptcha captcha;
        String code;
        String uuid = IdUtil.randomUUID();
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;
        if (Constants.CAPTCHA_MATH.equalsIgnoreCase(properties.getCaptchaType())) {
            RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
            captcha = CaptchaUtil.createLineCaptcha(130, 48);
            captcha.setGenerator(randomGenerator);
            captcha.createCode();
        } else {
            captcha = CaptchaUtil.createShearCaptcha(130, 48, 4, 4);
        }
        code = captcha.getCode();
        redisUtil.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        return Dict.create().set("captchaKey", uuid).set("captchaBase64", "data:image/png;base64," + captcha.getImageBase64());
    }
}
