package com.creditreport.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mamahendru on 6/23/17.
 * This class represents credit report of user. It has all the values as required.
 * It contains tradelines, housing expense, non housing expense and fixed expense before education.
 * It can represent itself as json.
 */
public class CreditReport {
    @JsonProperty("fixed_expenses_before_education")
    private long fixed_expenses_before_education = 0;
    @JsonProperty("tradelines")
    private List<TradeLine> tradeLines = new ArrayList<TradeLine>();
    @JsonIgnore
    private long housing_expense = 0;
    @JsonIgnore
    private long non_housing_expense = 0;

    public void addHousingExpense(long expense) {
        this.housing_expense = this.housing_expense + expense;
    }

    public void addNonHousingExpense(long expense) {
        this.non_housing_expense = this.non_housing_expense + expense;
    }

    public void addTradLine(TradeLine tradeLine) {
        this.tradeLines.add(tradeLine);
    }

    public List<TradeLine> getTradeLines() {
        return tradeLines;
    }

    public void setTradeLines(List<TradeLine> tradeLines) {
        this.tradeLines = tradeLines;
    }

    public long getHousing_expense() {
        return housing_expense;
    }

    public void setHousing_expense(long housing_expense) {
        this.housing_expense = housing_expense;
    }

    public long getNon_housing_expense() {
        return non_housing_expense;
    }

    public void setNon_housing_expense(long non_housing_expense) {
        this.non_housing_expense = non_housing_expense;
    }

    public long getFixed_expenses_before_education() {
        return fixed_expenses_before_education;
    }

    public void setFixed_expenses_before_education(long fixed_expenses_before_education) {
        this.fixed_expenses_before_education = fixed_expenses_before_education;
    }

    public static String getJson(CreditReport cr) throws Exception{
        String crJson = null;
        ObjectMapper mapper = new ObjectMapper();
        crJson = mapper.writeValueAsString(cr);
        return crJson;
    }
}
