package com.lch.obj;

import com.lch.GameWin;
import com.lch.utils.GameUtils;

import java.awt.*;

public class ShellObj extends Gameobj{
    @Override
    public Image getImg() {
        return super.getImg();
    }

    public ShellObj() {
        super();
    }

    public ShellObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y-=speed;
        //我方子弹的越界消失   条件 Y < 0  改变后的坐标（-100,100）
        if(y < 0){
            this.x = -100;
            this.y = 100;
            GameUtils.removeList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
