package com.app.fe.config;

import com.app.fe.common.oauth2.handler.OAuth2AuthenticationFailureHandler;
import com.app.fe.common.oauth2.handler.OAuth2AuthenticationSuccessHandler;
import com.app.fe.member.code.MemberRole;
import com.app.fe.common.jwt.CookieAuthorizationRequestRepository;
import com.app.fe.common.jwt.JwtAccessDeniedHandler;
import com.app.fe.common.jwt.JwtAuthenticationEntryPoint;
import com.app.fe.common.jwt.JwtAuthenticationFilter;
import com.app.fe.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final AuthService authService;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
    private final OAuth2AuthenticationSuccessHandler authenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler authenticationFailureHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .httpBasic(AbstractHttpConfigurer::disable)
                // 기본 form 로그인 비활성화
                .formLogin(AbstractHttpConfigurer::disable)
                // JWT를 사용하므로 서버 세션 비활성화
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 요청 인가 처리 설정
        http
                .authorizeHttpRequests(request -> request.requestMatchers(
                                AntPathRequestMatcher.antMatcher("/"),
                                AntPathRequestMatcher.antMatcher("/favicon.ico"),
                                AntPathRequestMatcher.antMatcher("/h2-console/**"),
                                AntPathRequestMatcher.antMatcher("/auth/**"),
                                AntPathRequestMatcher.antMatcher("/logout"),
                                AntPathRequestMatcher.antMatcher("/oauth2/**"))
                        .permitAll())
                .authorizeHttpRequests(request -> request.requestMatchers(
                                AntPathRequestMatcher.antMatcher("/api/v1/**"),
                                AntPathRequestMatcher.antMatcher("/tbl/work/**")
                        ).hasRole(MemberRole.USER.name())
                        .anyRequest().authenticated());

        http.oauth2Login(oauth2 -> {
            oauth2.authorizationEndpoint(authorizationEndpointConfig -> authorizationEndpointConfig.authorizationRequestRepository(cookieAuthorizationRequestRepository));
            oauth2.userInfoEndpoint(ep -> ep.userService(authService));
            oauth2.successHandler(authenticationSuccessHandler);
            oauth2.failureHandler(authenticationFailureHandler);
        }).logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutSuccessUrl("/auth/token"));

        http.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
            httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(jwtAuthenticationEntryPoint);
            httpSecurityExceptionHandlingConfigurer.accessDeniedHandler(jwtAccessDeniedHandler);
        });

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
