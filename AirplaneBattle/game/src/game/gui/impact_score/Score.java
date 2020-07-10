package game.gui.impact_score;

import game.gui.war.Scene;

import java.awt.*;

/**
 * 计分类
 * Created by Leevast
 */
public class Score  {
    public static int sc = 0;
    private  int i = 0;
    private Scene scene = null ;



    public Score(Scene scene){
        this.scene = scene ;
    }
    public void paint (Graphics g){
        if(scene.getMyFrame().getGameState()) {

        }
        g.drawString("分数：" + i ,10,30);

    }
    public void update(){
        i = i +  1000;
        sc = i ;
    }
    public void initialise(){
        sc = 0 ;
        i = 0 ;
    }
}
