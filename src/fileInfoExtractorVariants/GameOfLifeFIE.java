package fileInfoExtractorVariants;

import java.util.Map;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import cellVariants.Cell;
import cellVariants.GameOfLifeCell;

public class GameOfLifeFIE implements FileInfoExtractor {

	@Override
	public Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		throw new XMLStreamException("Invalid global variables in file");
	}
	@Override
	public void addDefaultGlobals(Map<String,Double> globals) {}

	@Override
	public Cell getCell(XMLStreamReader xmlRead, String shape) throws XMLStreamException {
		int val = Integer.parseInt(xmlRead.getAttributeValue(0));
		if(val < 0 || val > 1) {
			throw new XMLStreamException("Invalid Game Of Life cell type.");
		}
		return new GameOfLifeCell(val, shape);
	}

	@Override
	public void writeCell(XMLStreamWriter myWriter, Cell cell) throws XMLStreamException {
		myWriter.writeAttribute("state", Integer.toString(cell.getState()));
	}



}
