package game.gui.enemy;

import game.gui.war.MyFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * Created by Leevast
 */
public class Big_Enemy extends Enemy{

    private BufferedImage image;
    private int life = 4;  
    private int time = 0 ;
    private String picPath = "/images/plane_enemy"+ (int)(Math.random() * 3 + 1) + ".png";

    public Big_Enemy(){
        this.x = ( int )( Math.random()*100 + 100 );
        this.y = -50 ;
        try{
             image = ImageIO.read(getClass().getResource(picPath));   // 导入图片
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    /*
    *       绘制敌机图片
    * */
    @Override
    public void paint(Graphics g){

            g.drawImage(image,x,y,this);
            g.drawRect(x+20,y-5,60 ,3);
            g.fillRect(x+20,y-5,60- (4-life) * 15 ,3);

    }
    /*
    *       更新敌机位置
    * */
    public boolean  update_1(){
        if (y > MyFrame.height ||life <= 0 ) {
           return true ;
        }else{
        		time = 0;
        		this.y += 1;
            return false ;
        }
}
        //获得碰撞区域
    public Rectangle impactArea(){
        return new Rectangle(this.x,this.y,image.getWidth(this) , image.getHeight(this) ) ;
    }
    public void setLife(){
        this.life  -- ;
    }
    public int getLife(){
        return this .life ;
    }
}
