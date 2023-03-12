package com.techeer.goodnighthackathonspringboot.domain.review.service;

import com.techeer.goodnighthackathonspringboot.domain.restaurant.domain.entity.Restaurant;
import com.techeer.goodnighthackathonspringboot.domain.restaurant.service.RestaurantService;
import com.techeer.goodnighthackathonspringboot.domain.review.domain.entity.Review;
import com.techeer.goodnighthackathonspringboot.domain.review.domain.repository.ReviewRepository;
import com.techeer.goodnighthackathonspringboot.domain.review.dto.ReviewRequest;
import com.techeer.goodnighthackathonspringboot.domain.review.dto.ReviewResponse;
import com.techeer.goodnighthackathonspringboot.domain.review.dto.ReviewUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository repository;
    private final RestaurantService restaurantService;

    public Page<ReviewResponse> searchReviews(String title, String content, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Review> result = repository.findByTitleContainingAndContentContaining(title, content, pageable);
        return result.map(ReviewResponse::of);
    }


    public ReviewResponse save(ReviewRequest request) {
        Restaurant restaurant = restaurantService.findEntity(request.getRestaurantId());
        Review entity = repository.save(request.toEntity(restaurant));
        return ReviewResponse.of(entity);
    }

    private Review findEntity(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("리뷰를 찾을 수 없습니다."));
    }

    public ReviewResponse findById(Integer id) {
        return ReviewResponse.of(findEntity(id));
    }

    @Transactional
    public ReviewResponse update(Integer id, ReviewUpdateRequest request) {
        Review entity = findEntity(id);
        entity.update(request);
        return ReviewResponse.of(entity);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }


}