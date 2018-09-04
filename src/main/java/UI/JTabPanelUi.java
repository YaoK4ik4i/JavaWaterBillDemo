package UI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Arrays;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.text.View;

/**
 *  关键点： 
 *  1. 继承BasicTabbedPaneUI类，这其中有我们必须要重写的一个方法： 
 *  public static ComponentUI createUI(JComponent c) { 
 *  return new XXXTabbedPaneUI(); 
 *  } 
 *  其中XXXTabbedPaneUI就是自己实现的BasicTabbedPaneUI的子类的名字。
 *
 *  2．下面类出几个改变外观的重要的方法： 
 *  a. protectedvoid installDefaults() //可以改变一些BasicTabbedPaneUI中默认的属性。 
 *  b.protectedvoid paintTabArea(Graphics g, int tabPlacement, int selectedIndex) //绘制整个选项卡区域 
 *  c. protectedvoid paintTabBackground(Graphics g, int tabPlacement, 
 *  int tabIndex, int x, int y, int w, int h, boolean isSelected)   //绘制某个选项卡的背景色 
 *  d. protectedvoid paintContentBorder(Graphics g, int tabPlacement, 
 *  int selectedIndex) //绘制TabbedPane容器的四周边框样式。 
 *  e. protectedvoid paintFocusIndicator(Graphics g, int tabPlacement, 
 *  Rectangle[] rects, int tabIndex, Rectangle iconRect, 
 *  Rectangle textRect, boolean isSelected) //绘制选中某个Tab后，获得焦点的样式。
 * 
 *
 */
public class JTabPanelUi extends BasicTabbedPaneUI {

    private Color selectColor;
    private Color deSelectColor;
    private int inclTab = 3;
    private int anchoFocoV = inclTab;
    private int anchoFocoH = 1;
    private int anchoCarpetas = 18;
    private Polygon shape;
    private Color bgColor;
    private Color frontColor;

    private ColorSet selectedColorSet;
    private ColorSet defaultColorSet;
    private ColorSet hoverColorSet;


    public  JTabPanelUi(String bgColorHex, String frontColorHex){
        setColor(bgColorHex,frontColorHex);

        selectedColorSet = new ColorSet();
        selectedColorSet.topGradColor1 = Color.decode("#d1d1d1");//选中的最上层
        selectedColorSet.topGradColor2 = Color.decode("#dddddd");//选中的第二层
        selectedColorSet.bottomGradColor1 = Color.decode("#dedede");//选中的第三层
        selectedColorSet.bottomGradColor2 = Color.decode("#e5e5e5");//选中的最下层
        defaultColorSet = new ColorSet();
        defaultColorSet.topGradColor1 = Color.decode("#f0f0f0");//未选的最上层
        defaultColorSet.topGradColor2 = Color.decode("#f0f0f0");
        defaultColorSet.bottomGradColor1 = Color.decode("#f0f0f0");
        defaultColorSet.bottomGradColor2 = Color.decode("#f0f0f0");
        hoverColorSet = new ColorSet();
        hoverColorSet.topGradColor1 = Color.decode("#f5f5f5");//鼠标悬停最上层
        hoverColorSet.topGradColor2 = Color.decode("#e5e5e5");
        hoverColorSet.bottomGradColor1 = Color.decode("#eaeaea");
        hoverColorSet.bottomGradColor2 = Color.decode("#f0f0f0");

    }
//  
//  public static ComponentUI createUI(JComponent c) {
//      return new TabbedPaneUI();
//  }

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

    public void setColor(String bgColorHex, String frontColorHex){
        bgColor = Color.decode(bgColorHex);
        frontColor = Color.decode(frontColorHex);
    }

    @Override
    protected void paintContentBorderTopEdge(Graphics g, int tabPlacement,
            int selectedIndex, int x, int y, int w, int h) {
        super.paintContentBorderTopEdge(g, tabPlacement, -1, x, y, w, h);
    }
    
//    @Override
//    protected void paintContentBorderBottomEdge(Graphics g, int tabPlacement,
//            int selectedIndex, int x, int y, int w, int h) {
//    	Graphics2D g2D = (Graphics2D) g.create();
//    	g2D.drawLine(x, y, x+w , y);
//    	g2D.setColor(Color.decode("#b8b8b8"));
//    }

    @Override
    protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
//        if (runCount > 1) {
//            int lines[] = new int[runCount];
//            for (int i = 0; i < runCount; i++) {
//                lines[i] = rects[tabRuns[i]].y
//                        + (tabPlacement == TOP ? maxTabHeight : 0);
//            }
//            Arrays.sort(lines);
//            if (tabPlacement == TOP) {
//                int fila = runCount;
//                for (int i = 0; i < lines.length - 1; i++, fila--) {
//                    Polygon carp = new Polygon();
//                    carp.addPoint(0, lines[i]);
//                    carp.addPoint(tabPane.getWidth() - 2 * fila - 2, lines[i]);
//                    carp.addPoint(tabPane.getWidth() - 2 * fila, lines[i] + 3);
//                    if (i < lines.length - 2) {
//                        carp.addPoint(tabPane.getWidth() - 2 * fila,
//                                lines[i + 1]);
//                        carp.addPoint(0, lines[i + 1]);
//                    } else {
//                        carp.addPoint(tabPane.getWidth() - 2 * fila, lines[i]
//                                + rects[selectedIndex].height);
//                        carp.addPoint(0, lines[i] + rects[selectedIndex].height);
//                    }
//                    carp.addPoint(0, lines[i]);
//                    g.setColor(hazAlfa(fila));
//                    g.fillPolygon(carp);
//                    g.setColor(darkShadow.darker());
//                    g.drawPolygon(carp);
//                }
//            } else {
//                int fila = 0;
//                for (int i = 0; i < lines.length - 1; i++, fila++) {
//                    Polygon carp = new Polygon();
//                    carp.addPoint(0, lines[i]);
//                    carp.addPoint(tabPane.getWidth() - 2 * fila - 1, lines[i]);
//                    carp.addPoint(tabPane.getWidth() - 2 * fila - 1,
//                            lines[i + 1] - 3);
//                    carp.addPoint(tabPane.getWidth() - 2 * fila - 3,
//                            lines[i + 1]);
//                    carp.addPoint(0, lines[i + 1]);
//                    carp.addPoint(0, lines[i]);
//                    g.setColor(hazAlfa(fila + 2));
//                    g.fillPolygon(carp);
//                    g.setColor(darkShadow.darker());
//                    g.drawPolygon(carp);
//                }
//            }
        
      super.paintTabArea(g, tabPlacement, selectedIndex);
    }

    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement,
            int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        Graphics2D g2D = (Graphics2D) g;
        ColorSet colorSet;

        Rectangle rect = rects[tabIndex];
        if (isSelected) {
            colorSet = selectedColorSet;
        } else if (getRolloverTab() == tabIndex) {
            colorSet = hoverColorSet;
        } else {
            colorSet = defaultColorSet;
        }
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        int width = rect.width;
        int xpos = rect.x;
        int yPos = rect.y;
        if (tabIndex > -1) {
            width--;
            xpos++;
            yPos += 2;
        }

        g2D.setPaint(new GradientPaint(xpos, 0, colorSet.topGradColor1, xpos,
                h / 2, colorSet.topGradColor2));
        g2D.fill(this.getUpArea(xpos, yPos, width, h - 2));

        g2D.setPaint(new GradientPaint(0, h / 2, colorSet.bottomGradColor1, 0,
                h, colorSet.bottomGradColor2));
        g2D.fill(this.getDownArea(xpos, yPos, width, h - 2));

    }

    private Shape getArea(int x, int y, int w, int h) {
        RoundRectangle2D rect = new RoundRectangle2D.Float(x, y, w, h, 15, 15);
        Area a = new Area(rect);
        Rectangle2D rec = new Rectangle2D.Float(x, y + h / 2, w, h / 2);
        Area b = new Area(rec);
        a.add(b);
        return a;
    }

    private Shape getUpArea(int x, int y, int w, int h) {
        Rectangle2D rec = new Rectangle2D.Float(x, y, w, h / 3 );
        Area a = new Area(rec);
        RoundRectangle2D rect = new RoundRectangle2D.Float(x, y, w, h, 15, 15);
        Area b = new Area(rect);
        a.intersect(b);
        return a;
    }

    private Shape getDownArea(int x, int y, int w, int h) {
    	 Rectangle2D rec = new Rectangle2D.Float(x, y + h / 3, w, h*2 / 3 +1);
         Area a = new Area(rec);
         RoundRectangle2D rect = new RoundRectangle2D.Float(x, y, w, h, 15, 15);
         Area b = new Area(rect);
         a.intersect(b);
         return a;
    }
    private class ColorSet {
        Color topGradColor1;
        Color topGradColor2;
        Color bottomGradColor1;
        Color bottomGradColor2;
    }
   
   

    @Override
    protected void paintText(Graphics g, int tabPlacement, Font font,
            FontMetrics metrics, int tabIndex, String title,
            Rectangle textRect, boolean isSelected) {
        super.paintText(g, tabPlacement, font, metrics, tabIndex, title,
                textRect, isSelected);
        g.setFont(font);
        View v = getTextViewForTab(tabIndex);
        if (v != null) {
            // html
            v.paint(g, textRect);
        } else {
            // plain text
            int mnemIndex = tabPane.getDisplayedMnemonicIndexAt(tabIndex);
            if (tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex)) {
                g.setColor(tabPane.getForegroundAt(tabIndex));
                BasicGraphicsUtils
                        .drawStringUnderlineCharAt(g, title, mnemIndex,
                                textRect.x, textRect.y + metrics.getAscent());
            } else { // tab disabled
                g.setColor(Color.BLACK);
                BasicGraphicsUtils
                        .drawStringUnderlineCharAt(g, title, mnemIndex,
                                textRect.x, textRect.y + metrics.getAscent());
                g.setColor(tabPane.getBackgroundAt(tabIndex).darker());
                BasicGraphicsUtils.drawStringUnderlineCharAt(g, title,
                        mnemIndex, textRect.x - 1,
                        textRect.y + metrics.getAscent() - 1);
            }
        }
    }

    //固定tab标签宽度
    @Override
    protected int calculateTabWidth(int tabPlacement, int tabIndex,
            FontMetrics metrics) {
        return 200;
//      return 10 + anchoFocoV
//              + super.calculateTabWidth(tabPlacement, tabIndex, metrics);
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
        return 68;
    }

    protected int calculateMaxTabHeight(int tabPlacement) {
        return 70;
    }
    //set tab title hight
    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex,
            int x, int y, int w, int h, boolean isSelected) {
      if (isSelected) {
            g.setColor(Color.decode("#505050"));
//            int[] xp = new int[] { x, x, x + 3, x + w - inclTab - 6,
//                    x + w - inclTab - 3, x + w - inclTab, x + w - inclTab, x };
//            int[] yp = new int[] { y + h, y + 3, y, y, y, y + 3, y + h, y + h };            
//          //drawPolygon(int xpoints[],int yPoints[],int nPoints)：画一个多边形,
//           //其中数组xPoints[]存储x坐标点，yPoints[]存储y坐标点，nPoints是坐标点个数。
//            shape = new Polygon(xp, yp, xp.length);   
            Graphics2D g2D = (Graphics2D) g.create();
 //           g2D.drawPolygon(shape);
            g2D.drawRoundRect(x, y, w, h, 11, 11);
            g.setColor(Color.decode("#505050"));
            //g.drawLine(x, y + h, x + w - inclTab, y + h);
     }else if (getRolloverTab() == tabIndex) {
    	 g.setColor(Color.decode("#a2a2a2"));
//         int[] xp = new int[] { x, x, x + 3, x + w - inclTab - 6,
//                 x + w - inclTab - 3, x + w - inclTab, x + w - inclTab, x };
//         int[] yp = new int[] { y + h, y + 3, y, y, y, y + 3, y + h, y + h };
//         shape = new Polygon(xp, yp, xp.length);
         Graphics2D g2D = (Graphics2D) g.create();
//         g2D.drawPolygon(shape);
    	 g2D.drawRoundRect(x, y, w, h, 11, 11);
         g.setColor(Color.decode("#a2a2a2"));
//         g.drawLine(x, y + h, x + w - inclTab, y + h);
     }
    }

    protected int getTabLabelShiftY(int tabPlacement, int tabIndex,
            boolean isSelected) {
        return 0;
    }

    protected int getTabLabelShiftX(int tabPlacement, int tabIndex,
            boolean isSelected) {
        return 0;
    }

    @Override
    protected void paintFocusIndicator(Graphics g, int tabPlacement,
            Rectangle[] rects, int tabIndex, Rectangle iconRect,
            Rectangle textRect, boolean isSelected) {
        if (tabPane.hasFocus() || isSelected) {
            // g.setColor(UIManager.getColor("ScrollBar.thumbShadow"));
            // g.drawPolygon(shape);
        }
    }

    protected Color hazAlfa(int fila) {
        int alfa = 0;
        if (fila >= 0) {
            alfa = 50 + (fila > 7 ? 70 : 10 * fila);
        }
        return new Color(0, 0, 0, alfa);
    }

    private int lastRollOverTab = -1;

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
        public void mouseDragged(MouseEvent arg0) {
            // TODO Auto-generated method stub
            
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
    }
}