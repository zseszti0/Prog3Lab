import java.util.List;

import static java.lang.Double.parseDouble;

public class Find extends Command {
    @Override
    void execute(List<String> commands) {
        switch(commands.get(1)) {
            case "name":{
                Main.raktar.forEach(beer ->{
                    if(beer.getName().toLowerCase().contains((commands.get(2)).toLowerCase())){
                        System.out.println(beer.toString());
                    }
                });
                break;
            }
            case "style":{
                Main.raktar.forEach(beer ->{
                    if(beer.getStyle().toLowerCase().contains((commands.get(2)).toLowerCase())){
                        System.out.println(beer.toString());
                    }
                });
                break;
            }
            case "strength":{
                Main.raktar.forEach(beer ->{
                    if(beer.getStrength() >= parseDouble(commands.get(2))){
                        System.out.println(beer.toString());
                    }
                });
                break;
            }
            case "weaker":{
                Main.raktar.forEach(beer ->{
                    if(beer.getStrength() < parseDouble(commands.get(2))){
                        System.out.println(beer.toString());
                    }
                });
                break;
            }
        }
    }
}
