
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Logger { //Clasa Singleton ce adauga functia de logging
    //in care se tine cont de activitatea utilizatorilor
    private static Logger instance;
    private ArrayList<String> logs;
    private PrintWriter fileWriter;


    private Logger() { //constructor
        logs = new ArrayList<>();
        try {
            fileWriter = new PrintWriter(new BufferedWriter(new FileWriter("app.log", true)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Logger getInstance() { //limiteaza la o singura instanta
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) { //metoda ce adauga data , si mesajul in fisier
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
