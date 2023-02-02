package dao;

import model.Reserva;

import java.sql.*;

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

}
