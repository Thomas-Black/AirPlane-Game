package com.lch.obj;

import com.lch.GameWin;

import java.awt.*;

public class BgObj extends Gameobj{
    public BgObj() {
        super();
    }

    public BgObj(Image img, int x, int y, double speed) {
        super(img,x,y,speed);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y+=speed;
        if(y>=0){
            y=-2000;
        }
    }
}
