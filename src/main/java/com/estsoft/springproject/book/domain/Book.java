package com.estsoft.springproject.book.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    String id;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    String name;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    String author;
}
