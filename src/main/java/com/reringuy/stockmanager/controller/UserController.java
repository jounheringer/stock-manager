package com.reringuy.stockmanager.controller;

import com.reringuy.stockmanager.models.Users;
import com.reringuy.stockmanager.services.UsersService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class UserController implements Serializable {
    private static final long serialVersionUID = 1L;
    private Users users;
    private List<Users> usersList;

    @Inject
    private UsersService usersService;

    @PostConstruct
    public void init() {
        this.users = new Users();
        this.usersList = this.usersService.GetAllUsers();
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public void saveUser() {
        try {
            this.usersService.SaveUser(this.users);
            this.users = new Users();
            this.usersList = this.usersService.GetAllUsers();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Usuário salvo com sucesso."));
        } catch (RuntimeException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar", ex.getMessage()));
        }
    }
}
