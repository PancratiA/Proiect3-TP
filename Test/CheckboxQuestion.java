package Test;
import java.util.ArrayList;

public class CheckboxQuestion extends Intrebare{ //Clasa ce extine o clasa abstracta

    private ArrayList<String> raspunsuriCorecte;

    public CheckboxQuestion(String intrebare, ArrayList<String> raspunsuri, String raspunsCorect) {
        super(intrebare,raspunsCorect);
        this.raspunsuriCorecte = raspunsuriCorecte;

    }
    public CheckboxQuestion() {


    }

    @Override
    public boolean verificaRaspuns(String raspuns) {
        if(raspuns.equals(getRaspunsCorect())) return true;
        return false;

    }


    @Override
    public String toString() {
        return "Checkbox:" + super.toString();
    }

    public ArrayList<String> adaugareRaspunsuri(){


        return raspunsuriCorecte;
    }
}