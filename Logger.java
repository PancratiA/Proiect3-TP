/*import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import Test.*;
public class Fisier {
    private static Fisier instance;
    private ArrayList<Test> teste;

    private Fisier() {
        teste = new ArrayList<>();
    }

    public static Fisier getInstance() {
        if (instance == null) {
            instance = new Fisier();
        }
        return instance;
    }

    public void addTest(Test test) {
        teste.add(test);
    }

    public void saveToFile(String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (Test test : teste) {
                fileWriter.write("Test:\n");
                for (Intrebare intrebare : test.getIntrebari()) {

                    if(intrebare instanceof CheckboxQuestion){
                        fileWriter.write("Check:\n");
                        fileWriter.write (intrebare.adaugareIntrebareFisier());
                        CheckboxQuestion check = (CheckboxQuestion)intrebare;
                        fileWriter.write(check.adaugareRaspunsuri() + "\n" );

                    }
                    if(intrebare instanceof TrueFalseQuestion){
                        fileWriter.write("T/F:\n");
                        fileWriter.write (intrebare.adaugareIntrebareFisier());
                        fileWriter.write( "True\nFalse\n" );



                    }
                    fileWriter.write("Corect:\n");
                    fileWriter.write (intrebare.adaugareRaspunsCorectFisier());

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            Test testCurent = null;
            Intrebare currentIntrebare = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Quiz:")) {
                    testCurent = new Test();
                } else if (testCurent != null) {

                    if (line.startsWith("Check:")) {

                       // (CheckboxQuestion)currentIntrebare =  new CheckboxQuestion();
                    } else if (currentIntrebare != null) {
                        if (!line.isEmpty()) {
                            // Assuming each line contains an answer option
                          //  currentIntrebare.addAnswerOption(line);
                        } else {
                            // Empty line indicates the end of answers for the current Intrebare
                            testCurent.addIntrebare(currentIntrebare);
                            currentIntrebare = null;
                        }
                    }
                }
            }

            if (testCurent != null) {
                teste.add(testCurent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
*/
/*
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import Test.*;

public class Fisier {
    private static Fisier instance;
    private ArrayList<Test> teste;

    private Fisier() {
        teste = new ArrayList<>();
    }

    public static Fisier getInstance() {
        if (instance == null) {
            instance = new Fisier();
        }
        return instance;
    }

    public void addTest(Test test) {
        teste.add(test);
    }

    public void saveToFile(String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (Test test : teste) {
                fileWriter.write("Quiz:\n");
                for (Intrebare intrebare : test.getIntrebari()) {
                    fileWriter.write(intrebare.adaugareIntrebareFisier());
                    if (intrebare instanceof CheckboxQuestion) {
                        fileWriter.write("\nCheck:\n");
                        CheckboxQuestion check = (CheckboxQuestion) intrebare;
                        fileWriter.write(check.adaugareRaspunsuri() + "\n");
                    } else if (intrebare instanceof TrueFalseQuestion) {
                        fileWriter.write("\nT/F:\n");
                        fileWriter.write("True");
                        fileWriter.write("False");
                    }
                    fileWriter.write("\nCorect:\n");
                    fileWriter.write(intrebare.adaugareRaspunsCorectFisier());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFisier(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            Test testCurent = null;
            Intrebare currentIntrebare = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Quiz:")) {
                    testCurent = new Test();
                } else if (testCurent != null) {
                    if (currentIntrebare != null) {
                        if (line.startsWith("Check:")) {
                            // Handle CheckboxQuestion type
                            currentIntrebare = new CheckboxQuestion();
                        } else if (line.startsWith("T/F:")) {
                            // Handle TrueFalseQuestion type
                            currentIntrebare = new TrueFalseQuestion();
                        } else if (!line.isEmpty()) {
                            // Assuming each line contains an answer option
                            currentIntrebare.setRaspunsCorect(line);
                        } else {
                            // Empty line indicates the end of answers for the current Intrebare
                            testCurent.addIntrebare(currentIntrebare);
                            currentIntrebare = null;
                        }
                    }
                }
            }

            if (testCurent != null) {
                teste.add(testCurent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Logger {
    private static Logger instance;
    private ArrayList<String> logs;
    private PrintWriter fileWriter;


    private Logger() {
        logs = new ArrayList<>();
        try {
            fileWriter = new PrintWriter(new BufferedWriter(new FileWriter("app.log", true)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss z")
                .withZone(ZoneId.systemDefault());
        StringBuffer log = new StringBuffer("[" + formatter.format(Instant.now()) + "] " + message);
        StackTraceElement location = new Throwable().getStackTrace()[1];
        log.append(" (" + location.getClassName() + ":" + location.getLineNumber() + ")");
        System.out.println(log);
        fileWriter.println(log);
        fileWriter.flush();
        logs.add(log.toString());
    }
}
