package com.linkinbio.backend.application.service.impl;

import com.linkinbio.backend.domain.model.ProfilePage;
import com.linkinbio.backend.domain.model.User;
import com.linkinbio.backend.domain.repository.ProfilePageRepository;
import com.linkinbio.backend.domain.repository.UserRepository;
import com.linkinbio.backend.domain.service.IProfilePageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfilePageServiceImpl implements IProfilePageService {

    private final ProfilePageRepository profilePageRepository;
    private final UserRepository userRepository;

    @Override
    public ProfilePage createProfilePage(String userId, String slug) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        ProfilePage page = new ProfilePage();
        page.setId(UUID.randomUUID().toString());
        page.setSlug(slug);
        page.setUser(user);

        return profilePageRepository.save(page);
    }

    @Override
    public Optional<ProfilePage> getBySlug(String slug) {
        return profilePageRepository.findBySlug(slug);
    }

    @Override
    public Optional<ProfilePage> getByUserId(String userId) {
        return profilePageRepository.findByUserId(userId);
    }

    @Override
    public ProfilePage updateProfilePage(String userId, ProfilePage updated) {
        ProfilePage existing = profilePageRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));

        existing.setSlug(updated.getSlug());
        existing.setBio(updated.getBio());
        existing.setTheme(updated.getTheme());
        existing.setAvatarUrl(updated.getAvatarUrl());

        return profilePageRepository.save(existing);
    }
}
