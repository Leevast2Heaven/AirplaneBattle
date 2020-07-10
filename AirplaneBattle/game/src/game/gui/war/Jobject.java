package game.gui.war;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Leevast
 */
public abstract class Jobject extends JPanel {
    public  int x = 0;                   //声明坐标轴x
    public  int y = 0;                  //声明坐标轴y
    public BufferedImage image =  null;         //声明image
    public abstract void  paint(Graphics g);
    public  void update(){}
     public Rectangle impactArea(){
         return new Rectangle(x,y,this.getWidth() ,this .getHeight() ) ;
     }

    /*
     * 属性x y 的get set 方法
     */
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
