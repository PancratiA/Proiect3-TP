package Test;

public class TrueFalseQuestion extends Intrebare {

    public TrueFalseQuestion(String intrebare, String raspunsCorect) {
        super(intrebare,raspunsCorect);
    }

    public TrueFalseQuestion() {

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