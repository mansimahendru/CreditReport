package com.creditreport.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.creditreport.constants.Liability;
import com.creditreport.service.CurrencyConversionService;

/**
 * Created by mamahendru on 6/23/17.
 * This class represents a tradeline. Trade line contains date, code, subcode, monthly payment, current balance.
 * Liability is calculated from code and subcode.
 * Type is obtained from liability. This is done for easy json serialization/deserialization.
 */
public class TradeLine {
    @JsonIgnore
    private String date;
    @JsonIgnore
    private int code;
    @JsonIgnore
    private int subcode;
    @JsonProperty("type")
    private String type;
    @JsonProperty("monthly_payment")
    private long monthly_payment;
    @JsonProperty("current_balance")
    private long current_balance;
    @JsonIgnore
    private Liability li;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getSubcode() {
        return subcode;
    }

    public void setSubcode(int subcode) {
        this.subcode = subcode;
    }

    public long getMonthly_payment() {
        return monthly_payment;
    }

    public void setMonthly_payment(long monthly_payment) {
        this.monthly_payment = monthly_payment;
    }

    public long getCurrent_balance() {
        return current_balance;
    }

    public void setCurrent_balance(long current_balance) {
        this.current_balance = current_balance;
    }

    public Liability getLi() {
        return li;
    }

    public void setLi(Liability li) {
        this.li = li;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static TradeLine getTradeLine(String line) {
        TradeLine tradeLine = null;
        try {
            String[] vals = line.split(" ");
            if (vals != null && vals.length >= 5) {
                tradeLine = new TradeLine();
                // date is not doing any thing at the moment based on requirement. It is not needed in output either.
                // Just saving the date as is.
                tradeLine.date = vals[0];
                tradeLine.code = Integer.parseInt(vals[1]);
                tradeLine.subcode = Integer.parseInt(vals[2]);
                tradeLine.li = Liability.find(tradeLine.code, tradeLine.subcode);
                tradeLine.type = tradeLine.li.getType();
                tradeLine.monthly_payment = CurrencyConversionService.convertToLong(vals[3]);
                tradeLine.current_balance = CurrencyConversionService.convertToLong(vals[4]);
            }
        }
        catch(Exception ex) {
            return null;
        }
        return tradeLine;
    }
}
