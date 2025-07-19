package com.linkinbio.backend.util;

import com.linkinbio.backend.adapters.dto.ai.AIContentResponse;
import com.linkinbio.backend.adapters.dto.link.LinkResponse;
import com.linkinbio.backend.adapters.dto.page.PageResponse;
import com.linkinbio.backend.domain.model.AIContent;
import com.linkinbio.backend.domain.model.LinkBlock;
import com.linkinbio.backend.domain.model.ProfilePage;

public class MapperUtil {

    public static PageResponse toPageResponse(ProfilePage page) {
        PageResponse dto = new PageResponse();
        dto.setSlug(page.getSlug());
        dto.setBio(page.getBio());
        dto.setTheme(page.getTheme());
        dto.setAvatarUrl(page.getAvatarUrl());
        return dto;
    }

    public static LinkResponse toLinkResponse(LinkBlock link) {
        LinkResponse dto = new LinkResponse();
        dto.setId(link.getId());
        dto.setTitle(link.getTitle());
        dto.setUrl(link.getUrl());
        dto.setIcon(link.getIcon());
        dto.setSortOrder(link.getSortOrder());
        return dto;
    }

    public static AIContentResponse toAIContentResponse(AIContent content) {
        AIContentResponse dto = new AIContentResponse();
        dto.setId(content.getId());
        dto.setType(content.getType());
        dto.setPrompt(content.getInputPrompt());
        dto.setGeneratedText(content.getGeneratedText());
        return dto;
    }
}
