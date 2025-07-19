package com.linkinbio.backend.domain.service;

import com.linkinbio.backend.domain.model.AIContent;

import java.util.List;

public interface IAIContentService {
    AIContent generate(String userId, String type, String prompt);
    List<AIContent> getByUserId(String userId);
}
