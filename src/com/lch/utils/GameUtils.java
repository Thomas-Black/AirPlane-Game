package com.lch.utils;

import com.lch.obj.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
//工具类
public class GameUtils {
    //背景图片
    /*public static Image bgImage=Toolkit.getDefaultToolkit().getImage("src/images/background.png");*/
    public static Image bgImage=new ImageIcon("src/images/bg1.png").getImage();
    //Boss战机
    public static Image BossImage=new ImageIcon("src/images/boos1.png").getImage();
    //爆炸图片
    public static Image explodeImage=new ImageIcon("src/images/explode.png").getImage();
    //我方战机
    public static Image PlaneImage=new ImageIcon("src/images/me3.png").getImage();
    //我方子弹图片
    public static Image shellImage=new ImageIcon("src/images/shell3.png").getImage();
    //敌方子弹图片
    public static Image bulletImage=new ImageIcon("src/images/bullet4.png").getImage();
    //敌机的图片
    public static Image enemyImage=new ImageIcon("src/images/dj3.png").getImage();


    //所有游戏物体的集合
    public static List<Gameobj> gameobjList=new ArrayList<>();
    //要删除元素的集合
    public static List<Gameobj> removeList=new ArrayList<>();
    //我方子弹的集合
    public static List<ShellObj> shellObjList=new ArrayList<>();
    //敌方子弹的集合
    public static List<BulletObj> bulletObjList=new ArrayList<>();
    //敌机的集合
    public static List<EnemyObj> enemyObjList=new ArrayList<>();
    //爆炸的集合
    //public static List<ExplodeObj> explodeObjList=new ArrayList<>();
//    public static List<BoosObj> boosObjList=new ArrayList<>();

    //绘制字符串的工具类
    public static void drawWord(Graphics gImage,String str,Color color,int size,int x,int y){
        gImage.setColor(color);
        gImage.setFont(new Font("仿宋",Font.BOLD,size));
        gImage.drawString(str,x,y);
    }
}
