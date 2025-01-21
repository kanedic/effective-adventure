package kr.or.ddit.yguniv.commons.exception;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PKDuplicatedExceptionHandler extends RuntimeException{

	@ExceptionHandler(PKDuplicatedException.class)
	public ResponseEntity<Object> handlePKNotFoundException(PKDuplicatedException ex) {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND) // 상태 코드 설정
				.body(Collections.singletonMap("message", ex.getMessage()));
    }
	
}
