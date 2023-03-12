package com.techeer.goodnighthackathonspringboot.domain.review.dto;

import com.techeer.goodnighthackathonspringboot.domain.review.domain.entity.Review;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class ReviewUpdateRequest {

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    public Review toEntity() {
        return Review.builder()
                .title(title)
                .content(content)
                .build();
    }
}
