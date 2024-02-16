package four;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConnectFour extends JFrame {
    LinkedHashMap<String, String> board = util.mapSetup();


    public ConnectFour() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1280, 720));
        setVisible(true);
        setLayout(new GridLayout(6, 7));
        setTitle("Connect Four");
        setLocationRelativeTo(null);
        board.forEach((key, value) -> {
            JButton j = new JButton(key);
            j.setName("Button".concat(key));
            getContentPane().add(j);
            System.out.println(j.getName());
            j.setFocusPainted(false);
        });
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }
}

