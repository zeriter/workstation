package com.workstation.security.service;

import com.workstation.modules.system.domain.security.AuthInfo;
import com.workstation.modules.system.domain.security.UserDetails;
import com.workstation.modules.system.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 认证服务类
 * @date 2023/12/9 17:57 周六
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthInfo userAuthInfo = userService.getUserAuthInfo(username);
        if (userAuthInfo == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserDetails(userAuthInfo);
    }
}
