package org.example.microuserservice.infrastructure.adapter;

import org.example.microuserservice.infrastructure.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringMongoUserRepository extends MongoRepository<UserEntity, String> {
}
