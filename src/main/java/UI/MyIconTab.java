package UI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.text.View;

//import UI.JTabPanelUi.RollOverListener;

	/**
	 * 
	 * @author Yao
	 *
	 */
public class MyIconTab extends BasicTabbedPaneUI {

    private Color selectColor;
    private Color deSelectColor;
    private int inclTab = 3;
    private int anchoFocoV = inclTab;
    private int anchoFocoH = 1;
    private int anchoCarpetas = 18;
    private Polygon shape;
    private Color bgColor;
    private Color frontColor;

  


    public  MyIconTab(String bgColorHex, String frontColorHex){
       

      

    }
//  
//  public static ComponentUI createUI(JComponent c) {
//      return new TabbedPaneUI();
//  }
    //固定tab标签宽度
    
    @Override
    protected void installDefaults() {
        super.installDefaults();
        RollOverListener l = new RollOverListener();
        tabPane.addMouseListener(l);
        tabPane.addMouseMotionListener(l);
        selectColor = new Color(184,184, 184);
        deSelectColor = new Color(240, 240, 240);
        tabAreaInsets.right = anchoCarpetas;
    }
    @Override
    protected int calculateTabWidth(int tabPlacement, int tabIndex,
            FontMetrics metrics) {
        return 120;
    }
    
    @Override
    protected int calculateTabHeight(int tabPlacement, int tabIndex,
            int fontHeight) {
//        if (tabPlacement == LEFT || tabPlacement == RIGHT) {
//            return super.calculateTabHeight(tabPlacement, tabIndex, fontHeight);
//        } else {
//            return anchoFocoH
//                    + super.calculateTabHeight(tabPlacement, tabIndex,
//                            fontHeight);
//        }
        return 34;
    }
    
    private int lastRollOverTab = -1;
    protected int calculateMaxTabHeight(int tabPlacement) {
        return 38;
    }
    
    private class RollOverListener implements MouseMotionListener,
    MouseListener {
    	public void mouseMoved(MouseEvent e) {
    			checkRollOver();
    		}

    	public void mouseEntered(MouseEvent e) {
    		checkRollOver();
    	}

    	public void mouseExited(MouseEvent e) {
    		tabPane.repaint();
    	}

    	private void checkRollOver() {
    		int currentRollOver = getRolloverTab();
    		if (currentRollOver != lastRollOverTab) {
    			lastRollOverTab = currentRollOver;
    			Rectangle tabsRect = new Rectangle(0, 0, tabPane.getWidth(), 
                tabPane.getHeight()+1);
    			tabPane.repaint(tabsRect);
    		}
}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

    }
}

    