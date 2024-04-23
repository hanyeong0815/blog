package com.self.blog.nosql.mapper;

import com.self.blog.domain.ProfileLog;
import com.self.blog.mongo.support.OneToOneEntityMapper;
import com.self.blog.nosql.entity.ProfileLogEntity;
import com.self.blog.nosql.projection.ProfileLogProjections.ProfileLogDetailViewProjection;
import com.self.blog.read_model.ProfileLogReadModels.ProfileLogDetailViewReadModel;
import org.mapstruct.Mapper;

@Mapper
public interface ProfileLogEntityMapper extends OneToOneEntityMapper<ProfileLog, ProfileLogEntity> {
    ProfileLogDetailViewReadModel from(ProfileLogDetailViewProjection profileLogDetailViewProjection);
}
