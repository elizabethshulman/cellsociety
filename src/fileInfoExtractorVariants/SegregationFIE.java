package fileInfoExtractorVariants;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import cellVariants.Cell;
import cellVariants.SegregationCell;

public class SegregationFIE implements FileInfoExtractor {

	@Override
	public Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		if(xmlRead.getLocalName().equals("satisfactionThreshold")) {
			xmlRead.next();
			return Double.parseDouble(xmlRead.getText());
		}
		throw new XMLStreamException("Invalid global variables in file.");
	}

	@Override
	public Cell getCell(XMLStreamReader xmlRead, String shape) throws XMLStreamException {
		switch(xmlRead.getAttributeValue(0)) {
			case "E": return new SegregationCell(0, shape);
			case "R": return new SegregationCell(1, shape);
			case "B": return new SegregationCell(2, shape);
			default: throw new XMLStreamException("Invalid Segregation cell type");
		}
	}

	@Override
	public void writeCell(XMLStreamWriter myWriter, Cell cell) throws XMLStreamException {
		String s;
		switch(cell.getState()) {
			case 2: s = "B";
			case 1: s = "R";
			default: s = "E";
		}
		myWriter.writeAttribute("state", s);
		
	}


}
