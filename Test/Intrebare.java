package Test;

public abstract class Intrebare { //Clasa Abstracta cu scopul de a generaliza intrebarile
    private String intrebare;
    private String raspunsCorect;



    public Intrebare(String intrebare, String raspunsCorect) {
        this.intrebare = intrebare;
        this.raspunsCorect=raspunsCorect;
    }

    public Intrebare(){
        this("","");
    }

    // Metoda abstracta
    public abstract boolean verificaRaspuns(String raspuns);

    @Override
    public String toString() {
        return "Intrebare: " + intrebare;
    }


    public String getIntrebare() {
        return intrebare;
    }

    public void setIntrebare(String intrebare) {
        this.intrebare = intrebare;
    }

    public String getRaspunsCorect() {
        return raspunsCorect;
    }

    public void setRaspunsCorect(String raspunsCorect) {
        this.raspunsCorect = raspunsCorect;
    }
}
