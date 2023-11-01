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
    public static Color backgroundColor = new Color(131, 218, 131);
    public static Color textColor = new Color(36, 59, 36);
    public static Font customFont = new Font("Serif", Font.PLAIN, 20);
    public static ArrayList<User> users = new ArrayList<>();
    public static String tipUser;
    private static UserProfesor profesor;
    private static UserElev elev;

    public static void main(String[] args) {
        Logger.getInstance().log("Pornire aplicatie");

        users.add(new UserElev("STU123", "John", "Doe"));
        users.add(new UserProfesor("PRO123", "Jane", "Smith", "Geografie"));

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
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(customFont);
        frame.add(passwordField);

        JButton loginButton = new JButton("Log in");
        loginButton.setFont(customFont);
        frame.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String parola = passwordField.getText();

                UserElev student = new UserElev();
                UserProfesor profesor = new UserProfesor();

                if (student.isValidPassword(parola)) {
                    tipUser = "elev";
                    Logger.getInstance().log("Platforma accesata de  Elev ");
                    frame.dispose();
                    JOptionPane.showMessageDialog(frame, "Platforma accesibila pentru Student ");

                    getID();
                } else if (profesor.isValidPassword(parola)) {
                    tipUser = "profesor";
                    Logger.getInstance().log("Platforma accesata de  Profesor ");
                    frame.dispose();
                    JOptionPane.showMessageDialog(frame, "Platforma accesibila pentru Profesor ");
                    getID();
                } else {
                    JOptionPane.showMessageDialog(frame, "Parola Incorecta!");
                }

                passwordField.setText("");
            }
        });

        frame.setPreferredSize(new Dimension(400, 200));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setBackground(backgroundColor);
        frame.setVisible(true);
    }

    public static void getID() {
        JFrame idFrame = new JFrame("Enter ID");
        idFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        idFrame.setSize(400, 150);
        idFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JLabel idLabel = new JLabel("Introduceti ID:");
        idLabel.setFont(customFont);
        idFrame.add(idLabel);

        JTextField idTextField = new JTextField(20);
        idFrame.add(idTextField);

        JButton idSubmitButton = new JButton("Submit");
        idSubmitButton.setFont(customFont);
        idFrame.add(idSubmitButton);

        JButton idInexistent = new JButton("Nu am un ID");
        idInexistent.setFont(customFont);
        idFrame.add(idInexistent);

        idInexistent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tipUser.equals("Elev")) {
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

                if (!idString.isEmpty()) {
                    boolean idExistaProf = false;
                    boolean idExistaStud = false;
                    for (User user : users) {
                        if (user instanceof UserProfesor) {
                            if (user.getID().equals(idString)) {
                                profesor = (UserProfesor) user;
                                idExistaProf = true;
                                break;
                            }
                        }
                        if (user instanceof UserElev) {
                            if (user.getID().equals(idString)) {
                                elev = (UserElev) user;
                                idExistaStud = true;
                                break;
                            }
                        }
                    }
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
                    JOptionPane.showMessageDialog(idFrame, "Introduceti un ID valid!");
                }
            }
        });

        idFrame.setPreferredSize(new Dimension(400, 200));
        idFrame.pack();
        idFrame.setLocationRelativeTo(null);
        idFrame.setBackground(backgroundColor);
        idFrame.setVisible(true);
    }

    public static UserProfesor panelLoginProfesor() {
        Logger.getInstance().log("Se Deschide platforma de Inregistrare Profesor");
        UserProfesor profesor = new UserProfesor();
        JFrame frameS = new JFrame("Fereastra Login Profesor");
        frameS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameS.setSize(1000, 600);
        frameS.setLayout(new GridLayout(0, 1));

        JComboBox<String> materieDropdown = new JComboBox<>(UserProfesor.getMaterii());
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

        String randomID = "PRO" + String.format("%03d", new Random().nextInt(1000));
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

        SubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String prenume = prenumeField.getText();
                String nume = numeField.getText();

                if (!prenume.isEmpty() && !nume.isEmpty()) {
                    if (prenume.charAt(0) == Character.toUpperCase(prenume.charAt(0))
                            && nume.charAt(0) == Character.toUpperCase(nume.charAt(0))) {
                        profesor.setNume(prenume);
                        profesor.setPrenume(prenume);

                        JOptionPane.showMessageDialog(frameS, "Bine ati Venit " + profesor.getNume() +
                                " " + profesor.getPrenume() + ". ID-ul dumneavoastra este : " + profesor.getID());

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
        return profesor;
    }

    public static void panelLoginStudent() {
        Logger.getInstance().log("Se Deschide platforma de Inregistrare Elev");
        UserElev elev = new UserElev();
        JFrame frameS = new JFrame("Fereastra Login Student");
        frameS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameS.setSize(1000, 600);
        frameS.setLayout(new GridLayout(0, 1));

        String randomID = "PRO" + String.format("%03d", new Random().nextInt(1000));
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

                if (!prenume.isEmpty() && !nume.isEmpty()) {
                    if (prenume.charAt(0) == Character.toUpperCase(prenume.charAt(0))
                            && nume.charAt(0) == Character.toUpperCase(nume.charAt(0))) {
                        elev.setNume(prenume);
                        elev.setPrenume(nume);

                        JOptionPane.showMessageDialog(frameS, "Bine ati Venit " + elev.getNume() +
                                " " + elev.getPrenume() + " ID-ul dumneavoastra este : " + randomID);

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
        SubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frameS, "Bine ati Venit " + elev.getNume() +
                        " " + elev.getPrenume() + " ID-ul dumneavoastra este : " + randomID);
                frameS.dispose();
                panelProfesor();
            }
        });
        frameS.setPreferredSize(new Dimension(1000, 600));
        frameS.pack();
        frameS.setLocationRelativeTo(null);
        frameS.setVisible(true);
    }

    public static void panelProfesor() {
        Logger.getInstance().log("Se Deschide Fereastra Principala Profesor");
        JFrame frameP = new JFrame("FereastraProfesor");
        frameP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameP.setSize(1000, 600);

        JScrollPane addQuestionPanel = panelAdaugareTest();
        JScrollPane viewQuestionPanel = panelVizualizareTest();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, addQuestionPanel, viewQuestionPanel);
        splitPane.setDividerLocation(500);

        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerSize(10);

        frameP.getContentPane().add(splitPane);

        frameP.setPreferredSize(new Dimension(1000, 600));
        frameP.pack();
        frameP.setLocationRelativeTo(null);
        frameP.setVisible(true);
    }

    public static void panelStudent() {
        Logger.getInstance().log("Se Deschide Fereastra Principala Elev");
        JFrame frameS = new JFrame("FereastraStudent");
        frameS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameS.setSize(1000, 600);
        frameS.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JTabbedPane tabbedPane = new JTabbedPane();

        frameS.setPreferredSize(new Dimension(1000, 600));
        frameS.pack();
        frameS.setLocationRelativeTo(null);
        frameS.setVisible(true);
    }
    private static Test test;
    private static JScrollPane panelAdaugareTest() {
        JPanel panelAdaugareTest = new JPanel();
        panelAdaugareTest.setLayout(new GridLayout(0, 1));  // Use a GridLayout with a single column

        Test test = new Test();
        test.setMaterie(profesor.getMaterie());

        JLabel titleLabel = new JLabel("Adauga Test");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        panelAdaugareTest.add(titleLabel);

        for (int i = 1; i <= 5; i++) {
            JLabel questionLabel = new JLabel("Intrebarea " + i + ":");
            JTextField questionField = new JTextField(30);

            JLabel answerLabel = new JLabel("Raspunsul corect pentru intrebarea " + i + ":");
            JTextField answerField = new JTextField(30);

            JLabel typeLabel = new JLabel("Tipul de intrebare (TrueFalse / Checkbox) pentru intrebarea " + i + ":");
            JTextField typeField = new JTextField(15);

            final int currentQuestionIndex = i; // Capture the current value of 'i'

            JButton addQuestionButton = new JButton("Adauga Intrebare " + i);

            panelAdaugareTest.add(questionLabel);
            panelAdaugareTest.add(questionField);
            panelAdaugareTest.add(answerLabel);
            panelAdaugareTest.add(answerField);
            panelAdaugareTest.add(typeLabel);
            panelAdaugareTest.add(typeField);
            panelAdaugareTest.add(addQuestionButton);

            addQuestionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String intrebare = questionField.getText();
                    String tipIntrebare = typeField.getText();
                    String raspunsCorect = answerField.getText();

                    if (tipIntrebare.equalsIgnoreCase("TrueFalse")) {

                        if (!raspunsCorect.equalsIgnoreCase("false") && !raspunsCorect.equalsIgnoreCase("true")) {
                            JOptionPane.showMessageDialog(panelAdaugareTest, "Rapunsul poate sa fie doar true sau false!");

                        } else {
                            test.addIntrebare(new TrueFalseQuestion(intrebare, raspunsCorect));
                            updateVizualizareTestPanel();
                            JOptionPane.showMessageDialog(null, "Intrebare adaugata cu success!");

                        }

                    } else if (tipIntrebare.equalsIgnoreCase("Checkbox")) {
                        String raspunsuriString = JOptionPane.showInputDialog("Raspunsurile gresite pentru intrebarea " + currentQuestionIndex + " (separate prin virgula):");
                        String[] raspunsuriArray = raspunsuriString.split(",");
                        ArrayList<String> raspunsuri = new ArrayList<>(Arrays.asList(raspunsuriArray));
                        raspunsuri.add(raspunsCorect);
                        test.addIntrebare(new CheckboxQuestion(intrebare, raspunsuri, raspunsCorect));
                        updateVizualizareTestPanel();
                        JOptionPane.showMessageDialog(null, "Intrebare adaugata cu success!");


                    } else {
                        JOptionPane.showMessageDialog(null, "Tipul de intrebare introdus nu este valid. Utilizati 'TrueFalse' sau 'Checkbox'.");
                    }
                }
            });
        }

        JScrollPane scrollPane = new JScrollPane(panelAdaugareTest);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        return scrollPane;
    }
    private static JPanel panelVizualizareTest;

    private static JScrollPane panelVizualizareTest() {
        JScrollPane scrollPane = new JScrollPane();
        panelVizualizareTest = new JPanel();
        panelVizualizareTest.setLayout(new BoxLayout(panelVizualizareTest, BoxLayout.PAGE_AXIS));

        JLabel titleLabel = new JLabel("Vizualizare Test");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        panelVizualizareTest.add(titleLabel);

        scrollPane.setViewportView(panelVizualizareTest);

        return scrollPane;
    }


    private static void updateVizualizareTestPanel() {
        panelVizualizareTest.removeAll();

        JLabel titleLabel = new JLabel("Vizualizare Test");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        panelVizualizareTest.add(titleLabel);

        if (test != null) {
            ArrayList<Intrebare> intrebari = test.getIntrebari();
            for (int i = 0; i < intrebari.size(); i++) {
                JLabel questionLabel = new JLabel("Intrebarea " + (i + 1) + ": " + intrebari.get(i).getIntrebare());
                panelVizualizareTest.add(questionLabel);
            }
        }

        panelVizualizareTest.revalidate();
        panelVizualizareTest.repaint();
    }




}

