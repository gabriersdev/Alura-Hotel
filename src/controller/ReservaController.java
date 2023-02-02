package controller;

import dao.ReservaDao;
import factory.ConnectionFactory;
import model.Reserva;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class ReservaController {

    private ReservaDao reservaDao;

    public ReservaController() {
        Connection connection = new ConnectionFactory().conexao();
        this.reservaDao = new ReservaDao(connection);
    }

    public void salvar(Reserva reserva){
        this.reservaDao.salvar(reserva);
    }

    public Double valorReserva(Long periodo){
        return this.reservaDao.valorReserva( periodo);
    }

    public Long calcularPeriodo(LocalDate entrada, LocalDate saida) {
        return this.reservaDao.calcularPeriodo(entrada, saida);
    }

    //public void listar()
}
