package fileInfoExtractorVariants;

import java.util.Map;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import cellVariants.Cell;
import cellVariants.RockPaperScissorsCell;

public class RockPaperScissorsFIE implements FileInfoExtractor{

	@Override
	public Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		throw new XMLStreamException("Invalid global variables in file");
	}

	@Override
	public void addDefaultGlobals(Map<String, Double> globals) {
	}

	@Override
	public Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		int val = Integer.parseInt(xmlRead.getAttributeValue(0));
		if(val < 0 || val > 3) {
			throw new XMLStreamException("Invalid Rock Paper Scissors cell type.");
		}
		if(val == 0)
			return new RockPaperScissorsCell(val, 0);
		int gradient = Integer.parseInt(xmlRead.getAttributeValue(1));
		return new RockPaperScissorsCell(val, gradient);
	}

	@Override
	public void writeCell(XMLStreamWriter myWriter, Cell cell) throws XMLStreamException {
		myWriter.writeAttribute("state", Integer.toString(cell.getState()));
		if(cell.getState() != 0)
			myWriter.writeAttribute("gradient", Integer.toString(((RockPaperScissorsCell) cell).getHealth()));	
	}

}
