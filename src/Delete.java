import java.util.List;

public class Delete extends Command{
    @Override
    void execute(List<String> commands) {
        Main.raktar.removeIf(b -> b.getName().equals(commands.get(1)));
    }
}
