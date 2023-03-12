package com.techeer.goodnighthackathonspringboot.domain.review.dto;

import com.techeer.goodnighthackathonspringboot.domain.restaurant.domain.entity.Restaurant;
import com.techeer.goodnighthackathonspringboot.domain.review.domain.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ReviewRequest {

    @NotNull
    private Integer restaurantId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    public Review toEntity(Restaurant restaurant) {
        return Review.builder()
                .restaurant(restaurant)
                .title(title)
                .content(content)
                .build();
    }
}