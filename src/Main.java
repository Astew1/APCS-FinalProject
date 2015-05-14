import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Zander on 5/12/2015.
 */
public class Main extends JPanel implements KeyListener {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setPreferredSize(screenSize);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        final Main panel = new Main();
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        Timer t = new Timer(35, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.update();
                panel.repaint();
            }
        });

        t.start();
    }

    Player p;
    private boolean[] keys;
    //w,a,s,d
    private boolean jump;
    Level level;

    public Main() {
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        level = new Level();
        jump = false;
        keys = new boolean[4];
        for (int i = 0; i < 4; i++) {
            keys[i] = false;
        }
        p = new Player(MyUtils.getImage("circle.png", 30, 30), 30, 30);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.clearRect(0, 0, getWidth(), getHeight());
        p.draw(g2);
        level.draw(g2);

    }

    public void update() {
        if(jump){
            p.jump();
            jump = false;
        }
        if (keys[0]) {
//            s.setvY(s.getvY() - 4);
        }
        if (keys[1]) {
            p.setvX(p.getvX() - 2);
        }
        if (keys[2]) {
            p.setvY(p.getvY() + 2);
        }
        if (keys[3]) {
            p.setvX(p.getvX() + 2);
        }
        p.update(getWidth(), getHeight(), level);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            keys[0] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            keys[1] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            keys[2] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            keys[3] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            jump = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W) {
            keys[0] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            keys[1] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            keys[2] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            keys[3] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {

        }
    }
}
