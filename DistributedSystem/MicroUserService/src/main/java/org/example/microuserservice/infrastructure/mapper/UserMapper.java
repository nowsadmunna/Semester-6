package org.example.microuserservice.infrastructure.mapper;

import org.example.microuserservice.domain.model.User;
import org.example.microuserservice.infrastructure.entity.UserEntity;

public class UserMapper {
    public static UserEntity toEntity(User user){
        return new UserEntity(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
    public static User toDomain(UserEntity userEntity){
        User mappedUser= new User(userEntity.getName(), userEntity.getEmail(), userEntity.getRole());
        mappedUser.setId(userEntity.getId());
        return mappedUser;
    }
}
