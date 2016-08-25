/**
 * this class converts any number to Sinhala text
 */
package com.Lire_tokenizer;

/**
 *
 * @author Shanaka
 */
public class numToText {
    //pattern variables
    private final String number;
    private final String dateYMD;
    private final String dateDMY;
    //private final String (the other date type)
    private final String decimal;
    private final String percentage;
    private final String expression;

    public numToText() {
        this.number = "[0-9]+";
        this.dateYMD = "[0-9]{2,4}(/|-)(0[1-9]|1[0-2]|[1-9])(/|-)(0[1-9]|(1|2)[0-9]|3[0-1]|[1-9])";
        this.dateDMY = "";
        this.decimal = "";
        this.expression = "";
        this.percentage = "";
    }
}
