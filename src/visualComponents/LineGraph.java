package visualComponents;

import java.util.HashMap;
import java.util.Map;

import cellVariants.Cell;
import graphVariants.Graph;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

public class LineGraph {
	
	private Map<Integer, XYChart.Series<Number, Number>> mySeries = new HashMap<Integer, XYChart.Series<Number, Number>>();
	private int myIteration = 0;
	private LineChart<Number, Number> myLineChart;
	private VBox myVBox;
	
	public LineGraph() {
		NumberAxis xAxis = new NumberAxis();
		xAxis.setId("axis");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setId("axis");
		myLineChart = new LineChart<Number, Number>(xAxis,yAxis);
		myLineChart.setId("line-chart");

		myVBox = new VBox();
		myVBox.getChildren().add(myLineChart);
	}
	
	public VBox getLineChart() {
		return myVBox;
	}
	
	private Map<Integer, Integer> countStates(Graph g) {
		Map<Integer, Integer> type_count = new HashMap<Integer, Integer>();
		
		for (Cell curr : g.getCells()) {
			int count = type_count.containsKey(curr.getState()) ? type_count.get(curr.getState()) : 0;
			type_count.put(curr.getState(), count + 1);
		}
		
		return type_count;
	}
	
	
	public void addCoordinates(Graph g) {
		Map<Integer, Integer> type_count = countStates(g);
		
		for (Integer state : type_count.keySet()) {
			if (! mySeries.containsKey(state)) {
				XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
				myLineChart.getData().add(series);
				mySeries.put(state, series);
			}
			
			XYChart.Series<Number, Number> series = mySeries.get(state);
			series.getData().add(new XYChart.Data<Number, Number>(myIteration, type_count.get(state)));
		}
		myIteration += 1;
	}
}
