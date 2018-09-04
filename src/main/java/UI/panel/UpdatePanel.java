package UI.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import org.apache.log4j.Logger;  
import org.apache.log4j.Priority;  
import org.apache.log4j.PropertyConfigurator;  

import UI.AppMainWindow;
import UI.ConstantsUI;
import UI.MyButtonBorder;
import tools.MakePanel;
import tools.SqliteBaseManager;

public class UpdatePanel extends JPanel {
	SqliteBaseManager db = new SqliteBaseManager();  
    ResultSet rs;
    
//    private static final Logger logger = Logger.getLogger(UpdatePanel.class);
    
    private JLabel labelSearchId;
    private JLabel labelUpdateName;
    private JLabel labelUpdateNum;
    private JLabel labelUpdatePrice;
    private JLabel labelUpdatePaymethod;
    private JLabel labelUpdatePirvate;
    private JLabel labelUpdateRemarks;
    
    private  JTextField textSearchId;
    private  JTextField textUpdateName;
    private  JTextField textUpdateNum;
    private  JTextField textUpdatePrice;    
    private  JTextField textUpdateRemarks;
    
    private  JPanel panelPayMethod;
    private  ButtonGroup bgPayMethod;
    private  JRadioButton radioUpdateCash;
    private  JRadioButton radioUpdateCard;
//    private  JRadioButton radioUpdateOther;
    
    private  JPanel panelPirvate;
    private  ButtonGroup bgPirvate;
    private  JRadioButton radioUpdateIsPrivate;
    private  JRadioButton radioUpdateIsNotPrivate;
    
    private JButton buttonSearchFromIdBtn;
    private JButton buttonDeleteBtn;
    private JButton buttonUpdateBtn;
    
    private Border border;
    
    private String strSQL = null;
    
    /**
     * 构造
     * @throws SQLException 
     */
    public UpdatePanel() {
    	try{       
        initialize();
        addComponent();
        addListener();
    	}catch(SQLException e){
    		 AppMainWindow.logger.error(e.toString());
    		e.printStackTrace();
    	}
    }
    
    /**
     * 初始化
     */
    private void initialize() {
    	 this.setLayout(new BorderLayout());
    	 
    	 labelSearchId = new JLabel("请填写需要修改的账目id:", JLabel.CENTER);
    	 labelUpdateName = new JLabel("物品名称:", JLabel.CENTER);
    	 labelUpdateNum = new JLabel("购买数量:", JLabel.CENTER);
    	 labelUpdatePrice = new JLabel("付款的金额:", JLabel.CENTER);
    	 labelUpdatePaymethod = new JLabel("付款方式:", JLabel.CENTER);
    	 labelUpdatePirvate = new JLabel("是否是私人用品:", JLabel.CENTER);
    	 labelUpdateRemarks = new JLabel("备注:", JLabel.CENTER);
    	 
    	 textSearchId = new JTextField(20);  //15x20
    	 textSearchId.setFont(new Font("宋体", Font.PLAIN,18));
    	 
    	 textUpdateName = new JTextField(25);
    	 textUpdateName.setFont(new Font("宋体", Font.PLAIN,18));
    	 
    	 textUpdateNum = new JTextField(15);
    	 textUpdateNum.setFont(new Font("宋体", Font.PLAIN,18));
    	 
    	 textUpdatePrice = new JTextField(20);
    	 textUpdatePrice.setFont(new Font("宋体", Font.PLAIN,18));
    	 
    	 textUpdateRemarks = new JTextField(25);
    	 textUpdateRemarks.setFont(new Font("宋体", Font.PLAIN,18));
//    	 textUpdateRemarks.setLineWrap(true);      
//    	 textUpdateRemarks.setWrapStyleWord(true); 
    	 
    	 buttonSearchFromIdBtn = new JButton("搜索");
    	 buttonSearchFromIdBtn.setPreferredSize(new Dimension(80,30));
    	 buttonSearchFromIdBtn.setBorder(new MyButtonBorder());
    	 buttonSearchFromIdBtn.setFont(new Font("微软雅黑", Font.PLAIN,20));
    	 
    	panelPayMethod = new JPanel();
    	panelPayMethod.setLayout(new FlowLayout(0,2,0));
    	radioUpdateCash = new JRadioButton("现金");
    	radioUpdateCash.setFont(new Font("宋体", Font.PLAIN,18));
    	
    	radioUpdateCard = new JRadioButton("信用卡");
    	radioUpdateCard.setFont(new Font("宋体", Font.PLAIN,18));
//    	
//    	radioUpdateOther = new JRadioButton("其他");
//    	radioUpdateOther.setFont(new Font("宋体", Font.PLAIN,18));
    	
    	bgPayMethod = new ButtonGroup();
    	bgPayMethod.add(radioUpdateCash);
    	bgPayMethod.add(radioUpdateCard);
//    	bgPayMethod.add(radioUpdateOther);
    	
    	panelPayMethod.add(radioUpdateCash);
    	panelPayMethod.add(radioUpdateCard);
//    	panelPayMethod.add(radioUpdateOther);
    	
    	panelPirvate = new JPanel();
    	panelPirvate.setLayout(new FlowLayout(0,5,0));
    	radioUpdateIsPrivate = new JRadioButton("私人");
    	radioUpdateIsPrivate.setFont(new Font("宋体", Font.PLAIN,18));
    	
    	radioUpdateIsNotPrivate = new JRadioButton("店内");
    	radioUpdateIsNotPrivate.setFont(new Font("宋体", Font.PLAIN,18));    	
    	   	
    	bgPirvate = new ButtonGroup();
    	bgPirvate.add(radioUpdateIsPrivate);
    	bgPirvate.add(radioUpdateIsNotPrivate);    	
    	
    	panelPirvate.add(radioUpdateIsPrivate);
    	panelPirvate.add(radioUpdateIsNotPrivate);
    	
    	
    	 buttonDeleteBtn = new JButton("删除此记录");
    	 buttonDeleteBtn.setPreferredSize(new Dimension(80,30));
    	 buttonDeleteBtn.setBorder(new MyButtonBorder());
    	 buttonDeleteBtn.setFont(new Font("微软雅黑", Font.BOLD,15));
    	 buttonDeleteBtn.setForeground(new Color(208,95,99));
    	 
    	 buttonUpdateBtn = new JButton("确认修改");
    	 buttonUpdateBtn.setPreferredSize(new Dimension(80,30));
    	 buttonUpdateBtn.setBorder(new MyButtonBorder());
    	 buttonUpdateBtn.setFont(new Font("微软雅黑", Font.PLAIN,18));
    	
    	 
    	
    	 
    	 
    }
    
    /**
     * 添加组件
     * @throws SQLException 
     */
    private void addComponent() throws SQLException {

        this.add(getUpPanel(),BorderLayout.NORTH);
        this.add(getCenterPanel(),BorderLayout.CENTER);
        this.add(getBottomPanel(),BorderLayout.SOUTH);
        // this.add(getDownPanel(), BorderLayout.SOUTH);
    }
    
    private JPanel getUpPanel(){
    	 JPanel upPanel = new JPanel();
    	 upPanel.setLayout(new GridLayout(1,2));
    	 border = new EtchedBorder(EtchedBorder.LOWERED);
    	 upPanel.setBorder(border);  
    	 
    	 JPanel searchId = new MakePanel(labelSearchId, textSearchId);
    	 searchId.add(buttonSearchFromIdBtn);
    	 
    	 upPanel.add(searchId);
    	 
    	 return upPanel;
    	
    }
    
    private JPanel getCenterPanel(){
    	JPanel centerPanel = new JPanel();
    	centerPanel.setLayout(new GridLayout(5,2));
    	 
    	 JPanel updateName = new MakePanel(labelUpdateName, textUpdateName);
    	 JPanel updateNum = new MakePanel(labelUpdateNum, textUpdateNum);
    	 JPanel updatePrice = new MakePanel(labelUpdatePrice, textUpdatePrice);    	
    	 JPanel updatePaymethod = new MakePanel(labelUpdatePaymethod, panelPayMethod);
    	 JPanel updatePrivate =  new MakePanel(labelUpdatePirvate, panelPirvate);    	 
    	 JPanel updateRemarks = new MakePanel(labelUpdateRemarks, textUpdateRemarks);    	
//    	 updateRemarks.setLayout(new FlowLayout(0,20,5));
//    	 JScrollPane remarksScroll = new JScrollPane(textUpdateRemarks);
//    	 remarksScroll.setPreferredSize(new Dimension (300,200));
//    	 updateRemarks.add(labelUpdateRemarks);
//    	 updateRemarks.add(remarksScroll);
    	 
    	 centerPanel.add(updateName);
    	 centerPanel.add(updatePaymethod);
    	 centerPanel.add(updateNum);
    	 centerPanel.add(updatePrivate);
    	 centerPanel.add(updatePrice);
    	 centerPanel.add(updateRemarks);   	 
    	 
    	return centerPanel;    	
    }
    
    private JPanel getBottomPanel(){
    	JPanel bottomPanel = new JPanel();
    	bottomPanel.setLayout(new GridLayout(1,2));
    	bottomPanel.setBorder(border);  
    	
    	JPanel blank = new JPanel();
    	
    	
    	bottomPanel.add(blank);
    	
    	JPanel buttonPanel = new JPanel();
   	    buttonPanel.setLayout(new FlowLayout(0,100,25));
   	    
   	    buttonPanel.add(buttonDeleteBtn);
   	    buttonPanel.add(buttonUpdateBtn);
   	    
   	    bottomPanel.add(buttonPanel);
   	    
		return bottomPanel;
    	
    }
    
    private void addListener() {
    	 buttonSearchFromIdBtn.addActionListener(new ActionListener(){
    		 
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 String itemname = null;
	    		 String paymethod = null;
	    		 String itemnumber = null;
	    		 String isprivate = null;
	    		 String price = null;
	    		 String remarks = null;
	    		 int i = 0;
	    		 Boolean b = false;
	    		 
	    		  textUpdateName.setText(""); 
	               textUpdateNum.setText("");
	               textUpdatePrice.setText("");
	               textUpdateRemarks.setText("");
				if( textSearchId.getText().trim().equals("")){	
					JOptionPane.showMessageDialog(null,"您必须输入账目id才能找到需要修改的账目信息。","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
				}else{
					try{
					String updateId = textSearchId.getText().trim();
					strSQL = "select * from accounts where orderid =\""+ updateId +"\"";
					
					rs= db.getResult(strSQL);
//					if(rs.wasNull()){
//						
//			            	 JOptionPane.showMessageDialog(null,"未找到此账目id，请确认id的正确性");
//			                 
//					}
//					else{
//					int row = rs.getInt("rowCount");
//					System.out.println(row);
//					if(row){
						int col = rs.getMetaData().getColumnCount();
					 while(rs.next()){
		                    for(i=1;i<col;i++)  {
		                    itemname = rs.getString("itemname");
		                    paymethod = rs.getString("paymethod");
		                    itemnumber = rs.getString("itemnumber");
		                    isprivate = rs.getString("isprivate");
		                    price = rs.getString("price");
		                    remarks = rs.getString("REMARKS"); 
		                    b = true;
		                    }
					 }
		               
//		                    System.out.println(b);
		              if(b){
		               textUpdateName.setText(itemname); 
		               textUpdateNum.setText(itemnumber);
		               textUpdatePrice.setText(price);
		               textUpdateRemarks.setText(remarks);
//		               System.out.println(isprivate);
//		               System.out.println(paymethod);
		            
		               if((paymethod.equals("现金") )){
		            	   radioUpdateCash.setSelected(true);
		               }else if((paymethod.equals("信用卡"))){
		            	   radioUpdateCard.setSelected(true);		           
		               }else{
		            	   JOptionPane.showMessageDialog(null,"数据未找到您此条账目的付款方式，已选择默认。","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
		            	   radioUpdateCash.setSelected(true);
		               }
		               
		               if(isprivate.equals("私人")){
		            	   radioUpdateIsPrivate.setSelected(true);
		               }else if(isprivate.equals("店内")){
		            	   radioUpdateIsNotPrivate.setSelected(true);
		               }else{
		            	   JOptionPane.showMessageDialog(null,"数据未找到您此条账目是否私人物品，已选择默认。","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
		            	   radioUpdateIsNotPrivate.setSelected(true);
		            	  }
		                    
		              	}else{
			 					 JOptionPane.showMessageDialog(null,"未找到此账目id，请确认id的正确性","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
			              }
					 
					}catch(Exception sqle){
						 AppMainWindow.logger.error(sqle.toString());
						sqle.printStackTrace();
					}
			}
			} 
    	 });
    	 
    	 buttonDeleteBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if( textSearchId.getText().trim().equals("")){	
					JOptionPane.showMessageDialog(null,"您必须输入账目id才能找到需要修改的账目信息。","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
				}else{
					try{
					String updateId = textSearchId.getText().trim();
					strSQL = "delete  from accounts where orderid =\""+ updateId +"\"";
				// TODO Auto-generated method stub
				int n = JOptionPane.showConfirmDialog(null, "是否删除此条账目信息?", "否",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,ConstantsUI.ICON_ALERT );   //对话框
				if(n==JOptionPane.YES_OPTION){
					boolean f = db.setTable(strSQL);
					if(f){
					JOptionPane.showMessageDialog(null,"账目号id"+  updateId+ "已经被删除。","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
					}else{
						JOptionPane.showMessageDialog(null,"账目号id"+  updateId+ "删除失败，请重试。","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
					}
					
    	 			}else{
    	 				return; 
    	 			}
			}catch(Exception sqle){
				 AppMainWindow.logger.error(sqle.toString());
				sqle.printStackTrace();
			}
					
				}
				}
    		 
    	 });
    	 
    	 buttonUpdateBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				 String itemname = null;
	    		 String paymethod = null;
	    		 String itemnumber = null;
	    		 String isprivate = null;
	    		 String price = null;
	    		 String remarks = null;
	    		 Boolean b = false;
	    		 
	    		 if(textUpdateName.getText().trim().equals("")){
	     			JOptionPane.showMessageDialog(null,"你要修改的物品名称不能为空？","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
			}else if(textUpdateNum.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null,"你要修改的物品数量不能为空？","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
				}else if( textUpdatePrice.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null,"你要修改的物品价格不能为空？","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
				}else{
					try{
					itemname = textUpdateName.getText().trim();
					itemnumber = textUpdateNum.getText().trim();
					price = textUpdatePrice.getText().trim();
					remarks = textUpdateRemarks.getText().trim();
					
					Enumeration<AbstractButton> paymethodBtns= bgPayMethod.getElements();  
					while (paymethodBtns.hasMoreElements()) {  
					    AbstractButton btn = paymethodBtns.nextElement();  
					    if(btn.isSelected()){  
					    	paymethod=btn.getText();  
					        break;  
					    }  
					}  
					
				    Enumeration<AbstractButton> privateBtns= bgPirvate.getElements();  
						while (privateBtns.hasMoreElements()) {  
					    AbstractButton btns = privateBtns.nextElement();  
					    if(btns.isSelected()){  
					    	 isprivate =btns.getText();  
					        break;  
					    }  
				}
						
					String updateId = textSearchId.getText().trim();
					
					strSQL = "update accounts set ITEMNAME =  \""+ itemname  +	
							"\" , ITEMNUMBER = \"" + itemnumber 	+ 
							"\" , PRICE = \"" + price 	+ 
							"\" , PAYMETHOD =  \"" + paymethod  +
							"\" , ISPRIVATE = \"" + isprivate +
							"\" , REMARKS = \"" + remarks + 
							"\" where ORDERID = \"" + updateId + "\"";
					
					boolean f = db.setTable(strSQL);
					if(f){
						JOptionPane.showMessageDialog(null,"账目号id"+  updateId+ "更新成功。","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
					}else{
						JOptionPane.showMessageDialog(null,"账目号id"+  updateId+ "更新失败，请重试！","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ALERT );
					}
					}catch(Exception sqle){
						 AppMainWindow.logger.error(sqle.toString());
						sqle.printStackTrace();
					}
			}
			}
    	 });
    }
    
    
    
}
