package com.challenge.zap.zappropertieslist;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by eliete on 8/31/16.
 */
public class Utils {

    private static final Locale BRAZIL = new Locale("pt","BR");
    private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
    public static final DecimalFormat MONEY = new DecimalFormat("¤ ###,###,##0.00",REAL);

    public static String moneyMask(int valor, DecimalFormat moeda){
        return moeda.format(valor);
    }

    public static void cleanErrorMessage(EditText editText, final TextInputLayout textInputLayout){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0)
                    textInputLayout.setError(null);
            }
        });

    }

    public static boolean validateText(String string){
        Pattern text = Pattern.compile("^[a-zA-Zà-üÀ-Ú 0123456789%$#*@#!?:]+$");
        return text.matcher(string).matches();
    }

    public static void setEmptyMessage(TextInputLayout textInputLayout, String errorMessage){
        textInputLayout.setError(errorMessage + " é obrigatório");
    }

    public static String formatDate(String dtUpdate){
        String tempDate = dtUpdate.replaceAll("\\D+", "");
        Date date = new Date(Long.parseLong(tempDate));
        String dateAsText = new SimpleDateFormat("dd/MM/yyyy")
                .format(date);
        return dateAsText;
    }
}
