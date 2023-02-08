package br.com.hotelAlura.dao;

import br.com.hotelAlura.model.Hospede;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospedeDao {

    private Connection connection;

    public HospedeDao(Connection connection) {
        this.connection = connection;

        try {
            this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //Método cria um Hóspede no Banco de Dados e retorna uma referência para um Hóspede com ID e tudo.
    public Hospede salvarTeste(Hospede hospede) {

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
                    hospedeCriado = this.listar(resultSet.getInt(1));
                }

            } catch (SQLException e) {
                System.out.println(e);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return hospedeCriado;
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
                    status = true;
                    this.connection.commit();
                }

            } catch (SQLException e) {
                System.out.println(e);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return status;
    }

    public List<Hospede> listar() throws SQLException {

        List<Hospede> hospedes = new ArrayList<>();
        String sql = "SELECT id_hospede, nome_hospede, sobrenome_hospede, data_nascimento_hospede, nacionalidade_hospede, telefone_hospede, cod_reserva_hospede FROM hospedes";

        try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {

            pstm.execute();

            ResultSet resultSet = pstm.getResultSet();

            while (resultSet.next()) {
                hospedes.add(
                        new Hospede(
                                resultSet.getInt("id_hospede"),
                                resultSet.getString("nome_hospede"),
                                resultSet.getString("sobrenome_hospede"),
                                resultSet.getDate("data_nascimento_hospede"),
                                resultSet.getString("nacionalidade_hospede"),
                                resultSet.getString("telefone_hospede"),
                                resultSet.getInt("cod_reserva_hospede")
                        )
                );

                this.connection.commit();
            }

        } catch (SQLException e) {
            this.connection.rollback();
            System.out.println(e);
        }

        return hospedes;
    }

    public Hospede listar(Integer id) throws SQLException {

        Hospede hospedeCriado = new Hospede();
        String sql = "SELECT id_hospede, nome_hospede, sobrenome_hospede, data_nascimento_hospede, nacionalidade_hospede, telefone_hospede, cod_reserva_hospede FROM hospedes WHERE id_hospede = ?";

        try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {

            pstm.setInt(1, id);
            pstm.execute();

            ResultSet resultSet = pstm.getResultSet();

            while (resultSet.next()) {
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

        } catch (SQLException e) {
            this.connection.rollback();
            System.out.println(e);
        }

        return hospedeCriado;
    }

    public Boolean alterar(Hospede hospede) throws SQLException {

        Boolean status = false;
        String sql = "UPDATE hospedes SET nome_hospede = ?, sobrenome_hospede = ?, data_nascimento_hospede = ?, nacionalidade_hospede = ?, telefone_hospede = ?, cod_reserva_hospede = ? WHERE id_hospede = ?";

        try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {

            pstm.setString(1, hospede.getNome());
            pstm.setString(2, hospede.getSobrenome());
            pstm.setDate(3, hospede.getData_nascimento());
            pstm.setString(4, hospede.getNacionalidade());
            pstm.setString(5, hospede.getTelefone());
            pstm.setInt(6, hospede.getCod_reserva());
            pstm.setInt(7, hospede.getId());

            pstm.executeUpdate();

            Integer linhasAlteradas = pstm.getUpdateCount();
            if (linhasAlteradas > 0) {
                status = true;
                this.connection.commit();
            }

        } catch (SQLException e) {
            this.connection.rollback();
            System.out.println(e);
        }

        return status;
    }

    public Boolean deletar(Hospede hospede) throws SQLException {
        Boolean status = false;
        String sql = "DELETE FROM hospedes WHERE id_hospede = ?";

        try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
            pstm.setInt(1, hospede.getId());
            pstm.execute();

            Integer linhasAlteradas = pstm.getUpdateCount();
            System.out.println(linhasAlteradas);
            if (linhasAlteradas > 0) {
                status = true;
                this.connection.commit();
            }

        } catch (SQLException e) {
            this.connection.rollback();
            System.out.println(e);
        }

        return status;
    }

    public List<Hospede> pesquisar(String string) throws SQLException {
        List<Hospede> hospedes = new ArrayList<>();
        String sql = """
                SELECT 
                id_hospede, 
                nome_hospede, 
                sobrenome_hospede, 
                data_nascimento_hospede, 
                nacionalidade_hospede, 
                telefone_hospede, 
                cod_reserva_hospede 
                FROM hospedes 
                WHERE 
                id_hospede = ? 
                OR nome_hospede LIKE ? 
                OR sobrenome_hospede LIKE ? 
                OR data_nascimento_hospede LIKE ? 
                OR nacionalidade_hospede LIKE ? 
                OR telefone_hospede LIKE ?
                OR cod_reserva_hospede LIKE ?;
                """;

        try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
            pstm.setString(1, "%" + string + "%");
            pstm.setString(2, "%" + string + "%");
            pstm.setString(3, "%" + string + "%");
            pstm.setString(4, "%" + string + "%");
            pstm.setString(5, "%" + string + "%");
            pstm.setString(6, "%" + string + "%");
            pstm.setString(7, "%" + string + "%");

            pstm.execute();

            try (ResultSet resultSet = pstm.getResultSet()) {
                while (resultSet.next()) {

                    hospedes.add(
                            new Hospede(
                                    resultSet.getInt("id_hospede"),
                                    resultSet.getString("nome_hospede"),
                                    resultSet.getString("sobrenome_hospede"),
                                    resultSet.getDate("data_nascimento_hospede"),
                                    resultSet.getString("nacionalidade_hospede"),
                                    resultSet.getString("telefone_hospede"),
                                    resultSet.getInt("cod_reserva_hospede")
                            ));

                }
            }

        } catch (SQLException e) {
            System.out.println("aqui");
            System.out.println(e);
        }

        return hospedes;
    }
}
