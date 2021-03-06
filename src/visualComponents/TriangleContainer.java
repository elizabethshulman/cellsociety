package visualComponents;

import cellsociety_team10.Graph;
import javafx.scene.shape.Polygon;

/**
 * @author benhubsch
 * 
 * The Class TriangleContainer.
 */
public class TriangleContainer extends Container {
	
	/**
	 * Instantiates a new triangle container.
	 */
	public TriangleContainer() {
		super();
	}

	/* (non-Javadoc)
	 * @see visualComponents.Container#drawGraph(graphVariants.Graph)
	 */
	@Override
	protected void drawGraph(Graph g) {
		double width;
		if (g.getCols() % 2 == 0) {
			width = Container.GRID_SIZE / ((g.getCols() / 2) + 0.5);
		} else {
			width = Container.GRID_SIZE / ((g.getCols() + 1)  / 2);
		}
		double height = Container.GRID_SIZE / g.getRows();

		double row_offset = 0;
		double col_offset = 0;
		for (int r=0; r < g.getRows(); r++) {
			for (int c=0; c < g.getCols(); c++) {
				Polygon p;
				if (r % 2 == 0) {
					if (c % 2 == 0) {
						p = generateTriangle(
								col_offset + width, height + row_offset, 
								col_offset, height + row_offset,
								col_offset + width / 2, row_offset
								);
					} else {
						p = generateTriangle(
								col_offset + width / 2, row_offset,
								col_offset + width + width / 2, row_offset,
								col_offset + width, row_offset + height
								);
						col_offset += width;
					}
				} else {
					if (c % 2 == 0) {
						p = generateTriangle(
								col_offset + width, row_offset, 
								col_offset, row_offset,
								col_offset + width / 2, row_offset + height
								);
					} else {
						p = generateTriangle(
								col_offset + width / 2, row_offset + height,
								col_offset + width + width / 2, row_offset + height,
								col_offset + width, row_offset
								);
						col_offset += width;
					}
				}
				myDisplay.getChildren().add(p);
				myPolygonArr[r][c] = p;
			}
			row_offset += height;
			col_offset = 0;
		}
	}
	
	/**
	 * Generate the 3 points of a triangle based on (a_i, b_i) tuples.
	 *
	 * @param a_1
	 * @param b_1
	 * @param a_2
	 * @param b_2
	 * @param a_3
	 * @param b_3
	 * @return Polygon
	 */
	private Polygon generateTriangle(double a_1, double b_1, double a_2, double b_2, double a_3, double b_3) {
		Polygon triangle = new Polygon();
		triangle.getPoints().addAll(new Double[]{
				a_1, b_1,
				a_2, b_2,
				a_3, b_3 });

		return triangle;
	}
}
