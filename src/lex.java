import java.io.File;
import java.util.HashMap;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map.Entry;
public class lex {
    public static void main(String[] args) {

        HashMap<String, Integer> token = new HashMap<String,Integer>();
        token.put("+",1);
        token.put("-",2);
        token.put("*",3);
        token.put("/",4);
        token.put("%",5);
        token.put("(",6);
        token.put(")",7);
        token.put(":=",8);
        token.put("=",9);
        token.put("<",10);
        token.put(">",11);
        token.put("<=",12);
        token.put(">=",13);
        token.put("&",14);
        token.put("|",15);
        token.put("{",23);
        token.put("}",24);
//        the followings are done down below
        // token.put("IDENT",16);
        // token.put("INT_LIT",17);
        // token.put("FP_LIT",18);
//        token.put("if",19);
//        token.put("while",20);
//        token.put("else",21);
//        token.put(";",22);



        HashMap<String, Integer> name = new HashMap<String,Integer>();
        name.put("ADD_OP",1);
        name.put("SUB_OP",2);
        name.put("MULT_OP",3);
        name.put("DIV_OP",4);
        name.put("MODULO",5);
        name.put("LEFT_PAREN",6);
        name.put("RIGHT_PAREN",7);
        name.put("ASSIGN_OP",8);
        name.put("EQUAL",9);
        name.put("LESS_THAN",10);
        name.put("GREATER_THAN",11);
        name.put("LESS_EQUAL",12);
        name.put("GREATER_EQUAL",13);
        name.put("LOGICAL_AND",14);
        name.put("LOGICAL_OR",15);
        name.put("IDENT",16);
        name.put("INT_LIT",17);
        name.put("FP_LIT",18);
        name.put("IF_CODE",19);
        name.put("WHILE_CODE",20);
        name.put("ELSE_CODE",21);
        name.put("SEMI_COLON",22);
        name.put("LEFT_CURLY",23);
        name.put("RIGHT_CURLY",24);
        LinkedList<Integer> list = new LinkedList<Integer>();
        LinkedList<String> reservedKeywords = new LinkedList<String>();
        reservedKeywords.add("IF_CODE");
        reservedKeywords.add("WHILE_CODE");
        reservedKeywords.add("ELSE_CODE");
        reservedKeywords.add("SEMI_COLON");



        File file=new File("file.txt");     //Creation of File Descriptor for input file

        try {
            Scanner scanner = new Scanner(file).useDelimiter(" ");;
            while (scanner.hasNextLine()) {
                String string = scanner.next();
                if(token.containsKey(string)){
                    list.addLast(token.get(string));
                }else{
                    if (!reservedKeywords.contains(string)){
                        if (ID(string)){
                     list.addLast(16);
                    }else {
                            if (string=="while"){
                                list.addLast(20);
                            } else if (string=="if") {
                                list.addLast(19);
                            } else if (string=="else") {
                                list.addLast(21);
                            }
                            else if (string==";") {
                                list.addLast(22);
                            }
                        }
                      }else if (isIntegerLiteral(string)){
                        list.addLast(17);
                    }else if(isFloatingPointLiteral(string)){
                        list.addLast(18);
                    }

                }}

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // String theName=" ";
        for (Integer tokenNum : list) {
            if (name.containsValue(tokenNum)){

                System.out.println(getKey(name,tokenNum) + " => "+ tokenNum);
            }

        }

    }


    public static String getKey( HashMap<String, Integer> name, Integer tokenNum ) {
        String theKey="";
        for(Entry<String, Integer> entry: name.entrySet()) {

            if(entry.getValue() == tokenNum) {
                theKey= entry.getKey();
            }
        }
        return theKey;
    }
    public static boolean isIntegerLiteral(String number) {
        String numberRegex = "[0-9]|[1-9][0-9]*|[0-7]+|(0[xX][0-9a-fA-F]+)(i64|I64|u|U|l|L)?";
        Pattern pattern = Pattern.compile(numberRegex);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
    public static boolean isFloatingPointLiteral(String number) {
        String numberRegex = "[+-]?([0-9]*.[0-9]+|[0-9]+.)([eE][+-]?[0-9]+)?[LlFf]?|[+-]?[0-9]+[Ee][+-]?[0-9]+[LlFf]?";
        Pattern pattern = Pattern.compile(numberRegex);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
    public static boolean ID(String number) {
        String numberRegex = "[_]?([a-zA-Z]+[_]?[a-zA-Z]*)";
        Pattern pattern = Pattern.compile(numberRegex);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
}
