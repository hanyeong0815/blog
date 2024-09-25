package com.self.blog.board.elasticsearch.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.self.blog.board.application.repository.BoardSearchRepository;
import com.self.blog.board.application.usecase.data.BoardListViewDto.BoardRecommendListView;
import com.self.blog.board.domain.BoardElasticsearch;
import com.self.blog.board.elasticsearch.entity.BoardEntity;
import com.self.blog.board.elasticsearch.mapper.BoardElasticsearchMapper;
import com.self.blog.common.utils.time.ServerTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardSearchPersistence implements BoardSearchRepository {
    private final BoardElasticsearchRepository repository;
    private final BoardElasticsearchMapper mapper;

    private final ElasticsearchClient elasticsearchClient;

    private final ServerTime serverTime;

    @Override
    public void saveAll(List<BoardElasticsearch> boardList) {
        List<BoardEntity> boardEntityList = boardList.stream()
                .map(mapper::toEntity)
                .toList();
        repository.saveAll(boardEntityList);
    }

    @Override
    public List<BoardRecommendListView> search() throws IOException {
        Instant now = serverTime.nowInstant();

        // 24시간 전
//        Instant oneDayAgo = now.minusSeconds(24 * 60 * 60);

        // DOTO 당장 조회가 없어서 필요 없음
        // 날짜 범위 쿼리 설정 (24시간 전부터 현재 시간까지)
//        Query dateRangeQuery = Query.of(q -> q
//                .range(r -> r
//                        .field("@timestamp")  // 로그의 timestamp 필드 기준
//                        .gte(JsonData.of(oneDayAgo.toString()))  // 24시간 전 이상
//                        .lte(JsonData.of(now.toString()))  // 현재 시간 이하
//                )
//        );

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
//                .query(dateRangeQuery)
                .aggregations("top_boards", boardIdAgg)
        );

        // Elasticsearch로 검색 요청 수행
        SearchResponse<Void> searchResponse = elasticsearchClient.search(searchRequest, Void.class);

        // Aggregation 결과에서 상위 8개의 boardId 추출
        List<String> topBoardIds = searchResponse.aggregations()
                .get("top_boards") // 집계 이름에 맞게 가져옴
                .sterms()  // String terms 집계 사용
                .buckets().array().stream()
                .map(StringTermsBucket::key)  // 각 버킷의 키(boardId)를 가져옴
                .map(FieldValue::stringValue)
                .toList();

        System.out.println(topBoardIds);

        return repository.findByIdIn(topBoardIds).stream()
                .map(mapper::from)
                .toList();
    }
}
