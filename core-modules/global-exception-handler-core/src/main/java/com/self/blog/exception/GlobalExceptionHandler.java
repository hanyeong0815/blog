package com.self.music.exception;

import com.self.music.common.support.exception.CustomException;
import com.self.music.common.support.exception.ErrorCode;
import com.self.music.common.support.exception.ResponseError;
import com.self.music.common.support.exception.status2xx.NoContentException;
import com.self.music.common.utils.time.ServerTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public final class GlobalExceptionHandler {
    private final ServerTime serverTime;

    /**
     * 다른 핸들러에서 처리하지 않은 커스텀 익셉션에 대한 핸들링
     * */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseError> handleCustomException(CustomException exception) {
        exception.printStackTrace();
        ErrorCode errorCode = exception.getErrorCode();
        HttpStatus httpStatus = errorCode.defaultHttpStatus();

        ResponseError response = ResponseError.of(exception);

        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * 204 No Content
     */
    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<Void> handleNoContentException(NoContentException exception) {
        exception.printStackTrace();
        return ResponseEntity.noContent().build();
    }

    /**
     * (주로 API Request Body) validation 제공 애노테이션 등에서 필터링된 유효성 예외를 최종적으로 이곳에서 처리함.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        exception.printStackTrace();

        FieldError fieldError = exception.getBindingResult().getFieldError();

        ResponseError response = ResponseError.builder()
                .name(exception.getBody().getTitle())
                .message(
                        fieldError == null ? exception.getMessage() : fieldError.getDefaultMessage()
                )
                .status(exception.getStatusCode().value())
                .timestamp(serverTime.nowLocal())
                .build();

        if (exception.getCause() != null) {
            response.appendCause(
                    ResponseError.platCauseAsSubErrors(exception.getCause())
            );
        }
        return new ResponseEntity<>(response, exception.getStatusCode());
    }
}