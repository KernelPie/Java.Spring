package com.vlad.news.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor

public class NewsDTO {
    private Long id;
    private String title;
    private String text;
    private Instant date;
}
