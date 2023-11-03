package Utilizator;

public class UserElev implements User {
    private String ID;
    private String nume;
    private String prenume;
    private static final String[] parole = {"student","elev"};

    public UserElev() {

    }

    public UserElev(String ID , String nume , String prenume){
        this.ID=ID;
        this.nume=nume;
        this.prenume=prenume;

    }
<<<<<<< HEAD
    public boolean isValidPassword(String parola) { //metoda preluata din interfata
=======
    public boolean isValidPassword(String parola) {
>>>>>>> 8c187b8b6f1c6571bfb68389aa718ee2337b82d2
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
    }////metoda preluata din interfata

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }
}