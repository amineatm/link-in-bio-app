package com.linkinbio.backend.adapters.controller;

import com.linkinbio.backend.adapters.dto.page.*;
import com.linkinbio.backend.domain.model.ProfilePage;
import com.linkinbio.backend.domain.service.IProfilePageService;
import com.linkinbio.backend.infrastructure.security.UserContext;
import com.linkinbio.backend.infrastructure.security.CurrentUser;
import com.linkinbio.backend.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pages")
@RequiredArgsConstructor
public class ProfilePageController {

    private final IProfilePageService profilePageService;

    @PostMapping
    public ResponseEntity<PageResponse> createPage(@RequestBody PageRequest request) {
        CurrentUser user = UserContext.get();
        ProfilePage page = profilePageService.createProfilePage(user.getId(), request.getSlug());
        return ResponseEntity.ok(MapperUtil.toPageResponse(page));
    }

    @GetMapping("/me")
    public ResponseEntity<PageResponse> getMyPage() {
        CurrentUser user = UserContext.get();
        ProfilePage page = profilePageService.getByUserId(user.getId()).orElseThrow();
        return ResponseEntity.ok(MapperUtil.toPageResponse(page));
    }

    @PutMapping("/me")
    public ResponseEntity<PageResponse> updatePage(@RequestBody PageResponse request) {
        CurrentUser user = UserContext.get();

        ProfilePage updated = new ProfilePage();
        updated.setSlug(request.getSlug());
        updated.setBio(request.getBio());
        updated.setTheme(request.getTheme());
        updated.setAvatarUrl(request.getAvatarUrl());

        ProfilePage result = profilePageService.updateProfilePage(user.getId(), updated);
        return ResponseEntity.ok(MapperUtil.toPageResponse(result));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<PageResponse> getBySlug(@PathVariable String slug) {
        ProfilePage page = profilePageService.getBySlug(slug).orElseThrow();
        return ResponseEntity.ok(MapperUtil.toPageResponse(page));
    }
}
