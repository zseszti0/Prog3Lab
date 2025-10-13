import java.util.List;
import static java.lang.Double.parseDouble;

public class Add extends Command{
    @Override
    void execute(List<String> commands) {
        Main.raktar.add(new Beer(commands.get(1),commands.get(2),parseDouble(commands.get(3))));
    }
}
