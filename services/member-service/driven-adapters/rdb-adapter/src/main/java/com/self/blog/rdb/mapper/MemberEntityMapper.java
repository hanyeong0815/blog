package com.self.blog.rdb.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.domain.Member;
import com.self.blog.rdb.entity.MemberEntity;
import com.self.blog.rdb.projection.MemberProjections.MemberIdProjection;
import com.self.blog.readmodel.MemberReadModels.MemberIdReadModel;
import org.mapstruct.Mapper;

@Mapper
public interface MemberEntityMapper extends DomainEntityMapper<Member, MemberEntity> {
    MemberIdReadModel readModelFromProjection(MemberIdProjection memberIdProjection);
}
