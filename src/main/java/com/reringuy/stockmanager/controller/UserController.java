package com.reringuy.stockmanager.controller;

import com.reringuy.stockmanager.models.Users;
import com.reringuy.stockmanager.services.UsersService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class UserController implements Serializable {
    private static final long serialVersionUID = 1L;
    private Users users;

    @Inject
    private UsersService usersService;

    @PostConstruct
    public void init() {
        this.users = new Users();
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public void saveUser() {
        this.usersService.SaveUser(this.users);
        this.users = new Users();
    }
}
