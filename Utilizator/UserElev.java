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
    public boolean isValidPassword(String parola) {
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