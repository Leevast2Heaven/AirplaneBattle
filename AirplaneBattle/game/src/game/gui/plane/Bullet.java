package game.gui.plane;

import game.gui.war.Jobject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

import static game.gui.war.MyFrame.height;

/**
 * 子弹类
 * Created by Leevast
 */
public class Bullet extends Jobject{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x ;
    private int y ;
    private int speed = 5;
    private String url = "/images/bullet1.png";

    public Bullet(int x ,int y ){

        this.x = x + 30;
        this.y = y - 20;
        try{
            image = ImageIO.read(getClass().getResource(url));   // 导入图片
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(image,x,y,this);
    }
    public Rectangle impactArea(){
        return new Rectangle(x,y,image.getWidth() ,image .getHeight() ) ;
    }

    public boolean move(){

        if( y <  0 )                       // 如果子弹飞出窗口
            return true ;
        else{
             y  -=  speed;
             
             return  false ;
        }
    }
    public void setl(int x ,int y){
    	this.x = x +30 ;
    	this.y = y  -20;
    	
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}
