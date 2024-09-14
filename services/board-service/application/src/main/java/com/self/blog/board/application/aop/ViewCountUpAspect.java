package com.self.blog.board.application.aop;

import com.self.blog.board.application.repository.BoardRepository;
import com.self.blog.board.application.repository.BoardViewRepository;
import com.self.blog.board.application.repository.ViewCountValidationRepository;
import com.self.blog.board.domain.BoardActionLog;
import com.self.blog.board.domain.ViewCountValidation;
import com.self.blog.common.utils.time.ServerTime;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Aspect
@Component
@RequiredArgsConstructor
public class ViewCountUpAspect {
    private final ViewCountValidationRepository viewCountValidationRepository;
    private final BoardViewRepository boardViewRepository;
    private final BoardRepository boardRepository;

    private final ServerTime serverTime;

    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:5044")
            .build();

    @Before(value = "@annotation(ViewCountUp) && args(boardId, username, viewIp)", argNames = "boardId,username,viewIp")
    public void viewCountUp(String boardId, String username, String viewIp) {
        boolean hasViewCountValidation = viewCountValidationRepository
                .existsByIdAndUsername(boardId, username);
        boolean isWriter = boardRepository.existsByIdAndUsername(boardId, username);

        if (hasViewCountValidation || isWriter) return;

        boardViewRepository.viewCountUp(boardId);

        ViewCountValidation viewCountValidation = ViewCountValidation.builder()
                .boardId(boardId)
                .username(username)
                .ttl(1L)
                .build();
        viewCountValidationRepository.save(viewCountValidation);

        sendLogToLogstash(boardId);
    }

    private void sendLogToLogstash(String boardId) {
        BoardActionLog boardActionLog = BoardActionLog.builder()
                .boardId(boardId)
                .action("view")
                .timestamp(serverTime.nowInstant())
                .build();

        webClient.post()
                .uri("/")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(boardActionLog))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
