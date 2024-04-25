package com.self.blog.profile.mongo.mapper;

import com.self.blog.profile.domain.ProfileLog;
import com.self.blog.board.mongo.support.OneToOneEntityMapper;
import com.self.blog.profile.mongo.entity.ProfileLogEntity;
import com.self.blog.profile.mongo.projection.ProfileLogProjections.ProfileLogDetailViewProjection;
import com.self.blog.profile.read_model.ProfileLogReadModels.ProfileLogDetailViewReadModel;
import org.mapstruct.Mapper;

@Mapper
public interface ProfileLogEntityMapper extends OneToOneEntityMapper<ProfileLog, ProfileLogEntity> {
    ProfileLogDetailViewReadModel from(ProfileLogDetailViewProjection profileLogDetailViewProjection);
}
