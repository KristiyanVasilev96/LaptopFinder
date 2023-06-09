package project.laptop.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity{
    private boolean approved;
    private LocalDateTime created;
    @Column(columnDefinition = "TEXT",nullable = false)
    private String text;
    @ManyToOne
    private UserEntity author;
    @ManyToOne
    private OfferEntity offer;

    public CommentEntity() {
    }

    public boolean isApproved() {
        return approved;
    }

    public CommentEntity setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public CommentEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public String getText() {
        return text;
    }

    public CommentEntity setText(String text) {
        this.text = text;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public CommentEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public OfferEntity getOffer() {
        return offer;
    }

    public CommentEntity setOffer(OfferEntity offer) {
        this.offer = offer;
        return this;
    }
}
