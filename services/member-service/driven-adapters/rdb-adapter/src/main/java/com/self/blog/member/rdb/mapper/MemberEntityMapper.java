package com.self.blog.member.rdb.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.member.domain.Member;
import com.self.blog.member.rdb.entity.MemberEntity;
import com.self.blog.member.rdb.projection.MemberProjections.MemberIdProjection;
import com.self.blog.member.readmodel.MemberReadModels.MemberIdReadModel;
import org.mapstruct.Mapper;

@Mapper
public interface MemberEntityMapper extends DomainEntityMapper<Member, MemberEntity> {
    MemberIdReadModel readModelFromProjection(MemberIdProjection memberIdProjection);
}
