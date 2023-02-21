package com.codechallenge.roles.web.interfaces;

import com.codechallenge.roles.data.models.User;
import com.codechallenge.roles.web.DTO.UsersDTO;

import java.util.List;
import java.util.UUID;

public interface UsersClientInterface {
    public List<UsersDTO> getUsers();

    public User getUserById(UUID userId);

}
