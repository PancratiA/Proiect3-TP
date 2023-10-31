package Utilizator;

public class UserProfesor implements User {
    private String ID;
    private static final String[] IDuri = {"TAP12", "TAP34", "TAP56"};


    public UserProfesor(String ID) {
        this.ID = ID;
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