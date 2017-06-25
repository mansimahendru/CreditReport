package com.creditreport;

import com.creditreport.model.CreditReport;
import com.creditreport.model.TradeLine;
import com.creditreport.service.CRExpenseCalculatorService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

/**
 * Created by mamahendru on 6/23/17.
 * This is main class to obtain credit report in json format.
 */
public class CreditCalculator {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = null;
        try {
            File file = new File(args[0]);
            reader = new BufferedReader(new FileReader(file));
            String str = "";
            CreditReport cr = new CreditReport();
            while ((str = reader.readLine()) != null) {
                TradeLine tradeLine = TradeLine.getTradeLine(str);
                if(tradeLine != null)
                    cr.addTradLine(tradeLine);
            }
            CRExpenseCalculatorService.calculateExpense(cr);
            System.out.println(CreditReport.getJson(cr));
            System.out.println(cr.toJson());
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally{
            if(reader != null)
                reader.close();
        }
    }
}
