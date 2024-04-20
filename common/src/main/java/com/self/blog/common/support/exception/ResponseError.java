package com.self.music.common.support.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
public record ResponseError(
        String code,
        Integer status,
        String name,
        String message,
        @JsonInclude(Include.NON_EMPTY) List<ApiSubError> cause,
        LocalDateTime timestamp
) {
    public ResponseError {
        // 생성자가 쓰일 때, 생성자의 파라미터에 대한 검토와 변경을 수행하는 공간.
        if (code == null) code = "API_ERROR";
        if (status == null) status = 500;
        if (name == null) name = "ApiError";
        if (message == null || "".equals(message)) message = "API 사용 중 서버에서 오류가 발생했습니다.";
        if (timestamp == null) timestamp = LocalDateTime.now();
    }

    @Builder
    public record ApiSubError(String field, String message) {}

    public static ResponseError of(HttpStatus httpStatus) {
        return ResponseError.builder()
                .code(httpStatus.name())
                .status(httpStatus.value())
                .name(httpStatus.getReasonPhrase())
                .message(httpStatus.series().name())
                .build();
    }

    public static ResponseError of(HttpStatus httpStatus, ApiSubError... cause) {
        List<ApiSubError> causeList = List.of(cause);

        return ResponseError.builder()
                .code(httpStatus.name())
                .status(httpStatus.value())
                .name(httpStatus.getReasonPhrase())
                .message(httpStatus.series().name())
                .cause(causeList)
                .build();
    }

    public static ResponseError of(ErrorCode errorCode) {
        String errorName = errorCode.defaultException().getClass().getName();
        errorName = errorName.substring(errorName.lastIndexOf('.') + 1);

        return ResponseError.builder()
                .code(errorCode.name())
                .status(errorCode.defaultHttpStatus().value())
                .name(errorName)
                .message(errorCode.defaultMessage())
                .build();
    }

    public static ResponseError of(CustomException exception) {
        return of(exception.ERROR_CODE, exception.getMessage(), platCauseAsSubErrors(exception));
    }

    public static ResponseError of(ErrorCode errorCode, ApiSubError... cause) {
        return of(errorCode, errorCode.defaultMessage(), cause);
    }

    public static ResponseError of(ErrorCode errorCode, String message, ApiSubError... cause) {
        List<ApiSubError> causeList = List.of(cause);
        String errorName = errorCode.defaultException().getClass().getName();
        errorName = errorName.substring(errorName.lastIndexOf('.') + 1);

        return ResponseError.builder()
                .code(errorCode.name())
                .status(errorCode.defaultHttpStatus().value())
                .name(errorName)
                .message(message)
                .cause(causeList)
                .build();
    }

    public ResponseError appendCause(ApiSubError... cause) {
        return this.appendCause(List.of(cause));
    }

    public ResponseError appendCause(List<ApiSubError> cause) {
        return ResponseError.builder()
                .timestamp(this.timestamp())
                .status(this.status())
                .name(this.name())
                .message(this.message())
                .cause(cause)
                .build();
    }

    // 서브 에러를 중첩 구조에서 1차원 배열 구조로 펼치는 메서드 예시.
    public static ApiSubError[] platCauseAsSubErrors(Throwable cause) {
        Throwable currentCause = cause;
        List<ApiSubError> subErrors = new ArrayList<>();

        while (currentCause != null) {
            String errorFullName = currentCause.getClass().getSimpleName();
            String field = errorFullName.substring(errorFullName.lastIndexOf('.') + 1);
            subErrors.add(
                    ApiSubError.builder()
                            .field(field)
                            .message(currentCause.getLocalizedMessage())
                            .build()
            );
            currentCause = currentCause.getCause();
        }

        ApiSubError[] subErrorArray = new ApiSubError[subErrors.size()];
        return subErrors.toArray(subErrorArray);
    }
}