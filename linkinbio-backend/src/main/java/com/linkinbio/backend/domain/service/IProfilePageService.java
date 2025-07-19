package com.linkinbio.backend.domain.service;

import com.linkinbio.backend.domain.model.ProfilePage;

import java.util.Optional;

public interface IProfilePageService {
    ProfilePage createProfilePage(String userId, String slug);
    Optional<ProfilePage> getBySlug(String slug);
    Optional<ProfilePage> getByUserId(String userId);
    ProfilePage updateProfilePage(String userId, ProfilePage updated);
}
