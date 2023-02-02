package dao;

import factory.ConnectionFactory;
import model.Reserva;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReservaDao {

    private Connection connection;

    public ReservaDao(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Reserva reserva) {
        String sql = "INSERT INTO reservas (data_entrada_reserva, data_saida_reserva, valor_saida_reserva, forma_pagamento_reserva) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstm = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstm.setDate(1, reserva.getDataEntrada());
            pstm.setDate(2, reserva.getDataSaida());
            pstm.setString(3, reserva.getValor());
            pstm.setString(4, reserva.getFormaPagamento());

            pstm.executeUpdate();

            try (ResultSet resultSet = pstm.getGeneratedKeys()) {

                while (resultSet.next()) {
                    //Retornando o id para a referência Reserva
                    reserva.setId(resultSet.getInt(1));
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Double valorReserva(Long periodo){
        return periodo * 50.0;
    }

    public Long calcularPeriodo(LocalDate entrada, LocalDate saida){
        return ChronoUnit.DAYS.between(entrada, saida) + 1;
    }

    public void listarReservas(){

        try(PreparedStatement statement = this.connection.prepareStatement("SELECT id_reserva, data_entrada_reserva, data_saida_reserva, valor_saida_reserva, forma_pagamento_reserva FROM reservas")){
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()){
                System.out.println(resultSet.getInt("id_reserva"));
                System.out.println(resultSet.getDate("data_entrada_reserva"));
                System.out.println(resultSet.getDate("data_saida_reserva"));
                System.out.println(resultSet.getDouble("valor_saida_reserva"));
                System.out.println(resultSet.getString("forma_pagamento_reserva"));
            }

        }catch (SQLException e){
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        ReservaDao reservaDao = new ReservaDao(new ConnectionFactory().conexao());
        reservaDao.listarReservas();
    }
}
