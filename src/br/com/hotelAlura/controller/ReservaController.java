package br.com.hotelAlura.controller;

import br.com.hotelAlura.dao.ReservaDao;
import br.com.hotelAlura.factory.ConnectionFactory;
import br.com.hotelAlura.model.Reserva;

import java.sql.Connection;
import java.time.LocalDate;

public class ReservaController {

    private ReservaDao reservaDao;

    public ReservaController() {
        Connection connection = new ConnectionFactory().conexao();
        this.reservaDao = new ReservaDao(connection);
    }

    public Integer salvar(Reserva reserva) {
        return this.reservaDao.salvar(reserva);
    }

    public Double valorReserva(Long periodo) {
        return this.reservaDao.valorReserva(periodo);
    }

    public Long calcularPeriodo(LocalDate entrada, LocalDate saida) {
        return this.reservaDao.calcularPeriodo(entrada, saida);
    }

    //public void listar()
}
