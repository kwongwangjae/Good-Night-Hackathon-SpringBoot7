package com.techeer.goodnighthackathonspringboot.domain.hello.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "hello")
public class Hello {
    @Id
    @Column(name = "id")
    private  int id;
    @Column(name = "content")
    private  String contents;
}
