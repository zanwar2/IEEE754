package project2csc4101;
import java.lang.StringBuilder;

public class Convertor {
    public static String IEEE754(double input){
        
        StringBuilder binaryVal = new StringBuilder();
        StringBuilder workingString = new StringBuilder();
        //appending the sign value
        if(input > 0)
            binaryVal.append("0 | ");
        else
        {
            binaryVal.append("1 | ");
            input *= -1;
        }
        
        //separating the integer from the fraction
        int integer = (int)Math.floor(input);
        double fraction = input - integer;
        
        //converting the int to binary
        workingString.append(integerToBinary(integer));
        
        //adding decimal point to working String 
        workingString.append(".");
        
        //converting the fraction to a binary
        workingString.append(fractionToBinary(fraction));
        String temp = workingString.toString();
        
        //converting string binary to a double value
        double binary = Double.parseDouble(temp);
        
        
        //finding shift value for exponent
        int shift = 0;
        if(binary > 1) {
            while(binary > 10) {
                binary /= 10;
                shift++;
            }
        }
        else {
            while(binary < 1) {
                binary *= 10;
                shift--;
            }
        }
        
        //Finding set bias value in binary and adding it to final string
        int setBias = 127 + shift;
        StringBuilder setBiasString = new StringBuilder();
        setBiasString.append(integerToBinary(setBias));
        while(setBiasString.length() < 8)
        {
            setBiasString.insert(0,0);
        }
        binaryVal.append(setBiasString);
        binaryVal.append(" | ");
        
        //removing the decimal point
        for(int i=0; i<workingString.length(); i++) {
            if(workingString.charAt(i) == '.') {
                workingString.deleteCharAt(i);
            }
        }
        
        //Removes header 0's in the mantissa
        while(workingString.charAt(0) == '0')
        {
            workingString.deleteCharAt(0);
        }
        
        //deletes the value before the decimal
        workingString.deleteCharAt(0);
        
        //appends the mantissa to the final string
        for(int i=0; i<workingString.length(); i++) {            
            if(i<=22) {
                binaryVal.append(workingString.charAt(i));
            }
        }
        
        return binaryVal.toString();
    }
    
    //convert fraction to binary and return the stringbuilder
    public static StringBuilder fractionToBinary(double val)
    {
        StringBuilder s = new StringBuilder();
        while(val > 0){
            int temp;
            val *=2;
            temp = (int) Math.floor(val);
            s.append(temp);
            val -= (double) temp;
        }
        return s;
    }    
    
    //convert integer to binary and return the stringbuilder
    public static StringBuilder integerToBinary(int val)
    {
        StringBuilder s = new StringBuilder();
        while(val >= 1)
        {
            s.insert(0,val%2);
            val /= 2;
        }
        return s;
    }
    

}
