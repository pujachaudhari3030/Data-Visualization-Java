package data_Visualization;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.XYSeries;						//scatter chart
import org.jfree.data.xy.XYSeriesCollection;

public class data_Visualization {

	private JFrame frame;
	private JTextField item_text;
	private JTextField amount_text;
	DefaultTableModel model;
	JTable table;
	JPanel chart_panel;
	JPanel table_panel = new JPanel();
	private DefaultPieDataset<String> pieDataset;
	private JFreeChart pieChart;
	private ChartPanel chartPanel= new ChartPanel(pieChart);
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					data_Visualization window = new data_Visualization();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public data_Visualization() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(250, 20, 1038, 759);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setVisible(true);
		displayTable();
		
		JLabel heading = new JLabel("Data Visualization");
		heading.setForeground(Color.pink);
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		heading.setFont(new Font("Arial Black", Font.BOLD, 23));
		heading.setBounds(0, 0, 1024, 70);
		frame.getContentPane().add(heading);
		
		JLabel itemlable = new JLabel("Item Name");
		itemlable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		itemlable.setForeground(Color.YELLOW);
		itemlable.setBounds(51, 100, 85, 31);
		frame.getContentPane().add(itemlable);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAmount.setForeground(Color.YELLOW);

		lblAmount.setBounds(51, 152, 85, 31);
		frame.getContentPane().add(lblAmount);
		
		item_text = new JTextField();
		item_text.setBounds(146, 108, 191, 19);
		frame.getContentPane().add(item_text);
		item_text.setColumns(10);
		
		amount_text = new JTextField();
		amount_text.setColumns(10);
		amount_text.setBounds(146, 160, 191, 19);
		frame.getContentPane().add(amount_text);
		
		JButton add_data = new JButton("Add Data");
		add_data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = item_text.getText();
				String amountData = amount_text.getText();
				Object[] data = {itemName,amountData};
				model.addRow(data);
				item_text.setText("");
				amount_text.setText("");
			}
		});
		add_data.setFont(new Font("Verdana", Font.BOLD, 12));
		add_data.setBounds(194, 231, 101, 36);
		frame.getContentPane().add(add_data);
		
		JButton reset = new JButton("RESET");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				    chart_panel.removeAll();
			        table_panel.removeAll();
			        displayTable();
			        frame.validate();
			}
		});
		reset.setFont(new Font("Verdana", Font.BOLD, 12));
		reset.setBounds(405, 231, 101, 36);
		frame.getContentPane().add(reset);
		
		
		table_panel.setBounds(10, 278, 470, 444);
		frame.getContentPane().add(table_panel);
		
		chart_panel = new JPanel();
		chart_panel.setBounds(518, 278, 496, 434);
		frame.getContentPane().add(chart_panel);
		
		JButton Piebtn = new JButton("PIE Chart");
		Piebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 chart_panel.removeAll();
		         showPie();
		         frame.validate();
			}
		});
		Piebtn.setFont(new Font("Verdana", Font.BOLD, 12));
		Piebtn.setBounds(558, 107, 112, 36);
		frame.getContentPane().add(Piebtn);
		
		JButton btnBarChart = new JButton("BAR Chart");
		btnBarChart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chart_panel.removeAll();
		        showBar();
		        frame.validate();
			}
		});
		btnBarChart.setFont(new Font("Verdana", Font.BOLD, 12));
		btnBarChart.setBounds(558, 159, 112, 36);
		frame.getContentPane().add(btnBarChart);
		
		JButton Plot = new JButton("Plot Graph");
		Plot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chart_panel.removeAll();
		        showPlot();
		        frame.validate();
			}
		});
		Plot.setFont(new Font("Verdana", Font.BOLD, 12));
		Plot.setBounds(558, 207, 112, 36);
		frame.getContentPane().add(Plot);	
		
		JButton setp_chart = new JButton("Line Chart");
		setp_chart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chart_panel.removeAll();
		        showStepChart();
		        frame.validate();
			}
		});
		setp_chart.setFont(new Font("Verdana", Font.BOLD, 12));
		setp_chart.setBounds(714, 107, 112, 36);
		frame.getContentPane().add(setp_chart);
		
		JButton histogram = new JButton("Histogram");
		histogram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chart_panel.removeAll();
		        showHistogramChart();
		        frame.validate();
			}
		});
		histogram.setFont(new Font("Verdana", Font.BOLD, 12));
		histogram.setBounds(714, 159, 112, 36);
		frame.getContentPane().add(histogram);
		
		JButton spider_chart_btn = new JButton("SpiderChart");
		spider_chart_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chart_panel.removeAll();
		        showSpiderChart();
		        frame.validate();
			}
		});
		spider_chart_btn.setFont(new Font("Verdana", Font.BOLD, 12));
		spider_chart_btn.setBounds(714, 207, 112, 36);
		frame.getContentPane().add(spider_chart_btn);
	}
	public void displayTable() {
		String[] a = {"ITEMS","AMOUNT"};
        model = new DefaultTableModel(null,a);
        table = new JTable(model);
        table.setForeground(Color.RED);
        table_panel.add(new JScrollPane(table));
		
	}
	public void showPie() {
		pieDataset = new DefaultPieDataset<String>();
        for(int i=0;i<table.getRowCount();i++){
        	String name = table.getValueAt(i,0).toString();
        	Double amt = Double.valueOf(table.getValueAt(i,1).toString());
        	pieDataset.setValue(name,amt);

    		charts();
        }
		
	}
	public void showBar() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(int i=0;i<table.getRowCount();i++){
        	String name = table.getValueAt(i,0).toString();
        	Double amt = Double.valueOf(table.getValueAt(i,1).toString());
        	dataset.addValue(amt, amt,name );
        }
		// ... dataset creation
        JFreeChart barChart = ChartFactory.createBarChart(
                "Bar Chart",
                "Item", "Amount", dataset,
                PlotOrientation.VERTICAL,
                true, 
                true, 
                true);
         //chartPanel.add(barChart);
        chartPanel = new ChartPanel(barChart);
        barChart.getPlot(); 
        chartPanel.setPreferredSize(new Dimension(500, 420));
        chart_panel.add(chartPanel);	
	}
	private void showPlot() {
		XYSeries series = new XYSeries("Data Series");
		int bin_no=1;
		
		// Add more data points as needed
		for(int i=0;i<table.getRowCount();i++){
        	//String name = table.getValueAt(i,0).toString();
        	Double amt = Double.valueOf(table.getValueAt(i,1).toString());
        	series.add(bin_no,amt );
        	bin_no+=1;
        }
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		
		JFreeChart Plotchart = ChartFactory.createScatterPlot(
				"Plot Chart",
                "Item(idex shown in table)", "Amount", dataset,
                PlotOrientation.VERTICAL,
                true, 
                true, 
                true);
         //chartPanel.add(barChart);
        chartPanel = new ChartPanel(Plotchart);
        Plotchart.getPlot(); 
        chartPanel.setPreferredSize(new Dimension(500, 420));
        chart_panel.add(chartPanel);	
	}
	private void showStepChart() {
		
		XYSeries areaSeries = new XYSeries("Area Series");
		XYSeriesCollection dataset = new XYSeriesCollection();
		int bin_no=1;
		// Add data to the datasets based on user inputs
		for(int i=0;i<table.getRowCount();i++){
        	
        	Double amt = Double.valueOf(table.getValueAt(i,1).toString());
        	
        	areaSeries.add(bin_no,amt);
        	bin_no+=1;
        }
		//dataset.addSeries(lineSeries);
    	dataset.addSeries(areaSeries);
		// Create a chart based on the datasets
		JFreeChart areaAndLineChart = ChartFactory.createXYAreaChart(
				"Area and Line Chart",
                "Item", "Amount", dataset,   // Y-Axis Label
		    PlotOrientation.VERTICAL,   // Orientation
		    true,   
		    true,   
		    true
		);		
		chartPanel = new ChartPanel(areaAndLineChart);
        areaAndLineChart.getPlot(); 
        chartPanel.setPreferredSize(new Dimension(500, 420));
        chart_panel.add(chartPanel);	
	}
	private void showHistogramChart() {
		
		HistogramDataset dataset = new HistogramDataset();
		for(int i=0;i<table.getRowCount();i++){
        
        	Double amt = Double.valueOf(table.getValueAt(i,1).toString());
        	double[] data = {1000}; // Add data points as an array
        	int bins = amt.toString().length(); // Specify the number of bins
        	
        	dataset.addSeries(amt, data, 10, bins, amt);
        
        	
        }
		// Create a chart based on the dataset
		String chartTitle = "Histogram Chart";
		String xAxisLabel = "Values";
		String yAxisLabel = "Frequency";
		PlotOrientation orientation = PlotOrientation.VERTICAL;
		boolean showLegend = true;
		boolean toolTips = true;
		boolean urls = true;
		JFreeChart histogram_panel = ChartFactory.createHistogram(
			    chartTitle, xAxisLabel, yAxisLabel, dataset, orientation, showLegend, toolTips, urls
			);
		chartPanel = new ChartPanel(histogram_panel);
        histogram_panel.getPlot(); 
        chartPanel.setPreferredSize(new Dimension(500, 420));
        chart_panel.add(chartPanel);
	}

	private void showSpiderChart() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(int i=0;i<table.getRowCount();i++){
        	String name = table.getValueAt(i,0).toString();
        	dataset.addValue(i, name,name );
        }
		SpiderWebPlot plot = new SpiderWebPlot(dataset);
        JFreeChart spider_chart = new JFreeChart("Spider Chart", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
        chartPanel = new ChartPanel(spider_chart);
        spider_chart.getPlot(); 
        chartPanel.setPreferredSize(new Dimension(500, 420));
        chart_panel.add(chartPanel);
		
	}
	private void charts() {
		pieChart = ChartFactory.createPieChart("PIE Chart",pieDataset,true,true,true);
	    pieChart.getPlot();
	    chartPanel = new ChartPanel(pieChart);
	    chartPanel.setPreferredSize(new Dimension(500, 420));
	    chart_panel.add(chartPanel);
		
	}
}
