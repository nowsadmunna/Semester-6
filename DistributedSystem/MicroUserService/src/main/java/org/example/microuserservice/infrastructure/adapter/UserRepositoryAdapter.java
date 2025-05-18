package org.example.microuserservice.infrastructure.adapter;

import org.example.microuserservice.domain.model.User;
import org.example.microuserservice.domain.port.out.UserRepositoryPort;
import org.example.microuserservice.infrastructure.entity.UserEntity;
import org.example.microuserservice.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class UserRepositoryAdapter implements UserRepositoryPort {
    private final SpringMongoUserRepository repository;
    public UserRepositoryAdapter(SpringMongoUserRepository repository) {
        this.repository = repository;
    }
    @Override
    public User save(User user){
        UserEntity entity=UserMapper.toEntity(user);
        return UserMapper.toDomain(repository.save(entity));
    }
    @Override
    public Optional<User> findById(String id){
        return repository.findById(id).map(UserMapper::toDomain);
    }
}
