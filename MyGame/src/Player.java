import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Player extends Thread {
    boolean running = true;
    List<Image> animation = new LinkedList<>();
    Image current;
    int j = 0;

    public Player(){
        for (int i = 0; i<16;i++){
            animation.add(new ImageIcon("animation/Bild"+(i+1)+".png").getImage());
        }
        current = animation.get(0);
    }

    @Override
    public void run() {
        while(running) {
            current = animation.get(j);
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            j=(j+1)%16;
        }
    }

}
