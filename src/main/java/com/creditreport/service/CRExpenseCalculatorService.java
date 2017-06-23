package com.creditreport.service;

import com.creditreport.model.CreditReport;
import com.creditreport.model.TradeLine;

import java.util.List;

/**
 * Created by mamahendru on 6/23/17.
 * This class calculates housing expense, non housing expense and fixed expense before education.
 * Expenses are calculated based on liability in tradeline.
 * Expense is added only if current balance is > 0.
 * Mortgage liability goes in housing expense.
 * Credit card and all other goes towards non housing expense.
 * Student loan is not counted in either of two expenses.
 * fixed expense is sum of housing expense and non housing expense.
 */
public class CRExpenseCalculatorService {
    public static void calculateExpense(CreditReport cr) {
        for(TradeLine line : cr.getTradeLines()) {
            if(line.getCurrent_balance() > 0) {
                switch (line.getLi()) {
                    case MORTGAGE:
                        cr.addHousingExpense(line.getMonthly_payment());
                        break;
                    case CREDITCARD:
                        cr.addNonHousingExpense(line.getMonthly_payment());
                        break;
                    case STUDENTLOAN:
                        break;
                    case OTHER:
                        cr.addNonHousingExpense(line.getMonthly_payment());
                        break;
                    default:
                        break;
                }
            }
            if(cr.getHousing_expense() <= 0)
                cr.setHousing_expense(1061);

            cr.setFixed_expenses_before_education(cr.getHousing_expense() + cr.getNon_housing_expense());
        }
    }
}
