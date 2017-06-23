package com.creditreport.constants;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by mamahendru on 6/23/17.
 * This enum represents liability of user. Liabilities are currently mortgage, creditcard, student loan and other.
 * It takes type, code and list of subcodes.
 * It decides liability based on code and subcode.
 * Creditcard liability is also called other at this time as per requirement. It is easy to change it to creditcard by just changing the value of type.
 */
public enum Liability {
    MORTGAGE("mortgage" ,10, 12, 15),
    CREDITCARD("other", 12, 5),
    STUDENTLOAN("education", 5, 1),
    OTHER("other", 0, 0);

    private String type;
    private int code;
    private Set<Integer> subcode = new HashSet<Integer>();

    Liability(String type, int code, int ...subcodes) {
        this.type = type;
        this.code = code;
        for(int s : subcodes)
            this.subcode.add(s);
    }

    public static Liability find(int code, int subcode) {
        for(Liability li : Liability.values()){
            if(li.code == code && li.subcode.contains(subcode)){
                return li;
            }
        }
        return OTHER;
    }

    public String getType() {
        return this.type;
    }

}
