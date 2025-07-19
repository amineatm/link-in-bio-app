package com.linkinbio.backend.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "profile_pages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfilePage {

    @Id
    private String id;

    @Column(unique = true, nullable = false)
    private String slug;

    private String bio;
    private String theme;
    private String avatarUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LinkBlock> linkBlocks;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }
}
