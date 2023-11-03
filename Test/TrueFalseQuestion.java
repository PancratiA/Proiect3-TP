package Test;

//Clasa cu scopul de a tipiza felul de intrebare
public class TrueFalseQuestion extends Intrebare  { //Clasa ce extinde Clasa abstracta intrebare;


    public TrueFalseQuestion(String intrebare, String raspunsCorect) {
        super(intrebare,raspunsCorect);
    }

    @Override
    public boolean verificaRaspuns(String raspuns) {

        return false;
    }

    @Override
    public String toString() {
        return "True/False: " + super.toString();
    }
}