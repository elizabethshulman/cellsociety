package fileInfoExtractorVariants;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import cellVariants.Cell;
import cellVariants.GameOfLifeCell;

public class GameOfLifeFIE implements FileInfoExtractor {

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
	public String getEncoding(int state) {
		return Integer.toString(state);
	}



}
