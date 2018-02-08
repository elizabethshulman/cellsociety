package fileInfoExtractorVariants;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import cellVariants.Cell;
import cellVariants.FireCell;

import cellsociety_team10.FileInfoExtractor;

public final class FireFIE extends FileInfoExtractor {

	@Override
	protected Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		if(xmlRead.getLocalName().equals("probCatchFire")) {
			xmlRead.next();
			return Double.parseDouble(xmlRead.getText());
		}
		throw new XMLStreamException("Invalid global variables in file.");
	}

	@Override
	protected Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		int val = Integer.parseInt(xmlRead.getAttributeValue(0));
		if(val < 0 || val > 2) {
			throw new XMLStreamException("Invalid Fire cell type.");
		}
		return new FireCell(val);
	}
	@Override
	public List<int[]> calcNeighborLocations(int row, int col, int gridRowLength, int gridColLength) {
		ArrayList<int[]> neighborCoordinates = new ArrayList<>();
		for(int a = row - 1; a <= row + 1; a++) {
			for(int b = col - 1; b <= col + 1; b++) {
				if(isValidGridLocation(a,b,gridRowLength,gridColLength) && (a == row ^ b == col)) { 
					neighborCoordinates.add(new int[]{a,b});
				}
			}
		}
		return neighborCoordinates;
	}

}
