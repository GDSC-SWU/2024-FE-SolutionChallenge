package com.app.fe.community;

import com.app.fe.common.dto.DefaultRes;
import com.app.fe.community.dto.CommunityReq;
import com.app.fe.community.dto.CommunityRes;
import com.app.fe.community.dto.ReplyReq;
import com.app.fe.community.dto.ReplyRes;
import com.app.fe.community.service.CommunityService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/community")
@RestController
public class CommunityController {

    private final CommunityService tblCommunityService;

    /**
     * 게시물
     */
    @PostMapping("/create")
    public DefaultRes<CommunityReq.Create, CommunityRes.Detail> create(
            @RequestPart(value = "tblCommunityReq") CommunityReq.Create create,
            @RequestPart(value = "tblCommunityFile", required = false) MultipartFile file) {
        return new DefaultRes<>(create, tblCommunityService.create(create, file));
    }

    @GetMapping("/detail/{tblCommunityId}")
    public DefaultRes<Long, CommunityRes.Detail> detail(
            @PathVariable(value = "tblCommunityId") Long tblCommunityId) {
        return new DefaultRes<>(tblCommunityId, tblCommunityService.detail(tblCommunityId));
    }

    @PostMapping("/search")
    public DefaultRes<CommunityReq.Search, CommunityRes.PageItem> search(
            @RequestBody CommunityReq.Search search) {
        return new DefaultRes<>(search, tblCommunityService.search(search));
    }

    @PostMapping("/update")
    public DefaultRes<CommunityReq.Update, CommunityRes.Detail> search(
            @RequestBody @Valid CommunityReq.Update update) {
        return new DefaultRes<>(update, tblCommunityService.update(update));
    }

    @DeleteMapping("/delete/{tblCommunityId}")
    public DefaultRes<Long, String> delete(@PathVariable(value = "tblCommunityId") Long tblCommunityId) {
        return new DefaultRes<>(tblCommunityId, tblCommunityService.delete(tblCommunityId));
    }


    /**
     * 댓글
     */
    @PostMapping("/reply/create")
    public DefaultRes<ReplyReq.Create, ReplyRes.PageItem> createReply(
            @RequestBody ReplyReq.Create createReply) {
        return new DefaultRes<>(createReply, tblCommunityService.createReply(createReply));
    }

    @PostMapping("/reply/search")
    public DefaultRes<ReplyReq.Search, ReplyRes.PageItem> searchReply(
            @RequestBody ReplyReq.Search search) {
        return new DefaultRes<>(search, tblCommunityService.searchReply(search));
    }

    @PostMapping("/reply/update")
    public DefaultRes<ReplyReq.Update, ReplyRes.Detail> updateReply(
            @RequestBody @Valid ReplyReq.Update update) {
        return new DefaultRes<>(update, tblCommunityService.updateReply(update));
    }

    @DeleteMapping("/reply/delete/{tblReplyId}")
    public DefaultRes<Long, String> deleteReply(@PathVariable(value = "tblReplyId") Long tblReplyId) {
        return new DefaultRes<>(tblReplyId, tblCommunityService.deleteReply(tblReplyId));
    }

    /**
     * 파일
     */
    @PostMapping("/thumb-nail/download/{thumbNailFileId}")
    public ResponseEntity<Resource> downloadThumbNailFile(@PathVariable(value = "thumbNailFileId") Long thumbNailFileId,
                                                          HttpServletRequest httpServletRequest) {
        return tblCommunityService.downloadThumbNailFile(thumbNailFileId, httpServletRequest);
    }

    @DeleteMapping("/thumb-nail/delete/{thumbNailFileId}")
    public DefaultRes<Long, String> deleteThumbNailFile(@PathVariable(value = "thumbNailFileId") Long thumbNailFileId) {
        return new DefaultRes<>(thumbNailFileId, tblCommunityService.deleteThumbNailFile(thumbNailFileId));
    }
}
