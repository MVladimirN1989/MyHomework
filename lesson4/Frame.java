package lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    public Frame(){
        setTitle("CHAT");
        setBounds(50, 50, 400, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel top=new JPanel();
        add(top,BorderLayout.CENTER);
        top.setLayout(new BorderLayout());

        JPanel bottom=new JPanel();
        add(bottom, BorderLayout.SOUTH);
        bottom.setLayout(new BorderLayout());

        JTextArea textArea=new JTextArea();
        textArea.setEditable(false);
        add(textArea,BorderLayout.CENTER);

        JTextField input=new JTextField();
        bottom.add(input,BorderLayout.CENTER);
        JButton button=new JButton("отправить");
        bottom.add(button,BorderLayout.EAST);

        ActionListener inputText=new InputTexts(textArea,input);
        button.addActionListener(inputText);
        input.addActionListener(inputText);

        setVisible(true);
    }
}
