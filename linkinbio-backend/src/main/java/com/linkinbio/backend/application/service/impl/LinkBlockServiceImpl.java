package com.linkinbio.backend.application.service.impl;

import com.linkinbio.backend.domain.model.LinkBlock;
import com.linkinbio.backend.domain.repository.LinkBlockRepository;
import com.linkinbio.backend.domain.service.ILinkBlockService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class LinkBlockServiceImpl implements ILinkBlockService {

    private final LinkBlockRepository linkBlockRepository;

    @Override
    public List<LinkBlock> getByPageId(String pageId) {
        return linkBlockRepository.findByPageIdOrderBySortOrderAsc(pageId);
    }

    @Override
    public LinkBlock create(LinkBlock linkBlock) {
        linkBlock.setId(UUID.randomUUID().toString());
        return linkBlockRepository.save(linkBlock);
    }

    @Override
    public LinkBlock update(String id, LinkBlock updatedLink) {
        LinkBlock existing = linkBlockRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Link not found"));

        existing.setTitle(updatedLink.getTitle());
        existing.setUrl(updatedLink.getUrl());
        existing.setIcon(updatedLink.getIcon());
        existing.setSortOrder(updatedLink.getSortOrder());

        return linkBlockRepository.save(existing);
    }

    @Override
    public void delete(String id) {
        linkBlockRepository.deleteById(id);
    }
}
