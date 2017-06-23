package com.creditreport.service;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by mamahendru on 6/23/17.
 * This class is added to convert the amout to format that is required by the expense calculator service.
 */
public class CurrencyConversionService {
    public static long convertToLong(String amount) throws Exception {
        int sum = 0;
        int decimal = -1;
        if(amount != null && !amount.trim().equals("")){
            amount = amount.trim();
            char[] chars = amount.toCharArray();
            for(char c : chars) {
                if(Character.isDigit(c)) {
                    if(decimal >= 0)
                        decimal++;
                    else if (decimal > 2)
                        break;
                    sum = sum * 10 + Character.getNumericValue(c);
                } else if(c == '$') {
                    continue;
                } else if(c == '.'){
                    decimal++;
                    continue;
                } else if(c == ','){
                    continue;
                }
                else {
                    throw new Exception("Invalid Currency");
                }
            }
        }
        return sum;
    }
}
