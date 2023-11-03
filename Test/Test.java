package Test;
import java.util.ArrayList;

public class Test  { //Clasa principala a proiectului
    //Clasa cu scopul de a utiliza intrebari pentru a forma teste;

    private String materie;
    private ArrayList<Intrebare> intrebari;

    public Test() {
        intrebari = new ArrayList<>();
    }
    public Test(String materie,ArrayList<Intrebare> intrebari ){this.materie=materie;
    this.intrebari=intrebari;}

    public void addIntrebare(Intrebare intrebare) {
        if(intrebare!=null)intrebari.add(intrebare);
    }

    public ArrayList<Intrebare> getIntrebari() {
        return intrebari;
    }

    public String getMaterie() {
        return materie;
    }

    public void setMaterie(String materie) {
        this.materie = materie;
    }

    public boolean hasTrueFalseQuestions() { //Metoda ce verifica daca Intrebarile din Test sunt True/false
        for (Intrebare intrebare : intrebari) {
            if (intrebare instanceof TrueFalseQuestion) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCheckboxQuestions() {//Metoda ce verifica daca Intrebarile din Test sunt Checkbox
        for (Intrebare intrebare : intrebari) {
            if (intrebare instanceof CheckboxQuestion) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() { //Metoda toString folosing StringBuilder
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Materie: ").append(materie).append("\n");
        stringBuilder.append("Intrebari si raspunsuri:\n");

        for (Intrebare intrebare : intrebari) {
            stringBuilder.append("Intrebare: ").append(intrebare.getIntrebare()).append("\n");

            if (intrebare instanceof TrueFalseQuestion) {
                stringBuilder.append("Tip: True/False").append("\n");
            } else if (intrebare instanceof CheckboxQuestion) {
                stringBuilder.append("Tip: Checkbox").append("\n");
            }

            stringBuilder.append("Raspuns corect: ").append(intrebare.getRaspunsCorect()).append("\n");

            if (intrebare instanceof CheckboxQuestion) {
                ArrayList<String> raspunsuriGresite = ((CheckboxQuestion) intrebare).getRaspunsuriGresite();
                stringBuilder.append("Raspunsuri gresite: ").append(raspunsuriGresite.toString()).append("\n");
            }

            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

}

