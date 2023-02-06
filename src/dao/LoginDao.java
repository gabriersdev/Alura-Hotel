package dao;

import factory.ConnectionFactory;
import model.Login;
import model.Reserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.PropertyPermission;

public class LoginDao {

    private Connection connection;

    public LoginDao(Connection connection) throws SQLException {
        this.connection = connection;
        this.connection.setAutoCommit(false);
    }

    public Boolean acessar(Login login){
        Boolean status = false;

        String sql = "SELECT id_usuario FROM usuarios WHERE nome_usuario = ? AND senha_usuario = ?";

        try(PreparedStatement pstm = this.connection.prepareStatement(sql)){
            pstm.setString(1, login.getNome());
            pstm.setString(2, login.getSenha());

            pstm.execute();

            try(ResultSet resultSet = pstm.getResultSet()){
                while (resultSet.next()){
                    if(resultSet.getInt("id_usuario") != 0){
                        status = true;
                    }
                }
            }catch (SQLException e){
                System.out.println(e);
            }

        }catch (SQLException e){
            System.out.println(e);
        }

        return status;
    }

    public static void main(String[] args) throws SQLException{
        LoginDao loginDao = new LoginDao(new ConnectionFactory().conexao());
        Boolean sucesso = loginDao.acessar(new Login("admin", "admin"));
        System.out.println(sucesso);
    }
}
