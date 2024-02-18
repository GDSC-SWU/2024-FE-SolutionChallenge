package com.app.fe.community.dto;

import com.app.fe.common.dto.BaseRes;
import com.app.fe.common.dto.BaseSearchRes;
import com.app.fe.community.entity.Reply;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ReplyRes {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Detail extends BaseRes {
        private Long tblReplyId;
        private String replyContent;

        @Builder
        public Detail(Long tblReplyId, String replyContent) {
            this.tblReplyId = tblReplyId;
            this.replyContent = replyContent;
        }

        public static Detail of(Reply tblReply) {
            Detail detail = Detail.builder()
                    .tblReplyId(tblReply.getTblReplyId())
                    .replyContent(tblReply.getReplyContent())
                    .build();
            detail.initMemberBaseRes(tblReply);
            return detail;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Item extends BaseRes {
        private Long tblReplyId;
        private String replyContent;

        @Builder
        public Item(Long tblReplyId, String replyContent) {
            this.tblReplyId = tblReplyId;
            this.replyContent = replyContent;
        }

        public static Item of(Reply tblReply) {
            Item item = Item.builder()
                    .tblReplyId(tblReply.getTblReplyId())
                    .replyContent(tblReply.getReplyContent())
                    .build();
            item.initMemberBaseRes(tblReply);
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
