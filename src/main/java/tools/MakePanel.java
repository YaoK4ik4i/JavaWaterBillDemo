package tools;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MakePanel extends JPanel {
	
	JLabel label;
	JComponent field;
	public MakePanel(JLabel label, JComponent field){
		super(true);		
		this.label = label;
		this.field = field;
		initialize();
		setPanel();
		
	}
	
	private void initialize() {
		this.setLayout(new FlowLayout(0,25,5));
	}
	
	public void setPanel(){
		label.setFont(new Font("微软雅黑", Font.PLAIN,18));
		field.setPreferredSize(new Dimension (400,30));
		this.add(label);
		this.add(field);
		
		
	}
}
