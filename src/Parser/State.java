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
        Matcher regex_matcher;

        if (!Character.isWhitespace(c)) {

           if (!this.Symbol_states.contains(current_state)) {
               if(Character.isAlphabetic(c)) {
                   status = "In Progress";
                   content += c;
                   alphaCheck();
               }
               else if(Character.isDigit(c)) {
                   content += c;
                   status = "In Progress";
                   regex_matcher = invalid_alpha_num_pattern.matcher(this.content);
                   if ( regex_matcher.find()) {
                       current_state = -1;
                   }
                   else {
                       current_state = 13;
                   }
               }
               else {
                   newToken();
                   this.content += c;
                   if (this.Symbols.contains(c)){
                       System.out.println("entramos?");
                       this.status = "In Progress";
                   }
                   else {
                       this.status = "Start";
                   }
                   SymbolCheck();
               }
           }
           else {
               regex_matcher = alpha_num_pattern.matcher(this.content);
               if (regex_matcher.find()) {
                   newToken();
               }
               this.content += c;
               SymbolCheck();
               if (this.status == "Start") {
                   newToken();
               }
               if (this.Symbols.contains(c)){
                   System.out.println("entramos?");
                   this.status = "In Progress";
               }
               else {
                   this.status = "Start";
               }


           }
        }
        else {
            this.status = "Finish";
            this.current_state = -1;
            newToken();
        }
        System.out.println("Actualmente el lexema es: " + this.content +" y estamos en el estado: " +this.current_state + " y nos encontramos en el estado: " + this.status );
    }
    public void alphaCheck() {
        switch(this.content) {
            case "while":
                this.current_state = 10;
                break;
            case "return":
                this.current_state = 11;
                break;
            case "if":
                this.current_state = 9;
                break;
            case "else":
                this.current_state = 12;
            default:
                if (this.content.equals("int") || this.content.equals("float") || this.content.equals("char")|| this.content.equals("string") || this.content.equals("void")) {
                    this.current_state = 0;
                }
                else if (Character.isDigit(this.content.charAt(0))) {
                    this.current_state = -1;
                }
                else {
                    this.current_state = 1;
                }
                break;
        }
    }
    public void SymbolCheck() {
        switch (this.content) {
            case ";":
                this.status = "Start";
                this.current_state = 2;
                break;
            case ",":
                this.status = "Start";
                this.current_state = 3;
                break;
            case "(":
                this.status = "Start";
                this.current_state = 4;
                break;
            case ")":
                this.status = "Start";
                this.current_state = 5;
                break;
            case "{":
                this.status = "Start";
                this.current_state = 6;
                break;
            case "}":
                this.status = "Start";
                this.current_state = 7;
                break;
            case "=":
                this.current_state = 8;
                break;
            case "||":
                this.status = "Start";
                this.current_state = 15;
                break;
            case "&&":
                this.status = "Start";
                this.current_state = 15;
                break;
            case "+":
                this.status = "Start";
                this.current_state = 14;
                break;
            case "-":
                this.status = "Start";
                this.current_state = 14;
                break;
            case "*":
                this.status = "Start";
                this.current_state = 16;
                break;
            case "/":
                this.status = "Start";
                this.current_state = 16;
                break;
            case "==":
                this.status = "Start";
                this.current_state = 17;
                break;
            case "<":

                this.current_state = 17;
                break;
            case "<=":
                this.current_state = 17;
                break;
            case "=>":
                this.current_state = 17;
                break;
            case ">":
                this.current_state = 17;
                break;
            case "=!":
                this.current_state = 17;
                break;
            case "$":
                this.status = "Start";
                this.current_state = 18;
                break;
            default:
                this.current_state = -1;
                break;
        }
    }
    public String stateCheck() {
        String categoria = "";
        switch(this.current_state) {
            case -1:
                categoria =" Token Inválido ";
                break;
            case 0:
                categoria =" Tipo de dato ";
                break;
            case 1:
                categoria = " ID ";
                break;
            case 2:
                categoria = " Punto y Coma ";
                break;
            case 3:
                categoria = " Coma ";
                break;
            case 4:
                categoria = " Paréntesis Izquierdo ";
                break;
            case 5:
                categoria = " Paréntesis Derecho ";
                break;
            case 6:
                categoria = " Corchete Izquierdo ";
                break;
            case 7:
                categoria = " Corchete Derecho ";
                break;
            case 8:
                categoria = " Asignación ";
                break;
            case 9:
                categoria = " Condicional IF ";
                break;
            case 10:
                categoria = " Palabra reservada WHILE ";
                break;
            case 11:
                categoria = " Palabra reservada RETURN ";
                break;
            case 12:
                categoria = " Condicional ELSE ";
                break;
            case 13:
                categoria = " Constante ";
                break;
            case 14:
                categoria = " Operador Aritmético (OpSum) ";
                break;
            case 15:
                categoria = " Operador Lógico (OpLogico) ";
                break;
            case 16:
                categoria = " Operador Aritmético (opMultiplicacion) ";
                break;
            case 17:
                categoria = " Operador Relacional (opRelacional) ";
                break;
            case 18:
                categoria = " Símbolo de pesos ";
                break;
        }
        return categoria;
    }
    public void newToken() {
        previous_token.setLexema(this.content);
        previous_token.setCategory(stateCheck());
        previous_token.setM_n(this.current_state);
        this.content = "";

    }

}
