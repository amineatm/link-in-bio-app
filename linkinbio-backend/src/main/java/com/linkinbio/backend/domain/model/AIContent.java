package com.linkinbio.backend.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "ai_content")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AIContent {

    @Id
    private String id;

    private String type; // e.g. "TAGLINE", "BIO"
    private String inputPrompt;

    @Lob
    private String generatedText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }
}
