package com.linkinbio.backend.adapters.controller;

import com.linkinbio.backend.adapters.dto.ai.*;
import com.linkinbio.backend.domain.model.AIContent;
import com.linkinbio.backend.domain.service.IAIContentService;
import com.linkinbio.backend.infrastructure.security.UserContext;
import com.linkinbio.backend.infrastructure.security.CurrentUser;
import com.linkinbio.backend.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AIContentController {

    private final IAIContentService aiService;

    @PostMapping("/generate")
    public ResponseEntity<AIContentResponse> generate(@RequestBody AIPromptRequest req) {
        CurrentUser user = UserContext.get();
        AIContent content = aiService.generate(user.getId(), req.getType(), req.getPrompt());
        return ResponseEntity.ok(MapperUtil.toAIContentResponse(content));
    }

    @GetMapping("/history")
    public ResponseEntity<List<AIContentResponse>> getHistory() {
        CurrentUser user = UserContext.get();
        List<AIContent> history = aiService.getByUserId(user.getId());
        List<AIContentResponse> responses = history.stream().map(MapperUtil::toAIContentResponse).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}
