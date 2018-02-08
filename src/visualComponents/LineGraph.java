package visualComponents;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

public class LineGraph {
	
	private LineChart<Number, Number> myLineChart;
	private VBox myVBox;
	
	public LineGraph() {
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		LineChart<Number, Number> myLineChart = 
				new LineChart<Number, Number>(xAxis,yAxis);
		
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

		XYChart.Series<Number, Number> series1 = new XYChart.Series<Number, Number>();
		series1.getData().add(new XYChart.Data<Number, Number>(10, 23));
		series1.getData().add(new XYChart.Data<Number, Number>(45, 14));
		series1.getData().add(new XYChart.Data<Number, Number>(77, 15));
		series1.getData().add(new XYChart.Data<Number, Number>(23, 24));
		series1.getData().add(new XYChart.Data<Number, Number>(01, 34));

		XYChart.Series<Number, Number> series2 = new XYChart.Series<Number, Number>();
		series2.getData().add(new XYChart.Data<Number, Number>(7, 33));
		series2.getData().add(new XYChart.Data<Number, Number>(17, 34));
		series2.getData().add(new XYChart.Data<Number, Number>(34, 25));
		series2.getData().add(new XYChart.Data<Number, Number>(22, 44));
		series2.getData().add(new XYChart.Data<Number, Number>(43, 39));

		XYChart.Series<Number, Number> series3 = new XYChart.Series<Number, Number>();
		series3.getData().add(new XYChart.Data<Number, Number>(44, 44));
		series3.getData().add(new XYChart.Data<Number, Number>(21, 35));
		series3.getData().add(new XYChart.Data<Number, Number>(22, 36));
		series3.getData().add(new XYChart.Data<Number, Number>(43, 33));
		series3.getData().add(new XYChart.Data<Number, Number>(23, 31));
	
		myLineChart.getData().addAll(series1, series2, series3);
		
		myVBox = new VBox();
		myVBox.getChildren().add(myLineChart);
	}
	
	public VBox getLineChart() {
		return myVBox;
	}
}
