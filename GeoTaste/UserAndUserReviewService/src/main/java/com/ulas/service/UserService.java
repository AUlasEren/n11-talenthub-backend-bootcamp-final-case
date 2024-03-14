package com.ulas.service;

import com.ulas.dto.RestaurantDTO;
import com.ulas.entity.User;
import com.ulas.exception.EErrorType;
import com.ulas.exception.UserManagerException;
import com.ulas.general.BaseEntityService;
import com.ulas.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService extends BaseEntityService<User, UserRepository> {
    protected UserService(UserRepository repository) {
        super(repository);
    }


}
