package com.lch.obj;

import com.lch.GameWin;
import com.lch.utils.GameUtils;

import java.awt.*;

public class EnemyObj extends Gameobj{
    public EnemyObj() {
        super();
    }

    public EnemyObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y+=speed;
        //敌我飞机碰撞检测
        if(this.getRec().intersects(this.frame.planeObj.getRec())){
            GameWin.state=3;
        }
        //敌机的越界消失   判断条件 y > 600 改变后的坐标为（-200，-200）
        if(y > 600){
            this.x = -300;
            this.y = 300;
            GameUtils.removeList.add(this);
        }
        //遍历我方子弹，让敌机与我方子弹集合中的每一个元素一一检测
        for(ShellObj shellObj: GameUtils.shellObjList){
            if(this.getRec().intersects(shellObj.getRec())){
                gImage.drawImage(GameUtils.explodeImage,this.x,this.y,null);
                GameUtils.removeList.add(shellObj);
                GameUtils.removeList.add(this);
                GameWin.score++;
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
