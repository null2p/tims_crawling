package com.example.timsCrawler.controller;

import com.example.timsCrawler.domain.dto.MilitaryLateTimeResponseDto;
import com.example.timsCrawler.domain.dto.WorkTimeResponseDto;
import com.example.timsCrawler.service.TimsCrawlerService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
public class TimsCrawlerController {
    private final TimsCrawlerService timsCrawlerService;

    @GetMapping("/tims-crawler/main")
    public String mainPage() {
        return "crawler_user_info";
    }

    @GetMapping("/tims-crawler/dashboard")
    public String dashboardPage(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            Model model, HttpServletRequest httpServletRequest) throws IOException {

        Cookie[] cookies = httpServletRequest.getCookies();
        WorkTimeResponseDto workTimeResponseDto = timsCrawlerService.getWeekAttendanceList(cookies);
        workTimeResponseDto.setName(timsCrawlerService.getName(cookies));
        model.addAttribute("workTimeResponse", workTimeResponseDto);

        MilitaryLateTimeResponseDto militaryLateTimeResponseDto = timsCrawlerService.getYearAttendanceList(cookies, date);
        model.addAttribute("militaryLateTimeResponse", militaryLateTimeResponseDto);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        if (date != null) {
            String dateString = date.format(formatter);
            model.addAttribute("date", dateString);
        } else {
            model.addAttribute("date", null);
        }

        return "crawler_dashboard";
    }

    @PostMapping("/dateSubmit")
    public String dateSubmit(@RequestParam("dateInput") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        return "redirect:/tims-crawler/dashboard?date=" + date;
    }
}
