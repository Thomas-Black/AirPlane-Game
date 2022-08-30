package com.lch.obj;

import com.lch.GameWin;
import com.lch.utils.GameUtils;

import java.awt.*;

public class BulletObj extends Gameobj{
    public BulletObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y+=speed;
        //敌方子弹与我方飞机碰撞检测
        if(this.getRec().intersects(this.frame.planeObj.getRec())){
            //GameUtils.removeList.add(frame.planeObj);
            GameWin.state=3;
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
