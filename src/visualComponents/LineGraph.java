package visualComponents;

import java.util.HashMap;
import java.util.Map;

import cellVariants.Cell;
import graphVariants.Graph;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

/**
 * This class handles the display of the LineChart object which appears at the top of the
 * visualization when a simulation is running. It charts the number of cells of a given
 * type over the life of the simulation.
 */
public class LineGraph {
	
	private Map<Integer, XYChart.Series<Number, Number>> mySeries = new HashMap<Integer, XYChart.Series<Number, Number>>();
	private int myIteration = 0;
	private LineChart<Number, Number> myLineChart;
	private VBox myVBox;
	
	/**
	 * Instantiates a new LineGraph object.
	 */
	public LineGraph() {
		NumberAxis xAxis = new NumberAxis();
		xAxis.setId("axis");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setId("axis");
		myLineChart = new LineChart<>(xAxis,yAxis);
		myLineChart.setId("line-chart");

		myVBox = new VBox();
		myVBox.getChildren().add(myLineChart);
	}
	
	/**
	 * Gets the VBox object.
	 *
	 * @return VBox
	 */
	public VBox getLineChart() {
		return myVBox;
	}
	
	/**
	 * Count states.
	 *
	 * @param g the g
	 * @return Map<Integer,Integer>
	 */
	private Map<Integer, Integer> countStates(Graph g) {
		Map<Integer, Integer> type_count = new HashMap<Integer, Integer>();
		
		for (Cell curr : g.getCells()) {
			int count = type_count.containsKey(curr.getState()) ? type_count.get(curr.getState()) : 0;
			type_count.put(curr.getState(), count + 1);
		}
		
		return type_count;
	}
	
	/**
	 * Sets the visibility.
	 */
	private void setVisibility() {
		if (myIteration > 0) {
			myLineChart.setVisible(true);
		} else {
			myLineChart.setVisible(false);
		}
	}
	
	/**
	 * Adds the coordinates.
	 *
	 * @param g the g
	 */
	public void addCoordinates(Graph g) {
		setVisibility();
		
		Map<Integer, Integer> type_count = countStates(g);
		for (Integer state : type_count.keySet()) {
			if (! mySeries.containsKey(state)) {
				XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
				myLineChart.getData().add(series);
				mySeries.put(state, series);
			}
			
			XYChart.Series<Number, Number> series = mySeries.get(state);
			XYChart.Data<Number, Number> point = new XYChart.Data<>(myIteration, type_count.get(state));
			series.getData().add(point);
			
			for(Node n : myLineChart.lookupAll(".series" + state)) {
	            n.setStyle("-fx-stroke: " + g.getCorrectColor(state) + ";");
	        }
		}
		myIteration += 1;
	}
	
	/**
	 * Reset chart.
	 */
	public void resetChart() {
		myLineChart.getData().clear();
		mySeries.clear();
		myIteration = 0;
	}
}
