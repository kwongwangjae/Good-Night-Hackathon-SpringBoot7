package com.techeer.goodnighthackathonspringboot.domain.restaurant.dto;

import com.techeer.goodnighthackathonspringboot.domain.restaurant.domain.entity.Restaurant;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class RestaurantRequest {

    @NotBlank(message = "이름 작성")
    private String name;

    @NotBlank(message = "카테고리 작성")
    private String category;

    public Restaurant toEntity() {
        return Restaurant.builder()
                .name(name)
                .category(category)
                .build();
    }
}
