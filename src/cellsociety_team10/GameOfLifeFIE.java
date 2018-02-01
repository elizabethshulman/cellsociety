package cellsociety_team10;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class GameOfLifeFIE extends FileInfoExtractor {

	@Override
	public Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		throw new XMLStreamException("Invalid global variables in file");
	}

	@Override
	public Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		int val = Integer.parseInt(xmlRead.getAttributeValue(0));
		if(val < 0 || val > 2)
			throw new XMLStreamException("Invalid Cell cell type.");
		return new Cell(val);
	}

}
