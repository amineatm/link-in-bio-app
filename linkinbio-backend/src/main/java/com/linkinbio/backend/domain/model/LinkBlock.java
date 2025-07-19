package com.linkinbio.backend.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "link_blocks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkBlock {

    @Id
    private String id;

    private String title;
    private String url;
    private String icon;
    private int sortOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id")
    private ProfilePage page;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }
}
