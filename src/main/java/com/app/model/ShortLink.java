package com.app.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "short_link")
@Getter
@Setter
@EqualsAndHashCode
public class ShortLink {

    @Id
    @GeneratedValue
    @Column(name = "short_link_id")
    private Long id;

    @Column(name = "original", nullable = false, updatable = false)
    private String original;

    @Column(name = "link", nullable = false, updatable = false)
    private String link;

    @Column(name = "rank")
    private long rank;

    @Column(name = "count")
    private long count;

    @PrePersist
    public void toCreate() {
        if (link == null) {
            UUID uuid = UUID.randomUUID();
            link = "/l/" + uuid;
        }
    }
}
