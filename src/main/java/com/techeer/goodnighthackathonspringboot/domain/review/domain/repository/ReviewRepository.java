package com.techeer.goodnighthackathonspringboot.domain.review.domain.repository;

import com.techeer.goodnighthackathonspringboot.domain.review.domain.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Page<Review> findByTitleContainingAndContentContaining(String title, String content, Pageable pageable);
}
