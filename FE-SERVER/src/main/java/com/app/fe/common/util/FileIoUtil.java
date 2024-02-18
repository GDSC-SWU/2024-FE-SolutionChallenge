package com.app.fe.common.util;

import com.app.fe.common.entity.FileBaseEntity;
import com.app.fe.community.code.FileType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Getter
@Component
public class FileIoUtil {

    private static final String CONTENT_TYPE = "application/octet-stream";

    @Value("${custom.file-path}")
    private String absolutePath;

    public FileBaseEntity saveFile(MultipartFile file, FileType fileType) {
        String saveFilePath = String.join("/", fileType.getFilePath(), DateUtil.getDate_yyyyMM());
        String fileName = "";

        try {
            String orgFileName = file.getOriginalFilename();
            String ext = getFileExtension(orgFileName);
            fileName = UUID.randomUUID().toString().replaceAll("-", "") + '.' + ext;

            Path absoluteDir = Paths.get(absolutePath).toAbsolutePath().normalize();
            if (!Files.exists(absoluteDir, LinkOption.NOFOLLOW_LINKS)) {
                throw new IllegalArgumentException("파일의 경로가 존재하지 않습니다. : " + absoluteDir);
            }

            Path saveDir = Paths.get(absoluteDir.toString(), saveFilePath).normalize();
            Files.createDirectories(saveDir);
            StringUtils.cleanPath(fileName);
            Path targetPath = saveDir.resolve(fileName).normalize();
            file.transferTo(targetPath);
        } catch (IOException | NullPointerException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return new FileBaseEntity(saveFilePath, fileName, file.getOriginalFilename(), file.getSize(), fileType);
    }

    public ResponseEntity<Resource> loadFilesAsResource(Path downloadUrl, String orgFileName, String contentType, HttpServletRequest httpServletRequest) {

        Resource resource = null;
        String headerValue = "";
        ContentDisposition contentDisposition = null;
        downloadUrl = Paths.get(absolutePath, downloadUrl.toString()).toAbsolutePath().normalize();

        try {
            resource = new UrlResource(downloadUrl.toUri());
            contentType = httpServletRequest.getServletContext()
                    .getMimeType(resource.getFile().getAbsolutePath());
            headerValue = ServletUtil.getEncodingFileName(orgFileName, httpServletRequest);

            if (contentType == null) {
                contentType = CONTENT_TYPE;
            }
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("파일을 찾을 수 없습니다.");
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }

    public <S extends FileBaseEntity> Boolean removeFile(S fileBaseEntity) {
        try {
            Path targetPath = Paths.get(absolutePath, fileBaseEntity.getFilePath()).normalize();
            if (!Files.exists(targetPath, LinkOption.NOFOLLOW_LINKS)) { // LinkOption.NOFOLLOW_LINKS 심볼릭 링크 추적 X
                throw new IllegalArgumentException("파일의 경로가 존재하지 않습니다. : " + targetPath);
            }

            targetPath = targetPath.resolve(fileBaseEntity.getFileUuid()).normalize();
            return Files.deleteIfExists(targetPath.toAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(String.format("파일을 삭제할 수 없습니다. : %s", e.getMessage()));
        }
    }

    public String getFileExtension(String fileName) {
        // 마지막 '.' 으로부터 다음 문자열은 확장자
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
