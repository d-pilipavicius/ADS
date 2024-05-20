import javax.swing.*;
import java.awt.*;

public class Main extends JPanel{
    EightBishops chess = new EightBishops(100, 100, 50, "src/chessp.png");
    Algorithm algorithm = new Algorithm(chess);
    JButton button;
    public Main() {
        setLayout(null);
        button = new JButton("Next variation");

        button.setSize(150, 40);
        button.setLocation(600, 400);
        button.addActionListener(e -> {
            algorithm.nextVariation();
            repaint();
        });
        add(button);
    }
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(800, 800);
        f.add( new Main() );
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    @Override
    public void paint(Graphics g){
         super.paint(g);
         this.setBackground( new Color(82, 161, 255) );
         chess.displayBoard(g, this);
    }
}