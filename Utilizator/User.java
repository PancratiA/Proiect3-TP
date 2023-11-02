package Utilizator;

public interface User {
    String getID();
    boolean isValidPassword(String password);
}