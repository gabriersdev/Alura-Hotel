package views.utilitarios;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.sql.Date;

public class Converte {
    public static String[] converterDataParaStrings(Date data) {
        return data.toString().split("-");
    }

    public static String converterJTextFieldParaString(JDateChooser data) {
        return ((JTextField) data.getDateEditor().getUiComponent()).getText();
    }
}
