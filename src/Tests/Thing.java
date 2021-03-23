package Tests;

public class Thing {
    public String message;

    public Thing() {
        System.out.println("soy una cosa que fue construida");
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
