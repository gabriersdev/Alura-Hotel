package dao;

import factory.ConnectionFactory;
import model.Hospede;

import java.sql.*;

public class HospedeDao {

    private Connection connection;

    public HospedeDao(Connection connection) {
        this.connection = connection;
    }

    public Boolean salvar(Hospede hospede) {

        Boolean status = false;
        String sql = "INSERT INTO hospedes (nome_hospede, sobrenome_hospede, data_nascimento_hospede, nacionalidade_hospede, telefone_hospede, cod_reserva_hospede) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstm.setString(1, hospede.getNome());
            pstm.setString(2, hospede.getSobrenome());
            pstm.setDate(3, hospede.getData_nascimento());
            pstm.setString(4, hospede.getNacionalidade());
            pstm.setString(5, hospede.getTelefone());
            pstm.setInt(6, hospede.getCod_reserva());

            pstm.executeUpdate();

            try (ResultSet resultSet = pstm.getGeneratedKeys()) {

                while (resultSet.next()) {
                    System.out.println("O id criado foi o " + resultSet.getInt(1));
                    status = true;
                }

            } catch (SQLException e) {
                System.out.println(e);
            }


        } catch (SQLException e) {
            System.out.println(e);
        }

        return status;
    }


    public static void main(String[] args) {
        HospedeDao hospedeDao = new HospedeDao(new ConnectionFactory().conexao());

        Hospede hospede = new Hospede("Gabriel", "Ribeiro", Date.valueOf("2004-01-01"), "Brasileiro", "31123456789", 1);
        System.out.println(hospedeDao.salvar(hospede));

    }
}
