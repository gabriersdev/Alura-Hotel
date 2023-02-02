package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {
    public static void main(String[] args) {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        try(Connection connection = connectionFactory.conexao()){
            System.out.println("Fechando conexão");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
