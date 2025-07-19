package com.linkinbio.backend.domain.repository;

import com.linkinbio.backend.domain.model.AIContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AIContentRepository extends JpaRepository<AIContent, String> {
    List<AIContent> findByUserId(String userId);
}
