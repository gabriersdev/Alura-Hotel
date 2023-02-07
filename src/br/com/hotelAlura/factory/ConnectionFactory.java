package br.com.hotelAlura.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory implements AutoCloseable {

    //Vai receber os dados e guardá-los
    private DataSource dataSource;

    public ConnectionFactory() {
        //Permite acessos simultâneos a Aplicação.
        //Estabelecendo conexão com o Banco de Dados
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel_alura?useTimezone=true&serverTimezone=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("");

        this.dataSource = comboPooledDataSource;
    }

    public Connection conexao() {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        try {
            this.dataSource.getConnection().close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
