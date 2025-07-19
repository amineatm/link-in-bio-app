package com.linkinbio.backend.adapters.controller;

import com.linkinbio.backend.adapters.dto.link.LinkRequest;
import com.linkinbio.backend.adapters.dto.link.LinkResponse;
import com.linkinbio.backend.domain.model.LinkBlock;
import com.linkinbio.backend.domain.model.ProfilePage;
import com.linkinbio.backend.domain.service.ILinkBlockService;
import com.linkinbio.backend.domain.service.IProfilePageService;
import com.linkinbio.backend.infrastructure.security.UserContext;
import com.linkinbio.backend.infrastructure.security.CurrentUser;
import com.linkinbio.backend.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/links")
@RequiredArgsConstructor
public class LinkBlockController {

    private final ILinkBlockService linkService;
    private final IProfilePageService profilePageService;

    @GetMapping
    public ResponseEntity<List<LinkResponse>> getLinks() {
        CurrentUser user = UserContext.get();
        String pageId = profilePageService.getByUserId(user.getId()).orElseThrow().getId();
        List<LinkBlock> links = linkService.getByPageId(pageId);
        List<LinkResponse> responses = links.stream().map(MapperUtil::toLinkResponse).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<LinkResponse> createLink(@RequestBody LinkRequest req) {
        CurrentUser user = UserContext.get();
        ProfilePage page = profilePageService.getByUserId(user.getId()).orElseThrow();

        LinkBlock link = new LinkBlock();
        link.setTitle(req.getTitle());
        link.setUrl(req.getUrl());
        link.setIcon(req.getIcon());
        link.setSortOrder(req.getSortOrder());
        link.setPage(page);

        return ResponseEntity.ok(MapperUtil.toLinkResponse(linkService.create(link)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LinkResponse> update(@PathVariable String id, @RequestBody LinkRequest req) {
        LinkBlock updated = new LinkBlock();
        updated.setTitle(req.getTitle());
        updated.setUrl(req.getUrl());
        updated.setIcon(req.getIcon());
        updated.setSortOrder(req.getSortOrder());
        return ResponseEntity.ok(MapperUtil.toLinkResponse(linkService.update(id, updated)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        linkService.delete(id);
        return ResponseEntity.ok().build();
    }
}
