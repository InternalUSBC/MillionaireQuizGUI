package View;

import Models.Username;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by izaac on 5/06/2017.
 */
public class UsernameView extends JPanel
{
    private Username userName = new Username();

    private JTextArea usernameText;
    private JTextArea incorrectInput;

    private JTextField usernameField;

    private JLabel usernameBackground;

    private JButton submitUsername;

    Font font = new Font("Dialog", Font.BOLD, 15);

    public JTextArea getUsername()
    {
        return this.usernameText;
    }

    public UsernameView(ActionListener w)
    {
        setLayout(null);

        this.usernameText = new JTextArea("Enter A Username");
        usernameText.setFont(font);
        usernameText.setLineWrap(true);
        usernameText.setWrapStyleWord(true);
        usernameText.setEditable(false);
        usernameText.setOpaque(false);
        usernameText.setForeground(Color.white);
        usernameText.setBounds(278, 169, 211, 30);
        add(usernameText);

        this.usernameField = new JTextField();
        setOpaque(false);
        usernameField.setBounds(206, 209, 343, 34);
        usernameField.setColumns(10);
        usernameField.setName("usernameField");
        add(usernameField);

        this.submitUsername = new JButton("Submit");
        submitUsername.setOpaque(false);
        submitUsername.setFont(font);
        submitUsername.setContentAreaFilled(false);
        submitUsername.setBorderPainted(false);
        submitUsername.setForeground(Color.white);
        submitUsername.setName("submitUsernameButton");
        submitUsername.setBounds(318, 273, 119, 30);
        submitUsername.addActionListener(w);
        add(submitUsername);

        if(userName.getUserInput() == false)
        {
            this.incorrectInput = new JTextArea();
            incorrectInput.setFont(font);
            incorrectInput.setLineWrap(true);
            incorrectInput.setWrapStyleWord(true);
            incorrectInput.setEditable(false);
            incorrectInput.setOpaque(false);
            incorrectInput.setForeground(Color.red);
            incorrectInput.setBounds(259, 248, 250, 22);
            add(incorrectInput);
        }

        this.usernameBackground = new JLabel("New label");
        usernameBackground.setIcon(new ImageIcon("img\\Username Altered.jpg"));
        usernameBackground.setBounds(0, 0, 760, 400);
        add(usernameBackground);


    }
}
