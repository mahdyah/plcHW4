package packet;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    String nextChar;
    static String nextToken;
    ArrayList<Token>tokens;
    Lexer(String nextToken){
        this.nextToken=nextToken;
        this.tokens=new ArrayList<Token>();
    }
public static String lex() throws FileNotFoundException {
    String filePath = "file.txt";

    File file = new File("file.txt");     //Creation of File Descriptor for input file


    Scanner scanner = new Scanner(file).useDelimiter(" ");
    ;
    while (scanner.hasNextLine()) {
        String nextToken = scanner.next();
    }
    return nextToken;
}




    // <EXPR> --> <TERM> {(`+`|`-`) <TERM>}
    public void EXPR() throws FileNotFoundException {
        TERM();
        if( nextToken == "+" ||nextToken=="-"){
            TERM();
        }else{
            error();
        }
    }

    // <TERM> --> <FACT> {(`*`|`/`|`%`) <FACT>}
    public void TERM() throws FileNotFoundException {
        FACT();
        if( nextToken == "*" ||nextToken=="/" ||nextToken=="%"){
            FACT();
        }else{
            error();
        }
    }


    // <FACT> --> ID | INT_LIT | FLOAT_LIT | `(` <EXPR> `)`
    public void FACT() throws FileNotFoundException {
        if (nextToken == ((ID(nextToken))?nextToken:null ) ||nextToken==((INT_LIT(nextToken))?nextToken:null ) || nextToken== ((FP_LIT(nextToken))?nextToken:null )) {
            lex();
        }else if (nextToken == "("){
            EXPR();
            if(nextToken==")"){
                return;
            }else{
                error();
            }

        }
    }

    // <BTERM> --> <BAND> {(`==`|`!=`) <BAND>}
    public void BTERM() throws FileNotFoundException {
        BAND();
        if (nextToken == "=="||nextToken=="!=" ) {
            BAND();
        }else{
            error();
        }
    }

    // <BAND> --> <BOR> {`&&` <BOR>}
    public void BAND() throws FileNotFoundException {
        BOR();
        if (nextToken == "&&" ) {
            BOR();
        }else{
            error();
        }
    }

    // <BOR> --> <EXPR> {`||` <EXPR>}
    public void BOR() throws FileNotFoundException {
        EXPR();
        if (nextToken == "||" ) {
            EXPR();
        }else{
            error();
        }
    }

    // <STMT> --> <IF_STMT> | <BLOCK> | <EXPR> | <WHILE_LOOP>
    public  void ifstmt() throws FileNotFoundException {
        if (nextToken != "if")
            error();
        else {
            lex();
            if (nextToken != "(")
                error();
            else {
                lex();
                BOOL_EXPR();
                if (nextToken != ")")
                    error();
                if (nextToken == "{")
                    block();
                else {
                    statement();
                    if (nextToken != ";") {
                        error();
                    }
                    if (nextToken == "else") {
                        lex();
                        if (nextToken == "{")
                            block();
                        else {
                            statement();
                            if (nextToken != ";") {
                                error();
                            } } } }}} }

    // <WHILE_LOOP> --> `while` `(` <BOOL_EXPR> `)` ( <STMT> `;` | <BLOCK> )
    public void while_loop() throws FileNotFoundException {
        if (nextToken != "if")
            error();
        else {
            lex();
            if (nextToken != "(")
                error();
            else {
                lex();
                BOOL_EXPR();
                if (nextToken != ")")
                    error();
                if (nextToken == "{")
                    block();
                else {
                    statement();
                    if (nextToken != ";") {
                        error();
                    }
                }}} }

    // <STMT_LIST> --> { <STMT> `;` }
    public  void STMT_LIST() throws FileNotFoundException {
        if (nextToken == "{")
            STMT();
        if (nextToken == "}")
            error();
        else if (nextToken == ";")
            if(nextToken != "}"){
                error();
            }
    }

    // STMT  --> WHILE_LOOP |IF_STMT |BLOCK |EXPR
    public void STMT() throws FileNotFoundException {
        if (nextToken == "while")
            while_loop();
        else if (nextToken == "if")
            ifstmt();
        else if (nextToken == "{")
            block();
        else if (nextToken == ((ID(nextToken))?nextToken:null ) ||nextToken==((INT_LIT(nextToken))?nextToken:null ) || nextToken=="(")
            expr();
        else{
            error();
        }
    }


    // <BOOL_EXPR> --> <BTERM> {(`>`|`<`|`>=`|`<=`) <BTERM>}
    public  void BOOL_EXPR() throws FileNotFoundException {
        BTERM();
        if (nextToken == ">"||nextToken=="<" || nextToken == ">="||nextToken=="<=" ) {
            BTERM();
        }else{
            error();
        }


    }

    // <BLOCK> --> `{` <STMT_LIST> `}`
    public void block() throws FileNotFoundException {
        if (nextToken == "{")
            STMT_LIST();
        if (nextToken == "}")
            error();
        else if (nextToken == ";")
            if(nextToken != "}"){
                error();
            }
    }


    public void expr() throws FileNotFoundException {
        System.out.println( "Enter <expr>\n");
        /* Parse the first term */
        term();
    /* As long as the next token is + or -, get
	 the next token and parse the next term */
        while (nextToken == "+" || nextToken == "-") {
            lex();
            term();
        }
        System.out.println( "Enter <expr>\n");
    }
    public void term() throws FileNotFoundException {
        System.out.println("Enter <term>\n");
        /* Parse the first factor */
        factor();
	/* As long as the next token is * or /, get the
	 next token and parse the next factor */
        while (nextToken == "*" || nextToken == "/") {
            lex();
            factor();
        }
        System.out.println("Exit <term>\n");
    }
    public void  factor() throws FileNotFoundException {
        System.out.println("Enter <factor>\n");
        /* Determine which RHS */
        if (nextToken == ((ID(nextToken))?nextToken:null ) || nextToken == ((INT_LIT(nextToken))?nextToken:null ))
            /* Get the next token */
            lex();
		/* If the RHS is ( <expr> ), call lex to pass over the
		 left parenthesis, call expr, and check for the right
		 parenthesis */
        else { if (nextToken == "(") {
            lex();
            expr();
            if (nextToken == ")")
                lex();
            else
                error();
        }
		/* It was not an id, an integer literal, or a left
		 parenthesis */
        else
            error();
        }
        System.out.println("Exit <factor>\n");
    }
    public static void statement(){

    }

    public static void error(){
        System.out.println("An Error Occured");
    }
    public  boolean FP_LIT(String number) {
        String numberRegex = "[+-]?([0-9]*.[0-9]+|[0-9]+.)([eE][+-]?[0-9]+)?[LlFf]?|[+-]?[0-9]+[Ee][+-]?[0-9]+[LlFf]?";
        Pattern pattern = Pattern.compile(numberRegex);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
    public  boolean ID(String number) {
        String numberRegex = "[_]?([a-zA-Z]+[_]?[a-zA-Z]*)";
        Pattern pattern = Pattern.compile(numberRegex);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
    public  boolean INT_LIT(String number) {
        String numberRegex = "[0-9]|[1-9][0-9]*|[0-7]+|(0[xX][0-9a-fA-F]+)(i64|I64|u|U|l|L)?";
        Pattern pattern = Pattern.compile(numberRegex);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

}
