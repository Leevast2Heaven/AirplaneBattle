package game.gui.impact_score;

import game.gui.enemy.*;
import game.gui.plane.Plane;
import game.gui.war.Jobject;
import game.gui.war.Scene;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 碰撞类.
 * Created by Leevast
 */
public class Crash extends Jobject{
    private  ArrayList<Impact> imt = null ;      //碰撞类的集合
    private Scene scene = null ;
    private EnemyCommander  ec ;
    private  Plane p ;



    public Crash(Scene scene){
        this.scene = scene ;
        ec = scene.getEnemyCommander();
        p = scene.getPlane();
        imt = new ArrayList<>() ;
        p = scene.getPlane() ;
        ec = scene.getEnemyCommander();
    }
    @Override
    public void paint(Graphics g){
        for(Impact im : imt)
            im.paint(g);
    }
    @Override
    public void update(){
        bulletUpdate();
        enemyUpdate();

    }
    private void bulletUpdate(){

        for(int i = 0 ;i <  p.getBullets().size(); i++ ){
            for(int j = 0 ;j < ec.getEnemys().size() ;j ++ ){
                if(ec.getEnemys().get(j).impactArea().intersects(p.getBullets().get(i).impactArea())){
                	
       
                    imt.add(new Impact(p.getBullets().get(i).getX()-30, p.getBullets().get(i).getY() -30));
                    p.getBullets().remove(i);
                    
                    if(ec.getEnemys().get(j) instanceof Little_Enemy ){
                    	 ec.getEnemys().remove(j);
                    }else if(ec.getEnemys().get(j) instanceof Big_Enemy){
                    	if(((Big_Enemy)ec.getEnemys().get(j)).getLife()>0)
                    		((Big_Enemy)ec.getEnemys().get(j)).setLife();
                    	else
                    		ec.getEnemys().remove(j);
                    }else{
                    	if(((boss_Enemy)ec.getEnemys().get(j)).getLife()>0)
                    		ec.getEnemys().get(j).setLife();
                    	else
                    		ec.getEnemys().remove(j);
                    }
                     scene.getScore().update();
                    break;
            }
        }
        }
        Iterator<Impact>  im = imt.iterator();
        while(im.hasNext()){
            if(im.next().update_1()){
                im.remove();
            }
        }
    }
    private void enemyUpdate(){
        Iterator<Enemy>  ec1 = ec.getEnemys().iterator();
        while(ec1.hasNext()){
            Enemy temp = ec1.next() ;
            if( temp.impactArea().intersects( scene.getPlane().impactArea())){
                if(scene.getPlane().getLife() > 0){
                    scene.getPlane().setLife();
                    if(temp instanceof Little_Enemy ){
                   	 ec1.remove();
                    }else if(temp instanceof Big_Enemy){
                   	if(((Big_Enemy)temp).getLife()>0)
                   		
                   		((Big_Enemy)temp).setLife();
                   	else
                   		ec1.remove();
                   }else{
                   	if(((boss_Enemy)temp).getLife()>0)
                   		
                   		((boss_Enemy)temp).setLife();
                   	else
                   		ec1.remove();
                   }
                    imt.add(new Impact(temp.getX() -2, temp.getY() - 5));
                }
            }
        }
    }
    public ArrayList<Impact> getImpact(){
        return imt ;
    }
    public void initialise(){
        imt.clear();
    }
}
