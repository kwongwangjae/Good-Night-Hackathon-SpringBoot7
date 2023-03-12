package com.techeer.goodnighthackathonspringboot.domain.review.controller;

import com.techeer.goodnighthackathonspringboot.domain.review.dto.ReviewRequest;
import com.techeer.goodnighthackathonspringboot.domain.review.dto.ReviewResponse;
import com.techeer.goodnighthackathonspringboot.domain.review.dto.ReviewUpdateRequest;
import com.techeer.goodnighthackathonspringboot.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponse save(
            @Validated @RequestBody ReviewRequest request
    ) {
        return service.save(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ReviewResponse> searchReviews(@RequestParam(required = false) String title,
                                              @RequestParam(required = false) String content,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        return service.searchReviews(title, content, page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewResponse findById(
            @PathVariable Integer id
    ) {
        ReviewResponse reviewResponse = service.findById(id);
        if (reviewResponse == null) {
            throw new EntityNotFoundException("해당 ID의 리뷰를 찾을 수 없습니다.");
        }
        return reviewResponse;
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponse update(
            @PathVariable Integer id,
            @RequestBody ReviewUpdateRequest request
    ) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Integer id
    ) {
        service.delete(id);
    }
}