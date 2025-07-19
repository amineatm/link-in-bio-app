package com.linkinbio.backend.application.service.impl;

import com.linkinbio.backend.domain.model.AIContent;
import com.linkinbio.backend.domain.model.User;
import com.linkinbio.backend.domain.repository.AIContentRepository;
import com.linkinbio.backend.domain.repository.UserRepository;
import com.linkinbio.backend.domain.service.IAIContentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AIContentServiceImpl implements IAIContentService {

    private final AIContentRepository aiContentRepository;
    private final UserRepository userRepository;

    @Override
    public AIContent generate(String userId, String type, String prompt) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        AIContent content = new AIContent();
        content.setId(UUID.randomUUID().toString());
        content.setType(type);
        content.setInputPrompt(prompt);
        content.setGeneratedText("PLACEHOLDER"); // OpenAiClient will fill this later
        content.setUser(user);

        return aiContentRepository.save(content);
    }

    @Override
    public List<AIContent> getByUserId(String userId) {
        return aiContentRepository.findByUserId(userId);
    }
}
