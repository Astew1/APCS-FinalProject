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
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();     //SCREEN DIMENSIONS FROM THIS ARE 1440 | 900  (WIDTH | HEIGHT)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setPreferredSize(screenSize);
        frame.setResizable(false);

        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        final Main panel = new Main();
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        Timer t = new Timer(35, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.repaint();
                panel.update();

            }
        });

        t.start();
    }

    Player p;
    private boolean[] keys;
    //w,a,s,d
    private boolean jump;
    Level level;
    private int theorX;         //the position on the theoretical screen
    private final int theorMax = 1440*2;     //arbitrary: the max width of the screen is 3* the current width (3 real screens is the whole level)
    private final int playerSideBuffer = 150;       //the distance to the side of the screen before it should start scrolling

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
        theorX = 0;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.clearRect(0, 0, getWidth(), getHeight());
        p.draw(g2);
        level.draw(g2, theorX);
        g2.drawString("DEBUG: vX: " + (int)p.getvX() + ", vY: " + (int)p.getvY(), 100, 100);

    }

    public void update() {
        if(jump){
            p.jump();
            jump = false;
        }
        if (keys[1]) {
            p.setvX(p.getvX() - 2);
        }
        if (keys[3]) {
            p.setvX(p.getvX() + 2);
        }
        p.update(getWidth(), getHeight(), level);

        // /MOVE THE THEORX based on the ball pos
        if(p.getX()<playerSideBuffer){       //move theorX left if not at 0
            int ballDiff = (int)(playerSideBuffer -p.getX());
            if(theorX>0) {
                if (theorX - ballDiff > 0) {
                    p.setX(playerSideBuffer);
                    theorX -= ballDiff;
                }
                else{
                    p.setX(playerSideBuffer-theorX);
                    theorX = 0;
                }
            }
        }
        else if (p.getX()>1440-playerSideBuffer){//move theorX right if not at theorMax.
            int ballDiff = (int)(p.getX()-1240);
            if(theorX<theorMax) {
                if (ballDiff + theorX < theorMax) {
                    theorX += ballDiff;
                    p.setX(1440-playerSideBuffer);
                }
                else{
                    p.setX(1440-playerSideBuffer + (theorMax-theorX));
                    theorX = theorMax;
                }
            }
        }
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
