package UI.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;  
import org.apache.log4j.Priority;  
import org.apache.log4j.PropertyConfigurator;  

import tools.DateSplit;
import tools.MakePanel;
import tools.MakeStatisticsPanel;
import tools.SqliteBaseManager;
import tools.Caculate;
import UI.AppMainWindow;
import UI.ConstantsUI;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
/**
 * 查询统计面板
 *
 * @author Yao
 * @param <StringPrivate>
 */
public class StatisticsPanel extends JPanel {
	SqliteBaseManager db = new SqliteBaseManager();  
    ResultSet rs;
    
//	private static final Logger logger = Logger.getLogger(StatisticsPanel.class);
	
	 private JLabel labelStatitsticsHeader;
	 private JLabel labelStatitsticsYear;
	 private JLabel labelStatitsticsMonth;
	 private JLabel labelStatitsticsCash;
	 private JLabel labelStatitsticsCashSummary;
	 private JLabel labelStatitsticsCashShopSummary;
	 private JLabel labelStatitsticsCashPrivateSummary;	 
	 private JLabel labelStatitsticsCard;
	 private JLabel labelStatitsticsCardSummary;
	 private JLabel labelStatitsticsCardShopSummary;
	 private JLabel labelStatitsticsCardPrivateSummary;
	 private JLabel labelStatitsticsAllSummary;
//	 private JLabel labelYuan;
	 private JLabel blankPanel;
	 
	 private  JTextField textStatitsticsYear;
	 private  JTextField textStatitsticsMonth;
	 
	 private JScrollPane scrollCashShopTable;	    
	 private JTable tableCashShop;
	 
	 private JScrollPane scrollCashPrivateTable;	    
	 private JTable tableCashPrivate;
	 
	 private JScrollPane scrollCardShopTable;	    
	 private JTable tableCardShop;
	 
	 private JScrollPane scrollCardPrivateTable;	    
	 private JTable tableCardPrivate;
	 
	 private Border border;
	 
	 private JButton buttonsearchStatitstics;
	 
	 private String [] date;
	 private String yearA;
	 private String monthA;
	 
	 Vector<String>  dataColumn; 
	 Vector dataCashShopRow = new  Vector<String>();
	 Vector dataCashPrivateRow = new  Vector<String>();
	 Vector dataCardShopRow = new  Vector<String>();
	 Vector dataCardPrivateRow = new  Vector<String>();
	 
	 Vector <String> sumData = new  Vector <String>();
	 
	 DefaultTableModel modelCashShop; 
	 DefaultTableModel modelCashPrivate; 
	 DefaultTableModel modelCardShop; 
	 DefaultTableModel modelCardPrivate; 
	 
	float sumAll;
	float sumCashPrivate;
	float  sumCashShop;
	float  sumCardShop;
	float sumCardPrivate;
	float  sumCash;
	float sumCard;
	 
	
	 
	 
	 private DatePicker datepick;
	 // private DatePicker datepickTo;
	 private DatePickerSettings datepicksetting;
	
	 public StatisticsPanel() {
	    	try{       
	        initialize();
	        addComponent();
	        addListener();
	    	}catch(Exception e){
	    		 AppMainWindow.logger.error(e.toString());
	    		e.printStackTrace();
	    	}
	    }
	 private void initialize(){
		 this.setLayout(new BorderLayout());
		 
		 labelStatitsticsHeader = new JLabel("请输入需要统计的月份:",JLabel.CENTER);
		 labelStatitsticsYear = new JLabel("年:",JLabel.CENTER);
		 labelStatitsticsMonth = new JLabel("月:",JLabel.CENTER);
		 labelStatitsticsCash = new JLabel("现金支付方式合计:",JLabel.CENTER);
		 labelStatitsticsCashSummary = new JLabel("?月现金合计:",JLabel.CENTER);   // %XX月现金合计
		 
		 
		 labelStatitsticsCashShopSummary = new JLabel("店内用品现金付款合计:",JLabel.CENTER);
		 labelStatitsticsCashPrivateSummary = new JLabel("私人用品现金付款合计:",JLabel.CENTER);
		 labelStatitsticsCard = new JLabel("信用卡支付方式合计:",JLabel.CENTER);
		 labelStatitsticsCardSummary = new JLabel("?月信用卡合计:",JLabel.CENTER);   // %XX月信用卡合计
		 labelStatitsticsCardSummary.setFont(new Font("微软雅黑", Font.BOLD,19));
		 
		 labelStatitsticsCardShopSummary = new JLabel("店内用品信用卡付款合计:",JLabel.CENTER);
		 labelStatitsticsCardPrivateSummary  = new JLabel("私人用品信用卡付款合计:",JLabel.CENTER);
		 labelStatitsticsAllSummary  = new JLabel("?月合计付款:     元",JLabel.CENTER);   // %XX月总合计
		 labelStatitsticsAllSummary.setFont(new Font("微软雅黑", Font.BOLD,22));
		 labelStatitsticsAllSummary.setForeground(new Color(0,118,53));
		 
		 
		 blankPanel = new JLabel("");
		 
		 
		 
		 datepicksetting = new DatePickerSettings();
    	 datepicksetting.setAllowEmptyDates(false);    	 
    	 datepicksetting.setFontValidDate(new Font("宋体", Font.PLAIN,18));
    	 
    	 datepick = new DatePicker(datepicksetting);
    	 DateSplit dateSplit = new DateSplit(datepick);
    	    date = dateSplit.Splitt();
			yearA = date[0];
			monthA = date[1];
			
		textStatitsticsYear = new JTextField(yearA,20);
		textStatitsticsYear.setFont(new Font("宋体", Font.ITALIC,18));
		textStatitsticsYear.setForeground(new Color(171,173,179));
		
		textStatitsticsMonth = new JTextField(monthA,20);
		textStatitsticsMonth.setFont(new Font("宋体", Font.ITALIC,18));
		textStatitsticsMonth.setForeground(new Color(171,173,179));
		
		dataColumn = new Vector <String>();
		dataColumn.add("账目编号"); 			//1
		dataColumn.add("物品名称");			//7
		dataColumn.add("物品数量");				//8
		dataColumn.add("物品价格 (元)");				//9
		dataColumn.add("备注");			//10
		 
		modelCashShop = new DefaultTableModel(dataCashShopRow, dataColumn);
		modelCashPrivate = new DefaultTableModel(dataCashPrivateRow, dataColumn);
		modelCardShop = new DefaultTableModel(dataCardShopRow, dataColumn);
		modelCardPrivate = new DefaultTableModel(dataCardPrivateRow, dataColumn);
		
		tableCashShop = new JTable(modelCashShop);
		tableCashShop.setFont(new Font("宋体", Font.PLAIN,21));
		tableCashShop.setRowHeight(23);
		tableCashShop.getTableHeader().setFont(new Font("宋体", Font.BOLD,18));
		
		scrollCashShopTable =new JScrollPane();
		scrollCashShopTable.setViewportView(tableCashShop);
		
		tableCashPrivate = new JTable(modelCashPrivate);
		tableCashPrivate.setFont(new Font("宋体", Font.PLAIN,21));
		tableCashPrivate.setRowHeight(23);
		tableCashPrivate.getTableHeader().setFont(new Font("宋体", Font.BOLD,18));
		
		scrollCashPrivateTable =new JScrollPane();
		scrollCashPrivateTable.setViewportView(tableCashPrivate);
		
		tableCardShop = new JTable(modelCardShop);
		tableCardShop.setFont(new Font("宋体", Font.PLAIN,21));
		tableCardShop.setRowHeight(23);
		tableCardShop.getTableHeader().setFont(new Font("宋体", Font.BOLD,18));
		
		scrollCardShopTable =new JScrollPane();
		scrollCardShopTable.setViewportView(tableCardShop);
		
		tableCardPrivate = new JTable(modelCardPrivate);
		tableCardPrivate.setFont(new Font("宋体", Font.PLAIN,21));
		tableCardPrivate.setRowHeight(23);
		tableCardPrivate.getTableHeader().setFont(new Font("宋体", Font.BOLD,18));
		
		scrollCardPrivateTable =new JScrollPane();
		scrollCardPrivateTable.setViewportView(tableCardPrivate);
		
		buttonsearchStatitstics = new JButton("查询");
			
		 
		 
		 
	 }
	 
	 private void addComponent(){
    	 this.add(getUpPanel(),BorderLayout.NORTH);
    	 this.add(getCenterPanel(),BorderLayout.CENTER);
         this.add(getBottomPanel(),BorderLayout.SOUTH);
    }
	 
	 private JPanel getUpPanel() {
		 JPanel searchPanel = new JPanel();
    	 searchPanel.setLayout(new GridLayout(1,1));
    	 border = new EtchedBorder(EtchedBorder.LOWERED);
    	 searchPanel.setBorder(border);    
    	 
    	 JPanel statisticsQuery = new 	MakePanel(labelStatitsticsHeader,textStatitsticsYear);
    	 statisticsQuery.add(labelStatitsticsYear);
    	 statisticsQuery.add(textStatitsticsMonth);
    	 statisticsQuery.add(labelStatitsticsMonth);
    	 statisticsQuery.add(buttonsearchStatitstics );
    	 
    	 searchPanel.add(statisticsQuery);  
		return searchPanel;
		 
	 }
	 
	 private JPanel getCenterPanel() {
		 JPanel statisticsPanel = new JPanel();
		 statisticsPanel.setLayout(new GridLayout(2,1));
		 
		 JPanel cashStatisticsPanel = new MakeStatisticsPanel(labelStatitsticsCashShopSummary,labelStatitsticsCashPrivateSummary,
				 labelStatitsticsCashSummary,scrollCashShopTable,scrollCashPrivateTable);
		 
		 JPanel cardStatisticsPanel = new MakeStatisticsPanel(labelStatitsticsCardShopSummary,labelStatitsticsCardPrivateSummary,
				 labelStatitsticsCardSummary,scrollCardShopTable,scrollCardPrivateTable);
		 
		 statisticsPanel.add(cashStatisticsPanel);
		 statisticsPanel.add(cardStatisticsPanel);
		 
		 
		return  statisticsPanel;
		 
	 }
	 
	 private JPanel getBottomPanel() {
		 JPanel summaryPanel= new JPanel();
		 summaryPanel.setLayout(new FlowLayout(0,400,5));
		 
		 summaryPanel.add(blankPanel);
		 
		 JPanel accountPanel= new JPanel();
//		 accountPanel.setBorder(border);
		 accountPanel.add(labelStatitsticsAllSummary);
		 
		 summaryPanel.add(accountPanel);
		 
		 
		return summaryPanel;
		 
	 }
	 
	 private void addListener() {
		
		 textStatitsticsYear.addFocusListener(new FocusListener(){

				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stub
					textStatitsticsYear.setText(yearA);
					textStatitsticsYear.setFont(new Font("宋体", Font.PLAIN,18));
					textStatitsticsYear.setForeground(Color.BLACK);
			        
				}

				@Override
				public void focusLost(FocusEvent arg0) {
					// TODO Auto-generated method stub
					
				}			
	    		
	    		});   
		 
		 textStatitsticsMonth.addFocusListener(new FocusListener(){

				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stub
					textStatitsticsMonth.setText(monthA);
					textStatitsticsMonth.setFont(new Font("宋体", Font.PLAIN,18));
					textStatitsticsMonth.setForeground(Color.BLACK);
			        
				}

				@Override
				public void focusLost(FocusEvent arg0) {
					// TODO Auto-generated method stub
					
				}			
	    		
	    		});   
		 
		 buttonsearchStatitstics.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				// TODO Auto-generated method stub
				if(textStatitsticsYear.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null,"请填入要统计账目的年份。","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
				}else if(textStatitsticsMonth.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null,"请填写要统计账目的月份。","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
				}else{
					modelCashShop.setRowCount(0); 
					modelCashPrivate.setRowCount(0);
					modelCardShop.setRowCount(0); 
					modelCardPrivate.setRowCount(0); 
					sumCashShop = 0;
					sumCashPrivate = 0;
					sumCash = 0;
					sumCardShop = 0;
					sumCardPrivate = 0;
					sumCard = 0;
					sumAll = 0;
					
					try{
						
					String strCashShop = "SELECT * FROM accounts WHERE year  = \""+ textStatitsticsYear.getText().trim() 
								 +"\" AND  month  =  \""+ textStatitsticsMonth.getText().trim()
								 +"\" AND  ISPRIVATE = '店内'  AND PAYMETHOD = '现金' ";
						rs= db.getResult(strCashShop);
						
						Caculate caculateCashShop = new Caculate();
						
						dataCashShopRow = caculateCashShop.getStatisticsResult(rs);
						sumCashShop = caculateCashShop.getSumResult();					
						if(!dataCashShopRow.isEmpty()){
							modelCashShop.setDataVector(dataCashShopRow,dataColumn);
							QueryPanel.FitTableColumns(tableCashShop);
							//dataTable.setModel(model);
							}else{
						    	JOptionPane.showMessageDialog(null,"没有搜索{店内现金}购买的相关记录。","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
						    }
						labelStatitsticsCashShopSummary.setText("店内用品现金付款合计:"+ sumCashShop + "元" );
						String strCashPrivate = "SELECT * FROM accounts WHERE year  = \""+ textStatitsticsYear.getText().trim() 
								 +"\" AND  month  =  \""+ textStatitsticsMonth.getText().trim()
								 +"\" AND  ISPRIVATE = '私人'  AND PAYMETHOD = '现金'  ";
						
						rs= db.getResult(strCashPrivate);
						Caculate caculateCashPrivate = new Caculate();
						dataCashPrivateRow = caculateCashPrivate .getStatisticsResult(rs);
						sumCashPrivate = caculateCashPrivate.getSumResult();							
						if(!dataCashPrivateRow.isEmpty()){
							modelCashPrivate.setDataVector(dataCashPrivateRow,dataColumn);
							QueryPanel.FitTableColumns(tableCashPrivate );
							}else{
						    	JOptionPane.showMessageDialog(null,"没有搜索到{现金私人}购买的相关记录。","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
						    }
						labelStatitsticsCashPrivateSummary.setText("私人用品现金付款合计:"+ sumCashPrivate + "元" );
						sumCash = sumCashShop + sumCashPrivate;
						labelStatitsticsCashSummary.setText(textStatitsticsYear.getText().trim() + "年"  + 
								textStatitsticsMonth.getText().trim() + "月现金付款合计:"+ sumCash + "元" );
						labelStatitsticsCashSummary.setFont(new Font("微软雅黑", Font.BOLD,19));
						//dataTable.setModel(model);

						String strCardShop = "SELECT * FROM accounts WHERE year  = \""+ textStatitsticsYear.getText().trim() 
								 +"\" AND  month  =  \""+ textStatitsticsMonth.getText().trim()
								 +"\" AND  ISPRIVATE = '店内'  AND PAYMETHOD = '信用卡' ";
						rs= db.getResult(strCardShop);
						
						Caculate caculateCardShop = new Caculate();
						
						dataCardShopRow =  caculateCardShop.getStatisticsResult(rs);
						sumCardShop =  caculateCardShop.getSumResult();					
						if(!dataCardShopRow.isEmpty()){
							modelCardShop.setDataVector(dataCardShopRow,dataColumn);
							QueryPanel.FitTableColumns(tableCardShop);
							//dataTable.setModel(model);
							}else{
						    	JOptionPane.showMessageDialog(null,"没有搜索到{信用卡店内}购买的相关记录。","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
						    }
						labelStatitsticsCardShopSummary.setText("店内用品信用卡付款合计:"+  sumCardShop + "元" );
						
						String strCardPrivate = "SELECT * FROM accounts WHERE year  = \""+ textStatitsticsYear.getText().trim() 
								 +"\" AND  month  =  \""+ textStatitsticsMonth.getText().trim()
								 +"\" AND  ISPRIVATE = '私人'  AND PAYMETHOD = '信用卡'  ";
						
						rs= db.getResult(strCardPrivate);
						Caculate caculateCardPrivate = new Caculate();
						dataCardPrivateRow = caculateCardPrivate .getStatisticsResult(rs);
						sumCardPrivate = caculateCardPrivate.getSumResult();							
						if(!dataCardPrivateRow.isEmpty()){
							modelCardPrivate.setDataVector(dataCardPrivateRow,dataColumn);
							QueryPanel.FitTableColumns(tableCardPrivate);
							//dataTable.setModel(model);
							}else{
						    	JOptionPane.showMessageDialog(null,"没有搜索到{信用卡私人}购买的相关记录。","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
						    }
						labelStatitsticsCardPrivateSummary.setText("私人用品信用卡付款合计:"+ sumCardPrivate + "元" );
						
						sumCard = sumCardShop + sumCardPrivate;
						
						labelStatitsticsCardSummary.setText(textStatitsticsYear.getText().trim() + "年"  + 
						textStatitsticsMonth.getText().trim() + "月信用卡付款合计:"+ sumCard + "元" );
						
						labelStatitsticsCardSummary.setFont(new Font("微软雅黑", Font.BOLD,19));
					
						sumAll = sumCash + sumCard;
						labelStatitsticsAllSummary.setText(textStatitsticsYear.getText().trim() + "年"  + 
						textStatitsticsMonth.getText().trim() + "月总共付款合计:"+ sumAll + "元");
						
					}catch(Exception sqle){
						 AppMainWindow.logger.error(sqle.toString());
						sqle.printStackTrace();
					}
				}
			}
	 });
	 
	 }
	 
	 
}
