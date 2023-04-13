package packet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import static packet.ReservedWords.*;

public class MainCLass {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello world!");
   Lexer.lex();

    }



    public static ArrayList<String> extractLexemes(String text) {
        ArrayList<String> lexemes = new ArrayList<String>();
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                // Found a non-letter/digit character, extract the lexeme
                if (startIndex < endIndex) {
                    String lexeme = text.substring(startIndex, endIndex);
                    lexemes.add(lexeme.toLowerCase()); // Convert to lowercase for consistency
                }
                startIndex = i + 1; // Start of next lexeme
                endIndex = i + 1;
            } else {
                endIndex++;
            }
        }
        // Extract the last lexeme
        if (startIndex < endIndex) {
            String lexeme = text.substring(startIndex, endIndex);
            lexemes.add(lexeme.toLowerCase()); // Convert to lowercase for consistency
        }
        return lexemes;
    }






}
