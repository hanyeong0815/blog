package com.self.blog.board.application.aop;

import com.self.blog.board.application.repository.BoardViewRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class ViewCountUpAspect {
//    private final ViewCountValidationRepository viewCountValidationRepository;
    private final BoardViewRepository boardViewRepository;

    @Before(value = "@annotation(ViewCountUp) && args(boardId, username, viewIp)", argNames = "boardId,username,viewIp")
    public void viewCountUp(String boardId, String username, String viewIp) {
        // FIXME 계속 조회수 안올가는 문제있음
//        boolean hasViewCountValidation = viewCountValidationRepository
//                .existsViewCountValidationByIdAndUsernameAndViewIp(
//                        boardId, username, viewIp
//                );
//
//        if (hasViewCountValidation) return;

        boardViewRepository.viewCountUp(boardId);
//        ViewCountValidation viewCountValidation = ViewCountValidation.builder()
//                .boardId(boardId)
//                .username(username)
//                .viewIp(viewIp)
//                .ttl(1L)
//                .build();
//        viewCountValidationRepository.save(viewCountValidation);
    }
}
