package com.techeer.goodnighthackathonspringboot.domain.review.domain.entity;

import com.techeer.goodnighthackathonspringboot.domain.restaurant.domain.entity.Restaurant;
import com.techeer.goodnighthackathonspringboot.domain.review.dto.ReviewUpdateRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    private String title;

    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public Review(Restaurant restaurant, String title, String content) {
        this.restaurant = restaurant;
        this.title = title;
        this.content = content;
    }

    public void update(ReviewUpdateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }

}
