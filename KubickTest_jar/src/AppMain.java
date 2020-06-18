import arduino.AlertBox;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import javafx.scene.transform.Scale;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class AppMain {
    static Person defaultPerson;
    static Person person = null;
    static String folderPath = "./src/resources/";
    private static BufferedImage icon = null;
    static AppMain appMain;

    static {
        appMain = new AppMain();
        ArrayList<String> courses = new ArrayList<>();
        courses.add("Список курсов");
        ArrayList<String> achievements = new ArrayList<>();
        achievements.add("Список достижений");
        defaultPerson = new Person("0", "Имя", "Фамилия", courses, achievements, 100);
        String pathPhoto = folderPath + "icon" + ".jpg";
        File file = new File(pathPhoto);
        icon = null;
        try {
            icon = ImageIO.read(file);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    JFrame frame;

    public static String uid = "";
    private JPanel panel1;
    private JButton loadButton;
    private JLabel lvlLabel;
    private JLabel xpLabel;
    private JTextField uField;
    private JTextField fField;
    private JLabel aLabel;
    private JScrollPane aScroll;
    private JLabel cLabel;
    private JScrollPane cScroll;
    private JLabel fLabel;
    private JLabel uLabel;
    private JList list;
    private JList list1;
    private JLabel photoLabel;
    private BufferedImage image;


    public AppMain() {
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uField.setText(uid);
                FileInputStream fis = null;
                try {
                    String fileName = uid.replace("\n", " ").trim();
                    fis = new FileInputStream(folderPath + fileName + ".out");
                    ObjectInputStream oin = new ObjectInputStream(fis);
                    person = (Person) oin.readObject();

                    try {
                        String pathPhoto = folderPath + fileName + ".jpg";
                        File file = new File(pathPhoto);
                        image = ImageIO.read(file);
                        photoLabel.setIcon(new ImageIcon(image));
                    } catch (IOException ex) {
                        System.out.println("Photo not found");
                        String pathPhoto = folderPath + "default" + ".jpg";
                        File file = new File(pathPhoto);
                        try {
                            image = ImageIO.read(file);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        photoLabel.setIcon(new ImageIcon(image));
                    }

                } catch (IOException | ClassNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                    person = defaultPerson;
                    String pathPhoto = folderPath + "default" + ".jpg";
                    File file = new File(pathPhoto);
                    try {
                        image = ImageIO.read(file);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    photoLabel.setIcon(new ImageIcon(image));
                }
                showData(AppMain.this);
            }

        });
    }

    public static void showData(AppMain appMain) {
        appMain.fField.setText(person.getFirstName() + " " + person.getLastName());
        String[] cA = new String[person.getCourses().size()];
        for (int i = 0; i < person.getCourses().size(); i++) {
            cA[i] = person.getCourses().get(i);
        }
        Font font = appMain.aLabel.getFont();
        String[] aA = new String[person.getAchievements().size()];
        for (int i = 0; i < person.getAchievements().size(); i++) {
            aA[i] = person.getAchievements().get(i);
        }
        appMain.list = new JList(cA);
        appMain.list.setFont(font);
        appMain.list.setVisibleRowCount(3);
        appMain.cScroll.setViewportView(appMain.list);
        appMain.list1 = new JList(aA);
        appMain.list1.setFont(font);
        appMain.list1.setVisibleRowCount(3);
        appMain.aScroll.setViewportView(appMain.list1);
        appMain.lvlLabel.setText("Уровень: " + person.getExp() / 100);
        appMain.xpLabel.setText("Опыт: " + person.getExp());
    }

    public static void main(String[] args) throws InterruptedException {


        ComReader comReader = new ComReader();
        Thread comThread = new Thread(comReader, "ComReader");
        comThread.start();
        appMain.frame = new JFrame("Personal cards");
        if (icon != null) appMain.frame.setIconImage(icon);
        appMain.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem addExp = new JMenuItem("Add Exp");
        addExp.addActionListener(new AddExpListener());
        JMenuItem addPerson = new JMenuItem("Add Person");
        addPerson.addActionListener(new AddPersonListener());
        JMenuItem addCourses = new JMenuItem("Add Courses&Achieves");
        addCourses.addActionListener(new AddCoursesListener());
        fileMenu.add(addExp);
        fileMenu.add(addPerson);
        fileMenu.add(addCourses);
        menuBar.add(fileMenu);
        appMain.frame.setJMenuBar(menuBar);
        appMain.frame.add(appMain.panel1);
        appMain.frame.setSize(900, 600);
        appMain.frame.setBounds(100, 100, 900, 650);
        appMain.frame.setVisible(true);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(5, 5, 5, 5), -1, -1));
        panel1.setMaximumSize(new Dimension(900, 600));
        panel1.setMinimumSize(new Dimension(900, 600));
        panel1.setOpaque(false);
        panel1.setPreferredSize(new Dimension(900, 600));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, -1, -1, panel1.getFont()), null));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(300, 550), new Dimension(300, 550), new Dimension(300, 550), 0, false));
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        lvlLabel = new JLabel();
        lvlLabel.setEnabled(true);
        Font lvlLabelFont = this.$$$getFont$$$("JetBrains Mono", -1, 28, lvlLabel.getFont());
        if (lvlLabelFont != null) lvlLabel.setFont(lvlLabelFont);
        lvlLabel.setHorizontalAlignment(0);
        lvlLabel.setHorizontalTextPosition(0);
        lvlLabel.setText("Уровень: 0");
        panel2.add(lvlLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(250, 50), new Dimension(250, 50), new Dimension(250, 50), 0, false));
        xpLabel = new JLabel();
        Font xpLabelFont = this.$$$getFont$$$("JetBrains Mono", -1, 28, xpLabel.getFont());
        if (xpLabelFont != null) xpLabel.setFont(xpLabelFont);
        xpLabel.setHorizontalAlignment(0);
        xpLabel.setHorizontalTextPosition(0);
        xpLabel.setText("Опыт: 0");
        panel2.add(xpLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(250, 50), new Dimension(250, 50), new Dimension(250, 50), 0, false));
        loadButton = new JButton();
        Font loadButtonFont = this.$$$getFont$$$("JetBrains Mono", -1, 28, loadButton.getFont());
        if (loadButtonFont != null) loadButton.setFont(loadButtonFont);
        loadButton.setText("Load");
        loadButton.setVerifyInputWhenFocusTarget(true);
        panel2.add(loadButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(250, 50), new Dimension(250, 50), new Dimension(250, 50), 0, false));
        photoLabel = new JLabel();
        Font photoLabelFont = this.$$$getFont$$$("JetBrains Mono", -1, 28, photoLabel.getFont());
        if (photoLabelFont != null) photoLabel.setFont(photoLabelFont);
        photoLabel.setHorizontalAlignment(0);
        photoLabel.setHorizontalTextPosition(0);
        photoLabel.setIcon(new ImageIcon(getClass().getResource("/resources/default.jpg")));
        photoLabel.setText("");
        panel2.add(photoLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(280, 360), null, null, 1, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(510, 550), new Dimension(-1, 550), new Dimension(-1, 550), 1, false));
        panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel4, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(500, 100), null, null, 0, false));
        panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        aLabel = new JLabel();
        Font aLabelFont = this.$$$getFont$$$("JetBrains Mono", -1, 24, aLabel.getFont());
        if (aLabelFont != null) aLabel.setFont(aLabelFont);
        aLabel.setText("ACHIEVES:");
        panel4.add(aLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, 50), null, null, 1, false));
        aScroll = new JScrollPane();
        Font aScrollFont = this.$$$getFont$$$("JetBrains Mono", -1, 24, aScroll.getFont());
        if (aScrollFont != null) aScroll.setFont(aScrollFont);
        aScroll.setHorizontalScrollBarPolicy(31);
        panel4.add(aScroll, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(280, -1), new Dimension(280, -1), new Dimension(280, -1), 0, false));
        list1 = new JList();
        list1.setEnabled(false);
        Font list1Font = this.$$$getFont$$$("JetBrains Mono", -1, 24, list1.getFont());
        if (list1Font != null) list1.setFont(list1Font);
        list1.setVisibleRowCount(3);
        aScroll.setViewportView(list1);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel5, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(500, 100), null, null, 0, false));
        panel5.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        cLabel = new JLabel();
        Font cLabelFont = this.$$$getFont$$$("JetBrains Mono", -1, 28, cLabel.getFont());
        if (cLabelFont != null) cLabel.setFont(cLabelFont);
        cLabel.setText("COURSE:");
        panel5.add(cLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, 50), new Dimension(150, 50), new Dimension(150, 50), 1, false));
        cScroll = new JScrollPane();
        Font cScrollFont = this.$$$getFont$$$("JetBrains Mono", -1, 24, cScroll.getFont());
        if (cScrollFont != null) cScroll.setFont(cScrollFont);
        panel5.add(cScroll, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(280, -1), new Dimension(280, -1), new Dimension(280, -1), 0, false));
        list = new JList();
        list.setEnabled(false);
        Font listFont = this.$$$getFont$$$("JetBrains Mono", -1, 24, list.getFont());
        if (listFont != null) list.setFont(listFont);
        list.setVisibleRowCount(3);
        cScroll.setViewportView(list);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel6, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(500, 100), null, null, 0, false));
        panel6.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        fLabel = new JLabel();
        Font fLabelFont = this.$$$getFont$$$("JetBrains Mono", -1, 28, fLabel.getFont());
        if (fLabelFont != null) fLabel.setFont(fLabelFont);
        fLabel.setText("FIO:");
        panel6.add(fLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, 50), null, null, 1, false));
        fField = new JTextField();
        fField.setEnabled(false);
        Font fFieldFont = this.$$$getFont$$$("JetBrains Mono", -1, 28, fField.getFont());
        if (fFieldFont != null) fField.setFont(fFieldFont);
        fField.setText("");
        panel6.add(fField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(280, -1), new Dimension(280, -1), null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel7, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(500, 100), null, null, 0, false));
        panel7.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        uLabel = new JLabel();
        Font uLabelFont = this.$$$getFont$$$("JetBrains Mono", -1, 28, uLabel.getFont());
        if (uLabelFont != null) uLabel.setFont(uLabelFont);
        uLabel.setText("UID:");
        panel7.add(uLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, 50), null, null, 1, false));
        uField = new JTextField();
        uField.setEnabled(false);
        Font uFieldFont = this.$$$getFont$$$("Droid Sans Mono", -1, 22, uField.getFont());
        if (uFieldFont != null) uField.setFont(uFieldFont);
        panel7.add(uField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(280, -1), new Dimension(280, -1), null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(500, 100), null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("JetBrains Mono", -1, 36, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("KUBUK Student card");
        panel8.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }


    private static class AddExpListener extends AppMain implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            person.addExp();
            loadPerson(person);
            AlertBox alert = new AlertBox(new Dimension(400, 100), "Success", "10 EXP Added");
            showData(appMain);
            alert.display();
        }
    }

    public static void loadPerson(Person person) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(folderPath + person.getUid() + ".out");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(person);
            oos.close();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    //add person
    public static class AddPersonListener implements ActionListener {
        private JLabel apUidLabel;
        private JLabel apFioLabel;
        private JLabel apExpLabel;
        private JLabel apPhotoLabel;
        private JTextField apUidField;
        private JTextField apFioField;
        private JTextField apExpField;
        private JButton apPhotoButton;
        private JButton apOkButton;
        private JButton apCancelButton;
        private JPanel apMainPanel;
        private JFrame addPersonFrame;
        private File file;

        public void loadPicture(File file) {
            File end = new File(folderPath + apUidField.getText() + ".jpg");
            end.delete();
            try {
                Files.copy(file.toPath(), end.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public AddPersonListener() {

            apOkButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String loadUid = apUidField.getText();
                    String loadFirstName = apFioField.getText().split(" ")[0];
                    String loadLastName = apFioField.getText().split(" ")[1];
                    int loadExp = Integer.parseInt(apExpField.getText());
                    Person loadPerson = new Person(loadUid, loadFirstName, loadLastName, new ArrayList<>(), new ArrayList<>(), loadExp);
                    loadPerson(loadPerson);

                    addPersonFrame.setVisible(false); //you can't see me!
                    addPersonFrame.dispose();
                    AlertBox alert = new AlertBox(new Dimension(400, 100), "Success", "Student Added");
                    alert.display();
                }
            });
            apPhotoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser filePhoto = new JFileChooser();
                    filePhoto.showOpenDialog(addPersonFrame);
                    file = filePhoto.getSelectedFile();
                    apPhotoButton.setText(file.getName());
                    loadPicture(file);
                }
            });
            apCancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addPersonFrame.setVisible(false); //you can't see me!
                    addPersonFrame.dispose();
                }
            });
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            addPersonFrame = new JFrame("Add Person");
            apFioField.setText("");
            apExpField.setText("");
            apPhotoButton.setText("Load");
            System.out.println("**");
            addPersonFrame.add(apMainPanel);
            appMain.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            addPersonFrame.setSize(550, 350);
            addPersonFrame.setBounds(200, 200, 550, 350);
            addPersonFrame.setVisible(true);
            apUidField.setText(uid.replace("\n", " ").trim());
        }

        {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
            $$$setupUI$$$();
        }

        /**
         * Method generated by IntelliJ IDEA GUI Designer
         * >>> IMPORTANT!! <<<
         * DO NOT edit this method OR call it in your code!
         *
         * @noinspection ALL
         */
        private void $$$setupUI$$$() {
            apMainPanel = new JPanel();
            apMainPanel.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
            apMainPanel.setMaximumSize(new Dimension(500, 300));
            apMainPanel.setMinimumSize(new Dimension(500, 300));
            apMainPanel.setPreferredSize(new Dimension(300, 300));
            apMainPanel.setRequestFocusEnabled(false);
            apMainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
            final JPanel panel1 = new JPanel();
            panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
            Font panel1Font = this.$$$getFont$$$("JetBrains Mono", -1, 18, panel1.getFont());
            if (panel1Font != null) panel1.setFont(panel1Font);
            apMainPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(500, 50), new Dimension(500, 50), null, 0, false));
            panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
            apUidLabel = new JLabel();
            Font apUidLabelFont = this.$$$getFont$$$("JetBrains Mono", -1, 20, apUidLabel.getFont());
            if (apUidLabelFont != null) apUidLabel.setFont(apUidLabelFont);
            apUidLabel.setText("UID:");
            panel1.add(apUidLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, 50), null, null, 1, false));
            apUidField = new JTextField();
            Font apUidFieldFont = this.$$$getFont$$$("JetBrains Mono", -1, 20, apUidField.getFont());
            if (apUidFieldFont != null) apUidField.setFont(apUidFieldFont);
            panel1.add(apUidField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(350, 40), new Dimension(350, 40), null, 0, false));
            final JPanel panel2 = new JPanel();
            panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
            apMainPanel.add(panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(500, 50), new Dimension(500, 50), null, 0, false));
            panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
            apFioLabel = new JLabel();
            Font apFioLabelFont = this.$$$getFont$$$("JetBrains Mono", -1, 20, apFioLabel.getFont());
            if (apFioLabelFont != null) apFioLabel.setFont(apFioLabelFont);
            apFioLabel.setText("FIO: ");
            panel2.add(apFioLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, 50), null, null, 1, false));
            apFioField = new JTextField();
            Font apFioFieldFont = this.$$$getFont$$$("JetBrains Mono", -1, 20, apFioField.getFont());
            if (apFioFieldFont != null) apFioField.setFont(apFioFieldFont);
            panel2.add(apFioField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(350, 40), new Dimension(350, 40), null, 0, false));
            final JPanel panel3 = new JPanel();
            panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
            apMainPanel.add(panel3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(500, 50), new Dimension(500, 50), null, 0, false));
            panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
            apExpLabel = new JLabel();
            Font apExpLabelFont = this.$$$getFont$$$("JetBrains Mono", -1, 20, apExpLabel.getFont());
            if (apExpLabelFont != null) apExpLabel.setFont(apExpLabelFont);
            apExpLabel.setText("Exp:");
            panel3.add(apExpLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, 50), null, null, 1, false));
            apExpField = new JTextField();
            Font apExpFieldFont = this.$$$getFont$$$("JetBrains Mono", -1, 20, apExpField.getFont());
            if (apExpFieldFont != null) apExpField.setFont(apExpFieldFont);
            panel3.add(apExpField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(350, 40), new Dimension(350, 40), null, 0, false));
            final JPanel panel4 = new JPanel();
            panel4.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
            apMainPanel.add(panel4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(500, 50), new Dimension(500, 50), null, 0, false));
            panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
            apPhotoLabel = new JLabel();
            Font apPhotoLabelFont = this.$$$getFont$$$("JetBrains Mono", -1, 20, apPhotoLabel.getFont());
            if (apPhotoLabelFont != null) apPhotoLabel.setFont(apPhotoLabelFont);
            apPhotoLabel.setText("Photo:");
            panel4.add(apPhotoLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, 50), new Dimension(109, 26), null, 1, false));
            apPhotoButton = new JButton();
            Font apPhotoButtonFont = this.$$$getFont$$$("JetBrains Mono", -1, 20, apPhotoButton.getFont());
            if (apPhotoButtonFont != null) apPhotoButton.setFont(apPhotoButtonFont);
            apPhotoButton.setText("Load photo");
            panel4.add(apPhotoButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, -1), null, null, 0, false));
            final JPanel panel5 = new JPanel();
            panel5.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
            apMainPanel.add(panel5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(350, 50), new Dimension(350, 50), null, 0, false));
            panel5.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
            apOkButton = new JButton();
            Font apOkButtonFont = this.$$$getFont$$$("JetBrains Mono", -1, 20, apOkButton.getFont());
            if (apOkButtonFont != null) apOkButton.setFont(apOkButtonFont);
            apOkButton.setText("Ok");
            panel5.add(apOkButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), null, null, 0, false));
            apCancelButton = new JButton();
            Font apCancelButtonFont = this.$$$getFont$$$("JetBrains Mono", -1, 20, apCancelButton.getFont());
            if (apCancelButtonFont != null) apCancelButton.setFont(apCancelButtonFont);
            apCancelButton.setText("Cancel");
            panel5.add(apCancelButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), null, null, 0, false));
        }

        /**
         * @noinspection ALL
         */
        private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
            if (currentFont == null) return null;
            String resultName;
            if (fontName == null) {
                resultName = currentFont.getName();
            } else {
                Font testFont = new Font(fontName, Font.PLAIN, 10);
                if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                    resultName = fontName;
                } else {
                    resultName = currentFont.getName();
                }
            }
            return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        }

        /**
         * @noinspection ALL
         */
        public JComponent $$$getRootComponent$$$() {
            return apMainPanel;
        }

    }

    public static class AddCoursesListener implements ActionListener {
        private JTextField acCField;
        private JButton acCButton;
        private JTextField acAField;
        private JButton acAButton;
        private JLabel acCLabel;
        private JLabel acALabel;
        private JPanel acPanel;
        private JFrame acFrame;

        public AddCoursesListener() {
            acCButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (acCField.getText().length() > 2) {
                        ArrayList<String> list = person.getCourses();
                        list.add(acCField.getText());
                        person.setCourses(list);
                        loadPerson(person);
                        acCField.setText("");
                        showData(appMain);
                        AlertBox alert = new AlertBox(new Dimension(400, 100), "Success", "Course added");
                        alert.display();
                    }
                }
            });
            acAButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (acAField.getText().length() > 2) {
                        ArrayList<String> list = person.getAchievements();
                        list.add(acAField.getText());
                        person.setAchievements(list);
                        loadPerson(person);
                        acAField.setText("");
                        showData(appMain);
                        AlertBox alert = new AlertBox(new Dimension(400, 100), "Success", "Achievement added");
                        alert.display();
                    }
                }
            });
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            acFrame = new JFrame("Add Courses & Achievements");
            acAField.setText("");
            acCField.setText("");
            acFrame.add(acPanel);
            appMain.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            acFrame.setSize(550, 350);
            acFrame.setBounds(200, 200, 550, 350);
            acFrame.setVisible(true);

        }

        {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
            $$$setupUI$$$();
        }

        /**
         * Method generated by IntelliJ IDEA GUI Designer
         * >>> IMPORTANT!! <<<
         * DO NOT edit this method OR call it in your code!
         *
         * @noinspection ALL
         */
        private void $$$setupUI$$$() {
            acPanel = new JPanel();
            acPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
            acPanel.setMaximumSize(new Dimension(500, 110));
            acPanel.setMinimumSize(new Dimension(500, 110));
            acPanel.setPreferredSize(new Dimension(500, 110));
            acPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
            final JPanel panel1 = new JPanel();
            panel1.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
            acPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(490, 50), null, null, 0, false));
            acCLabel = new JLabel();
            Font acCLabelFont = this.$$$getFont$$$("JetBrains Mono", -1, 20, acCLabel.getFont());
            if (acCLabelFont != null) acCLabel.setFont(acCLabelFont);
            acCLabel.setText("Course:");
            panel1.add(acCLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(120, 30), null, null, 1, false));
            acCField = new JTextField();
            Font acCFieldFont = this.$$$getFont$$$("JetBrains Mono", -1, 20, acCField.getFont());
            if (acCFieldFont != null) acCField.setFont(acCFieldFont);
            panel1.add(acCField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, -1), new Dimension(150, -1), null, 0, false));
            acCButton = new JButton();
            Font acCButtonFont = this.$$$getFont$$$("JetBrains Mono", -1, 12, acCButton.getFont());
            if (acCButtonFont != null) acCButton.setFont(acCButtonFont);
            acCButton.setText("Load Course");
            panel1.add(acCButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(140, -1), null, null, 0, false));
            final JPanel panel2 = new JPanel();
            panel2.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
            acPanel.add(panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(490, 50), null, null, 0, false));
            acALabel = new JLabel();
            Font acALabelFont = this.$$$getFont$$$("JetBrains Mono", -1, 20, acALabel.getFont());
            if (acALabelFont != null) acALabel.setFont(acALabelFont);
            acALabel.setText("Achieved:");
            panel2.add(acALabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(120, 30), null, null, 1, false));
            acAField = new JTextField();
            Font acAFieldFont = this.$$$getFont$$$("JetBrains Mono", -1, 20, acAField.getFont());
            if (acAFieldFont != null) acAField.setFont(acAFieldFont);
            panel2.add(acAField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, -1), new Dimension(150, -1), null, 0, false));
            acAButton = new JButton();
            Font acAButtonFont = this.$$$getFont$$$("JetBrains Mono", -1, 12, acAButton.getFont());
            if (acAButtonFont != null) acAButton.setFont(acAButtonFont);
            acAButton.setText("Load Achieved");
            panel2.add(acAButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(140, -1), null, null, 0, false));
        }

        /**
         * @noinspection ALL
         */
        private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
            if (currentFont == null) return null;
            String resultName;
            if (fontName == null) {
                resultName = currentFont.getName();
            } else {
                Font testFont = new Font(fontName, Font.PLAIN, 10);
                if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                    resultName = fontName;
                } else {
                    resultName = currentFont.getName();
                }
            }
            return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        }

        /**
         * @noinspection ALL
         */
        public JComponent $$$getRootComponent$$$() {
            return acPanel;
        }
    }
}