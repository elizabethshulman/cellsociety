package fileInfoExtractorVariants;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import cellVariants.GameOfLifeCell;
import cellVariants.*;
import cellsociety_team10.FileInfoExtractor;

public class GameOfLifeFIE extends FileInfoExtractor {

	@Override
	protected Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		throw new XMLStreamException("Invalid global variables in file");
	}

	@Override
	protected Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		int val = Integer.parseInt(xmlRead.getAttributeValue(0));
		if(val < 0 || val > 2)
			throw new XMLStreamException("Invalid Game Of Life cell type.");
		return new GameOfLifeCell(val);
	}

}
