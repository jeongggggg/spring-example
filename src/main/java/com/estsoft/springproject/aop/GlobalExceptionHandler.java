package com.estsoft.springproject.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 전역적으로 발생하는 예외를 처리하기 위한 클래스
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * IllegalArgumentException 발생 시 이를 처리하고,
     * HTTP 400 Bad Request 상태 코드를 반환
     *
     * @param e 발생한 IllegalArgumentException 객체
     * @return 예외 메시지를 담은 ResponseEntity를 반환
     */
    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        // 예외 메시지를 바디로 담아 HTTP 400 상태를 반환
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
