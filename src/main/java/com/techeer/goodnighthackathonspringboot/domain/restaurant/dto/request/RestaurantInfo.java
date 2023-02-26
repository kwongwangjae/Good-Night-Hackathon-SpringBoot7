package com.techeer.goodnighthackathonspringboot.domain.restaurant.dto.request;

import com.techeer.goodnighthackathonspringboot.domain.restaurant.domain.Category;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantInfo {
    private String name;
    private Category category;
}