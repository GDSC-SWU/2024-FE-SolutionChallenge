package com.app.fe.auth.service;

import com.app.fe.common.util.OAuthAttributes;
import com.app.fe.member.code.MemberRole;
import com.app.fe.member.entity.Member;
import com.app.fe.common.jwt.JwtTokenProvider;
import com.app.fe.common.model.CustomUserDetails;
import com.app.fe.common.util.CookieUtil;
import com.app.fe.member.repository.MemberRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AuthService extends DefaultOAuth2UserService {

    @Value("${app.auth.token.refresh-cookie-key}")
    private String cookieKey;

    private final JwtTokenProvider tokenProvider;
    private final MemberRepository tblMemberRepository;

    @Transactional
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // 기본 디폴트 OAuth Service
        OAuth2UserService auth2UserService = new DefaultOAuth2UserService();

        // 소셜에서 가져온 User 정보를 할당
        OAuth2User oAuth2User = auth2UserService.loadUser(userRequest);

        // 소셜 구분 이름
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // OAuth2 로그인 진행시 필수 키 값(PK)
        String userNameAttr = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        // 유저 정보들
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // registrationId 소셜 구분 값에 따라 Member Entity 생성
        Member updateData = OAuthAttributes.extract(registrationId, attributes);
        updateData.updateProvider(registrationId);

        // 신규면 가입, 기존 유저면 정보 업데이트
        Member tblMember;
        Optional<Member> memberOptional = tblMemberRepository.findByEmailAndProvider(updateData.getEmail(), updateData.getProvider());
        if (memberOptional.isPresent()) {
            tblMember = memberOptional.get();
            tblMember.update(updateData);
        } else {
            tblMember = tblMemberRepository.save(Member.builder()
                    .name(updateData.getName())
                    .email(updateData.getEmail())
                    .picture(updateData.getPicture())
                    .provider(updateData.getProvider())
                    .providerId(updateData.getProviderId())
                    .memberRole(MemberRole.USER)
                    .build());
        }

        /*DefaultOAuth2User defaultOAuth2User = new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(member.getMemberRole().getValue())),
                customAttribute(attributes, userNameAttr, member, registrationId),
                userNameAttr);*/

        log.debug("##### 로그인 성공");
        return CustomUserDetails.create(tblMember);
    }

    @Transactional
    public String refreshToken(HttpServletRequest request, HttpServletResponse response, String oldAccessToken) {
        // 1. Validation Refresh Token
        String oldRefreshToken = CookieUtil.getCookie(request, cookieKey)
                .map(Cookie::getValue).orElseThrow(() -> new RuntimeException("no Refresh Token Cookie"));

        log.debug("쿠키 리프레시 토큰 : {}", oldRefreshToken);
        log.debug("액세스 토큰 : {}", oldAccessToken);

        if (!tokenProvider.validateToken(oldRefreshToken)) {
            throw new RuntimeException("Not Validated Refresh Token");
        }

        // 2. 유저정보 얻기
        Authentication authentication = tokenProvider.getAuthentication(oldAccessToken.trim());
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        Long id = Long.valueOf(user.getName());
        Member tblMember = tblMemberRepository.getTblMember(id);

        // 3. Match Refresh Token
        String savedToken = tblMember.getRefreshToken();

        if (!savedToken.equals(oldRefreshToken)) {
            throw new RuntimeException("Not Matched Refresh Token");
        }

        // 4. JWT 갱신
        String accessToken = tokenProvider.createAccessToken(authentication);
        tokenProvider.createRefreshToken(authentication, response);

        return accessToken;
    }

    /**
     * 필요할 때 사용
     * @param attributes
     * @param userNameAttributeName
     * @param tblMember
     * @param registrationId
     * @return
     */
    private Map<String, Object> customAttribute(Map<String, Object> attributes, String userNameAttributeName,
                                                Member tblMember, String registrationId) {
        Map<String, Object> customAttribute = new LinkedHashMap<>();
        customAttribute.put(userNameAttributeName, attributes.get(userNameAttributeName));
        customAttribute.put("provider", registrationId);
        customAttribute.put("name", tblMember.getName());
        customAttribute.put("email", tblMember.getEmail());
        customAttribute.put("picture", tblMember.getPicture());
        return customAttribute;
    }
}
