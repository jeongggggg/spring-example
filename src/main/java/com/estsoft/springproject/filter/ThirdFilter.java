package com.estsoft.springproject.filter;

import jakarta.servlet.FilterConfig;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.IOException;

public class ThirdFilter implements Filter {

    // 필터 초기화 메서드
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("ThirdFilter init()"); // 필터 초기화 시 호출
    }

    // 필터가 요청을 처리하는 메서드
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("ThirdFilter doFilter() request"); // 요청 처리 시작 로그

        // 클라이언트가 요청으로 넘긴 파라미터를 수정하기 위해 RequestWrapper 사용
        filterChain.doFilter(new RequestWrapper((HttpServletRequest) servletRequest), servletResponse);

        System.out.println("ThirdFilter doFilter() response"); // 응답 처리 완료 로그
    }

    // 필터 종료 시 호출되는 메서드
    @Override
    public void destroy() {
        System.out.println("ThirdFilter destroy()"); // 필터 종료 시 호출
    }

    // HttpServletRequestWrapper를 상속한 내부 클래스
    private static class RequestWrapper extends HttpServletRequestWrapper {

        // 생성자: HttpServletRequest 객체를 전달받음
        public RequestWrapper(HttpServletRequest request) {
            super(request); // 부모 클래스의 생성자 호출
        }

        /**
         * 클라이언트가 파라미터로 전달한 값에 "123" 문자열을 추가하기 위한 메서드
         */
        @Override
        public String[] getParameterValues(String parameter) {
            String[] values = super.getParameterValues(parameter); // 전달받은 parameter의 값 가져오기

            if (values == null) {
                return null; // 값이 없으면 null 반환
            }

            for (int i = 0; i < values.length; i++) {
                if (values[i] != null) {
                    // parameter의 끝에 "123" 문자열 추가
                    values[i] = values[i] + "123";
                }
            }
            return values; // 수정된 값 반환
        }
    }
}