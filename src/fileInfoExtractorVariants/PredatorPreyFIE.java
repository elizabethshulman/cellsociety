package fileInfoExtractorVariants;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import cellVariants.Cell;
import cellVariants.PredatorPreyCell;

public class PredatorPreyFIE implements FileInfoExtractor{

	@Override
	public Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		if(xmlRead.getLocalName().matches("fishBreedTime|sharkBreedTime|sharkStarveTime")) {
			xmlRead.next();
			return Double.parseDouble(xmlRead.getText());
		}
		throw new XMLStreamException("Invalid global variables in file.");
	}

	@Override
	public Cell getCell(XMLStreamReader xmlRead, String shape) throws XMLStreamException {
		switch(xmlRead.getAttributeValue(0))
		{
			case "E": return new PredatorPreyCell(0, shape);
			case "F": return new PredatorPreyCell(1, shape);
			case "S": return new PredatorPreyCell(2, shape);
			default: throw new XMLStreamException("Invalid Predator-Prey cell type.");
		}
	}

	@Override
	public void writeCell(XMLStreamWriter myWriter, Cell cell) throws XMLStreamException {
		String s;
		switch(cell.getState()) {
			case 2: s = "S";
			case 1: s = "F";
			default: s = "E";
		}
		myWriter.writeAttribute("state", s);
		
	}


}
