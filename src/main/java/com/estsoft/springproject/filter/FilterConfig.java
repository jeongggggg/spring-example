package com.estsoft.springproject.filter;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 이 클래스가 Spring의 설정 클래스임을 나타냄
public class FilterConfig {
    // 첫 번째 필터를 등록하는 메서드
    @Bean
    public FilterRegistrationBean<Filter> firstFilter() {
        // FilterRegistrationBean 객체 생성
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();

        // 필터 인스턴스를 설정
        filter.setFilter(new FirstFilter());
        // 필터가 적용될 URL 패턴을 설정
        filter.addUrlPatterns("/books");
        filter.setOrder(1); // 필터 실행 순서 설정 (숫자가 낮을수록 먼저 실행)

        return filter; // 설정한 필터 반환
    }

    // 두 번째 필터를 등록하는 메서드
    @Bean
    public FilterRegistrationBean<Filter> secondFilter() {
        // FilterRegistrationBean 객체 생성
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();

        // 필터 인스턴스를 설정
        filter.setFilter(new SecondFilter());
        // 필터가 적용될 URL 패턴을 설정
        filter.addUrlPatterns("/books");
        filter.setOrder(2); // doFilter() 순서 보장

        return filter; // 설정한 필터 반환
    }

    // 세 번째 필터를 등록하는 메서드
    @Bean
    public FilterRegistrationBean<Filter> thirdFilter() {
        // FilterRegistrationBean 객체 생성
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();

        // 필터 인스턴스를 설정
        filter.setFilter(new ThirdFilter());
        // 필터가 적용될 URL 패턴을 설정
        filter.addUrlPatterns("/books");
        filter.setOrder(3); // 필터 실행 순서 설정

        return filter; // 설정한 필터 반환
    }
}