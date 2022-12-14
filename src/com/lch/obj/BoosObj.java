package com.lch.obj;

import com.lch.GameWin;
import com.lch.utils.GameUtils;

import java.awt.*;

public class BoosObj extends Gameobj{

    int life = 30;
    @Override
    public Image getImg() {
        return super.getImg();
    }

    public BoosObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        if(x>280 || x<0){
            speed=-speed;
        }
        x+=speed;
        for(ShellObj shellObj : GameUtils.shellObjList){
            if(this.getRec().intersects(shellObj.getRec())){
                shellObj.setX(-100);
                shellObj.setY(100);
                GameUtils.removeList.add(shellObj);
                life--;
            }
            if(life<=0){
                GameWin.state = 4;
            }
        }

        //血条的白色背景
        gImage.setColor(Color.white);
        gImage.fillRect(20,40,150,10);
        //血条的绘制
        gImage.setColor(Color.RED);
        gImage.fillRect(20,40,life * 150 / 30,10 );
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
