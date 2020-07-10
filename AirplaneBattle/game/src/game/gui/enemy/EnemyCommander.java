package game.gui.enemy;

import game.gui.war.Jobject;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;


public class EnemyCommander extends Jobject {

	private static final long serialVersionUID = 1L;
	private final int INTERVALTIME = 100 ;
    private int time = 0;
    private  LinkedList<Enemy>  enemys = new LinkedList<>();         //当前出现在页面的飞机的集合
    private int linum = 0 ;                  //统计小飞机出现的个数
    private int binum = 0 ;                 //统计大飞机出现的次数
    public  int bosnum = 0;
    public static boolean stop = false;


    //把集合的敌机绘制出来
    @Override
    public void paint(Graphics g){
    	if(binum ==10){
    		g.drawString("关卡3", 10, 10);
    	}
    	else if(linum == 50){
    		g.drawString("关卡2", 10, 10);
    	}
    	else{g.drawString("关卡1", 10, 10);
    	}
        if (!enemys.isEmpty())
            for (Enemy e : enemys)
                e.paint(g);

    }
    @Override
    public void update(){
        if (time == INTERVALTIME && !stop) {
            time  = 0 ;
            if(binum == 10){
            	bosnum ++ ;
            	time = -3500;
            	if(bosnum < 3 )
            	enemys.add( new boss_Enemy() );
            	else
            		stop = true ;
            }else if(linum == 50){
            	binum ++;
            	time = -200;
                enemys.add( new Big_Enemy() );
            }else{
            	  enemys.add( new Little_Enemy() );
            	  linum++ ;
            };
        }
        time++;
        
        Iterator<Enemy> enl = enemys.iterator();
        while (enl.hasNext()) {
            if (enl.next(). update_1())
                enl.remove();
            }
    }
    public LinkedList<Enemy> getEnemys(){
        return enemys;
    }
    public void initialise(){
        enemys.clear();
        binum = 0 ;
        linum = 0 ;
        bosnum = 0;
        time = 0;
        stop = false ;
    }
}
