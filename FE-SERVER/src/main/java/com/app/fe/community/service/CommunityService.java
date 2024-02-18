package com.app.fe.community.service;

import com.app.fe.common.dto.BaseSearchRes;
import com.app.fe.common.entity.FileBaseEntity;
import com.app.fe.common.util.FileIoUtil;
import com.app.fe.common.util.SecurityUtil;
import com.app.fe.community.dto.CommunityReq;
import com.app.fe.community.dto.CommunityRes;
import com.app.fe.community.dto.ReplyReq;
import com.app.fe.community.dto.ReplyRes;
import com.app.fe.community.entity.Community;
import com.app.fe.community.entity.CommunityFile;
import com.app.fe.community.entity.Reply;
import com.app.fe.community.repository.CommunityFileRepository;
import com.app.fe.community.repository.CommunityRepository;
import com.app.fe.community.repository.ReplyRepository;
import com.app.fe.member.entity.Member;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = false)
@Service
public class CommunityService {

    private final CommunityRepository tblCommunityRepository;
    private final ReplyRepository tblReplyRepository;
    private final CommunityFileRepository tblCommunityFileRepository;

    private final SecurityUtil securityUtil;
    private final FileIoUtil fileIoUtil;

    /**
     * 게시물
     */
    public CommunityRes.Detail create(CommunityReq.Create create, MultipartFile file) {
        Member currentTblMember = securityUtil.getCurrentTblMember();
        Community tblCommunity = create.toEntity();
        tblCommunity.creator(currentTblMember);

        if (Objects.nonNull(file)) {
            log.debug("###### FILE : {}", file.getOriginalFilename());

            FileBaseEntity fileBaseEntity = fileIoUtil.saveFile(file, create.getFileType());
            CommunityFile tblCommunityFile = CommunityFile.builder()
                    .fileBaseEntity(fileBaseEntity)
                    .tblCommunity(tblCommunity)
                    .build();
            tblCommunityFile.creator(currentTblMember);
            tblCommunity.addFile(tblCommunityFile);
        }

        return CommunityRes.Detail.of(tblCommunityRepository.save(tblCommunity));
    }

    public CommunityRes.Detail detail(Long tblCommunityId) {
        return CommunityRes.Detail.of(tblCommunityRepository.getTblCommunity(tblCommunityId));
    }

    public CommunityRes.PageItem search(CommunityReq.Search search) {
        return getSearchTblCommuninities(tblCommunityRepository.findAll(search.ofPageble()));
    }

    @Transactional
    public CommunityRes.Detail update(CommunityReq.Update update) {
        Member currentTblMember = securityUtil.getCurrentTblMember();
        Community tblCommunity = tblCommunityRepository.getTblCommunity(update.getTblCommunityId());
        Community updateData = update.toEntity();

        tblCommunity.update(updateData);
        tblCommunity.updater(currentTblMember);

        return CommunityRes.Detail.of(tblCommunity);
    }

    @Transactional
    public String delete(Long tblCommunityId) {
        Community tblCommunity = tblCommunityRepository.getTblCommunity(tblCommunityId);
        List<Reply> tblReplies = tblCommunity.getTblReplies();
        List<CommunityFile> thumbNailFile = tblCommunity.getThumbNailFile();

        if (!CollectionUtils.isEmpty(tblReplies)) {
            tblReplyRepository.deleteAll(tblReplies);
        }

        /**
         * 파일은 삭제가 불가능 할 수도 있기에 delete와 파일 삭제는 순차 삭제
         */
        if (!CollectionUtils.isEmpty(thumbNailFile)) {
            thumbNailFile.forEach(f -> {
                tblCommunityFileRepository.delete(f);
                fileIoUtil.removeFile(f);
            });
        }

        tblCommunityRepository.delete(tblCommunity);
        return "삭제 완료";
    }

    /**
     * 댓글
     */
    @Transactional
    public ReplyRes.PageItem createReply(ReplyReq.Create create) {
        Member currentTblMember = securityUtil.getCurrentTblMember();
        Community tblCommunity = tblCommunityRepository.getTblCommunity(create.getTblCommunityId());

        Reply tblReply = create.toEntity(tblCommunity);
        tblReply.creator(currentTblMember);
        tblCommunity.addReply(tblReply);

        return getSearchTblReplies(tblReplyRepository.findAllByTblCommunity(tblCommunity, create.ofPageble()));
    }

    public ReplyRes.PageItem searchReply(ReplyReq.Search search) {
        Community tblCommunity = tblCommunityRepository.getTblCommunity(search.getTblCommunityId());
        return getSearchTblReplies(tblReplyRepository.findAllByTblCommunity(tblCommunity, search.ofPageble()));
    }

    @Transactional
    public ReplyRes.Detail updateReply(ReplyReq.Update update) {
        Member currentTblMember = securityUtil.getCurrentTblMember();
        Reply tblReply = tblReplyRepository.getTblReply(update.getTblReplyId());
        Reply updateData = update.toEntity();

        tblReply.update(updateData);
        tblReply.updater(currentTblMember);

        return ReplyRes.Detail.of(tblReply);
    }

    @Transactional
    public String deleteReply(Long tblReplyId) {
        Reply tblReply = tblReplyRepository.getTblReply(tblReplyId);
        tblReplyRepository.delete(tblReply);
        return "삭제 완료";
    }

    /**
     * 파일
     */
    @Transactional
    public ResponseEntity<Resource> downloadThumbNailFile(Long thumbNailFileId, HttpServletRequest httpServletRequest) {
        CommunityFile tblCommunityFile = tblCommunityFileRepository.getTblCommunityFile(thumbNailFileId);

        Path sourceFilePath = Paths.get(tblCommunityFile.getFilePath())
                .resolve(tblCommunityFile.getFileUuid());
        return fileIoUtil.loadFilesAsResource(sourceFilePath, tblCommunityFile.getFileOrgName(), null, httpServletRequest);
    }

    @Transactional
    public String deleteThumbNailFile(Long thumbNailFileId) {
        CommunityFile tblCommunityFile = tblCommunityFileRepository.getTblCommunityFile(thumbNailFileId);
        tblCommunityFileRepository.delete(tblCommunityFile);
        fileIoUtil.removeFile(tblCommunityFile);
        return "파일 삭제 완료";
    }

    /**
     * 검색 결과 유틸
     */
    private CommunityRes.PageItem getSearchTblCommuninities(Page<Community> tblCommuninities) {
        List<CommunityRes.Item> items = tblCommuninities.getContent().stream()
                .map(CommunityRes.Item::of)
                .toList();
        return new CommunityRes.PageItem(items, new BaseSearchRes(tblCommuninities));
    }

    private ReplyRes.PageItem getSearchTblReplies(Page<Reply> tblReplies) {
        List<ReplyRes.Item> items = tblReplies.getContent().stream()
                .map(ReplyRes.Item::of)
                .toList();
        return new ReplyRes.PageItem(items, new BaseSearchRes(tblReplies));
    }
}
