package Parser;
import java.util.HashSet;
import java.util.regex.*;
public class State {
    private int current_state;
    private String content;
    public Token previous_token = new Token();
    private HashSet Symbols = new HashSet();
    private HashSet Symbol_states = new HashSet();
    private char char_in_turn;
    private String aplha_num_regex = "^[a-zA-Z0-9]*$";
    private String invalid_alpha_num_token_regex = "^[0-9][a-zA-Z]+?";
    private Pattern alpha_num_pattern = Pattern.compile(aplha_num_regex);
    private Pattern invalid_alpha_num_pattern = Pattern.compile(invalid_alpha_num_token_regex);
    public String status;
    public State() {
        char[] Symbols = {'|', '&', '=', '>', '<'};
        int[] Symbols_states = {2,3,4,5,6,7,8,18};
        for(char symbol: Symbols) {
            this.Symbols.add(symbol);
        }
        for (int symbols_state: Symbols_states){
            this.Symbol_states.add(symbols_state);
        }
        this.content = "";
        this.status = "Start";

    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public int getCurrentstate() {
        return current_state;
    }

    public void setCurrentstate(int currentstate) {
        this.current_state = currentstate;
    }
    public void checState(Character c) {

    }

    public void newToken() {
        previous_token.setLexema(this.content);
        previous_token.setCategory(stateCheck());
        previous_token.setM_n(this.current_state);
        this.content = "";

    }

}
