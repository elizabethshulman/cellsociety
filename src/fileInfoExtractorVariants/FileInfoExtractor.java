package fileInfoExtractorVariants;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import cellVariants.Cell;

public abstract class FileInfoExtractor {
	public abstract Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException;
	public abstract Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException;
	public abstract List<int[]> calcNeighborLocations(int row, int col, int gridRowLength, int gridColLength);
	protected boolean isValidGridLocation(int row, int col, int gridRowLength, int gridColLength) {
		return row >= 0 && row < gridRowLength && col >= 0 && col < gridColLength;
	}
}
