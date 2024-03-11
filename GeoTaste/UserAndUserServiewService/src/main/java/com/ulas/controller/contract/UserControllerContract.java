package com.ulas.controller.contract;

import com.ulas.dto.UserDTO;
import com.ulas.request.UserSaveRequest;
import com.ulas.request.UserUpdateRequest;

public interface UserControllerContract {
    UserDTO saveUser(UserSaveRequest request);
    UserDTO delete(Long id);

    UserDTO updateUser(UserUpdateRequest request);
}
