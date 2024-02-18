package com.app.fe.community.dto;

import com.app.fe.common.dto.BaseRes;
import com.app.fe.community.code.FileType;
import com.app.fe.community.entity.CommunityFile;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CommunityFileRes {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Detail extends BaseRes {

        private Long tblCommunityFileId;
        private String filePath;
        private String fileUuid;
        private String fileOrgName;
        private Long fileSize;
        private FileType fileType;

        @Builder
        public Detail(Long tblCommunityFileId, String filePath, String fileUuid, String fileOrgName, Long fileSize, FileType fileType) {
            this.tblCommunityFileId = tblCommunityFileId;
            this.filePath = filePath;
            this.fileUuid = fileUuid;
            this.fileOrgName = fileOrgName;
            this.fileSize = fileSize;
            this.fileType = fileType;
        }

        public static Detail of(CommunityFile tblCommunityFile) {
            Detail detail = Detail.builder()
                    .tblCommunityFileId(tblCommunityFile.getTblCommunityFileId())
                    .filePath(tblCommunityFile.getFilePath())
                    .fileUuid(tblCommunityFile.getFileUuid())
                    .fileOrgName(tblCommunityFile.getFileOrgName())
                    .fileSize(tblCommunityFile.getFileSize())
                    .fileType(tblCommunityFile.getFileType())
                    .build();
            detail.initMemberBaseRes(tblCommunityFile);
            return detail;
        }
    }
}
