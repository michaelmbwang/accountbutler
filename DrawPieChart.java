package Part3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.*;
import javax.swing.JComponent;

class Slice {
   double value;
   Color color;
   public Slice(double value, Color color) {  
      this.value = value;
      this.color = color;
   }
}

class DrawPieChart extends JComponent {
	
	private static final long serialVersionUID = 1L;
   private float[] amounts;
   private String[] names;
   private String title;
   private ArrayList<Slice> slices;
   private HashMap<String, Color> pieChartMap;
   
   public DrawPieChart(float[] amounts, String[] categories) {
	   this.amounts = amounts;
	   this.names = categories;
	   title = "Expenses by category";
	   for(int i=0;i<amounts.length;i++ ){
		   final Random r=new Random();
		   Color c = new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256),r.nextInt(256));
		   slices.add(new Slice(amounts[i], c));  
		   pieChartMap.put(names[i], c);
	   }
   }
   
 
   public void paint(Graphics g) {
      drawPie((Graphics2D) g, getBounds(), slices);
      float[] values = {0, 1, 2};
      super.paintComponent(g);
      if (values == null || values.length == 0)
        return;
      double minValue = 0;
      double maxValue = 0;
      for (int i = 0; i < values.length; i++) {
        if (minValue > values[i])
          minValue = values[i];
        if (maxValue < values[i])
          maxValue = values[i];
      }

      Dimension d = getSize();
//      int clientWidth = d.width;
      int clientHeight = d.height;
      Font titleFont = new Font("SansSerif", Font.BOLD, 20);
      FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
      Font labelFont = new Font("SansSerif", Font.PLAIN, 10);
      FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);
//      int titleWidth = titleFontMetrics.stringWidth("title");
      int y = titleFontMetrics.getAscent();
//      int x = (clientWidth - titleWidth) / 2;
      g.setFont(titleFont);
      //add title
      g.drawString(title, getBounds().width/2, y);
      //add description
      for(int i=0;i<names.length;i++){
    	  g.setColor(pieChartMap.get(names[i]));
    	  g.drawString(names[i]+": "+amounts[i], getBounds().width/2, (i+1)*y);
      }
//      int top = titleFontMetrics.getHeight();
//      int bottom = labelFontMetrics.getHeight();
      if (maxValue == minValue)
        return;
//      double scale = (clientHeight - top - bottom) / (maxValue - minValue);
      y = clientHeight - labelFontMetrics.getDescent();
      g.setFont(labelFont);
   }
   
   void drawPie(Graphics2D g, Rectangle area, ArrayList<Slice> slices) {
      double total = 0.0D;
      for(Slice s: slices){
    	  total += s.value;
      }
      double curValue = 0.0D;
      int startAngle = 0;
      for(Slice s:slices){
    	  startAngle = (int) (curValue * 360 / total);
          int arcAngle = (int) (s.value * 360 / total);
          g.setColor(s.color);
          g.fillArc(area.x, area.y, area.width/2, area.height, 
          startAngle, arcAngle);
          curValue += s.value;
      }
   }
}