package com.app.fe.community.dto;

import com.app.fe.common.dto.BaseRes;
import com.app.fe.common.dto.BaseSearchRes;
import com.app.fe.community.code.CommunityType;
import com.app.fe.community.entity.Community;
import com.app.fe.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class CommunityRes {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Detail extends BaseRes {
        private Long tblCommunityId;
        private String communityTypeCode;
        private String communityTypeValue;
        private String communityTitle;
        private String communityContent;
        private Member tblMember;
        private CommunityFileRes.Detail thumbNailFile;

        @Builder
        public Detail(Long tblCommunityId, Member tblMember,
                      CommunityType communityType, String communityTitle,
                      String communityContent, CommunityFileRes.Detail thumbNailFile) {
            this.tblCommunityId = tblCommunityId;
            this.tblMember = tblMember;
            this.communityTypeCode = communityType.name();
            this.communityTypeValue = communityType.getValue();
            this.communityTitle = communityTitle;
            this.communityContent = communityContent;
            this.thumbNailFile = thumbNailFile;
        }

        public static Detail of(Community tblCommunity) {
            Detail detail = Detail.builder()
                    .tblCommunityId(tblCommunity.getTblCommunityId())
                    .communityType(tblCommunity.getCommunityType())
                    .communityTitle(tblCommunity.getCommunityTitle())
                    .communityContent(tblCommunity.getCommunityContent())
                    .thumbNailFile(!CollectionUtils.isEmpty(tblCommunity.getThumbNailFile()) ?
                            CommunityFileRes.Detail.of(tblCommunity.getThumbNailFile().get(0)) : null)
                    .build();
            detail.initMemberBaseRes(tblCommunity);
            return detail;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Item extends BaseRes {
        private Long tblCommunityId;
        private String communityTypeCode;
        private String communityTypeValue;
        private String communityTitle;
        private String communityContent;
        private CommunityFileRes.Detail thumbNailFile;

        @Builder
        public Item(Long tblCommunityId, CommunityType communityType, String communityTitle,
                    String communityContent, CommunityFileRes.Detail thumbNailFile) {
            this.tblCommunityId = tblCommunityId;
            this.communityTypeCode = communityType.name();
            this.communityTypeValue = communityType.getValue();
            this.communityTitle = communityTitle;
            this.communityContent = communityContent;
            this.thumbNailFile = thumbNailFile;
        }

        public static Item of(Community tblCommunity) {
            Item item = Item.builder()
                    .tblCommunityId(tblCommunity.getTblCommunityId())
                    .communityType(tblCommunity.getCommunityType())
                    .communityContent(tblCommunity.getCommunityContent())
                    .thumbNailFile(!CollectionUtils.isEmpty(tblCommunity.getThumbNailFile()) ?
                            CommunityFileRes.Detail.of(tblCommunity.getThumbNailFile().get(0)) : null)
                    .build();
            item.initMemberBaseRes(tblCommunity);
            return item;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PageItem {
        private List<Item> items;
        private BaseSearchRes pageRes;

        public PageItem(List<Item> items, BaseSearchRes pageRes) {
            this.items = items;
            this.pageRes = pageRes;
        }
    }
}
