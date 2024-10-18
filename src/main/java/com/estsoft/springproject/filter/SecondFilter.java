package com.estsoft.springproject.filter;

import jakarta.servlet.FilterConfig;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SecondFilter implements Filter {

    // 필터 초기화 메서드
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("SecondFilter init()"); // 필터 초기화 시 호출
    }

    // 필터가 요청을 처리하는 메서드
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("SecondFilter doFilter() request"); // 요청 처리 시작 로그

        // 서블릿 응답 객체를 래퍼 클래스로 포장
        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) servletResponse);

        // 다음 필터 또는 서블릿으로 요청을 전달
        filterChain.doFilter(servletRequest, responseWrapper);

        // 클라이언트에게 응답하기 전에 응답 본문을 대문자로 변환
        String responseString = responseWrapper.getOutputString().toUpperCase();

        // 대문자로 변환한 값을 응답합니다
        servletResponse.getOutputStream().write(responseString.getBytes(StandardCharsets.UTF_8));
        servletResponse.flushBuffer();  // 응답 버퍼를 플러시하여 클라이언트에 전송

        System.out.println("SecondFilter doFilter() response"); // 응답 처리 완료 로그
    }

    // 필터 종료 시 호출되는 메서드
    @Override
    public void destroy() {
        System.out.println("SecondFilter destroy()"); // 필터 종료 시 호출
    }

    // HttpServletResponseWrapper를 상속한 내부 클래스
    private static class ResponseWrapper extends HttpServletResponseWrapper {

        ByteArrayOutputStream byteArrayOutputStream; // 응답 본문을 저장할 바이트 배열 출력 스트림
        ResponseBodyServletOutputStream responseBodyServletOutputStream; // 응답 본문을 출력할 서블릿 출력 스트림

        // 생성자: HttpServletResponse 객체를 전달받음
        public ResponseWrapper(HttpServletResponse response) {
            super(response); // 부모 클래스의 생성자 호출
            byteArrayOutputStream = new ByteArrayOutputStream(); // 바이트 배열 출력 스트림 초기화
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            throw new UnsupportedOperationException("getWriter() is not supported");
        }

        // 서블릿 출력 스트림을 반환하는 메서드
        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            if (responseBodyServletOutputStream == null) {
                // ResponseBodyServletOutputStream을 초기화
                responseBodyServletOutputStream = new ResponseBodyServletOutputStream(byteArrayOutputStream);
            }
            return responseBodyServletOutputStream; // 래퍼 스트림 반환
        }

        // 응답 본문을 String으로 변환하여 반환
        public String getOutputString() {
            return byteArrayOutputStream.toString(StandardCharsets.UTF_8);
        }
    }

    /**
     * 서블릿의 응답 본문에서 데이터를 가져오기 위한 클래스
     */
    private static class ResponseBodyServletOutputStream extends ServletOutputStream {

        private final DataOutputStream outputStream; // 데이터 출력을 위한 데이터 출력 스트림

        // 생성자: 출력 스트림을 전달받음
        public ResponseBodyServletOutputStream(OutputStream output) {
            this.outputStream = new DataOutputStream(output); // 데이터 출력 스트림 초기화
        }

        // 바이트를 작성하는 메서드
        @Override
        public void write(int b) throws IOException {
            outputStream.write(b); // 데이터 출력 스트림에 바이트 작성
        }

        // 스트림이 준비된 상태인지 확인하는 메서드
        @Override
        public boolean isReady() {
            return true; // 항상 준비된 상태로 반환
        }

        // 쓰기 리스너를 설정하는 메서드 (사용되지 않음)
        @Override
        public void setWriteListener(WriteListener listener) {
            // 현재 구현에서는 사용되지 않음
        }
    }
}