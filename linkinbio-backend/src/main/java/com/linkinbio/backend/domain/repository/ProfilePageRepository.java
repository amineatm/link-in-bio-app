package com.linkinbio.backend.domain.repository;

import com.linkinbio.backend.domain.model.ProfilePage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfilePageRepository extends JpaRepository<ProfilePage, String> {
    Optional<ProfilePage> findBySlug(String slug);
    Optional<ProfilePage> findByUserId(String userId);
}
