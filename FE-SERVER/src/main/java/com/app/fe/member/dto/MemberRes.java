package com.app.fe.member.dto;

import com.app.fe.member.code.MemberRole;
import com.app.fe.member.entity.Member;
import com.app.fe.nation.dto.NationRes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

public class MemberRes {

    @Getter
    @Builder
    public static class Detail {
        private Long tblMemberId;
        private String name;
        private String email;

        private String picture;

        private MemberRole memberRole;

        @JsonIgnore
        private String provider;

        private String providerId;

        @JsonIgnore
        private String refreshToken;

        private NationRes.Detail tblNation;

        public static Detail of(Member tblMember) {
            return Detail.builder()
                    .tblMemberId(tblMember.getTblMemberId())
                    .name(tblMember.getName())
                    .email(tblMember.getEmail())
                    .picture(tblMember.getPicture())
                    .memberRole(tblMember.getMemberRole())
                    .provider(tblMember.getProvider())
                    .providerId(tblMember.getProviderId())
                    .refreshToken(tblMember.getRefreshToken())
                    .tblNation(Objects.nonNull(tblMember.getTblNation()) ? NationRes.Detail.of(tblMember.getTblNation()) : null)
                    .build();
        }
    }
}
