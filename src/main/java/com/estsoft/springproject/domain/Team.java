package com.estsoft.springproject.domain;

import com.estsoft.springproject.entity.Member;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    @Column
    private String name;

    // 양방향 연관 관계 -> @OneToMany (X), 잘 사용하지 않음 성능에 좋지 않음, jpa 특성 상 팀 하나당 가지고 있는 여러 개의 묶음 들을 모두 조회 되기 때문에.
    // 연관 관계의 주인 명시
    @OneToMany(mappedBy = "tesm") // team 객체가 연관관계의 주인.
    List<Member> members;
}
