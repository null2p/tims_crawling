package com.example.timsCrawler.controller;

import com.example.timsCrawler.domain.Member;
import com.example.timsCrawler.domain.dto.WorkTimeResponseDto;
import com.example.timsCrawler.service.TimsCrawlerService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ApiController {

    private final TimsCrawlerService timsCrawlerService;

    @PostMapping(path="/login")
    public ResponseEntity<String> login(@RequestBody Member member, HttpServletResponse httpServletResponse) {
        try {
            Connection.Response loginResponse = timsCrawlerService.tryLogin(member);
            String loginResponseBody = loginResponse.body();

            if(loginResponseBody.contains("확인하세요")){
                return new ResponseEntity<>("Login Failed", HttpStatus.UNAUTHORIZED);
            }

            // 성공적인 로그인의 경우, 쿠키 설정 등
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            Map<String, String> responseCookies = loginResponse.cookies();
            for (Map.Entry<String, String> entry : responseCookies.entrySet()) {
                Cookie cookie = new Cookie(entry.getKey(), entry.getValue());
                httpServletResponse.addCookie(cookie);
            }
            httpServletResponse.addCookie(new Cookie("company",member.getCompany()));

            return new ResponseEntity<>("Login Successful", HttpStatus.OK);
        } catch (Exception e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new ResponseEntity<>("Login Failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


//    @GetMapping(path = "/late-time")
//    public void getTotalLateTime(HttpServletRequest request) throws IOException {
//        Cookie[] cookies = request.getCookies();
//        timsCrawlerService.getYearAttendanceList(cookies);
//    }

//    @GetMapping(path="/name")
//    public void getName(HttpServletRequest request) throws IOException {
//        Cookie[] cookies = request.getCookies();
//        timsCrawlerService.getName(cookies);
//    }

//    @GetMapping(path="/work-time")
//    public ResponseEntity<WorkTimeResponseDto> getWorkTime(HttpServletRequest request) throws IOException{
//        Cookie[] cookies = request.getCookies();
//        return ResponseEntity.ok(timsCrawlerService.getWeekAttendanceList(cookies));
//    }
}