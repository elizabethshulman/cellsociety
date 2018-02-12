package visualComponents;

import cellsociety_team10.Graph;
import javafx.scene.shape.Polygon;

/**
 * @author benhubsch
 * 
 * The Class SquareContainer.
 */
public class SquareContainer extends Container {
	
	/**
	 * Instantiates a new square container.
	 */
	public SquareContainer() {
		super();
	}

	/* (non-Javadoc)
	 * @see visualComponents.Container#drawGraph(graphVariants.Graph)
	 */
	@Override
	protected void drawGraph(Graph g) {
		double width = Container.GRID_SIZE / g.getCols();
		double height = Container.GRID_SIZE / g.getRows();

		double row_offset = 0;
		double col_offset = 0;
		for (int r=0; r < g.getRows(); r++) {
			for (int c=0; c < g.getCols(); c++) {
				Polygon p = generateSquare(
						col_offset, row_offset,							
						col_offset, row_offset + height,
						col_offset + width, row_offset + height,
						col_offset + width, row_offset
						);
				myPolygonArr[r][c] = p;
				myDisplay.getChildren().add(p);
				col_offset += width;

			}
			row_offset += height;
			col_offset = 0;
		}
	}

	/**
	 * Generate square based on the 4 points represented by (a_i, b_i) tuples.
	 *
	 * @param a_1
	 * @param b_1
	 * @param a_2
	 * @param b_2
	 * @param a_3
	 * @param b_3
	 * @param a_4
	 * @param b_4
	 * @return Polygon
	 */
	private Polygon generateSquare(double a_1, double b_1, double a_2, double b_2, double a_3, double b_3, double a_4, double b_4) {
		Polygon square = new Polygon();
		square.getPoints().addAll(new Double[]{
				a_1, b_1,
				a_2, b_2,
				a_3, b_3,
				a_4, b_4 });

		return square;
	}
}
