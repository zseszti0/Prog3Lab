public class Ls extends Command{
    @Override
    void execute(java.util.List<String> commands) {
        for(Beer b : Main.raktar){
            System.out.println(b.toString());
        }
    }
}
