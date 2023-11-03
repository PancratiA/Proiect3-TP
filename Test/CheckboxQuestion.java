package Test;
import java.util.ArrayList;
//Clasa cu scopul de a tipiza felul de intrebare
public class CheckboxQuestion extends Intrebare  { //Clasa ce extinde o clasa abstracta


    private ArrayList<String> raspunsuriGresite;

    public CheckboxQuestion(String intrebare, ArrayList<String> raspunsuri, String raspunsCorect) {
        super(intrebare,raspunsCorect);
        this.raspunsuriGresite = raspunsuri;

    }

//Metoda Mostenita din clasa Intrebare
    @Override
    public boolean verificaRaspuns(String raspuns) {
        if(raspuns.equals(getRaspunsCorect())) return true;
        return false;

    }

    @Override
    public String toString() {
        return "Checkbox:" + super.toString();
    }

    public ArrayList<String> getRaspunsuriGresite() {
        return raspunsuriGresite;
    }


}