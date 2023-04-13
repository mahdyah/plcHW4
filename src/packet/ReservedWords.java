package packet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ReservedWords {

    LEXEME(new HashMap<String, String>() {{
        put( "MULT_OP","*");
        put("ADD_OP", "+");
        put("SUB_OP","-" );
        put("MODULO", "%");
        put("LEFT_PAREN", "(");
        put("RIGHT_PAREN",")");
        put( "LEFT_CURLY","{");
        put("RIGHT_CURLY", "}");
        put("ASSIGN_OP","=");
        put( "EQUAL","==");
        put("LESS_THAN","<");
        put( "GREATER_THAN",">");
        put("LESS_EQUAL","<=" );
        put( "GREATER_EQUAL",">=");
        put("LOGICAL_AND","&&");
        put ("LOGICAL_OR","||");
        put ("WHILE_CODE","while");
        put ("IF_CODE","if");
        put ("ELSE_CODE","else");
        put ("SEMICOLON",";");
    }});
    private final HashMap<String, String> rws;

    ReservedWords(HashMap<String, String> rws) {
        this.rws = rws;

    }

//    ID(""),
//    INT_LIT(""),
//    FP_LIT(""),
    public HashMap<String, String> getLexeme(){
        return rws;
    }

    public  boolean INT_LIT(String number) {
        String numberRegex = "[0-9]|[1-9][0-9]*|[0-7]+|(0[xX][0-9a-fA-F]+)(i64|I64|u|U|l|L)?";
        Pattern pattern = Pattern.compile(numberRegex);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

}
