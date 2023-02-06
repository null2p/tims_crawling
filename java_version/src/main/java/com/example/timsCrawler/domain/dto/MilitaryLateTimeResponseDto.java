package com.example.timsCrawler.domain.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MilitaryLateTimeResponseDto {
    Integer day;
    Integer hour;
    Integer min;
    Integer dayOff;
}
