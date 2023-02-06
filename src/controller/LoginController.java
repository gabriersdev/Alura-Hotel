package controller;

import dao.LoginDao;
import factory.ConnectionFactory;
import model.Login;

import java.sql.SQLException;

public class LoginController {

    private LoginDao loginDao;

    public LoginController() throws SQLException {
        this.loginDao = new LoginDao(new ConnectionFactory().conexao());
    }

    public Boolean acessar(Login login){
        return this.loginDao.acessar(login);
    }

}
