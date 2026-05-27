package org.example.session12_bai1.entity;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Book {

    private Long id;
    private String title;
    private String author;
    private Double price;
}
