import java.io.Serializable;

public class Beer implements Serializable {
    String name;
    String style;
    double strength;

    Beer(String n, String s, double strn){
        this.name = n;
        this.style = s;
        this.strength = strn;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStyle() {
        return style;
    }
    public void setStyle(String style) {
        this.style = style;
    }
    public double getStrength() {
        return strength;
    }
    public void setStrength(double strength) {
        this.strength = strength;
    }

    @Override
    public String toString() {
        return (name + " " + style + " | " + strength + "%");
    }
}
