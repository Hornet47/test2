import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class View {
    JFrame frame;
    JPanel panel;
    int x = 350;
    int y = 50;
    int v = 0;
    int first=0;
    int second=0;
    int dir = 10;
    Set<Point> list = new HashSet<>();

    //Player player = new Player();

    public void timer(){
        if(second==0){
            x = x+first;if(first!=0)dir = first;
        }
        else {
            x = x+second;dir = second;
        }
        y=y+v;
        v=v+((100-y)/20);
        frame.repaint();
    }
    public View() {
        //player.start();
        frame = new JFrame("MyGame");
        frame.setSize(1100,600);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //player.running =false;
                System.exit(0);
            }
        });
        frame.setLocation(200,100);
        panel = new JPanel(){
            @Override
            public void paint(Graphics g){
                if (dir==10)g.drawImage(new ImageIcon("icecream2.png").getImage(),x,y,300,300,null);
                else g.drawImage(new ImageIcon("icecream3.png").getImage(),x,y,300,300,null);
                for(Point p: list){
                    g.fillOval(p.x,p.y,5,5);
                }
            }
        };
        panel.setLocation(0,0);
        panel.setSize(1100,600);
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                list.add(new Point(e.getX(),e.getY()));
            }
        };
        panel.addMouseListener(adapter);


        frame.add(panel);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_LEFT){
                    if(first==0)first=-10;
                    else if(first==10)second = -10;
                }
                if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                    if(first==0)first=10;
                    else if(first==-10)second = 10;
                }
            }
            @Override
            public void keyReleased(KeyEvent e){
                if(second==0)first=0;
                else if((e.getKeyCode()==KeyEvent.VK_LEFT&&second==10)||(e.getKeyCode()==KeyEvent.VK_RIGHT&&second==-10)){
                    first=second;second=0;
                }else {
                    first=-second;second = 0;
                }
            }
        });

        frame.setVisible(true);
        Timer timer = new Timer(17,e->timer());
        timer.start();
    }

}
