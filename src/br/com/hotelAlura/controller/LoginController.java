package br.com.hotelAlura.controller;

import br.com.hotelAlura.dao.LoginDao;
import br.com.hotelAlura.factory.ConnectionFactory;
import br.com.hotelAlura.model.Login;

import java.sql.SQLException;

public class LoginController {

    private LoginDao loginDao;

    public LoginController() throws SQLException {
        this.loginDao = new LoginDao(new ConnectionFactory().conexao());
    }

    public Boolean acessar(Login login) {
        return this.loginDao.acessar(login);
    }

}
