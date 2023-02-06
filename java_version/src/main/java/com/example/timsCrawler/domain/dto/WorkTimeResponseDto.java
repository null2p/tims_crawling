package com.example.timsCrawler.domain.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkTimeResponseDto {
    String name;
    Integer hour;
    Integer min;
    Integer totalMin;
    Integer percentage;
}
