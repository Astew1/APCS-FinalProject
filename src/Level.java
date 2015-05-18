import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Zander on 5/14/2015.
 */
public class Level {

    private ArrayList<Platform> platforms;
    public Level(){
        platforms = new ArrayList<Platform>();
        platforms.add(new Platform(200,700, 500, 60));
        platforms.add(new Platform(600,500, 500, 60));
        platforms.add(new Platform(1000,300, 500, 60));
    }

    public int touches(Sprite s){
        for (Platform platform : platforms) {
            if(platform.touches(s) != -1){
                return platform.touches(s);
            }
        }
        return -1;
    }

    public void draw(Graphics2D g2){
        for (Platform platform : platforms) {
            platform.draw(g2);
        }
    }

}
