package fileInfoExtractorVariants;

import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import cellVariants.Cell;
import cellVariants.GameOfLifeCell;

import cellsociety_team10.FileInfoExtractor;

public class GameOfLifeFIE extends FileInfoExtractor {

	@Override
	protected Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		throw new XMLStreamException("Invalid global variables in file");
	}

	@Override
	protected Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		int val = Integer.parseInt(xmlRead.getAttributeValue(0));
		if(val < 0 || val > 2) {
			throw new XMLStreamException("Invalid Game Of Life cell type.");
		}
		return new GameOfLifeCell(val);
	}

	@Override
	public ArrayList<int[]> calcNeighborLocations(int row, int col, int gridRowLength, int gridColLength) {
		ArrayList<int[]> neighborCoordinates = new ArrayList<int[]>();
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
