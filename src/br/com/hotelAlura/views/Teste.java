package br.com.hotelAlura.views;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Teste {
    public static void main(String[] args) {

        LocalDate inicio = LocalDate.of(2023, 01, 01);
        LocalDate fim = LocalDate.of(2023, 02, 02);

        long days = ChronoUnit.DAYS.between(inicio, fim);
        System.out.println(days);
    }
}
