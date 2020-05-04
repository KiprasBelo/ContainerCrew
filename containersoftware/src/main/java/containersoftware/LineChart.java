package containersoftware;

import org.jfree.chart.ChartPanel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

//Screen that displays temperature graph data

public class LineChart extends ApplicationFrame {
	
	private ClientLog log = new ClientLog();

   public LineChart( String applicationTitle , String chartTitle, Container c) {
      super(applicationTitle);
      JFreeChart lineChart = ChartFactory.createLineChart(chartTitle,"Hours","Temperature (C)",createDataset(c),PlotOrientation.VERTICAL,true,true,false);
      
      //Finding screen size to center JFrame
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int screenHeight = screenSize.height;
      int screenWidth = screenSize.width;
      
      ChartPanel chartPanel = new ChartPanel( lineChart );
      //chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 370 ) );
      chartPanel.setBounds(screenWidth/2 - 560/2, screenHeight/2 - 370/2, 560, 370);
      setContentPane( chartPanel );
   }

   private DefaultCategoryDataset createDataset(Container c) {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      String num = "";
      int i = 1;
      
      for(double x : c.getDataPoints()) {
    	  
    	  num = ""+i;
    	  dataset.addValue(c.getTemperature(), "Preferred Temperature", num);
    	  dataset.addValue(x, "Recorded Temperature", num);
    	  
    	  i++;
    	  
      }
      
      return dataset;
   }
}