package com.linkinbio.backend.domain.repository;

import com.linkinbio.backend.domain.model.LinkBlock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinkBlockRepository extends JpaRepository<LinkBlock, String> {
    List<LinkBlock> findByPageIdOrderBySortOrderAsc(String pageId);
}
