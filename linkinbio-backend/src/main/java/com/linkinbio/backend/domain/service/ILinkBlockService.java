package com.linkinbio.backend.domain.service;

import com.linkinbio.backend.domain.model.LinkBlock;

import java.util.List;

public interface ILinkBlockService {
    List<LinkBlock> getByPageId(String pageId);
    LinkBlock create(LinkBlock linkBlock);
    LinkBlock update(String id, LinkBlock updatedLink);
    void delete(String id);
}
