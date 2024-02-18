package com.app.fe.community.dto;

import com.app.fe.common.dto.BaseSearchReq;
import com.app.fe.community.code.CommunityType;
import com.app.fe.community.code.FileType;
import com.app.fe.community.entity.Community;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CommunityReq {

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Create {

        @NotNull
        private CommunityType communityType;

        @NotBlank
        private String communityTitle;

        @NotBlank
        private String communityContent;

        @NotNull
        private FileType fileType;

        public Community toEntity() {
            return Community.builder()
                    .communityType(communityType)
                    .communityTitle(communityTitle)
                    .communityContent(communityContent)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Update {

        @NotNull
        private Long tblCommunityId;

        private CommunityType communityType;
        private String communityTitle;
        private String communityContent;

        public Community toEntity() {
            return Community.builder()
                    .communityType(this.communityType)
                    .communityTitle(this.communityTitle)
                    .communityContent(this.communityContent)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Search extends BaseSearchReq {

    }
}
