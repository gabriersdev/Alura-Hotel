package controller;

import dao.HospedeDao;
import factory.ConnectionFactory;
import model.Hospede;

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

    public List<Hospede> listar() throws SQLException {
        return this.hospedeDao.listar();
    }

    public Boolean alterar(Hospede hospede) throws SQLException {
        return this.alterar(hospede);
    }

    public Boolean deletar(Hospede hospede) throws SQLException {
        return this.deletar(hospede);
    }

}
