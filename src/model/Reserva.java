package model;

import java.math.BigDecimal;
import java.sql.Date;

public class Reserva {

    private Integer id;
    private Date dataEntrada;
    private Date dataSaida;
    private String valor;
    //private Sting valor;
    private String formaPagamento;

    public Reserva(){

    }

    public Reserva(Date dataEntrada, Date dataSaida, String valor, String formaPagamento) {
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
    }

    public Reserva(Integer id, Date dataEntrada, Date dataSaida, String valor, String formaPagamento) {
        this.id = id;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Integer getId() {
        return this.id;
    }

    public Date getDataEntrada() {
        return this.dataEntrada;
    }

    public Date getDataSaida() {
        return this.dataSaida;
    }

    public String getValor() {
        return this.valor;
    }

    public String getFormaPagamento() {
        return this.formaPagamento;
    }
}
