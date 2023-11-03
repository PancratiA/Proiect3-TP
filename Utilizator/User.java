package Utilizator;

public interface User { // Interfata cu scopul de definire a metodelor necesare pentru gestionarea utilizatorilor

    String getID(); //Metoda cu scopul de returnare a ID-ului
    boolean isValidPassword(String password); //metoda cu scopul de validare a parolei
}