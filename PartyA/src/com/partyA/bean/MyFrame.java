package com.partyA.bean;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * User: Nana Yin
 * Date: 11/24/19
 * desc:
 */
public class MyFrame extends JFrame implements MouseListener {
    //保存坐标
    int x;
    int y;
    int x1;
    int y1;
    //黑子数
    //白子数
    //1是黑下，2是白下
    //默认开始是黑旗先下
    int flag=1;
    //表示游戏是否结束
    //true游戏开始，false游戏结束，不能再下
    boolean canPlay=true;
    //保存之前下过的棋子的坐标
    //'0'代表没有棋子，'1'代表黑棋，'2'代表白棋
    int [][]allChess=new int[19][19];
    //int [][]allChess=new int[25][25];
    //当前棋子的总数
    int chessSum=0;
    BufferedImage bgImage =null;

    JButton restart=new JButton("重新开始");
    JButton exit=new JButton("退出");
    JPanel south=new JPanel();
    public MyFrame() throws HeadlessException {
        this.setTitle("五子棋");
        setSize(630,700);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            bgImage= ImageIO.read(new File("C:\\Users\\zzq\\Desktop\\1.jpg"));
        } catch (IOException e1) {

            e1.printStackTrace();
        }
        addMouseListener(this);//将窗体加入监听

        south.setLayout(new FlowLayout(FlowLayout.LEFT,60,30));

        south.add(restart);
        south.add(exit);
        //初始化按钮事件监听器内部类
        MybuttonListener buttonListener =new MybuttonListener();
        //将三个按钮事件注册监听事件
        restart.addActionListener(buttonListener);
        exit.addActionListener(buttonListener);
        //将按钮面板加到窗体的南部
        this.add(south,BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        x1=e.getX();
        y1=e.getY();
        if(x1>=38&&x1<=588&&y1>=58&&y1<=620) {

            setCursor(new Cursor(Cursor.HAND_CURSOR));

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public void checkWin(int count) {

        if(count>=5) {//五子相连

            if(allChess[x][y]==1) {
                JOptionPane.showMessageDialog(this, "黑方胜出!!!!!!");
            }
            if(allChess[x][y]==2) {
                JOptionPane.showMessageDialog(this, "白方胜出!!!!!!");
            }
            canPlay=false;//游戏结束
        }
    }
    //重新开始
    public void restartGame(){
        for(int i=0;i<allChess.length;i++) {
            for(int j=0;j<allChess.length;j++) {
                allChess[i][j]=0;
            }
        }
        flag=1;//默认开始是黑旗先下
        canPlay=true;
        repaint();
    }

    //按钮事件监听器内部类
    class MybuttonListener  implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==restart) {
                restartGame();
            }
            if(e.getSource()==exit) {
                System.exit(0);
            }
        }
    }
}
