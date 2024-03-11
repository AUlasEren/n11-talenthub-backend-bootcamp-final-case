package com.ulas.controller.contract.impl;

import com.ulas.controller.contract.UserControllerContract;
import com.ulas.dto.UserDTO;
import com.ulas.entity.User;
import com.ulas.exception.EErrorType;
import com.ulas.exception.UserManagerException;
import com.ulas.mapper.UserMapper;
import com.ulas.request.UserSaveRequest;
import com.ulas.request.UserUpdateRequest;
import com.ulas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserControllerContractImpl implements UserControllerContract {
    private  final UserService userService;
    @Override
    public UserDTO saveUser(UserSaveRequest request) {
        User user = UserMapper.INSTANCE.convertToUser(request);
        user = userService.save(user);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public UserDTO delete(Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        }
        userService.delete(user.get());
        return UserMapper.INSTANCE.convertToUserDTO(user.get());
    }

    @Override
    public UserDTO updateUser(UserUpdateRequest request) {
        Optional<User> user = userService.findById(request.id());
        if (user.isEmpty()) {
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        }
        UserMapper.INSTANCE.updateUserFields(request, user.get());
        userService.save(user.get());
        return UserMapper.INSTANCE.convertToUserDTO(user.get());
    }
}
