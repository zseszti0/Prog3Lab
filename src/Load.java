import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class Load extends Command{
    @Override
    void execute(List<String> commands) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("beers.ser"))) {
            List<Beer> list = (List<Beer>) in.readObject();

            System.out.println("Deserialized list:");
            for (Beer b : list) {
                System.out.println(b);
            }

            Main.raktar.addAll(list);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
