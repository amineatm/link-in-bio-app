package com.linkinbio.backend.adapters.dto.ai;

import lombok.Data;

@Data
public class AIPromptRequest {
    private String type;     // e.g. TAGLINE, BIO
    private String prompt;   // e.g. “Generate a short tagline”
}
