package com.example.timsCrawler.controller;

import com.example.timsCrawler.domain.dto.MilitaryLateTimeResponseDto;
import com.example.timsCrawler.domain.dto.WorkTimeResponseDto;
import com.example.timsCrawler.service.TimsCrawlerService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class TimsCrawlerController {
    private final TimsCrawlerService timsCrawlerService;

    @GetMapping("/tims-crawler/main")
    public String mainPage() {
        return "crawler_user_info";
    }

    @GetMapping("/tims-crawler/dashboard")
    public String dashboardPage(Model model, HttpServletRequest httpServletRequest) throws IOException {
        Cookie[] cookies = httpServletRequest.getCookies();
        WorkTimeResponseDto workTimeResponseDto = timsCrawlerService.getWeekAttendanceList(cookies);

        MilitaryLateTimeResponseDto militaryLateTimeResponseDto = timsCrawlerService.getYearAttendanceList(cookies);

        //////// mocking ////////
        workTimeResponseDto.setName("아작체");
        militaryLateTimeResponseDto = MilitaryLateTimeResponseDto.builder()
                .day(1)
                .hour(17)
                .min(10)
                .build();
        ////////////////////////

        model.addAttribute("workTimeResponse", workTimeResponseDto);
        model.addAttribute("militaryLateTimeResponse", militaryLateTimeResponseDto);
        return "crawler_dashboard";
    }
}
