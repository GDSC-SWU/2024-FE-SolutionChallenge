package com.app.fe.common.util;

import com.app.fe.member.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum OAuthAttributes {

    GOOGLE("google", (attributes) -> Member.builder()
            .name(String.valueOf(attributes.get("name")))
            .email(String.valueOf(attributes.get("email")))
            .picture(String.valueOf(attributes.get("picture")))
            .provider("google")
            .providerId("google_" + attributes.get("sub"))
            .build());

    private final String registratrionId;
    private final Function<Map<String, Object>, Member> of;

    // provider (소셜 식별 ID)가 일치 하면 Member 반환
    public static Member extract(String registratrionId, Map<String, Object> attributes) {
        return Arrays.stream(values())
                .filter(p -> registratrionId.equals(p.registratrionId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("구글 계정을 확인 해주세요."))
                .of
                .apply(attributes);
    }
}
