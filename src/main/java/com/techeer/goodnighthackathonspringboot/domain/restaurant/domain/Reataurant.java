package com.techeer.goodnighthackathonspringboot.domain.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
public class Reataurant {
    public Reataurant() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Builder
    public Reataurant(String name, Category category){
        this.name = name;
        this.category = category;
    }
}
