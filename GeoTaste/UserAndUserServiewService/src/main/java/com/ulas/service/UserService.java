package com.ulas.service;

import com.ulas.entity.User;
import com.ulas.general.BaseEntityService;
import com.ulas.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseEntityService<User, UserRepository> {
    protected UserService(UserRepository repository) {
        super(repository);
    }
}
