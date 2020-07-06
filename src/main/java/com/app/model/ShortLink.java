package com.app.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "short_link")
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
            setLink("/api/l/" + uuid);
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortLink shortLink = (ShortLink) o;
        return rank == shortLink.rank &&
                count == shortLink.count &&
                Objects.equals(id, shortLink.id) &&
                Objects.equals(original, shortLink.original) &&
                Objects.equals(link, shortLink.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, original, link, rank, count);
    }

    @Override
    public String toString() {
        return "ShortLink{" +
                "id=" + id +
                ", original='" + original + '\'' +
                ", link='" + link + '\'' +
                ", rank=" + rank +
                ", count=" + count +
                '}';
    }
}
