package visualComponents;

import graphVariants.Graph;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class HexContainer extends Container {
	public HexContainer() {
		super();
		myDisplay.setId("container-display-borderless");
	}

	@Override
	protected void drawGraph(Graph g) {
		double height = (Container.GRID_SIZE / (g.getRows() + 0.5)) / 2.0;
		double length = (2.0 * height) / Math.sqrt(3.0);
		double inset = Math.sqrt(Math.pow(length, 2) - Math.pow(height, 2));

		double row_offset = 0;
		double col_offset = 0;
		for (int r=0; r < g.getRows(); r++) {
			for (int c=0; c < g.getCols(); c++) {
				Polygon p;
				if (c % 2 == 0) {
					p = generateHex(
							col_offset, row_offset + height,
							col_offset + inset, row_offset + height * 2,
							col_offset + inset + length, row_offset + height * 2,
							col_offset + length + 2 * inset, row_offset + height,
							col_offset + inset + length, row_offset,
							col_offset + inset, row_offset,
							Color.AQUA
							);
					myDisplay.getChildren().add(p);
					myPolygonArr[r][c] = p;
					col_offset += inset + length;
					row_offset += height;
				} else {
					p = generateHex(
							col_offset, row_offset + height,
							col_offset + inset, row_offset + height * 2,
							col_offset + inset + length, row_offset + height * 2,
							col_offset + length + 2 * inset, row_offset + height,
							col_offset + inset + length, row_offset,
							col_offset + inset, row_offset,
							Color.CRIMSON
							);
					myDisplay.getChildren().add(p);
					myPolygonArr[r][c] = p;
					row_offset -= height;
					col_offset += inset + length;
				}
			}
			col_offset = 0;
			if (g.getCols() % 2 == 1) {
				row_offset += height;
			} else {
				row_offset += height * 2;
			}
		}
	}
	
	private Polygon generateHex(double a_1, double b_1, double a_2, double b_2, double a_3, double b_3, double a_4, double b_4, double a_5, double b_5, double a_6, double b_6, Color color) {
		Polygon hex = new Polygon();
		hex.getPoints().addAll(new Double[]{
				a_1, b_1,
				a_2, b_2,
				a_3, b_3,
				a_4, b_4,
				a_5, b_5,
				a_6, b_6});
		hex.setFill(color);

		return hex;
	}
}
