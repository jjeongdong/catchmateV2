package com.back.catchmate.application.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;

@Getter
@Builder
public class UploadFile {
    private String originalFilename;
    private String contentType;
    private InputStream inputStream;
    private long size;
}
