package com.lch;

import com.lch.obj.*;
import com.lch.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWin extends JFrame {
    //游戏状态 0未开始 1游戏中 2暂停 3游戏失败 4通关成功
    public static int state=0;//定义游戏的默认状态
    //游戏得分
    public static int score=0;
    Image offScreenImage = null;
    int width=500;
    int height=800;
    //游戏的重绘次数
    int count=1;
   //敌机出现的数量
    int enemyCount = 0;
    //获取背景图对象
    BgObj bgObj=new BgObj(GameUtils.bgImage,0,-2000,2);
    //敌方BOOS对象
    public BoosObj boosObj=null;
    //我方飞机对象
    public PlaneObj planeObj=new PlaneObj(GameUtils.PlaneImage,100,
            700,50,50,0, this);


    //启动方法，设置窗口的信息
    public void StartGame() {
        //设置标题
        this.setTitle("飞机大战");
        //设置窗口大小
        this.setSize(width, height);
        //设置窗口位置
        this.setLocationRelativeTo(null);
        //设置是否可见
        this.setVisible(true);
        //设置窗口关闭
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //禁止改变窗口大小
        setResizable(false);

        GameUtils.gameobjList.add(bgObj);
        GameUtils.gameobjList.add(planeObj);

        //游戏的鼠标点击启动事件；e.getButton() == 1 表明按下的是游戏左键
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == 1 && state == 0) {
                    state = 1;
                    repaint();//重新调用paint方法
                }
            }
        });


        //重新开始游戏
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER && state == 3){
                    score = 0;
                    state = 0;
                    boosObj=null;
                    repaint();

                }
            }
        });

        //暂停
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 32){
                    switch (state){
                        case 1:
                            state = 2;
                            break;
                        case 2:
                            state = 1;
                            break;
                            default:
                    }
                }
            }
        });



        //窗口不断移动，需要重复调用paint方法
        while (true) {
            if(state==1){
                createObj();
                repaint();
            }

            try {
                Thread.sleep(25);//每间隔25毫秒
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //重写paint方法，用画笔g绘制图片
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(offScreenImage == null){
            offScreenImage = createImage(width,height);
        }
        Graphics gImage = offScreenImage.getGraphics();//获取offScreenImage的画笔对象
        gImage.fillRect(0,0,width,height);
        if (state == 0) {
            //未开始
            gImage.drawImage(GameUtils.bgImage, 0, 0, null);
            gImage.drawImage(GameUtils.BossImage, 130, 80, null);
            /*gImage.drawImage(GameUtils.explodeImage, 150, 400, null);*/
            GameUtils.drawWord(gImage,"点击开始游戏",Color.yellow,40,130,450);

        }
        if (state == 1) {//运行中
            //GameUtils.gameobjList.addAll(GameUtils.explodeObjList);
            //遍历所有物体

            for(int i=0;i<GameUtils.gameobjList.size();i++){
                GameUtils.gameobjList.get(i).paintSelf(gImage);
            }
            GameUtils.gameobjList.removeAll(GameUtils.removeList);
        }
        if (state == 3) {
            //失败
            gImage.drawImage(GameUtils.explodeImage, planeObj.getX(), planeObj.getY(), null);
            GameUtils.drawWord(gImage,"GAME OVER",Color.RED,50,150,400);
            GameUtils.drawWord(gImage,"按下Enter重新开始",Color.yellow,30,120,600);
            //gImage.drawImage(GameUtils.PlaneImage, 100, 700, null);
            GameUtils.gameobjList.removeAll(GameUtils.enemyObjList);
            GameUtils.gameobjList.removeAll(GameUtils.shellObjList);
            GameUtils.gameobjList.removeAll(GameUtils.bulletObjList);
            GameUtils.gameobjList.remove(boosObj);
            enemyCount = 0;

        }
        if (state == 4) {
            //通关
            gImage.drawImage(GameUtils.explodeImage, boosObj.getX()+30, boosObj.getY(), null);
            GameUtils.drawWord(gImage,"游戏通关",Color.green,50,150,400);
        }
        GameUtils.drawWord(gImage,score+"分",Color.green,40,30,100);
        g.drawImage(offScreenImage,0,0,null);
        count++;//重绘之后让count自增
    }


    //批量创建子弹和飞机
    void createObj() {
        //我方子弹
        if (count % 20 == 0) {
            GameUtils.shellObjList.add(new ShellObj(GameUtils.shellImage, planeObj.getX()+40,
                    planeObj.getY() - 50, 50, 50, 5, this));
            GameUtils.gameobjList.add(GameUtils.shellObjList.get(GameUtils.shellObjList.size() - 1));
//            if(enemyCount > 5){
//                if (count % 20 == 0) {
//                    GameUtils.shellObjList.add(new ShellObj(GameUtils.shellImage, planeObj.getX()+10,
//                            planeObj.getY() - 20, 50, 50, 5, this));
//                    GameUtils.gameobjList.add(GameUtils.shellObjList.get(GameUtils.shellObjList.size() - 1));
//                }
//            }
//            if(enemyCount > 10){
//                if (count % 20 == 0) {
//                    GameUtils.shellObjList.add(new ShellObj(GameUtils.shellImage, planeObj.getX()+70,
//                            planeObj.getY() - 20, 50, 50, 5, this));
//                    GameUtils.gameobjList.add(GameUtils.shellObjList.get(GameUtils.shellObjList.size() - 1));
//                }
//
//            }
        }
        if (count % 20 == 0) {
            GameUtils.shellObjList.add(new ShellObj(GameUtils.shellImage, planeObj.getX()+10,
                    planeObj.getY() - 20, 50, 50, 5, this));
            GameUtils.gameobjList.add(GameUtils.shellObjList.get(GameUtils.shellObjList.size() - 1));
        }
        if (count % 20 == 0) {
            GameUtils.shellObjList.add(new ShellObj(GameUtils.shellImage, planeObj.getX()+70,
                    planeObj.getY() - 20, 50, 50, 5, this));
            GameUtils.gameobjList.add(GameUtils.shellObjList.get(GameUtils.shellObjList.size() - 1));
        }
        //敌机
        if (count % 20 == 0) {
            GameUtils.enemyObjList.add(new EnemyObj(GameUtils.enemyImage,(int)(Math.random()*10)*50,0,
            60,60,5,this));
            GameUtils.gameobjList.add(GameUtils.enemyObjList.get(GameUtils.enemyObjList.size() - 1));
            enemyCount++;
        }
        //敌方子弹
        if (count % 40 == 0 && boosObj!=null) {
            GameUtils.bulletObjList.add(new BulletObj(GameUtils.bulletImage,boosObj.getX()+70,
                    boosObj.getY()+110,50,50,5,this));
           GameUtils.gameobjList.add(GameUtils.bulletObjList.get(GameUtils.bulletObjList.size()-1));
        }

        //BOOS出现条件
        if(enemyCount > 10 && boosObj == null){
            boosObj=new BoosObj(GameUtils.BossImage,250,0,
                    155,100,5,this);
            GameUtils.gameobjList.add(boosObj);
        }
    }
    public static void main(String[] args) {
        GameWin gameWin=new GameWin();
        gameWin.StartGame();
    }

}
