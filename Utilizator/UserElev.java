package Utilizator;

public class UserElev implements User {
    private String ID;
    private String nume;
    private String prenume;
    private static final String[] IDuri = {"STU123", "STU456", "STU789"};

    public UserElev(String ID) {
        this.ID = ID;
    }

    public UserElev(String ID , String nume , String prenume){
        this.ID=ID;
        this.nume=nume;
        this.prenume=prenume;

    }
    public boolean isValidID() {
        for (String id : IDuri) {
            if (ID.equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getID() {
        return ID;
    }
}