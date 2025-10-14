import java.util.List;

import static java.lang.Double.parseDouble;

public class Search extends Command {
    @Override
    void execute(List<String> commands) {
        switch(commands.get(1)) {
            case "name":{
                Main.raktar.forEach(beer ->{
                    if(beer.getName().equalsIgnoreCase(commands.get(2))){
                        System.out.println(beer.toString());
                    }
                });
                break;
            }
            case "style":{
                Main.raktar.forEach(beer ->{
                    if(beer.getStyle().equalsIgnoreCase(commands.get(2))){
                        System.out.println(beer.toString());
                    }
                });
                break;
            }
            case "strength":{
                Main.raktar.forEach(beer ->{
                    if(beer.getStrength() == parseDouble(commands.get(2))){
                        System.out.println(beer.toString());
                    }
                });
                break;
            }
        }
    }
}
