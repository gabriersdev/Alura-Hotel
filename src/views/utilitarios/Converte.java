package views.utilitarios;

import java.sql.Date;

public class Converte {
    public static String[] converterDataParaStrings(Date data){
        return data.toString().split("-");
    }
}
