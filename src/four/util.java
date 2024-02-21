package four;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;

public class util {

    public static void main(String[] args) {

    }

    public static String toCoOrdinate(int x, int y) {
        String s = "" + (char) (x + 65);
        s = s.concat(String.valueOf(y + 1));
        assert s.matches("[A-G][1-6]");
        return s;
    }

    public static LinkedHashMap<String, JButton> mapSetup() {
        LinkedHashMap<String, JButton> map = new LinkedHashMap<>();
        for (int y = 5; y >= 0; y--) {
            for (int x = 0; x < 7; x++) {
                JButton button = new JButton(" ");
                String coOrd = toCoOrdinate(x, y);
                button.setName("Button".concat(coOrd));
                button.setFocusPainted(false);
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Tahoma", Font.BOLD, 48));

                map.put(toCoOrdinate(x, y), button);
            }

        }
        return map;
    }

    public static String toKey(int x, int y) {
        return String.valueOf((char) (65 + x)).concat(String.valueOf(y + 1));
    }

    /*public static iterateGrid() */

}
