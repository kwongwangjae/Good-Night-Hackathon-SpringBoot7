package com.techeer.goodnighthackathonspringboot.domain.restaurant.service;

import com.techeer.goodnighthackathonspringboot.domain.restaurant.domain.entity.Restaurant;
import com.techeer.goodnighthackathonspringboot.domain.restaurant.domain.repository.RestaurantRepository;
import com.techeer.goodnighthackathonspringboot.domain.restaurant.dto.RestaurantRequest;
import com.techeer.goodnighthackathonspringboot.domain.restaurant.dto.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantResponse save(RestaurantRequest request) {
        Restaurant entity = repository.save(request.toEntity());
        return RestaurantResponse.of(entity);
    }

    public List<RestaurantResponse> findAll() {
        return repository.findAllByIsDeleted(false)
                .stream()
                .map(RestaurantResponse::of)
                .collect(Collectors.toList());
    }

    public List<RestaurantResponse> findAllByCategory(String category) {
        return repository.findAllByCategoryAndIsDeleted(category, false)
                .stream()
                .map(RestaurantResponse::of)
                .collect(Collectors.toList());
    }

    public Restaurant findEntity(Integer id) {
        return repository.findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new EntityNotFoundException("레스토랑을 찾을 수 없습니다."));
    }

    public RestaurantResponse findById(Integer id) {
        return RestaurantResponse.of(findEntity(id));
    }

    @Transactional
    public RestaurantResponse updateCategory(Integer id, String category) {
        Restaurant entity = findEntity(id);
        entity.updateCategory(category);
        return RestaurantResponse.of(entity);
    }

    @Transactional
    public void delete(Integer id) {
        findEntity(id).delete();
    }

}
