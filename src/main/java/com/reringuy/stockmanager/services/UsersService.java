package com.reringuy.stockmanager.services;

import com.reringuy.stockmanager.models.Users;
import com.reringuy.stockmanager.repositories.UsersRepository;
import com.reringuy.stockmanager.utils.Transactional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@RequestScoped
public class UsersService implements Serializable {
    @Inject
    private UsersRepository usersRepository;

    @Transactional
    public void SaveUser(Users users) {
        usersRepository.save(users);
    }

    @Transactional
    public void UpdateUser(Users users) {
        usersRepository.update(users);
    }

    @Transactional
    public void DeleteUser(long id) {
        usersRepository.deleteById(id);
    }

    public List<Users> GetAllUsers() {
        return usersRepository.findAll();
    }

    public Users GetUserById(long id) {
        return usersRepository.findById(id);
    }
}
