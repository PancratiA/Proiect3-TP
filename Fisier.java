import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import Test.*;

public class Fisier {
 //Clasa ce adauga intrebarile adaugate de profesori intr-un fisier

    public void adaugaTestInFisier(String numeFisier, Test test) {


        try (FileWriter fileWriter = new FileWriter(numeFisier, true)) {
            // Append the test string to the file
            fileWriter.write(test.toString() + System.lineSeparator());
            System.out.println("Test adăugat în fișier: " + numeFisier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*private Test parseTestFromString(String str) {
        Test test = new Test();
        String[] lines = str.split("\n");
        int lineIndex = 0;

        if (lineIndex < lines.length && lines[lineIndex].startsWith("Materie: ")) {
            test.setMaterie(lines[lineIndex].substring("Materie: ".length()));
            lineIndex++;
        }

        if (lineIndex < lines.length && lines[lineIndex].equals("Intrebari si raspunsuri:")) {
            lineIndex++;
        }

        while (lineIndex < lines.length) {
            if (lineIndex < lines.length && lines[lineIndex].startsWith("Intrebare: ")) {
                Intrebare intrebare;

                String intrebareLine = lines[lineIndex].substring("Intrebare: ".length());
                lineIndex++;

                if (lineIndex < lines.length && lines[lineIndex].equals("Tip: True/False")) {
                    TrueFalseQuestion trueFalseQuestion = new TrueFalseQuestion();
                    trueFalseQuestion.setIntrebare(intrebareLine);
                    intrebare = trueFalseQuestion;
                    lineIndex++;
                } else if (lineIndex < lines.length && lines[lineIndex].equals("Tip: Checkbox")) {
                    CheckboxQuestion checkboxQuestion = new CheckboxQuestion();
                    checkboxQuestion.setIntrebare(intrebareLine);
                    intrebare = checkboxQuestion;
                    lineIndex++;
                } else {
                    // Handle other cases or throw an error as needed
                    break;
                }

                if (lineIndex < lines.length && lines[lineIndex].startsWith("Raspuns corect: ")) {
                    intrebare.setRaspunsCorect(lines[lineIndex].substring("Raspuns corect: ".length()));
                    lineIndex++;

                    if (intrebare instanceof CheckboxQuestion) {
                        if (lineIndex < lines.length && lines[lineIndex].startsWith("Raspunsuri gresite: ")) {
                            String gresiteLine = lines[lineIndex].substring("Raspunsuri gresite: ".length());
                            String[] gresiteArray = gresiteLine.split(", ");
                            ArrayList<String> raspunsuriGresite = new ArrayList<>(Arrays.asList(gresiteArray));
                            ((CheckboxQuestion) intrebare).setRaspunsuriGresite(raspunsuriGresite);
                            lineIndex++;
                        }
                    }

                    test.addIntrebare(intrebare);
                }
            }

            lineIndex++;
        }

        return test;
    }


    public ArrayList<Test> incarcaTesteDinFisier(String numeFisier) {
        ArrayList<Test> teste = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(numeFisier))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Test test = parseTestFromString(line);
                if (test != null) {
                    teste.add(test);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teste;
    }*/
}


