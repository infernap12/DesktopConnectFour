package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectFour extends JFrame {
    int rows = 6;
    int cols = 7;
    LinkedHashMap<String, JButton> board = util.mapSetup();
    JPanel buttonPanel;
    JPanel resetPanel;
    JButton resetButton;
    boolean xTurn = true;
    boolean winner = false;
    private Stack<JButton> buttonStack;

    void testForWin() {
        JButton currentButton;
        buttonStack = new Stack<>();
        //vertical tests
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 6; y++) {
                currentButton = board.get(util.toKey(x, y));
                if (isWinningMove(currentButton)) return;
            }
            buttonStack.clear();
        }
        //horizontal tests
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 7; x++) {
                currentButton = board.get(util.toKey(x, y));
                if (isWinningMove(currentButton)) return;
            }
            buttonStack.clear();
        }
        for (int i = 0; i < 2; i++) {
            boolean rightToLeft = i == 1;
            for (int base = 0; base < rows + cols - 1; base++) {
                int startRow = base < cols ? 0 : base - cols + 1;
                int startCol = rightToLeft ? (base < cols ? cols - 1 - base : 0) : (base < cols ? base : cols - 1);

                for (int j = 0; j <= base; j++) {
                    int y = startRow + j;
                    int x = rightToLeft ? startCol + j : startCol - j;

                    if (y >= 0 && y < rows && x >= 0 && x < cols) {
                        currentButton = board.get(util.toKey(x, y));
                        if (isWinningMove(currentButton)) return;
                    }
                }
                buttonStack.clear();
            }
        }
    }

    private boolean isWinningMove(JButton currentButton) {
        if (buttonStack.isEmpty()) {
            buttonStack.push(currentButton);
        } else if (currentButton.getText().equals(buttonStack.peek().getText()) && !currentButton.getText().equals(" ")) {
            buttonStack.push(currentButton);
        } else {
            buttonStack.clear();
            buttonStack.push(currentButton);
        }
        if (buttonStack.size() == 4) {
            win(buttonStack);
            return true;
        }
        return false;
    }

    private void win(Stack<JButton> jButtons) {
        for (JButton jButton : jButtons) {
            jButton.setBackground(Color.decode("#0078d7").brighter());
        }
        winner = true;
    }


    public ConnectFour() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1280, 720));
        setVisible(true);
        setLayout(new BorderLayout());
        setTitle("Connect Four");
        setLocationRelativeTo(null);
        buttonPanel = new JPanel(new GridLayout(6, 7));
        resetPanel = new JPanel();
        resetButton = new JButton("Reset");
        resetButton.addActionListener(this::reset);
        resetButton.setName("ButtonReset");
        resetPanel.add(resetButton);
        add(buttonPanel, BorderLayout.CENTER);
        add(resetPanel, BorderLayout.SOUTH);


        board.forEach((key, value) -> {
            System.out.println(value.getName());
            value.addActionListener(this::clickButton);
            buttonPanel.add(value);
        });
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        setColours();
    }

    private void setColours() {
        AtomicInteger i = new AtomicInteger(2);
        board.forEach((key, value) -> {
//            if (i.get() % 2 == 0) {
//                value.setBackground(Color.decode("#0078d7").darker()); // poorly written hyperskill test that should fail the given example
//            } else {
                value.setBackground(Color.decode("#0078d7"));
//            }
            i.getAndIncrement();
        });
    }

    private void reset(ActionEvent actionEvent) {
        board.forEach((key, value) -> value.setText(" "));
        xTurn = true;
        winner = false;
        setColours();
    }

    void clickButton(ActionEvent e) {
        if (!winner){
            JButton clickedButton = (JButton) e.getSource();
            String coOrd = clickedButton.getName().substring(6, 8);
            System.out.println(coOrd);
            String destCoOrd;
            for (int i = 0; i < 6; i++) {
                destCoOrd = coOrd.substring(0, 1).concat(String.valueOf(i + 1));
                JButton button = board.get(destCoOrd);
                if (button.getText().equals(" ")) {
                    setCell(destCoOrd);
                    System.out.println(destCoOrd);
                    break;
                }
            }
        }
        testForWin();
    }

    private void setCell(String coOrd) {
        JButton button = board.get(coOrd);
        button.setText(xTurn ? "X" : "O");
        xTurn = !xTurn;
    }
}

