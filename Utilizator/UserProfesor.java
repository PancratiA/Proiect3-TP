
package Utilizator;

public class UserProfesor implements User { //clasa ce implementeaza o interfata
    private String ID;
    private String nume;
    private String prenume;
    private String materie;
    private static final String[] parole = {"prof", "teacher"}; //Constante pentru parola


    public UserProfesor() {

    }

    public UserProfesor(String ID , String nume,  String prenume, String materie) {
        this.ID=ID;
        this.nume=nume;
        this.prenume=prenume;
        this.materie=materie;


    }


    public boolean isValidPassword(String parola) { //Metoda preluata din interfata
        for (String pass : parole) {
            if (parola.equals(pass)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String getID() {
        return ID;
    } //metoda preluata din interfata

    public String getMaterie() {
        return materie;
    }

    public void setMaterie(String materie) {
        this.materie = materie;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }
    public static String[] getMaterii() { //metoda de standardizare a materiilor disponibile

        String[] subjects = {"Geografie", "Istorie", "Stiinte", "Sport", "Engleza"};
        return subjects;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }
}



