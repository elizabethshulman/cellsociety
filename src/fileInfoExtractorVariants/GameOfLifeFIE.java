package fileInfoExtractorVariants;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import cellVariants.Cell;
import cellVariants.GameOfLifeCell;

public class GameOfLifeFIE extends FileInfoExtractor {

	@Override
	public Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		throw new XMLStreamException("Invalid global variables in file");
	}

	@Override
	public Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		int val = Integer.parseInt(xmlRead.getAttributeValue(0));
		if(val < 0 || val > 2) {
			throw new XMLStreamException("Invalid Game Of Life cell type.");
		}
		return new GameOfLifeCell(val);
	}

	@Override
	public List<int[]> calcNeighborLocations(int row, int col, int gridRowLength, int gridColLength) {
		ArrayList<int[]> neighborCoordinates = new ArrayList<>();
		for(int a = row - 1; a <= row + 1; a++) {
			for(int b = col - 1; b <= col + 1; b++) {
				if(isValidGridLocation(a,b,gridRowLength,gridColLength) && (a != row || b != col)) {
					neighborCoordinates.add(new int[]{a,b});
				}
			}
		}
		return neighborCoordinates;
	}

}
