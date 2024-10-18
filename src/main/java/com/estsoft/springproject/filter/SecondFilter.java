package com.estsoft.springproject.filter;

import jakarta.servlet.FilterConfig;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * SecondFilter는 서블릿 필터로, 요청과 응답을 가로챔
 * 기본적인 필터 기능을 통해 요청과 응답 처리 시 로깅을 수행
 */
public class SecondFilter implements Filter {

    /**
     * 필터 초기화를 수행함
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("SecondFilter init()");
    }

    /**
     * 필터를 통해 요청을 처리하고, 필터 체인을 통해 응답을 처리
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("SecondFilter doFilter() request");

        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("SecondFilter doFilter() response");
    }

    /**
     * 필터를 종료할 때 호출
     */
    @Override
    public void destroy() {
        System.out.println("SecondFilter destroy()");
    }

    /**
     * HttpServletResponse를 래핑하여 응답의 바디를 수정하거나 변형할 수 있도록 함
     */
    private static class ResponseWrapper extends HttpServletResponseWrapper {

        ByteArrayOutputStream byteArrayOutputStream;
        ResponseBodyServletOutputStream responseBodyServletOutputStream;

        public ResponseWrapper(HttpServletResponse response) {
            super(response);
            byteArrayOutputStream = new ByteArrayOutputStream();
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            if (responseBodyServletOutputStream == null) {
                responseBodyServletOutputStream = new ResponseBodyServletOutputStream(byteArrayOutputStream);
            }
            return responseBodyServletOutputStream;
        }

        /**
         * 응답 바디를 문자열로 변환하여 반환
         */
        public String getOutputString() {
            return byteArrayOutputStream.toString(StandardCharsets.UTF_8);
        }
    }

    /**
     * 서블릿의 응답 바디에서 데이터를 가져오기 위한 클래스
     */
    private static class ResponseBodyServletOutputStream extends ServletOutputStream {

        private final DataOutputStream outputStream;

        public ResponseBodyServletOutputStream(OutputStream output) {
            this.outputStream = new DataOutputStream(output);
        }

        @Override
        public void write(int b) throws IOException {
            outputStream.write(b);
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener listener) {
        }
    }
}