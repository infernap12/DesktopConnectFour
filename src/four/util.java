package four;

import java.util.*;
import java.util.function.Consumer;

public class util {
    public static HashMap<String, String> board = util.mapSetup();

    public static void main(String[] args) {
        board.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("nerds");
    }

    public static String toCoOrdinate(int x, int y) {
        String s = "" + (char) (x + 65);
        s = s.concat(String.valueOf(y + 1));
        assert s.matches("[A-G][1-6]");
        return s;
    }

    public static LinkedHashMap<String, String> mapSetup() {
        LinkedHashMap<String, String> stringMap = new LinkedHashMap<>();
        for (int y = 5; y >= 0; y--) {
            for (int x = 0; x < 7; x++) {
                stringMap.put(toCoOrdinate(x, y), "");
            }

        }
        return stringMap;
    }

    /*public static iterateGrid() */

}
