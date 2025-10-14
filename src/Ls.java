import java.util.Comparator;

import static java.lang.Double.parseDouble;

public class Ls extends Command{
    @Override
    void execute(java.util.List<String> commands) {
        if(commands.size() > 1){
            switch(commands.get(1)) {
                case "name":{
                    Main.raktar.sort(Comparator.comparing(b -> b.getName().toLowerCase()));
                    break;
                }
                case "style":{
                    Main.raktar.sort(Comparator.comparing(b -> b.getStyle().toLowerCase()));
                    break;
                }
                case "strength":{
                    Main.raktar.sort(Comparator.comparingDouble(b -> b.getStrength()));
                    break;
                }
            }
        }
        for(Beer b : Main.raktar){
            System.out.println(b.toString());
        }
    }
}
