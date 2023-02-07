package br.com.hotelAlura.views.utilitarios;

public class Utilitarios {
    public static boolean isEmpty(String string) {
        string = string.trim();
        return (string == null || string.length() == 0);
    }

    public static boolean isClear(String string) {
        return (string.length() == 0);
    }

    public static boolean isNull(String string) {
        return (string == null);
    }
}
