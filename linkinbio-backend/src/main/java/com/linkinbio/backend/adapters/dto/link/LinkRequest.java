package com.linkinbio.backend.adapters.dto.link;

import lombok.Data;

@Data
public class LinkRequest {
    private String title;
    private String url;
    private String icon;
    private int sortOrder;
}
