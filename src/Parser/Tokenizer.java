package Parser;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.Stack;

public class Tokenizer {

    private ArrayList<Token> Tokens = new ArrayList<Token>();
    private String string_to_tokenize;
    private Stack<Character> atomicSeparation = new Stack<Character>();
    public  Tokenizer (String string_to_tokenize) {
        this.string_to_tokenize = string_to_tokenize;
    }
    public void tokenizeString() {
        fillStack();
        int i = 0;
        int current_state = -1;
        Character next;
        State state = new State();
        while (!atomicSeparation.empty()) {
            next = atomicSeparation.pop();
            System.out.println(next);
            state.checState(next);
            if (state.status != "Finish") {
                Tokens.add(state.previous_token);
            }
            i++;
        }


    }
    public ArrayList<Token> getTokens() {
        return Tokens;
    }
    public char[] convertStringToArray() {
        return this.string_to_tokenize.toCharArray();
    }
    public void fillStack() {
        char[] char_arr = convertStringToArray();
        for(int i = char_arr.length-1; i >= 0; i--) {
            Character c = char_arr[i];
            atomicSeparation.push(c);
        }
    }
}
