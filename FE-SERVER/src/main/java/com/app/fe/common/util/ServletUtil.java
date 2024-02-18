package com.app.fe.common.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@Component
public class ServletUtil {

    public static String getEncodingFileName(String fileName, HttpServletRequest servlet) throws UnsupportedEncodingException {
        String header = servlet.getHeader("User-Agent");
        StringBuffer sb = new StringBuffer("attachment; filename=");

        if (header.contains("Edge") || header.contains("MSIE") || header.contains("Trident")) {
            sb.append("\"");
            sb.append(URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "\\ "));
            sb.append("\"");
        } else {
            sb = new StringBuffer("attachment; filename*=");
            sb.append(URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20"));
        }

        return sb.toString();
    }
}
