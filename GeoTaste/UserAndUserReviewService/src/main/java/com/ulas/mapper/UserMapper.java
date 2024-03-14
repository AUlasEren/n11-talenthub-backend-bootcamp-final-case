package com.ulas.mapper;

import com.ulas.dto.UserDTO;
import com.ulas.entity.User;
import com.ulas.request.UserSaveRequest;
import com.ulas.request.UserUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "password", source = "password")
    User convertToUser(UserSaveRequest request);
    UserDTO convertToUserDTO(User user);
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "birthDate", source = "birthDate")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "latitude", source = "latitude")
    @Mapping(target = "longitude", source = "longitude")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "userReviewId", source = "userReviewId")
    @Mapping(target = "id", ignore = true)
    User updateUserFields(UserUpdateRequest request, @MappingTarget User user);

}
