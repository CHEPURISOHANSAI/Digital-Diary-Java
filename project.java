import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class DigitalDiary extends JFrame implements ActionListener {

    JTextField userField;
    JPasswordField passField;
    JButton loginBtn;

    JTextArea area;
    JButton saveBtn, openBtn, clearBtn;

    JPanel loginPanel, diaryPanel;

    String username = "manoj";
    String password = "1234";

    public DigitalDiary() {

        setTitle("Digital Diary");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // LOGIN PANEL
        loginPanel = new JPanel();
        loginPanel.setLayout(null);

        JLabel title = new JLabel("DIGITAL DIARY");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel user = new JLabel("Username:");
        JLabel pass = new JLabel("Password:");

        userField = new JTextField();
        passField = new JPasswordField();

        loginBtn = new JButton("Login");
        loginBtn.addActionListener(this);

        title.setBounds(240, 50, 250, 30);
        user.setBounds(180, 140, 100, 30);
        pass.setBounds(180, 190, 100, 30);

        userField.setBounds(280, 140, 180, 30);
        passField.setBounds(280, 190, 180, 30);

        loginBtn.setBounds(280, 250, 100, 35);

        loginPanel.add(title);
        loginPanel.add(user);
        loginPanel.add(pass);
        loginPanel.add(userField);
        loginPanel.add(passField);
        loginPanel.add(loginBtn);

        // DIARY PANEL
        diaryPanel = new JPanel();
        diaryPanel.setLayout(null);

        JLabel diaryTitle = new JLabel("MY PERSONAL DIARY");
        diaryTitle.setFont(new Font("Arial", Font.BOLD, 22));

        area = new JTextArea();
        JScrollPane scroll = new JScrollPane(area);

        saveBtn = new JButton("Save");
        openBtn = new JButton("Open");
        clearBtn = new JButton("Clear");

        saveBtn.addActionListener(this);
        openBtn.addActionListener(this);
        clearBtn.addActionListener(this);

        diaryTitle.setBounds(220, 20, 300, 30);
        scroll.setBounds(50, 70, 580, 280);

        saveBtn.setBounds(120, 380, 100, 35);
        openBtn.setBounds(280, 380, 100, 35);
        clearBtn.setBounds(440, 380, 100, 35);

        diaryPanel.add(diaryTitle);
        diaryPanel.add(scroll);
        diaryPanel.add(saveBtn);
        diaryPanel.add(openBtn);
        diaryPanel.add(clearBtn);

        add(loginPanel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginBtn) {

            String u = userField.getText();
            String p = new String(passField.getPassword());

            if (u.equals(username) && p.equals(password)) {
                remove(loginPanel);
                add(diaryPanel);
                revalidate();
                repaint();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Invalid Username or Password");
            }
        }

        if (e.getSource() == saveBtn) {
            try {
                FileWriter fw = new FileWriter("notes.txt", true);
                fw.write(area.getText());
                fw.write("\n-----------------\n");
                fw.close();

                JOptionPane.showMessageDialog(this,
                        "Notes Saved Successfully");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error Saving File");
            }
        }

        if (e.getSource() == openBtn) {
            try {
                BufferedReader br =
                        new BufferedReader(new FileReader("notes.txt"));

                area.read(br, null);
                br.close();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "File Not Found");
            }
        }

        if (e.getSource() == clearBtn) {
            area.setText("");
        }
    }

    public static void main(String[] args) {
        new DigitalDiary();
    }
}