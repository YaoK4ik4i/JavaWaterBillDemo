package UI.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
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
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.log4j.Logger;  
import org.apache.log4j.Priority;  
import org.apache.log4j.PropertyConfigurator;  

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import tools.Accounts;
import tools.MakePanel;
import tools.SqliteBaseManager;
import UI.AppMainWindow;
import UI.ConstantsUI;
import UI.MyButtonBorder;

/**
 * 添加面板
 *
 * @author Yao
 * @param <StringPrivate>
 */

public class AddPanel extends JPanel implements ActionListener, ItemListener{
	SqliteBaseManager db = new SqliteBaseManager();  
    ResultSet rs;
    
//    private static final Logger logger = Logger.getLogger(AddPanel.class);
    
    private  JLabel labelPurchaseItemName;
    private  JLabel labelPurchaseItemNum;
    private  JLabel labelPurchaseItemPrice;
    private  JLabel labelPurchaseTime;
    private  JLabel labelPurchasePayMethod;
    private  JLabel labelPurchaseIsPrivate;
    private  JLabel labelPurchaseRemarks;
    
    private  JTextField TextItemName;
    private  JTextField TextItemNum;
    private  JTextField TextItemPrice;
    
    private  JTextArea TextItemRemarks;
    
    private  JPanel RadioItemPayMethod;
    private  JRadioButton ItemPayCash;
    private  JRadioButton ItemPayCreditCard;
    private  JRadioButton ItemPayAlipay;
    private  JRadioButton ItemPayOther;
    
    private  JPanel RadioIsPrivate;
    private  JRadioButton IsPrivate;
    private  JRadioButton IsNotPrivate;
    private DatePicker datepick;
    private DatePickerSettings datepicksetting;
    
    private Border border;
    
    private JButton ClearBtn;
    private JButton ConfirmBtn;
    
    private MakePanel makePanel;
    
    private  String StringPayMethod = "现金";
    private  String StringPrivate = "私人";
    
    ButtonGroup bg1;
    ButtonGroup bg2;
    
    
    /**
     * 构造
     * @throws SQLException 
     */
    public AddPanel() {
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
    	//border = new EtchedBorder(EtchedBorder.LOWERED);
        // this.setBackground(ConstantsUI.MAIN_BACK_COLOR);
    	//this.setBorder(border);
        this.setLayout(new BorderLayout());
        
        labelPurchaseTime = new JLabel("请选择采购时间:", JLabel.CENTER);
        //labelPurchaseTime.setFont(new Font("微软雅黑", Font.PLAIN,20));
        labelPurchaseItemName = new JLabel("采购物品的名称:", JLabel.CENTER);
        labelPurchaseItemNum = new JLabel("采购的数量(x1):", JLabel.CENTER);
        labelPurchaseItemPrice = new JLabel("付款的金额(元):", JLabel.CENTER);
        labelPurchasePayMethod = new JLabel("请输入付款方式(现金/信用卡):", JLabel.CENTER);
        labelPurchaseIsPrivate = new JLabel("是否是私人物品:", JLabel.CENTER);
        labelPurchaseRemarks = new JLabel("备注:", JLabel.CENTER);
        labelPurchaseRemarks .setFont(new Font("微软雅黑", Font.PLAIN,18));
        
        TextItemName = new JTextField(25);  //15x20
        TextItemName.setFont(new Font("宋体", Font.PLAIN,18));
        TextItemNum = new JTextField("1",10);
        TextItemNum.setFont(new Font("宋体", Font.ITALIC,18));
        TextItemNum.setForeground(new Color(171,173,179));
        TextItemPrice = new JTextField("100",15);
        TextItemPrice.setForeground(new Color(171,173,179));
        TextItemPrice.setFont(new Font("宋体", Font.ITALIC,18));
        TextItemRemarks = new JTextArea("..备注一些必要信息",20,30);
        TextItemRemarks.setForeground(new Color(171,173,179));
        TextItemRemarks.setBorder(BorderFactory.createLineBorder(new Color(171,173,179)));
        TextItemRemarks.setFont(new Font("宋体", Font.ITALIC,18));
        TextItemRemarks.setLineWrap(true);      
        TextItemRemarks.setWrapStyleWord(true);            
        
        
        
        
        
        //this.setLayout(new FlowLayout());
    }
    
    /**
     * 添加组件
     * @throws SQLException 
     */
    private void addComponent() throws SQLException {

        this.add(getUpPanel(),BorderLayout.CENTER);
        this.add(getBottomPanel(),BorderLayout.SOUTH);
        // this.add(getDownPanel(), BorderLayout.SOUTH);
    }
    
    /**
     * 上侧面板
     *
     * @return JPanel
     * @throws SQLException 
     */
    private JPanel getUpPanel() {
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(1,2));
    border = new EtchedBorder(EtchedBorder.LOWERED);
    mainPanel.setBorder(border);    
     JPanel upPanel = new JPanel();
     //upPanel.setSize(1350, 538);
     upPanel.setLayout(new GridLayout(6,1));
     
    
     
     
     datepicksetting = new DatePickerSettings();
     datepicksetting.setAllowEmptyDates(false);
     datepicksetting.setFontValidDate(new Font("宋体", Font.PLAIN,18));
//时间选择控件
     datepick = new DatePicker(datepicksetting);  // LocalDate d = datepick.getDate(); //String df = (new SimpleDateFormat("yyyy-MM-dd")).format(d);
     JPanel time = new MakePanel(labelPurchaseTime, datepick);
//  time.setPreferredSize(new Dimension(160,60));
//     time.setLayout(new FlowLayout(0,15,20));
//     time.add(labelPurchaseTime);
//     time.add(datepick);
     upPanel.add(time);
     
     JPanel name = new MakePanel(labelPurchaseItemName,TextItemName);
     
//     name.setLayout(new FlowLayout(0,15,20));
//     name.add(labelPurchaseItemName);
//     name.add(TextItemName);
     upPanel.add(name);
     
     JPanel num = new MakePanel(labelPurchaseItemNum,TextItemNum);
//     num.setLayout(new FlowLayout(0,15,20));
//     num.add(labelPurchaseItemNum);
//     num.add(TextItemNum);      
     upPanel.add(num);
     
     JPanel price = new MakePanel(labelPurchaseItemPrice,TextItemPrice);
//     price.setLayout(new FlowLayout(0,15,20));
//     price.add(labelPurchaseItemPrice);
//     price.add(labelPurchaseItemPrice);      
     upPanel.add(price);
     
   
  
     
     
     RadioItemPayMethod = new JPanel();
     RadioItemPayMethod.setLayout(new FlowLayout(0,5,0));
     ItemPayCash = new JRadioButton("现金");
     ItemPayCash.setFont(new Font("宋体", Font.PLAIN,18));
     ItemPayCash.setSelected(true);
//     ItemPayCash.addItemListener(this);
     
     ItemPayCreditCard = new JRadioButton("信用卡");
     ItemPayCreditCard.setFont(new Font("宋体", Font.PLAIN,18));
//     ItemPayCreditCard.addItemListener(this);
     
//     ItemPayAlipay = new JRadioButton("支付宝");
//     ItemPayAlipay.setFont(new Font("宋体", Font.PLAIN,18));
//     ItemPayOther = new JRadioButton("其他(您可在备注里填写)");
//     ItemPayOther.setFont(new Font("宋体", Font.PLAIN,18));
//     ItemPayOther.addItemListener(this);
     
     bg1 = new ButtonGroup();
     bg1.add(ItemPayCash);
     bg1.add(ItemPayCreditCard);
//     bg1.add(ItemPayAlipay);
//     bg1.add(ItemPayOther);
     
     //RadioItemPayMethod.add(bg1);
     RadioItemPayMethod.add(ItemPayCash);
     RadioItemPayMethod.add(ItemPayCreditCard);
 //  RadioItemPayMethod.add(ItemPayAlipay);
//     RadioItemPayMethod.add(ItemPayOther);
     JPanel paymethod = new MakePanel(labelPurchasePayMethod,RadioItemPayMethod);
//     paymethod.setLayout(new FlowLayout(0,15,20));
//     paymethod.add(labelPurchasePayMethod);
     
//     paymethod.add(RadioItemPayMethod);     
     
     
     upPanel.add(paymethod);
     
   
      
     
     RadioIsPrivate = new JPanel();
     RadioIsPrivate.setLayout(new FlowLayout(0,5,0));
     IsPrivate = new JRadioButton("私人");
     IsPrivate.setFont(new Font("宋体", Font.PLAIN,15));
     
     
     
     IsNotPrivate = new JRadioButton("店内");
     IsNotPrivate.setFont(new Font("宋体", Font.PLAIN,15));
//  IsNotPrivate.addItemListener(this);
     IsNotPrivate.setSelected(true);
     
     bg2 = new ButtonGroup();
     bg2.add(IsPrivate);
     bg2.add(IsNotPrivate);
     
     RadioIsPrivate.add(IsNotPrivate);
     RadioIsPrivate.add(IsPrivate);
     
     JPanel isPrivate = new  MakePanel(labelPurchaseIsPrivate,RadioIsPrivate);
//     isPrivate.setLayout(new FlowLayout(0,15,20));
//     isPrivate.add(labelPurchaseIsPrivate);    
//     
//     
//     isPrivate.add(RadioIsPrivate);       
     upPanel.add(isPrivate);
     
     JPanel remarks = new JPanel();
     JScrollPane remarksScroll = new JScrollPane(TextItemRemarks);
     remarksScroll.setPreferredSize(new Dimension (300,200));
     remarks.setLayout(new FlowLayout(0,20,5));
     remarks.add(labelPurchaseRemarks);
    
     remarks.add(remarksScroll);
     
     mainPanel.add(upPanel);
     mainPanel.add(remarks);
    
           
     
     return mainPanel;
    }
    
    /**
     * 下侧面板
     *
     * @return JPanel
     * @throws SQLException 
     */
    private JPanel getBottomPanel() {
    	 JPanel bottomPanel = new JPanel();
    	 bottomPanel.setLayout(new GridLayout(1,2));
    	 JPanel blank = new JPanel();
    	 
    	 bottomPanel.add(blank);
    	 
    	 JPanel buttonPanel = new JPanel();
    	 buttonPanel.setLayout(new FlowLayout(0,100,25));
    	 
//    	 border = new EtchedBorder(EtchedBorder.LOWERED);
//    	 bottomPanel.setBorder(border);
    	 
    	 ClearBtn = new JButton("清空");
    	 ClearBtn.setPreferredSize(new Dimension(80,30));
    	 ClearBtn.setBorder(new MyButtonBorder());
    	 ClearBtn.setFont(new Font("微软雅黑", Font.PLAIN,20));
    	 ClearBtn.addActionListener(this);
    	 
    	 ConfirmBtn = new JButton("确认");
    	 ConfirmBtn.setPreferredSize(new Dimension(80,30));
    	 ConfirmBtn.setBorder(new MyButtonBorder());
    	 ConfirmBtn.setFont(new Font("微软雅黑", Font.PLAIN,20));
    	 ConfirmBtn.addActionListener(this);
    	 
    	 buttonPanel.add(ClearBtn);
    	 buttonPanel.add(ConfirmBtn);
    	 
    	 bottomPanel.add(buttonPanel);
    	
    	 
    	
         
         return bottomPanel;
    }
    
    private void addListener() {
//    	TextItemNum.getDocument().addDocumentListener(new DocumentListener(){
//
//			@Override
//			public void changedUpdate(DocumentEvent arg0) {
//				// TODO Auto-generated method stub
//			
//			}
//
//			@Override
//			public void insertUpdate(DocumentEvent arg1) {
//				// TODO Auto-generated method stub
//				
//				TextItemNum.setFont(new Font("宋体", Font.PLAIN,18));
//		        TextItemNum.setForeground(Color.BLACK);
//		        TextItemNum.setText("");
//			}
//
//			@Override
//			public void removeUpdate(DocumentEvent arg2) {
//				// TODO Auto-generated method stub
//				
//			}
//    		
//    	});
    	TextItemNum.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				TextItemNum.setText("");
				TextItemNum.setFont(new Font("宋体", Font.PLAIN,18));
		        TextItemNum.setForeground(Color.BLACK);
//		        TextItemNum.setBorder(new LineBorder(Color.BLACK));
		        
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}			
    		
    	});
    	
    	TextItemPrice.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				TextItemPrice.setText("");
				TextItemPrice.setFont(new Font("宋体", Font.PLAIN,18));
				TextItemPrice.setForeground(Color.BLACK);
		        
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}			
    		
    	});
    	
    	TextItemRemarks.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				TextItemRemarks.setText("");
				TextItemRemarks.setFont(new Font("宋体", Font.PLAIN,18));
				TextItemRemarks.setForeground(Color.BLACK);
		        
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}			
    		
    		}); 
    }
    	
//    	IsPrivate.addItemListener(new ItemListener(){
//
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				// TODO Auto-generated method stub
//				if(e.getSource() == IsPrivate){
//				StringPrivate = "私人";
//				}
//			}
//    		});
//    	
//    	IsNotPrivate.addItemListener(new ItemListener(){
//
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				// TODO Auto-generated method stub
//				if(e.getSource() == IsNotPrivate){
//				StringPrivate = "店内";
//				}
//			}
//    		});
//
//		}
//    
//    
//	@Override
//	public void itemStateChanged(ItemEvent e) {
//		// TODO Auto-generated method stub
//		if(e.getSource() == ItemPayCash){
//			 StringPayMethod = "现金";
//		}else if(e.getSource() == ItemPayCreditCard){
//			 StringPayMethod = "信用卡";
//		}
//			
//		}
		
//		if(e.getSource() == IsPrivate){
//			StringPrivate = "是";
//		}else if(e.getSource() == IsNotPrivate){
//			StringPrivate = "否";
//		}
	

		
    @Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
    	if(e.getSource() == ClearBtn){
    		TextItemName.setText("");
    		TextItemNum.setText("");
    		TextItemPrice.setText("");
    		TextItemRemarks.setText("");
    	}else if(e.getSource() == ConfirmBtn){
    		if(TextItemName.getText().trim().equals("")){
    			JOptionPane.showMessageDialog(null,"你要记录的购入物品名称是什么？","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
    		}else if(TextItemPrice.getText().trim().equals("")){
    			JOptionPane.showMessageDialog(null,"没有填写所要记录物品的价格","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
    		}else{
    			try{
    				if(TextItemRemarks.getText().trim().equals("..备注一些必要信息")){
    					TextItemRemarks.setText("");
    				}
    				String localDate = datepick.getDateStringOrEmptyString();
    				String [] Date= localDate.split("-");    			
    				
    				String theYear = Date[0];
    				String theMonth = Date[1];
    				String theDay =Date[2];
    				
    				Enumeration<AbstractButton> paymethodBtns= bg1.getElements();  
					while (paymethodBtns.hasMoreElements()) {  
					    AbstractButton btn = paymethodBtns.nextElement();  
					    if(btn.isSelected()){  
					    	StringPayMethod=btn.getText();  
					        break;  
					    }  
					} 
					
					Enumeration<AbstractButton> paymethodBtns2= bg2.getElements();  
					while (paymethodBtns2.hasMoreElements()) {  
					    AbstractButton btn = paymethodBtns2.nextElement();  
					    if(btn.isSelected()){  
					    	StringPrivate=btn.getText();  
					        break;  
					    }  
					} 
					
//    				System.out.println(StringPrivate+StringPayMethod);
    				if(db.insertTable(new Accounts(null,theYear,theMonth,theDay,StringPayMethod,StringPrivate,TextItemName.getText().trim(),TextItemNum.getText().trim(),TextItemPrice.getText().trim() ,TextItemRemarks.getText().trim()))){
    					JOptionPane.showMessageDialog(null, "添加账目信息成功！","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
    					TextItemName.setText("");
    		    		TextItemNum.setText("");
    		    		TextItemPrice.setText("");
    		    		TextItemRemarks.setText("");
    				}else{
    					JOptionPane.showMessageDialog(null, "添加账目信息失败！","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
    				}
//    				db.closeConnection();
    			}catch(Exception  ex){  			
    				 AppMainWindow.logger.error(ex.toString());
    			}
    		}
    	}
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}


    		
				
	
    

    
    
    

}
