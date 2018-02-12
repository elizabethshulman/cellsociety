//implements file reading and writing for Game of Life
//author: Andrew
package fileInfoExtractorVariants;

import java.util.Map;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import cellVariants.Cell;
import cellVariants.GameOfLifeCell;

public class GameOfLifeFIE implements FileInfoExtractor {
	//no global variables: throw exception if one is found
	@Override
	public Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		throw new XMLStreamException("Invalid global variables in file");
	}
	//no global variables: do nothing
	@Override
	public void addDefaultGlobals(Map<String,Double> globals) {}
	
	//read cell from file
	@Override
	public Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		int val = Integer.parseInt(xmlRead.getAttributeValue(0));
		if(val < 0 || val > 1) {
			throw new XMLStreamException("Invalid Game Of Life cell type.");
		}
		return new GameOfLifeCell(val);
	}
	//write cell state to file
	@Override
	public void writeCell(XMLStreamWriter myWriter, Cell cell) throws XMLStreamException {
		myWriter.writeAttribute("state", Integer.toString(cell.getState()));
	}



}
