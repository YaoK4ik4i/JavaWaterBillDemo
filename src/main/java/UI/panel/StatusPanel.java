package UI.panel;

import UI.AppMainWindow;
import UI.ConstantsUI;
import UI.MyIconButton;

import org.apache.log4j.Logger;  
import org.apache.log4j.Priority;  
import org.apache.log4j.PropertyConfigurator;  

import tools.ConstantsTools;
//import tools.PropertyUtil;
import tools.SqliteBaseManager;



import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 状态面板
 *
 * @author Yao
 */
public class StatusPanel extends JPanel {
    SqliteBaseManager db = new SqliteBaseManager();  
    ResultSet rs;
    


    private static final long serialVersionUID = 1L;
//    private static final Logger logger = Logger.getLogger(StatusPanel.class);

   
    private static JLabel labelLastTime;   //上次记录时间标签
    private static JLabel labelTime;    //上次记录内容的时间内容标签
    private static JLabel labelLastFive;         //最新五笔账目
    private static JLabel labelFirst;
    private static JLabel labelSecond;
    private static JLabel labelThird;
    private static JLabel labelFourth;
    private static JLabel labelFifth;
    private Border border;


    

    

    /**
     * 构造
     * @throws SQLException 
     */
    public StatusPanel() {
    	try{        
        initialize();
        addComponent();
           }catch( SQLException e){
        	   // TODO Auto-generated catch block
        	   AppMainWindow.logger.error(e.toString());
               e.printStackTrace();
           }
    }

    /**
     * 初始化
     */
    public void initialize() {
    	border = new EtchedBorder(EtchedBorder.LOWERED);
        // this.setBackground(ConstantsUI.MAIN_BACK_COLOR);
    	this.setBorder(border);
        this.setLayout(new GridLayout(2,1));
        //this.setLayout(new FlowLayout());
    }

    /**
     * 添加组件
     * @throws SQLException 
     */
    public void addComponent() throws SQLException {

        this.add(getLeftPanel());
        this.add(getRightPanel());
        // this.add(getDownPanel(), BorderLayout.SOUTH);

    }

    /**
     * 左侧面板
     *
     * @return JPanel
     * @throws SQLException 
     */
    private JPanel getLeftPanel() throws SQLException {
//       String year;
//       String month;
//       String day;
        String sumTime = null;
        JPanel panelLeft = new JPanel();
        //panelUp.setBackground(ConstantsUI.MAIN_BACK_COLOR);
        //组件按照加入的先后顺序按照设置的对齐方式从左向右排列，一行排满到下一行开始继续排列
       // panelLeft.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 50));
        //panelLeft.setBorder(new EmptyBorder(1, 1, 1, 1));
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));  //box布局垂直方式
        panelLeft.add(Box.createRigidArea(new Dimension(20,25)));    //在顶部添加一个隐藏面积为15X25的箱子
       // panelLeft.add(Box.createHorizontalStrut(25)); 
        
        
        

        JLabel labelLastTime = new JLabel(" 您好，欢迎回来！上一次您记录的日期:");
        labelLastTime.setFont(new Font("宋体",Font.BOLD,20));
        labelLastTime.setIcon(ConstantsUI.ICON_STATUS_HELLO);
        // labelTitle.setFont(ConstantsUI.FONT_TITLE);
        // labelTitle.setForeground(ConstantsUI.TOOL_BAR_BACK_COLOR);
        panelLeft.add(labelLastTime);
        panelLeft.add(Box.createRigidArea(new Dimension(0,20)));
        
        String strSQL = "SELECT * FROM accounts ORDER BY orderid DESC LIMIT 1";
        rs = db.getResult(strSQL);
        int col = rs.getMetaData().getColumnCount();
        if(col > 0){
        while(rs.next()){
                    String year = rs.getString("year");
                    String month = rs.getString("month");
                    String day = rs.getString("day");
                    sumTime = "   "+ year+ "年" + month +"月" + day+"日";

        }
        }else{
        			sumTime ="没有查询到数据";
        }
        JLabel labelTime = new JLabel(sumTime);
        labelTime.setFont(new Font("微软雅黑",Font.PLAIN,18));

        
        panelLeft.add(labelTime);

        return panelLeft;
    }

    /**
     * 中部面板
     *
     * @return JPanel
     * @throws SQLException 
     */
    public  JPanel getRightPanel() throws SQLException {
    	String orderid = null;
    	 String year = null;
    	 String month = null;
    	 String day = null;
    	 String itemname = null;
    	 String price = null;
        // 中间面板
         ArrayList<String> list=new ArrayList<String>();
//      String[] sumAccount = null;
         int i = 0;
         int j = 0;
        JPanel panelRight = new JPanel();
    // ArrayList<String> list = null;
    // panelCenter.setBackground(ConstantsUI.MAIN_BACK_COLOR);
        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
        panelRight.add(Box.createRigidArea(new Dimension(600,0)));   //20

        JLabel labelLastFive = new JLabel("最近5笔记录:");
        labelLastFive.setFont(ConstantsUI.FONT_TITLE);        
        panelRight.add(labelLastFive);
        panelRight.add(Box.createRigidArea(new Dimension(650,18))); 
        
        try{
        String strSQL = "SELECT * FROM accounts ORDER BY orderid DESC LIMIT 5";
        rs= db.getResult(strSQL);
        int rowCount = rs.getRow();
//        System.out.println(rowCount);
        int col = rs.getMetaData().getColumnCount();
//     sumAccount= new String[rowCount];
       
        while(rs.next()){
                    for(i=1;i<col;i++){
                    orderid = rs.getString("orderid");
                    year = rs.getString("year");
                    
                    month = rs.getString("month");
                    day = rs.getString("day");
                    itemname = rs.getString("itemname");
                    price = rs.getString("price");
                   
                   
                   
                    }  
                    list.add("账目id:"+ orderid + "        发生时间:" +year+ "年" + month +"月" + day + "日                      " +"购买了:"+ 
                            itemname+"               用了: ￥" +price +"元");
        }
        if(list.isEmpty()==false){
         String[] strArray=new String[list.size()];
//         System.out.println("list数据有" + list.size() + "条");
         list.toArray(strArray);
         JLabel labelFirst = new JLabel((strArray[0]));
         JLabel labelSecond = new JLabel((strArray[1]));
         JLabel labelThird = new JLabel((strArray[2]));
         JLabel labelFourth = new JLabel((strArray[3]));
         JLabel labelFifth = new JLabel((strArray[4]));
         labelFirst.setIcon(ConstantsUI.ICON_STATUS_YES);
         labelFirst.setFont(ConstantsUI.FONT_CONTENT);  
         labelSecond.setIcon(ConstantsUI.ICON_STATUS_YES);
         labelSecond.setFont(ConstantsUI.FONT_CONTENT);  
         labelThird.setIcon(ConstantsUI.ICON_STATUS_YES);
         labelThird.setFont(ConstantsUI.FONT_CONTENT);  
         labelFourth.setIcon(ConstantsUI.ICON_STATUS_YES);
         labelFourth.setFont(ConstantsUI.FONT_CONTENT);  
         labelFifth.setIcon(ConstantsUI.ICON_STATUS_YES);
         labelFifth.setFont(ConstantsUI.FONT_CONTENT);  
         panelRight.add(labelFirst);
         panelRight.add(Box.createRigidArea(new Dimension(650,10))); 
         panelRight.add(labelSecond);
         panelRight.add(Box.createRigidArea(new Dimension(0,10))); 
         panelRight.add(labelThird);
         panelRight.add(Box.createRigidArea(new Dimension(0,10))); 
         panelRight.add(labelFourth);
         panelRight.add(Box.createRigidArea(new Dimension(0,10))); 
         panelRight.add(labelFifth);
         }else{
        	 JLabel labelFirst = new JLabel();
             JLabel labelSecond =  new JLabel();
             JLabel labelThird = new JLabel();
             JLabel labelFourth =  new JLabel();
             JLabel labelFifth = new JLabel();
         } 
        }catch(SQLException sqle){
        	AppMainWindow.logger.error(sqle.toString());
			sqle.printStackTrace();
        }
        return panelRight;
    }

    
}
