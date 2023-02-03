package dao;

import factory.ConnectionFactory;
import model.Hospede;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospedeDao {

    private Connection connection;

    public HospedeDao(Connection connection) {
        this.connection = connection;

        try{
            this.connection.setAutoCommit(false);
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    //Método cria um Hóspede no Banco de Dados e retorna uma referência para um Hóspede com ID e tudo.
    public Hospede salvar(Hospede hospede) {

        Hospede hospedeCriado = new Hospede();
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
                    hospedeCriado = this.listarHospede(resultSet.getInt(1));
                }

            } catch (SQLException e) {
                System.out.println(e);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return hospedeCriado;
    }

    public Hospede listarHospede(Integer id){

        Hospede hospedeCriado = new Hospede();
        String sql = "SELECT id_hospede, nome_hospede, sobrenome_hospede, data_nascimento_hospede, nacionalidade_hospede, telefone_hospede, cod_reserva_hospede FROM hospedes WHERE id_hospede = ?";

        try (PreparedStatement pstm = this.connection.prepareStatement(sql)){

            pstm.setInt(1, id);
            pstm.execute();

            ResultSet resultSet = pstm.getResultSet();

            while (resultSet.next()){
                hospedeCriado = new Hospede(
                        resultSet.getInt("id_hospede"),
                        resultSet.getString("nome_hospede"),
                        resultSet.getString("sobrenome_hospede"),
                        resultSet.getDate("data_nascimento_hospede"),
                        resultSet.getString("nacionalidade_hospede"),
                        resultSet.getString("telefone_hospede"),
                        resultSet.getInt("cod_reserva_hospede")
                );

                this.connection.commit();
            }

        }catch (SQLException e){
            System.out.println(e);
        }

        return hospedeCriado;
    }

    public Boolean alterar(Hospede hospede) {

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
        Hospede hospede1 = hospedeDao.salvar(hospede);

        System.out.println(hospede1.getId());

    }
}
