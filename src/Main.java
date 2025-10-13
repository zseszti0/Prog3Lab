import java.io.*;
import java.util.*;

public class Main {
    public static List<Beer> raktar = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //HASHMAP STRUCTURE---
        HashMap<String, Command> map = new HashMap<>();

        map.put("add", new Add());
        map.put("list", new Ls());
        map.put("save", new Save());
        map.put("load", new Load());
        map.put("delete", new Delete());


        //MENU----------------
        Scanner scanner = new Scanner(System.in);
        String line;
        while (!(line = scanner.nextLine()).equals("exit")) {
            List<String> commands = Arrays.asList(line.split(" "));

            // Get something from it
            Command cmd = map.get(commands.getFirst());
            if (cmd != null) {
                cmd.execute(commands);
            }

        }
    }
}
