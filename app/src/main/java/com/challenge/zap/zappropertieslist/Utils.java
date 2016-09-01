package com.challenge.zap.zappropertieslist;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

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
}
