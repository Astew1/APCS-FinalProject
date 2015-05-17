import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Zander on 5/14/2015.
 */
public class Level {

    private ArrayList<Platform> platforms;
    public Level(){
        platforms = new ArrayList<Platform>();
        platforms.add(new Platform(200,400, 500, 300));
    }

    public Platform touches(Sprite s){
        for (Platform platform : platforms) {
            if(platform.touches(s) != null){
                return platform.touches(s);
            }
        }
        return null;
    }

    public void draw(Graphics2D g2){
        for (Platform platform : platforms) {
            platform.draw(g2);
        }
    }

//    public Point getBallPosFromPlatform(int side, Sprite s){      //this could be made more efficient
//        for(Platform plat:platforms){
//            if(plat.touches(s) != -1)
//                return plat.getBouncePos(side, s);
//        }
//        System.out.println("this should never happen, inside Level.getBallPosFromPlatform()");
//        return new Point(200, 200);
//    }

}
