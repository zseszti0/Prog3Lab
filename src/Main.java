import javax.swing.*;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // GUI indítása a biztonságos Event Dispatch Thread-en
        SwingUtilities.invokeLater(() -> {
            CaesarFrame frame = new CaesarFrame();
            frame.setVisible(true); // [cite: 27]
        });
    }
}
