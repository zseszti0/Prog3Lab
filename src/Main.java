import java.io.*;
import java.util.*;

public class Main {
    public static List<Beer> raktar = new ArrayList<>();

    public static void main(String[] args) {
        //HASHMAP STRUCTURE---
        Hashtable<String, Command> map = new Hashtable<>();

        map.put("add", new Add());
        map.put("list", new Ls());
        map.put("save", new Save());
        map.put("load", new Load());
        map.put("delete", new Delete());
        map.put("search", new Search());
        map.put("find", new Find());


        //MENU----------------
        Scanner scanner = new Scanner(System.in);
        String line;
        while (!(line = scanner.nextLine()).equals("exit")) {
            List<String> commands = Arrays.asList(line.split(" "));


            Command cmd = map.get(commands.getFirst());
            if (cmd != null) {
                cmd.execute(commands);
            }

        }
    }
}
