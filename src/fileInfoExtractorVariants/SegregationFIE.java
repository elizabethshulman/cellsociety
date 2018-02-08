package fileInfoExtractorVariants;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import cellVariants.Cell;
import cellVariants.SegregationCell;

import cellsociety_team10.FileInfoExtractor;

public class SegregationFIE extends FileInfoExtractor {

	@Override
	protected Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		if(xmlRead.getLocalName().equals("satisfactionThreshold")) {
			xmlRead.next();
			return Double.parseDouble(xmlRead.getText());
		}
		throw new XMLStreamException("Invalid global variables in file.");
	}

	@Override
	protected Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		switch(xmlRead.getAttributeValue(0)) {
			case "E": return new SegregationCell(0);
			case "R": return new SegregationCell(1);
			case "B": return new SegregationCell(2);
			default: throw new XMLStreamException("Invalid Segregation cell type");
		}
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
