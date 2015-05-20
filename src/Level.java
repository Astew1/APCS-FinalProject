import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Zander on 5/14/2015.
 */
public class Level {

    private ArrayList<Platform> platforms;
    public Level(){
        platforms = new ArrayList<Platform>();
        for (int i = 0; i < 12; i++) {
            platforms.add(new Platform((int)(Math.random()*(1440*3)), (int)(Math.random()*800), 500, 60));
        }
    }

    public Platform touches(Sprite s){
        for (Platform platform : platforms) {
            if(platform.touches(s) != null){
                return platform.touches(s);
            }
        }
        return null;
    }

    public void draw(Graphics2D g2, int translateX){
        for (Platform platform : platforms) {
            platform.draw(g2, translateX);
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
