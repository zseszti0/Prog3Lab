import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class Save extends Command{
    @Override
    void execute(List<String> commands) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("beers.ser"))) {
            out.writeObject(Main.raktar);
            System.out.println("A sörök ki lettek irva");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
