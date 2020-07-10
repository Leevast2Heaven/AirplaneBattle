package game.gui.enemy;

import game.gui.war.MyFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Leevast
 */
public class boss_Enemy extends Enemy{

    private BufferedImage image;
    private String picpath = "/images/plane_boss.png" ;
    private int life = 50;
    private int time = 0;
    public boss_Enemy(){                        //无参构造方法
    	//x= (int) (Math.random()*200 + 100);
    	x = 100;
    	y = 100 ;
        try{
             image = ImageIO.read(getClass().getResource(picpath));   // 导入图片
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(image,x,y,this);
        g.drawRect(x+ 35,y-5,100,3);
        g.fillRect(x+ 35,y-5,100- (50-life)*2,3);
    }
    /*
    *
    * */
    public boolean  update_1(){
            if (y > MyFrame.height || life <= 0 ) {
               return true ;
            }else{
            	if(time > 5 ){
                    this.y += 1;
                    time = 0;
            	}
            	time++ ;
               return false ;
            }
    }
    public Rectangle impactArea() {
        return new Rectangle(this.x, this.y, image.getWidth(this), image.getHeight(this));
    }
    public void setLife(){
        this.life  -- ;	
    }
    public int getLife(){    
        return this .life ;
    }
}
