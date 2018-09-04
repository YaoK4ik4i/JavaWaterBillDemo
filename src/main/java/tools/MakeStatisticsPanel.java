package tools;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class MakeStatisticsPanel  extends JPanel{
	JLabel labelUp;
	JLabel labelUpB;
	JLabel labelBottom;
	JLabel blankPanel;
	JComponent fieldLeft;
	JComponent fieldRight;
private Border border;
	
	public MakeStatisticsPanel(JLabel labelA, JLabel labelB, JLabel labelC, JComponent fieldA, JComponent fieldB){
		super(true);		
		this.labelUp = labelA;
		this.labelUpB = labelB;
		this.labelBottom = labelC;
		blankPanel = new JLabel("");
		this.fieldLeft = fieldA;
		this.fieldRight = fieldB;
		border = new EtchedBorder(EtchedBorder.LOWERED);
		initialize();
		setPanel();		
	}
	private void initialize() {
		this.setLayout(new BorderLayout());
		this.setBorder(border);    
		
	}
	
	public void setPanel(){
		labelUp.setFont(new Font("微软雅黑", Font.PLAIN,18));
		labelBottom.setFont(new Font("微软雅黑", Font.PLAIN,18));
		labelUpB.setFont(new Font("微软雅黑", Font.PLAIN,18));
		JPanel tableUpPanel = new JPanel();
		tableUpPanel.setLayout(new GridLayout(1,2));		
		tableUpPanel.add(labelUp);
		tableUpPanel.add(labelUpB);
		
		JPanel tableContentPanel = new JPanel();
		tableContentPanel.setLayout(new GridLayout(1,2));		
		tableContentPanel.add(fieldLeft);
		tableContentPanel.add(fieldRight);
		
		JPanel tableBottomPanel = new JPanel();
		tableBottomPanel.setLayout(new GridLayout(1,2));
		tableBottomPanel.add(blankPanel);
		tableBottomPanel.add(labelBottom);
		
		this.add(tableUpPanel,BorderLayout.NORTH);
		this.add( tableContentPanel,BorderLayout.CENTER);
		this.add( tableBottomPanel,BorderLayout.SOUTH);	
		
	}

}
