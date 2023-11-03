

import Utilizator.User;
import Utilizator.UserElev;
import Utilizator.UserProfesor;
import Test.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;


public class PlatformaTeste {

    public static Font customFont = new Font("Serif", Font.PLAIN, 20); //setare font

    //declarari variabile globale
    public static ArrayList<User> users = new ArrayList<>();
    public static String tipUser;
    private static UserProfesor profesor;
    private static UserElev elev;
    public static String numeFisier="quizzes.txt";

    public static void main(String[] args) {
        Logger.getInstance().log("Pornire aplicatie"); //Log
        users.add(new UserElev("STU123", "John", "Doe")); // Adauga un student
        users.add(new UserProfesor("PRO123", "Jane", "Smith", "Geografie")); // Adauga un profesor


        // Crearea unei ferestre de autentificare
        JFrame frame = new JFrame("Log-in");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel label = new JLabel("Bine ai venit!");
        label.setFont(new Font(customFont.getName(), Font.BOLD, 24));
        frame.add(label);


        JLabel label2 = new JLabel("Introdu Parola");
        label2.setFont(customFont);
        frame.add(label2);

        //camp pentru introducerea parolei
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(customFont);
        frame.add(passwordField);

        //buton de autentificare
        JButton loginButton = new JButton("Log in");
        loginButton.setFont(customFont);
        frame.add(loginButton);


        //Manipularea evenimentelor dupa apasarea butonului
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String parola = passwordField.getText(); //Optine parola

                UserElev student = new UserElev();
                UserProfesor profesor = new UserProfesor();

                if (student.isValidPassword(parola)) { //verifica daca parola este buna pt student
                    tipUser = "elev";
                    Logger.getInstance().log("Platforma accesata de  Elev ");
                    frame.dispose();
                    JOptionPane.showMessageDialog(frame, "Platforma accesibila pentru Student ");
                    getID(); //Apeleaza Metoda cu fereastra pentru introducerea ID-ului
                } else if (profesor.isValidPassword(parola)) { //verifica daca parola este buna pt profesor
                    tipUser = "profesor";
                    Logger.getInstance().log("Platforma accesata de  Profesor ");
                    frame.dispose();
                    JOptionPane.showMessageDialog(frame, "Platforma accesibila pentru Profesor ");
                    getID(); //Apeleaza Metoda cu fereastra pentru introducerea ID-ului

                } else {
                    JOptionPane.showMessageDialog(frame, "Parola Incorecta!"); //in cazul in care nu exista parola
                    //se afiseaza 'Parola Incorecta'

                }

                passwordField.setText(""); //reseteaza field-ul
            }
        });


        frame.setPreferredSize(new Dimension(400, 200));
        frame.pack();//aduna elementele
        frame.setLocationRelativeTo(null);//centreaza
        frame.setVisible(true); //face fereastra vizibila
    }


    public static void getID() { //Metoda ce se ocupa de ID

        //se creeaza o  fereastra pentru id-uri
        JFrame idFrame = new JFrame("ID");
        idFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        idFrame.setSize(400, 150);
        idFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JLabel idLabel = new JLabel("Introduceti ID:");
        idLabel.setFont(customFont);
        idFrame.add(idLabel);

        JTextField idTextField = new JTextField(20);
        idFrame.add(idTextField);
//Buton pentru Utilizatorii deja existenti
        JButton idSubmitButton = new JButton("Inainteaza");
        idSubmitButton.setFont(customFont);
        idFrame.add(idSubmitButton);
        //Buton pentru initierea creeari unui utilizator nou;
        JButton idInexistent = new JButton("Nu am un ID");
        idInexistent.setFont(customFont);
        idFrame.add(idInexistent);
        idInexistent.addActionListener(new ActionListener() { //Deschiderea ferestrelor de inregistrare in cazul
            //userilor noi
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tipUser.equals("elev")) {
                    panelLoginStudent();
                } else {
                    panelLoginProfesor();
                    Logger.getInstance().log("Se Deschide platforma de Inregistrare Profesor");
                }

            }
        });

        idSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idString = idTextField.getText();

                if (!idString.isEmpty()) { //verifica daca campul este gol

                    boolean idExistaProf = false;
                    boolean idExistaStud = false;
                    for (User user : users) {
                        if (user instanceof UserProfesor) {  //verifica daca Id-ul exista in cazul profesorilor
                            if (user.getID().equals(idString)) {
                                profesor = (UserProfesor) user;
                                idExistaProf = true;
                                break;
                            }
                        }
                        if (user instanceof UserElev) { //verifica daca id-ul exista in cazul elevilor
                            if (user.getID().equals(idString)) {
                                elev = (UserElev) user;
                                idExistaStud = true;
                                break;
                            }
                        }
                    }
                    //verificare pentru Pasul urmator - fereastra principala - in cazul in care userul exista
                    if (idExistaProf) {
                        idFrame.dispose();
                        JOptionPane.showMessageDialog(idFrame, "Bine ati Revenit");
                        panelProfesor();

                    } else if (idExistaStud) {
                        idFrame.dispose();
                        JOptionPane.showMessageDialog(idFrame, "Bine ati Revenit");
                        panelStudent();
                    } else {
                        JOptionPane.showMessageDialog(idFrame, "Acest ID nu exista!");

                    }


                } else {
                    JOptionPane.showMessageDialog(idFrame, "Nu ati introdus nimic!");
                }
            }
        });

        //setari fereastra

        idFrame.setPreferredSize(new Dimension(400, 200));
        idFrame.pack();
        idFrame.setLocationRelativeTo(null);
        idFrame.setVisible(true);
    }

    public static UserProfesor panelLoginProfesor() { //inregistrare usernou de tipul profesor
        Logger.getInstance().log("Se Deschide platforma de Inregistrare Profesor"); //log
        UserProfesor profesor = new UserProfesor();
        //creare fereastra
        JFrame frameS = new JFrame("Fereastra Login Profesor");
        frameS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameS.setSize(1000, 600);
        frameS.setLayout(new GridLayout(0, 1));


        JComboBox<String> materieDropdown = new JComboBox<>(UserProfesor.getMaterii()); //Dropdown de alegere a materiei
        materieDropdown.setFont(customFont);
        JLabel label2 = new JLabel("Alege Materia:");
        label2.setFont(customFont);
        frameS.add(label2);
        frameS.add(materieDropdown);

        materieDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String materieAleasa = (String) materieDropdown.getSelectedItem();
                profesor.setMaterie(materieAleasa);
            }
        });


        String randomID = "PRO" + String.format("%03d", new Random().nextInt(1000)); //randomizare ID de tipul PRO123
        profesor.setID(randomID);

        JLabel prenumeLabel = new JLabel("Introduceti numele:");
        prenumeLabel.setFont(customFont);
        frameS.add(prenumeLabel);

        JTextField prenumeField = new JTextField(20);
        frameS.add(prenumeField);

        JLabel numeLabel = new JLabel("Introduceti prenumele:");
        numeLabel.setFont(customFont);
        frameS.add(numeLabel);

        JTextField numeField = new JTextField(20);
        frameS.add(numeField);

        JButton SubmitButton = new JButton("Submit");
        SubmitButton.setFont(customFont);
        frameS.add(SubmitButton);

        SubmitButton.addActionListener(new ActionListener() { //buton de confirmare
            @Override
            public void actionPerformed(ActionEvent e) {
                String prenume = prenumeField.getText();
                String nume = numeField.getText();

                if (!prenume.isEmpty() && !nume.isEmpty()) { //verificare daca campurile sunt goale
                    if (prenume.charAt(0) == Character.toUpperCase(prenume.charAt(0)) //verifica daca numele incep cu litera mare
                            && nume.charAt(0) == Character.toUpperCase(nume.charAt(0))) {
                        profesor.setNume(nume);
                        profesor.setPrenume(prenume);

                        JOptionPane.showMessageDialog(frameS, "Bine ati Venit " + profesor.getNume() +
                                " " + profesor.getPrenume() + ". ID-ul dumneavoastra este : " + profesor.getID());

                        frameS.dispose();

                        panelProfesor(); //Initiaza fereastra principala pt profesori

                    } else {
                        JOptionPane.showMessageDialog(frameS, "Numele si prenumele trebuie sa inceapa cu litera mare!");
                    }
                } else {
                    JOptionPane.showMessageDialog(frameS, "Introduceti nume si prenume!");
                }
            }
        });

//setari fereastra
        frameS.setPreferredSize(new Dimension(1000, 600));
        frameS.pack();
        frameS.setLocationRelativeTo(null);
        frameS.setVisible(true);
        return profesor;


    }

    public static void panelLoginStudent() {
        Logger.getInstance().log("Se Deschide platforma de Inregistrare Elev"); //Log
        UserElev elev = new UserElev();

        //creare fereastra
        JFrame frameS = new JFrame("Fereastra Login Student");
        frameS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameS.setSize(1000, 600);
        frameS.setLayout(new GridLayout(0, 1));


        String randomID = "STU" + String.format("%03d", new Random().nextInt(1000)); //randomizare ID de tipul STU123
        elev.setID(randomID);
        JLabel prenumeLabel = new JLabel("Introduceti numele:");
        prenumeLabel.setFont(customFont);
        frameS.add(prenumeLabel);

        JTextField prenumeField = new JTextField(20);
        frameS.add(prenumeField);

        JLabel numeLabel = new JLabel("Introduceti prenumele:");
        numeLabel.setFont(customFont);
        frameS.add(numeLabel);

        JTextField numeField = new JTextField(20);
        frameS.add(numeField);

        JButton SubmitButton = new JButton("Submit");
        SubmitButton.setFont(customFont);
        frameS.add(SubmitButton);
        SubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String prenume = prenumeField.getText();
                String nume = numeField.getText();

                if (!prenume.isEmpty() && !nume.isEmpty()) { //verificare daca campurile sunt goale
                    if (prenume.charAt(0) == Character.toUpperCase(prenume.charAt(0)) //verificare daca numele incep
                            && nume.charAt(0) == Character.toUpperCase(nume.charAt(0))) {//cu litera mare
                        elev.setNume(nume);
                        elev.setPrenume(prenume);

                        JOptionPane.showMessageDialog(frameS, "Bine ati Venit " + elev.getNume() +
                                " " + elev.getPrenume() + " ID-ul dumneavoastra este : " + randomID); //popup pentru afisare ID nou

                        frameS.dispose();

                        panelProfesor();

                    } else {
                        JOptionPane.showMessageDialog(frameS, "Numele si prenumele trebuie sa inceapa cu litera mare!");
                    }
                } else {
                    JOptionPane.showMessageDialog(frameS, "Introduceti nume si prenume valide!");
                }
            }
        });

        frameS.setPreferredSize(new Dimension(1000, 600));
        frameS.pack();
        frameS.setLocationRelativeTo(null);
        frameS.setVisible(true);


    }

   public static void panelProfesor() { //fereastra principala pentru profesor
       Logger.getInstance().log("Se Deschide Fereastra Principala Profesor"); //Log
       JFrame frameP = new JFrame("FereastraProfesor");
       frameP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frameP.setSize(400, 200);
       frameP.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));



       JLabel label = new JLabel("Alegeti tipul de test pe care doriti sa-l adaugati:");
       label.setFont(customFont);
       frameP.add(label);

       String[] tipuriTeste = { "True/False Quiz", "Checkbox Quiz" };
       JComboBox<String> testDropdown = new JComboBox<>(tipuriTeste); //dropdown pentru alegerea tipului de test
       testDropdown.setFont(customFont);
       frameP.add(testDropdown);

       JButton creareTest = new JButton("Creeaza Test");
       creareTest.setFont(customFont);
       frameP.add(creareTest);

       creareTest.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String selectedTest = (String) testDropdown.getSelectedItem();
               if (selectedTest.equals("True/False Quiz")) { //in cazul in care alegerea este TF se initiaza fereastra de creare
                   creeareTrueFalseQuiz();
               } else if (selectedTest.equals("Checkbox Quiz")) {//in cazul in care alegerea este CB se initiaza fereastra de creare
                   creeareCheckboxQuiz();
               }
               frameP.dispose();
           }
       });


       frameP.setPreferredSize(new Dimension(400, 200));
       frameP.pack();
       frameP.setLocationRelativeTo(null);
       frameP.setVisible(true);
   }



    public static void creeareTrueFalseQuiz() { //Fereastra de creeare a testelor de tipul True/False
        Logger.getInstance().log("Se creeaza un test de tipul True/False");//log
        ArrayList<String> intrebari = new ArrayList<>();
        ArrayList<String> raspunsuri = new ArrayList<>();


        JFrame frameP = new JFrame("FereastraTF");
        //se creeaza fereastra
        frameP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameP.setSize(1000, 300);
        frameP.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        for (int i = 1; i < 5; i++) { //for pentru creearea a 4 instante diferite de introducere a unei intrebari
            JLabel intrbareLabel = new JLabel("Intrebarea " + i + ":");
            intrbareLabel.setFont(customFont);
            frameP.add(intrbareLabel);
            JTextField intrebareField = new JTextField(30);
            frameP.add(intrebareField);
            JLabel raspunsCorectLabel = new JLabel("Raspunsul corect pentru intrebarea " + i + ":");
            raspunsCorectLabel.setFont(customFont);
            frameP.add(raspunsCorectLabel);
            String[] tipuriRaspuns = { "True", "False" };
            JComboBox<String> raspunsCorectDropdown = new JComboBox<>(tipuriRaspuns); //dropdown pentru alegerea raspunsului
            raspunsCorectDropdown.setFont(customFont);
            frameP.add(raspunsCorectDropdown);
            JButton butonAdaugare = new JButton("Adauga Intrebare " + i);
            frameP.add(butonAdaugare);

            butonAdaugare.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String intrebare = intrebareField.getText();
                    String raspunsCorect = (String) raspunsCorectDropdown.getSelectedItem();

                    if (intrebare.isEmpty()) { //verifica daca a fost introdusa o intrebare
                        JOptionPane.showMessageDialog(frameP, "Introduceti o intrebare.");
                    } else {
                        intrebari.add(intrebare);
                        raspunsuri.add(raspunsCorect);
                        //resetare campuri
                        intrebareField.setText("");
                        raspunsCorectDropdown.setSelectedIndex(0);
                    }
                }
            });
        }

        JButton butonAdaugareTest = new JButton("Adauga Test");
        frameP.add(butonAdaugareTest);

        butonAdaugareTest.addActionListener(new ActionListener() {
            Fisier fisier = new Fisier(); //instantiere fisier
            @Override
            public void actionPerformed(ActionEvent e) {
                if (test == null) {
                    test = new Test();
                    test.setMaterie(profesor.getMaterie()); //seteaza materia testului
                }

                if (intrebari.size() == 4 && raspunsuri.size() == 4) {
                    for (int i = 0; i < 4; i++) {
                        test.addIntrebare(new TrueFalseQuestion(intrebari.get(i), raspunsuri.get(i))); //adauga o intrebare
                    }
                    fisier.adaugaTestInFisier(numeFisier,test); //adaugare in fisier

                    int choice = JOptionPane.showOptionDialog( //pop-up  pentru inchidere sau continuare
                            frameP,
                            "Test adaugat cu success! Doriti sa adaugati un alt test sau doriti sa inchideti programul?",
                            "Continuati sau inchideti?",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            new String[]{"Adauga alt test", "Inchide programul"},
                            "Adauga alt test"
                    );

                    if (choice == JOptionPane.NO_OPTION) {
                        frameP.dispose(); //inchiderea ferestrei
                    } else {
                        //resetarea campurilor
                        intrebari.clear();
                        raspunsuri.clear();
                        frameP.repaint();
                        panelProfesor(); //revenirea la fereastra principala
                    }
                } else {
                    JOptionPane.showMessageDialog(frameP, "Introduceti toate cele 4 intrebari si raspunsurile corecte.");
                }
            }
        });

        frameP.setPreferredSize(new Dimension(1000, 300));
        frameP.pack();
        frameP.setLocationRelativeTo(null);
        frameP.setVisible(true);
    }



    public static void creeareCheckboxQuiz() { //metoda de creare a testelor de tip Checkbox
        Logger.getInstance().log("Se creeaza un test de CheckBox"); //log
        ArrayList<String> intrebari = new ArrayList<>();
        ArrayList<String> raspunsuriCorecte = new ArrayList<>();
        ArrayList<ArrayList<String>> raspunsuriGresite = new ArrayList<>();

        JFrame frameP = new JFrame("FereastraCB");
        frameP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameP.setSize(1000, 700);
        frameP.setLayout(new GridLayout(0, 1));

        for (int i = 1; i < 5; i++) {//se creeaza patru instante de introducere a intrebarilor si raspunsurilor
            JLabel intrebareLabel = new JLabel("Intrebarea " + i + ":");
            intrebareLabel.setFont(customFont);
            frameP.add(intrebareLabel);
            JTextField intrebareField = new JTextField(30);
            frameP.add(intrebareField);

            JLabel raspunsCorectLabel = new JLabel("Raspunsul corect pentru intrebarea " + i + ":");
            raspunsCorectLabel.setFont(customFont);
            frameP.add(raspunsCorectLabel);
            JTextField raspunsCorectField = new JTextField(30);
            frameP.add(raspunsCorectField);

            JLabel raspunsuriGresiteLabel = new JLabel("Raspunsurile gresite pentru intrebarea " + i + " (separate prin virgula):");
            raspunsuriGresiteLabel.setFont(customFont);
            frameP.add(raspunsuriGresiteLabel);
            JTextField raspunsuriGresiteField = new JTextField(30);
            frameP.add(raspunsuriGresiteField);

            JButton adaugaIntrebareButton = new JButton("Adauga Intrebare " + i);
            frameP.add(adaugaIntrebareButton);

            adaugaIntrebareButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String intrebare = intrebareField.getText();
                    String raspunsCorect = raspunsCorectField.getText();
                    String[] raspunsuriGresiteArray = raspunsuriGresiteField.getText().split(","); // raspunsuri
                    //separate prin virgula

                    if (intrebare.isEmpty() || raspunsCorect.isEmpty() || raspunsuriGresiteArray.length != 3 ) {
                        //verifica daca campurile sunt goale sau daca sunt prea multe raspunsuri gresite
                        JOptionPane.showMessageDialog(frameP, "Introduceti toate datele corect.");
                    } else {
                        intrebari.add(intrebare);
                        raspunsuriCorecte.add(raspunsCorect);
                        ArrayList<String> raspunsuriGresiteList = new ArrayList<>(Arrays.asList(raspunsuriGresiteArray));
                        raspunsuriGresiteList.add(raspunsCorect);
                        raspunsuriGresite.add(raspunsuriGresiteList);

                        intrebareField.setText("");
                        raspunsuriGresiteField.setText("");
                        raspunsCorectField.setText("");
                    }
                }
            });
        }

        JButton adaugaTestButton = new JButton("Adauga Test");
        frameP.add(adaugaTestButton);

        adaugaTestButton.addActionListener(new ActionListener() {
            Fisier fisier = new Fisier(); //instantiere fisier

            @Override
            public void actionPerformed(ActionEvent e) {
                if (test == null) {
                    test = new Test();
                    test.setMaterie(profesor.getMaterie());
                }

                if (intrebari.size() == 4 && raspunsuriCorecte.size() == 4 ) { //verifica daca sunt doar  4 intrebari si 4 rasp. corecte
                    for (int i = 0; i < 4; i++) {
                        test.addIntrebare(new CheckboxQuestion(intrebari.get(i), raspunsuriGresite.get(i), raspunsuriCorecte.get(i)));
                    }
                    fisier.adaugaTestInFisier(numeFisier,test); //adaugare test in fisier


                    int choice = JOptionPane.showOptionDialog( //pop-up de inchidere sau continuare
                            frameP,
                            "Test adaugat cu success! Doriti sa adaugati un alt test sau doriti sa inchideti programul?",
                            "Continuati sau inchideti?",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            new String[]{"Adauga alt test", "Inchide programul"},
                            "Adauga alt test"
                    );

                    if (choice == JOptionPane.NO_OPTION) {
                        frameP.dispose(); //inchidere
                    } else {
                        intrebari.clear();
                        raspunsuriCorecte.clear();
                        raspunsuriGresite.clear();
                        frameP.repaint();
                        panelProfesor();//revenire la panel-ul principal
                    }
                } else {
                    JOptionPane.showMessageDialog(frameP, "Introduceti toate cele 4 intrebari si raspunsurile corecte.");
                }
            }
        });
       //setari
        frameP.setPreferredSize(new Dimension(1000, 700));
        frameP.pack();
        frameP.setLocationRelativeTo(null);
        frameP.setVisible(true);
    }


    public static void panelStudent() {
        Fisier fisier = new Fisier();
        Logger.getInstance().log("Se Deschide Fereastra Principala Elev");
        JFrame frameS = new JFrame("FereastraStudent");
        frameS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameS.setSize(1000, 600);
        frameS.setLayout(new GridLayout());
        JTabbedPane tabbedPane = new JTabbedPane(); //tabbed pane pentru diferitele teste
        frameS.add(tabbedPane);
        ArrayList<Test> teste= adaugaTeste();
        for (Test test : teste) {
            JPanel testPanel = createTestPanel(test); //initializeaza createTestPanel
            //System.out.println(test.toString());
            tabbedPane.addTab("Test " + test.getMaterie(), testPanel); //adauga panel-ul in Tabbed cu titlul corespunzator
        }


        frameS.setPreferredSize(new Dimension(1000, 600));
        frameS.pack();
        frameS.setLocationRelativeTo(null);
        frameS.setVisible(true);
    }

   public static int contorCorect=0;
    private static JPanel createTestPanel(Test test) { //metoda cu scopul de a scrie diferitele panel-uri pentru fiecare test


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(40,1));
        if (test.hasCheckboxQuestions()) { //verifica daca este Checkbox
            for (Intrebare intrebare : test.getIntrebari()) { //itereaza prin intrebari
                if (intrebare instanceof CheckboxQuestion) {
                    JLabel intrebareLabel = new JLabel(intrebare.getIntrebare()); //Label pentru intrebare
                    ArrayList<String> raspunsuri = new ArrayList<>(((CheckboxQuestion) intrebare).getRaspunsuriGresite());
                    String raspCorect =  intrebare.getRaspunsCorect();

                    raspunsuri.add(raspCorect);

                    ButtonGroup buttonGroup = new ButtonGroup();
                    panel.add(intrebareLabel);
                    for (String choice : raspunsuri) {
                        JRadioButton radioButton = new JRadioButton(choice); //un JRadioButton pentru fiecare varianta
                        radioButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (radioButton.isSelected() && ((CheckboxQuestion)intrebare).verificaRaspuns(choice)) {
                                    //daca ai ales varianta corecta creste contorul
                                    //contorul determina nr de raspunsuri corecte
                                    contorCorect++;
                                }
                            }
                        });
                        buttonGroup.add(radioButton);
                        panel.add(radioButton);
                    }


                }
            }
        } else
            if (test.hasTrueFalseQuestions()) { //daca testul este de tipul True/false
                for (Intrebare intrebare : test.getIntrebari()) {
                    if (intrebare instanceof TrueFalseQuestion) {
                        JLabel questionLabel = new JLabel(intrebare.getIntrebare());
                        ButtonGroup trueFalseGroup = new ButtonGroup();
                        JRadioButton trueRadioButton = new JRadioButton("Adevărat"); //2 butoane JRadio
                        JRadioButton falseRadioButton = new JRadioButton("Fals");
                        trueFalseGroup.add(trueRadioButton);
                        trueFalseGroup.add(falseRadioButton);

                        String raspunsCorect = intrebare.getRaspunsCorect();

                        trueRadioButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (trueRadioButton.isSelected() && raspunsCorect.equals("true")) { //verifica daca
                                    //raspunsul este selectat si true , apoi creste contorul
                                    contorCorect++;
                                }
                            }
                        });

                        falseRadioButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (falseRadioButton.isSelected() && raspunsCorect.equals("false")) {
                                    //verifica daca butonul este selectat si false
                                    contorCorect++;
                                }
                            }
                        });

                        panel.add(questionLabel);
                        panel.add(trueRadioButton);
                        panel.add(falseRadioButton);
                    }
                }
            }


        JButton checkAnswersButton = new JButton("Verifica Rezultate"); //buton

        checkAnswersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {Logger.getInstance().log("Se verifica un test");//Log

                rezultatTest(panel); //initiaza rezultatTest
            }
        });
        panel.add(checkAnswersButton);



        return panel;
    }
   public static void rezultatTest(JPanel panel){
       int choice = JOptionPane.showOptionDialog( //pop-up pentru afisarea rezultatului si pentru continuare/inchidere
               panel,
               "Felicitari ati avut "+ contorCorect + " raspunsuri corecte! Doriti sa adaugati un alt test sau doriti sa inchideti programul?",
               "Continuati sau inchideti?",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE,
               null,
               new String[]{"Continua", "Inchide programul"},
               "Continua"
       );

       if (choice == JOptionPane.NO_OPTION) {
           System.exit(0);

       } else {
           contorCorect=0;

       }



   }

    private static Test test;


    public static ArrayList<Test> adaugaTeste() { //metoda ce initializeaza 3 teste
        ArrayList<Test> teste = new ArrayList<Test>();



        ArrayList<Intrebare> q1 = new ArrayList<Intrebare>();
        q1.add(new TrueFalseQuestion("Capitala Frantei este Paris.", "true"));
        q1.add(new TrueFalseQuestion("Raul Amazon este situat in Africa.", "false"));
        q1.add(new TrueFalseQuestion("Cea mai inalta varf a Muntilor Carpati este Moldoveanu.", "true"));
        q1.add(new TrueFalseQuestion("India este o insula.", "false"));
        Test quiz1 = new Test("Geografie",q1);
        teste.add(quiz1);


        ArrayList<Intrebare> q2 = new ArrayList<Intrebare>();
        q2.add(new CheckboxQuestion("Selectati numerele prime:", new ArrayList<>(Arrays.asList("1", "2", "4")),"2"));
        q2.add(new CheckboxQuestion("2+1= ? ", new ArrayList<>(Arrays.asList("1", "2", "4")),"3"));
        q2.add(new CheckboxQuestion("Patrat cu l=2 . Aria = ? ", new ArrayList<>(Arrays.asList("1", "2", "20")),"4"));
        q2.add(new CheckboxQuestion("Patrat cu l=3 . Aria = ? ", new ArrayList<>(Arrays.asList("1", "2", "20")),"9"));

        Test quiz2 = new Test("Matematica",q2);
        teste.add(quiz2);



        ArrayList<Intrebare> q3 = new ArrayList<Intrebare>();
        q3.add(new TrueFalseQuestion("Primul razboi mondial a inceput in anul 1914.", "true"));
        q3.add(new TrueFalseQuestion("Napoleon Bonaparte a fost un general britanic.", "false"));
        q3.add(new TrueFalseQuestion("Thomas Jefferson a fost al treilea presedinte al Statelor Unite.", "true"));
        q3.add(new TrueFalseQuestion("Revolutia Franceza a inceput in anul 1789.", "true"));
        Test quiz3 = new Test("Istorie",q3);
        teste.add(quiz3);

        return teste;
    }





}
