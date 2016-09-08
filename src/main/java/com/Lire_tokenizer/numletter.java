/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Lire_tokenizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Shanaka
 */
public class numletter {
    public Map<Integer, String> tens = new HashMap<>();
    public Map<Integer, String> tensPart = new HashMap<>();
    public Map<Integer, String> tenPrefix = new HashMap<>();
    public Map<Integer, String> hundredsPart = new HashMap<>();
    public Map<Integer, String> hundredsPrefix = new HashMap<>();
    
    public Map<Integer, List<Integer>> letters = new HashMap<>();
        
    public numletter(){
        /*
        tens.put(0, "binduwa");
        tens.put(1, "eka");
        tens.put(2, "deka");
        tens.put(3, "thuna");
        tens.put(4, "hathara");
        tens.put(5, "paha");
        tens.put(6, "haya");
        tens.put(7, "hatha");
        tens.put(8, "ata");
        tens.put(9, "nawaya");
        tens.put(10, "dahaya");
        tens.put(11, "ekolaha");
        tens.put(12, "dolaha");
        tens.put(13, "dahathuna");
        tens.put(14, "dahahathara");
        tens.put(15, "pahalawa");
        tens.put(16, "dahasaya");
        tens.put(17, "dahahatha");
        tens.put(18, "dahaata");
        tens.put(19, "dahanawaya");
        tens.put(20, "wissa");
        tens.put(30, "thiha");
        tens.put(40, "hathaliha");
        tens.put(50, "panaha");
        tens.put(60, "heta");
        tens.put(70, "haththawa");
        tens.put(80, "asuuwa");
        tens.put(90, "anuuwa");
        tens.put(100, "ekaseeya");
        tens.put(1000, "ekdaaha");
        tens.put(100000, "eklakshaya");
        tens.put(1000000, "ekmiliyanya");
        tens.put(10000000, "ekbiliyana"); */

        tens.put(0, "බින්දුව");
        tens.put(1, "එක");
        tens.put(2, "දෙක");
        tens.put(3, "තුන");
        tens.put(4, "හතර");
        tens.put(5, "පහ");
        tens.put(6, "හය");
        tens.put(7, "හත");
        tens.put(8, "අට");
        tens.put(9, "නවය");
        tens.put(10, "දහය");
        tens.put(11, "එකොළහ");
        tens.put(12, "දොළහ");
        tens.put(13, "දහතුන");
        tens.put(14, "දහහතර");
        tens.put(15, "පහලව");
        tens.put(16, "දහසය");
        tens.put(17, "දහහත");
        tens.put(18, "දහඅට ");
        tens.put(19, "දහනවය");
        tens.put(20, "විස්ස");
        tens.put(21, "තිහ");
        tens.put(22, "හතලිහ");
        tens.put(23, "පනහ");
        tens.put(24, "හැට");
        tens.put(25, "හැත්තෑව");
        tens.put(26, "අසූව");
        tens.put(27, "අනූව");
        tens.put(100, "එකසීය");
        tens.put(1000, "එක්දහස");
        tens.put(100000, "එක්ලක්ෂය");
        tens.put(1000000, "එක්මිලියනය");
        tens.put(10000000, "එක්බිලියනය");

        /*
        tensPart.put(0, "");
        tensPart.put(1, "eka");
        tensPart.put(2, "de");
        tensPart.put(3, "thun");
        tensPart.put(4, "haara");
        tensPart.put(5, "pan");
        tensPart.put(6, "haya");
        tensPart.put(7, "hath");
        tensPart.put(8, "ata");
        tensPart.put(9, "nawa");
        tensPart.put(10, "dasa");
        tensPart.put(11, "ekolos");
        tensPart.put(12, "dolos");
        tensPart.put(13, "dahathun");
        tensPart.put(14, "dahahathara");
        tensPart.put(15, "pahalos");
        tensPart.put(16, "dahasaya");
        tensPart.put(17, "dahahath");
        tensPart.put(18, "dahaata");
        tensPart.put(19, "dahanawa"); */

        tensPart.put(0, "");
        tensPart.put(1, "එක");
        tensPart.put(2, "දෙ");
        tensPart.put(3, "තුන්");
        tensPart.put(4, "හාර");
        tensPart.put(5, "පන්");
        tensPart.put(6, "හය");
        tensPart.put(7, "හත්");
        tensPart.put(8, "අට");
        tensPart.put(9, "නව");
        tensPart.put(10, "දස");
        tensPart.put(11, "එකොලොස්");
        tensPart.put(12, "දොළොස්");
        tensPart.put(13, "දහතුන්");
        tensPart.put(14, "දහහතර");
        tensPart.put(15, "පහළොස්");
        tensPart.put(16, "දහසය");
        tensPart.put(17, "දහහත්");
        tensPart.put(18, "දහඅට");
        tensPart.put(19, "දහනව");

        /*
        tenPrefix.put(2, "wisi");
        tenPrefix.put(3, "this");
        tenPrefix.put(4, "hathalis");
        tenPrefix.put(5, "panas");
        tenPrefix.put(6, "heta");
        tenPrefix.put(7, "haththa");
        tenPrefix.put(8, "asuu");
        tenPrefix.put(9, "anuu"); */
        

        tenPrefix.put(2, "විසි");
        tenPrefix.put(3, "තිස්");
        tenPrefix.put(4, "හතලිස්");
        tenPrefix.put(5, "පනස්");
        tenPrefix.put(6, "හැට");
        tenPrefix.put(7, "හැත්තෑ");
        tenPrefix.put(8, "අසූ");
        tenPrefix.put(9, "අනූ");
        
        hundredsPart.put(1, "");
        hundredsPart.put(2, "සීය");
        hundredsPart.put(3, "දහස");
        hundredsPart.put(5, "ලක්ෂය");
        hundredsPart.put(6, "මිලියනය");
        hundredsPart.put(7, "බිලියනය");
        
        hundredsPrefix.put(1, "");
        hundredsPrefix.put(2, "සිය");
        hundredsPrefix.put(3, "දහස්");
        hundredsPrefix.put(5, "ලක්ෂ");
        hundredsPrefix.put(6, "මිලියන");
        hundredsPrefix.put(7, "බිලියන");
        
        
        
    }
    
    public String numberToText(String line){
        System.out.println("detecting numbers");
        //regular expression patterns to match
        String dateYMD = "[0-9]{2,4}(/|-)(0[1-9]|1[0-2]|[1-9])(/|-)(0[1-9]|(1|2)[0-9]|3[0-1]|[1-9])";
        String number = "([0-9]+\\.[0-9]+|[0-9]+)";
        String percentage = "([0-9]+\\.[0-9]+|[0-9]+)%";

        Matcher m;
        List<String> parts = new ArrayList<>();
        
        Pattern pDate = Pattern.compile(dateYMD);
        Pattern pNumber = Pattern.compile(number);
        Pattern pPercentage = Pattern.compile(percentage);
        
        //percentage
        m = pPercentage.matcher(line);
        while(m.find()) {            
            parts.add(line.substring(m.start(), m.end()));
            System.out.println("found percentage: "+line.substring(m.start(), m.end()));
        }
        for(String part : parts){
            line = line.replace(part, identifyPercentage(part));
        }
        parts.clear();
        
        //date
        m = pDate.matcher(line);
        while(m.find()) {            
            parts.add(line.substring(m.start(), m.end()));
            System.out.println(line.substring(m.start(), m.end()));
        }
        for(String part : parts){
            line = line.replace(part, identifyDate(part));
        }
        parts.clear();
        
        //number -- should be at the end
        m = pNumber.matcher(line);
        while(m.find()) {            
            parts.add(line.substring(m.start(), m.end()));
            System.out.println(line.substring(m.start(), m.end()));
        }
        for(String part : parts){
            line = line.replace(part, identifyNumber(part));
        }
        parts.clear();
        System.out.println(line);
        return line;
    }
    
    
    
    private String identifyNumber(String num){
        String[] parts = num.split("\\.");
        String word = "";
        String decimal = "";
        int first = Integer.parseInt(parts[0]);
        if(parts.length > 1)
            decimal = getDecimal(parts[1]);
        
        int places;
        int divider;
        boolean run = true;

        //10000
        //99001
        while(run){
            places = String.valueOf(first).length()-1;
            if(first >= 10000 && first <= 99999)
                places --;
            divider = (int) Math.pow(10, places);
            if(first == divider){
                word += tens.get(first);
                run = false;
            } else if(first >= 1 && first <=19){
                word += tens.get(first);
                run = false;
            } else if(first % divider == 0){
                if(first >=20 && first <=90){
                    word += tens.get(first);
                    run = false;
                }
                else{
                    if((first/divider) >= 20 && (first/divider) <= 99){
                        int get = first/divider;
                        word += tenPrefix.get((int)(get/10));
                        word += tensPart.get(get%10);
                    } else 
                        word += tensPart.get(first/divider);
                    word += hundredsPart.get(places);
                    run = false;
                }
            } else if((first % divider != 0) && (first >= divider)){
                if(first >=20 && first <=99){
                    word += tenPrefix.get((int)first/10);
                    word += tens.get(first%10);
                    run = false;
                } else {
                    if((int)(first/divider) >= 20 && (int)(first/divider) <= 99){
                        int get = (int)(first/divider);
                        word += tenPrefix.get((int)(get/10));
                        word += tensPart.get(get%10);
                    } else 
                        word += tensPart.get((int)(first/divider));
                    
                    word += hundredsPrefix.get(places);
                    first = first % divider; 
                }
            }
        }
        System.out.println(word);
        return word+" "+decimal;
    }
    
    
    private String getDecimal(String num){
        if("".equals(num))
            return "";
        
        String decimal = "dashama ";
        for(int i=0; i<num.length(); i++){
            System.out.println(Character.getNumericValue(num.charAt(i)));
            decimal += tens.get(Character.getNumericValue(num.charAt(i)));
            decimal += "යි";
        }
        return decimal;
    }
     
    
    
    private String identifyDate(String num){
        //num = num.replace("/", "-");
        String parts[] = num.split("[-|/]");
        String year = identifyNumber(parts[0]);
        String month = "";
        year += "යි";
        switch(Integer.parseInt(parts[1])){
            case 1	:month = "ජනවාරි "; break;
            case 2	:month = "පෙබරවාරි"; break;
            case 3	:month = "මාර්තු"; break;
            case 4	:month = "අප්‍රේල්"; break;
            case 5	:month = "මැයි"; break;
            case 6	:month = "ජුනි"; break;
            case 7	:month = "ජුලි"; break;
            case 8	:month = "අගෝස්තු"; break;
            case 9	:month = "සැප්තැම්බර්"; break;
            case 10	:month = "ඔක්තෝබර්"; break;
            case 11	:month = "නොවැම්බර්"; break;
            case 12	:month = "දෙසැම්බර්"; break;
            default     :month = "";
        }
        String day = identifyNumber(parts[2]);
        return year+" "+month+" "+day;
    }
    
    
    
    private String identifyPercentage(String num){
        String number = "සියයට";
        number += identifyNumber(num.replace("%", ""));
        return number;
    }
}
