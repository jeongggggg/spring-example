package com.estsoft.springproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

// 첫 번째 필터 클래스 정의
public class FirstFilter implements Filter {

    // 필터 초기화 메서드
    @Override
    public void init(jakarta.servlet.FilterConfig filterConfig) throws ServletException {
        System.out.println("FirstFilter init()"); // 필터 초기화 시 호출됨
    }

    // 필터의 주요 작업을 수행하는 메서드
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FirstFilter.doFilter() request"); // 요청 처리 시작 시 출력

        // 요청 URI를 가져오기 위해 ServletRequest를 HttpServletRequest로 변환
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 요청 URI 출력
        System.out.println("requestURI : " + request.getRequestURI());

        // 다음 필터 또는 서블릿으로 요청을 전달
        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("FirstFilter.doFilter() response"); // 응답 처리 후 출력
    }

    // 필터 종료 메서드
    @Override
    public void destroy() {
        System.out.println("FirstFilter destroy()"); // 필터 종료 시 호출됨
    }
}
