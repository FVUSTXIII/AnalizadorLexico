package Parser;

public class Token {

    private String category;
    private int m_n;
    private String lexema;
    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getM_n() {
        return m_n;
    }

    public void setM_n(int m_n) {
        this.m_n = m_n;
    }

}
