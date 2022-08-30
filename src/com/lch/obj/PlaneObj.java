package com.lch.obj;

import com.lch.GameWin;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlaneObj extends Gameobj{
    @Override
    public Image getImg() {
        return super.getImg();
    }

    public PlaneObj() {
        super();
    }
    //添加鼠标移动事件
    public PlaneObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
        this.frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                PlaneObj.super.x=e.getX()-50;//飞机的横坐标等于鼠标横坐标减去飞机宽度的一半
                PlaneObj.super.y=e.getY()-50;//飞机的纵坐标等于鼠标纵坐标减去飞机高度的一半
            }
        });
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        //我方飞机与敌方boos飞机碰撞检测
        if(this.frame.boosObj!=null&&this.getRec().intersects(this.frame.boosObj.getRec())){
            GameWin.state=3;
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
