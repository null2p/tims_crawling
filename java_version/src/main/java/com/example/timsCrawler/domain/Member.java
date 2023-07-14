package com.example.timsCrawler.domain;

import jakarta.servlet.http.Cookie;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class Member {
    private String username;
    private String password;
    private String company;
    Map<String, String> loginCookie;
    //todo Cookie -> Cookies로 바꿔서 모든 쿠키 저장하도록 변경해야함, setResponseCookie() 내에서 new 로 덮어쓰면 안됨!! 바보멍청아!! ㅜㅜ
    Cookie cookie;

    public void nullifyLoginData(){
        this.username = null;
        this.password = null;
    }

    public void setResponseCookie(){
        Set<String> keySet = loginCookie.keySet();
//        cookie = new Cookie();
        for (String key : keySet) {
            cookie = new Cookie(key, loginCookie.get(key));
        }
    }
}
