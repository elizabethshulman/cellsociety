package visualComponents;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

public class LineGraph {
	
	private LineChart<Number, Number> myLineChart;
	
	public LineGraph() {
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		myLineChart = new LineChart<>(xAxis,yAxis);
		
		myLineChart.setLegendVisible(false);
		myLineChart.setHorizontalGridLinesVisible(false);
		myLineChart.setVerticalGridLinesVisible(false);
		myLineChart.setCreateSymbols(false);
		myLineChart.setHorizontalZeroLineVisible(false);
		myLineChart.setVerticalZeroLineVisible(false);
		
		yAxis.setTickLabelsVisible(false);
		yAxis.setOpacity(0);
		
		xAxis.setTickLabelsVisible(false);
		xAxis.setOpacity(0);

	}
	
	public LineChart<Number, Number> getLineChart() {
		return myLineChart;
	}
}
