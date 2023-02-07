package br.com.hotelAlura.controller;

import br.com.hotelAlura.dao.HospedeDao;
import br.com.hotelAlura.factory.ConnectionFactory;
import br.com.hotelAlura.model.Hospede;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class HospedeController {

    private HospedeDao hospedeDao;

    public HospedeController() {
        Connection connection = new ConnectionFactory().conexao();
        this.hospedeDao = new HospedeDao(connection);
    }

    public Boolean salvar(Hospede hospede) {
        return this.hospedeDao.salvar(hospede);
    }

    public List<Hospede> listar() {
        try{
            return this.hospedeDao.listar();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Boolean alterar(Hospede hospede) {
        try{
            return this.alterar(hospede);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Boolean deletar(Hospede hospede){
        try{
            return this.deletar(hospede);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
