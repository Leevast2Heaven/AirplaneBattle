package game.gui.impact_score;

import game.gui.war.Jobject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 爆炸类
 * Created by Leevast
 */
public class Impact extends Jobject {
    private int x = 0;
    private int y = 0;
    private BufferedImage[] image = new BufferedImage[3];  ; 
    private String url1 = "/images/bomb1.png" ;
    private String url2 = "/images/bomb2.png" ;
    private String url3 = "/images/bomb3.png" ;
    private int dur = 0;
    private int pindex = 0;
    private int TIME = 8 ;

    public Impact( int x,int y){
        this.x = x ;
        this.y = y ;
        try{
            image[0] = ImageIO.read(getClass().getResource(url1));   // 导入图片
            image[1] = ImageIO.read(getClass().getResource(url2));
            image[2] = ImageIO.read(getClass().getResource(url3));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void paint(Graphics g){
               g.drawImage(image[pindex],x,y,null);
    }

    public boolean update_1(){

            if (dur > TIME) {
                dur = 0;
               return true ;
            }else{
               dur++;
               if(pindex <2)
               pindex++;
               return false;
            }
   }
}

