package com.self.blog.board.elasticsearch.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.self.blog.board.application.repository.BoardSearchRepository;
import com.self.blog.board.domain.Board;
import com.self.blog.board.elasticsearch.entity.BoardEntity;
import com.self.blog.board.elasticsearch.mapper.BoardElasticsearchMapper;
import com.self.blog.common.utils.time.ServerTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardSearchPersistence implements BoardSearchRepository {
    private final BoardElasticsearchRepository repository;
    private final BoardElasticsearchMapper mapper;

    private final ElasticsearchClient elasticsearchClient;

    @Override
    public void saveAll(List<Board> boardList) {
        List<BoardEntity> boardEntityList = boardList.stream()
                .map(mapper::toEntity)
                .toList();
        repository.saveAll(boardEntityList);
    }

    @Override
    public List<String> search() throws IOException {
        // Aggregation 설정
        Aggregation boardIdAgg = Aggregation.of(a -> a
                .terms(t -> t
                        .field("boardId.keyword")
                        .size(8) // 상위 8개의 boardId
                )
        );

        // 검색 요청 생성
        SearchRequest searchRequest = SearchRequest.of(sr -> sr
                .index("board-action-logs-*")  // 로그가 저장된 인덱스 이름
                .size(0)  // 실제 문서는 반환하지 않음
                .aggregations("top_boards", boardIdAgg)
        );

        // Elasticsearch로 검색 요청 수행
        SearchResponse<Void> searchResponse = elasticsearchClient.search(searchRequest, Void.class);

        // Aggregation 결과에서 상위 8개의 boardId 추출
        return searchResponse.aggregations()
                .get("top_boards") // 집계 이름에 맞게 가져옴
                .sterms()  // String terms 집계 사용
                .buckets().array().stream()
                .map(StringTermsBucket::key)  // 각 버킷의 키(boardId)를 가져옴
                .map(FieldValue::stringValue)
                .toList();

//        return repository.findByIdIn(topBoardIds).stream()
//                .map(mapper::toDomain)
//                .toList();
    }
}
