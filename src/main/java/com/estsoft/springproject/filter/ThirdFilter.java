package com.estsoft.springproject.filter;

import jakarta.servlet.FilterConfig;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.IOException;

/**
 * ThirdFilter는 서블릿 필터로, 클라이언트 요청 파라미터를 수정하는 기능을 가짐
 */
public class ThirdFilter implements Filter {

    /**
     * 필터 초기화를 수행
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("ThirdFilter init()");
    }

    /**
     * 요청을 처리하고, 파라미터를 수정하여 필터 체인을 통해 응답을 처리
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("ThirdFilter doFilter() request");

        // 클라이언트가 요청으로 넘긴 파라미터를 수정함
        filterChain.doFilter(new RequestWrapper((HttpServletRequest) servletRequest), servletResponse);

        System.out.println("ThirdFilter doFilter() response");
    }

    /**
     * 필터 종료 시 호출
     */
    @Override
    public void destroy() {
        System.out.println("ThirdFilter destroy()");
    }

    /**
     * HttpServletRequest를 래핑하여 파라미터 값을 수정
     */
    private static class RequestWrapper extends HttpServletRequestWrapper {

        public RequestWrapper(HttpServletRequest request) {
            super(request);
        }

        /**
         * 클라이언트가 파라미터로 전달한 값에 "123" 문자열을 추가하기 위한 메소드
         */
        @Override
        public String[] getParameterValues(String parameter) {
            String[] values = super.getParameterValues(parameter); // 전달받은 parameter 가져옴

            if (values == null) {
                return null;
            }

            // 각 파라미터 값 끝에 "123" 문자열을 추가
            for (int i = 0; i < values.length; i++) {
                if (values[i] != null) {
                    values[i] = values[i] + "123";
                }
            }
            return values;
        }
    }
}
