package com.self.blog.board.domain;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ViewCountValidation {
    public String boardId;
    public String username;
    public String viewIp;
    public Long ttl;
}
