package fileInfoExtractorVariants;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import cellVariants.Cell;
import cellVariants.SegregationCell;

public class SegregationFIE extends FileInfoExtractor {

	@Override
	public Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		if(xmlRead.getLocalName().equals("satisfactionThreshold")) {
			xmlRead.next();
			return Double.parseDouble(xmlRead.getText());
		}
		throw new XMLStreamException("Invalid global variables in file.");
	}

	@Override
	public Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		switch(xmlRead.getAttributeValue(0)) {
			case "E": return new SegregationCell(0);
			case "R": return new SegregationCell(1);
			case "B": return new SegregationCell(2);
			default: throw new XMLStreamException("Invalid Segregation cell type");
		}
	}

}
