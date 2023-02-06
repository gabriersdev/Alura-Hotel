package dao;

import factory.ConnectionFactory;
import model.Reserva;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ReservaDao {

    private Connection connection;

    public ReservaDao(Connection connection) {
        this.connection = connection;
    }

    public Integer salvar(Reserva reserva) {

        Integer idCriado = 0;
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
                    idCriado = resultSet.getInt(1);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return idCriado;
    }

    public Double valorReserva(Long periodo) {
        return periodo * 50.0;
    }

    public Long calcularPeriodo(LocalDate entrada, LocalDate saida) {
        return ChronoUnit.DAYS.between(entrada, saida) + 1;
    }

    public List<Reserva> listar() {

        List<Reserva> reservas = new ArrayList<>();

        try (PreparedStatement statement = this.connection.prepareStatement("SELECT id_reserva, data_entrada_reserva, data_saida_reserva, valor_saida_reserva, forma_pagamento_reserva FROM reservas")) {
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                reservas.add(
                        new Reserva(
                                resultSet.getInt("id_reserva"),
                                resultSet.getDate("data_entrada_reserva"),
                                resultSet.getDate("data_saida_reserva"),
                                resultSet.getString("valor_saida_reserva"),
                                resultSet.getString("forma_pagamento_reserva")
                        ));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return reservas;
    }

    public Reserva listar(Integer id) {

        Reserva reserva = new Reserva();

        try (PreparedStatement statement = this.connection.prepareStatement("SELECT id_reserva, data_entrada_reserva, data_saida_reserva, valor_saida_reserva, forma_pagamento_reserva FROM reservas WHERE id_reserva = ?")) {

            statement.setInt(1, id);

            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                reserva = new Reserva(
                        resultSet.getInt("id_reserva"),
                        resultSet.getDate("data_entrada_reserva"),
                        resultSet.getDate("data_saida_reserva"),
                        resultSet.getString("valor_saida_reserva"),
                        resultSet.getString("forma_pagamento_reserva")
                );

            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return reserva;
    }

    public Boolean alterar(Reserva reserva) {

        Boolean status = false;
        String sql = "UPDATE reservas SET data_entrada_reserva = ?, data_saida_reserva = ?, valor_saida_reserva = ?, forma_pagamento_reserva = ? WHERE id_reserva = ?";

        try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {

            pstm.setDate(1, reserva.getDataEntrada());
            pstm.setDate(2, reserva.getDataSaida());
            pstm.setString(3, reserva.getValor());
            pstm.setString(4, reserva.getFormaPagamento());
            pstm.setInt(5, reserva.getId());

            pstm.executeUpdate();

            Integer linhasAlteradas = pstm.getUpdateCount();
            if (linhasAlteradas > 0) {
                status = true;
                this.connection.commit();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return status;
    }

    public Boolean deletar(Reserva reserva) {

        Boolean status = false;
        String sql = "DELETE FROM reservas WHERE id_reserva = ?";

        try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {

            pstm.setInt(1, reserva.getId());
            pstm.executeUpdate();

            Integer linhasAlteradas = pstm.getUpdateCount();
            if (linhasAlteradas > 0) {
                status = true;
                this.connection.commit();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return status;
    }

    public static void main(String[] args) {
        ReservaDao reservaDao = new ReservaDao(new ConnectionFactory().conexao());
        List<Reserva> reservas = reservaDao.listar();

        /*
        reservas.forEach(reserva -> {
            System.out.println(reserva.getId());
            if(reserva.getId() == 2){
                reserva.setFormaPagamento("Dinheiro");
                reservaDao.alterarReserva(reserva);
            }
        });
         */
    }
}
