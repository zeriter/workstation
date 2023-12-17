package com.workstation.config;

import com.workstation.common.annotation.OpenAuth;
import com.workstation.enums.RequestMethodEnum;
import com.workstation.security.constants.SecurityConstants;
import com.workstation.security.handler.MyAccessDeniedHandler;
import com.workstation.security.handler.MyAuthenticationEntryPoint;
import com.workstation.security.jwt.JwtTokenFilter;
import com.workstation.security.jwt.JwtTokenProvider;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;


/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description Security配置类
 * @date 2023/12/9 17:03 周六
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Resource
    private MyAuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    private MyAccessDeniedHandler accessDeniedHandler;
    @Resource
    private JwtTokenProvider jwtTokenProvider;
    @Resource
    private ApplicationContext applicationContext;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requestMatcherRegistry ->
                        requestMatcherRegistry.requestMatchers(SecurityConstants.LOGIN_PATH).permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer
                                .authenticationEntryPoint(authenticationEntryPoint)
                                .accessDeniedHandler(accessDeniedHandler)
                )
                .csrf(AbstractHttpConfigurer::disable)

        ;
        // JWT 校验过滤器
        http.addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * 不走过滤器链的放行配置
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) applicationContext.getBean("requestMappingHandlerMapping");
        Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = requestMappingHandlerMapping.getHandlerMethods();
        Set<String> get = new HashSet<>();
        Set<String> post = new HashSet<>();
        Set<String> put = new HashSet<>();
        Set<String> patch = new HashSet<>();
        Set<String> delete = new HashSet<>();
        Set<String> all = new HashSet<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethodMap.entrySet()) {
            HandlerMethod handlerMethod = infoEntry.getValue();
            OpenAuth openAuth = handlerMethod.getMethodAnnotation(OpenAuth.class);
            if (null != openAuth) {
                List<RequestMethod> requestMethods = new ArrayList<>(infoEntry.getKey().getMethodsCondition().getMethods());
                RequestMethodEnum request = RequestMethodEnum.find(requestMethods.size() == 0 ? RequestMethodEnum.ALL.getType() : requestMethods.get(0).name());
                Set<String> patternValues = null;
                if (infoEntry.getKey().getPathPatternsCondition() != null) {
                    patternValues = infoEntry.getKey().getPathPatternsCondition().getPatternValues();
                    switch (Objects.requireNonNull(request)) {
                        case GET -> get.addAll(patternValues);
                        case POST -> post.addAll(patternValues);
                        case PUT -> put.addAll(patternValues);
                        case PATCH -> patch.addAll(patternValues);
                        case DELETE -> delete.addAll(patternValues);
                        default -> all.addAll(patternValues);
                    }
                }
            }
        }
        return (web) -> web.ignoring()
                .requestMatchers(
                        "/webjars/**",
                        "/doc.html",
                        "/swagger-resources/**",
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/ws/**",
                        "/ws-app/**"
                )
                .requestMatchers(HttpMethod.GET, get.toArray(new String[0]))
                .requestMatchers(HttpMethod.PATCH, patch.toArray(new String[0]))
                .requestMatchers(HttpMethod.POST, post.toArray(new String[0]))
                .requestMatchers(HttpMethod.PUT, put.toArray(new String[0]))
                .requestMatchers(HttpMethod.DELETE, delete.toArray(new String[0]))
                .requestMatchers(all.toArray(new String[0]));
    }

    /**
     * 密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * AuthenticationManager 手动注入
     *
     * @param authenticationConfiguration 认证配置
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
