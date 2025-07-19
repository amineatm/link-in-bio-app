package com.linkinbio.backend.adapters.dto.page;

import lombok.Data;

@Data
public class PageResponse {
    private String slug;
    private String bio;
    private String theme;
    private String avatarUrl;
}
