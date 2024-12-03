package GUI;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;


public class GameJFrame extends JFrame implements KeyListener, ActionListener {


    //跟游戏主界面相关的代码
    int step = 0;

    int[][] data = new int[4][4];

    int x = 0, y = 0;
    String path = "src\\GUI\\image\\animal\\animal3\\";

    JMenuItem regameJI = new JMenuItem("重新游戏");
    JMenuItem reloginJI = new JMenuItem("重新登录");
    JMenuItem exitJI = new JMenuItem("退出游戏");

    JMenuItem myWechat = new JMenuItem("加个好友QAQ");

    JMenuItem girl = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");


    public GameJFrame() {
        //初始化界面
        initJFrame();
        //初始化菜单
        initJMenuBar();
        //初始化数据
        initData();
        //初始化图片
        initImage();

        this.setVisible(true);
    }

    //初始化数据
    private void initData() {
        int [] temparr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};
        Random rand = new Random();
        int index = 0;
        int temp=0;
        //打乱数组
        for (int i = 0; i < temparr.length; i++) {
            index = rand.nextInt(temparr.length);
            temp=temparr[i];
            temparr[i]=temparr[index];
            temparr[index]=temp;
        }
        //二维化数据
        for (int i = 0; i < temparr.length; i++) {
            if(temparr[i]==0){
                x = i / 4;
                y = i % 4;
            }
            data[i/4][i%4] = temparr[i];
        }
    }

    //初始化图片
    private void initImage() {
        //清屏
        this.getContentPane().removeAll();

        //计步
        JLabel stepcount = new JLabel("步数：" + step);
        stepcount.setBounds(50,30,100,20);
        this.getContentPane().add(stepcount);

        //添加胜利图标
        if(victory()){
            JLabel winJLabel = new JLabel(new ImageIcon("src/GUI/image/win.png"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);
        }

        //放置图片
        for (int i = 0 ; i < 4 ; i++) {
            for (int j = 0 ; j < 4 ; j++) {
                //获取数据
                int number = data[i][j];
                //创建ImageIcon对象并放入JLabel容器中
                JLabel jLabel = new JLabel(new ImageIcon(path+number+".jpg"));
                //设置边框
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                //设置图片参数
                jLabel.setBounds(105*j + 83, 105*i + 134, 105, 105);
                //将JLabel容器放入JFrame界面中
                this.getContentPane().add(jLabel);
            }
        }

        //放置背景
        JLabel backgroud = new JLabel(new ImageIcon("src\\GUI\\image\\background.png"));
        backgroud.setBounds(40, 40, 508, 560);
        this.getContentPane().add(backgroud);

        //刷新
        this.getContentPane().repaint();

    }


    //初始化界面
    public void initJFrame(){
        this.setSize(603, 680);
        this.setTitle("拼图 v1.0");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.setLayout(null);
    }

    //初始化菜单栏
    public void initJMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我");

        JMenu changeImage = new JMenu("更换图片");



        //给条目绑定事件
        regameJI.addActionListener(this);
        reloginJI.addActionListener(this);
        exitJI.addActionListener(this);
        myWechat.addActionListener(this);
        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);


        menuBar.add(functionJMenu);
        menuBar.add(aboutJMenu);
        functionJMenu.add(changeImage);
        functionJMenu.add(regameJI);
        functionJMenu.add(reloginJI);
        functionJMenu.add(exitJI);
        aboutJMenu.add(myWechat);

        changeImage.add(girl);
        changeImage.add(animal);
        changeImage.add(sport);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //查看原图
        int keyCode = e.getKeyCode();
        if (keyCode == 65) {
            this.getContentPane().removeAll();

            ImageIcon ii = new ImageIcon(path + "all.jpg");
            JLabel jLabel = new JLabel(ii);
            jLabel.setBounds(83,134,420,420);
            this.getContentPane().add(jLabel);

            JLabel backgroud = new JLabel(new ImageIcon("src\\GUI\\image\\background.png"));
            backgroud.setBounds(40, 40, 508, 560);
            this.getContentPane().add(backgroud);

            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {




        int keyCode = e.getKeyCode();

        if(victory()){
            initImage();
            return;
        }

        //图片左移
        if(keyCode == 37){

            if (y == 3) {
                return;
            }
            else {
                data[x][y] = data[x][y+1];
                data[x][y+1] = 0;
                y++;
                step++;
                initImage();
            }
        }
        //图片右移
        else if(keyCode == 39){
            if(y == 0){
                return;
            }
            else {
                data[x][y] = data[x][y-1];
                data[x][y-1] = 0;
                y--;
                step++;
                initImage();
            }

        }
        //图片上移
        else if(keyCode == 38){
            if (x == 3) {
                return;
            }
            else {
                data[x][y] = data[x+1][y];
                data[x+1][y] = 0;
                x++;
                step++;
                initImage();
            }
        }
        //图片下移
        else if(keyCode == 40){
            if(x == 0){
                return;
            }
            else{
                data[x][y] = data[x-1][y];
                data[x-1][y] = 0;
                x--;
                step++;
                initImage();
            }

        }

        //查看原图
        else if(keyCode == 65){
            initImage();
        }

        //一键通关
        else if(keyCode == 87){
            data =  new int [][]{{1,2,3,4},
                            {5,6,7,8},
                            {9,10,11,12},
                            {13,14,15,0},
            };
            initImage();
        }



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == regameJI){
            //计数器清零
            step = 0;
            //初始化数据
            initData();
            //初始化图片
            initImage();
        } else if (source == reloginJI) {
            //关闭当前界面
            this.setVisible(false);
            //弹出登录界面
            new LoginJFrame();
        } else if (source == exitJI) {
            System.exit(0);
        } else if (source == myWechat) {
            //创建弹出窗
            JDialog jDialog = new JDialog();
            //创建容器
            JLabel jLabel = new JLabel(new ImageIcon("src/GUI/image/myWechat.png"));
            jLabel.setBounds(0,0,500,522);
            jDialog.getContentPane().add(jLabel);

            //设置标题
            jDialog.setTitle("落影成花终有期");
            //设置大小
            jDialog.setSize(600,600);
            //居中
            jDialog.setLocationRelativeTo(null);
            //置顶
            jDialog.setAlwaysOnTop(true);
            //不关闭无法执行下一步操作
            jDialog.setModal(true);
            //设置可视化
            jDialog.setVisible(true);



        } else if (source == girl) {
            Random r = new Random();
            int num = r.nextInt(1,14);
            path = "src\\GUI\\image\\girl\\girl" + num + "\\";
            initData();
            initImage();
        } else if (source == animal) {
            Random r = new Random();
            int num = r.nextInt(1,9);
            path = "src\\GUI\\image\\animal\\animal" + num + "\\";
            initData();
            initImage();
        } else if (source == sport) {
            Random r = new Random();
            int num = r.nextInt(1,11);
            path = "src\\GUI\\image\\sport\\sport" + num + "\\";
            initData();
            initImage();
        }
    }

    public boolean victory(){
        int win[][] = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }


}
