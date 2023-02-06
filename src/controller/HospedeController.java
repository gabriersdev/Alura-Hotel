package controller;

import dao.HospedeDao;
import factory.ConnectionFactory;
import model.Hospede;

import java.sql.Connection;

public class HospedeController {

    private HospedeDao hospedeDao;

    public HospedeController(){
        Connection connection = new ConnectionFactory().conexao();
        this.hospedeDao = new HospedeDao(connection);
    }

    public Boolean salvar(Hospede hospede){
        return this.hospedeDao.salvar(hospede);
    }

}
