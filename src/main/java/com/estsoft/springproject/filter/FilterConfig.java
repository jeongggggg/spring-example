package com.estsoft.springproject.filter;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 필터 설정을 위한 Config 클래스
 */
@Configuration
public class FilterConfig {

    /**
     * FirstFilter를 등록하고, "/books" 경로에 대해 적용되도록 설정
     * 해당 필터는 우선순위 1로 등록
     */
    @Bean
    public FilterRegistrationBean<Filter> firstFilter() {
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();

        filter.setFilter(new FirstFilter());
        filter.addUrlPatterns("/books");
        filter.setOrder(1); // 첫 번째로 실행되도록 순서 설정

        return filter;
    }

    /**
     * SecondFilter를 등록하고, "/books" 경로에 대해 적용되도록 설정
     * 해당 필터는 우선순위 2로 등록
     */
    @Bean
    public FilterRegistrationBean<Filter> secondFilter() {
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();

        filter.setFilter(new SecondFilter());
        filter.addUrlPatterns("/books");
        filter.setOrder(2);  // 두 번째로 실행되도록 순서 설정

        return filter;
    }

    /**
     * ThirdFilter도 추가하려면 아래 코드를 사용하여 등록할 수 있음
     * 주석 해제 후 우선순위 3으로 설정하여 실행 순서를 보장할 수 있음
     */
//    @Bean
//    public FilterRegistrationBean<Filter> thirdFilter() {
//        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
//
//        filter.setFilter(new ThirdFilter());
//        filter.addUrlPatterns("/books");
//        filter.setOrder(3);  // 세 번째로 실행되도록 순서 설정함
//
//        return filter;
//    }
}
