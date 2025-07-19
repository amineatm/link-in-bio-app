package com.linkinbio.backend.adapters.dto.ai;

import lombok.Data;

@Data
public class AIContentResponse {
    private String id;
    private String type;
    private String prompt;
    private String generatedText;
}
