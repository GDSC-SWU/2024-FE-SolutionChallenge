package com.app.fe.community.dto;

import com.app.fe.common.dto.BaseSearchReq;
import com.app.fe.community.entity.Community;
import com.app.fe.community.entity.Reply;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ReplyReq {

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Create extends BaseSearchReq {
        private Long tblCommunityId;
        private String replyContent;

        public Reply toEntity(Community tblCommunity) {
            return Reply.builder()
                    .tblCommunity(tblCommunity)
                    .replyContent(this.replyContent)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Search extends BaseSearchReq {
        private Long tblCommunityId;
    }

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Update {
        private Long tblReplyId;
        private String replyContent;

        public Reply toEntity() {
            return Reply.builder()
                    .replyContent(this.replyContent)
                    .build();
        }
    }
}
