package fileInfoExtractorVariants;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import cellsociety_team10.Cell;
import cellsociety_team10.FileInfoExtractor;

public class SegregationFIE extends FileInfoExtractor {

	@Override
	protected Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		if(xmlRead.getLocalName().equals("satisfactionThreshold")) {
			xmlRead.next();
			return Double.parseDouble(xmlRead.getText());
		}
		else throw new XMLStreamException("Invalid global variables in file.");
	}

	@Override
	protected Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		switch(xmlRead.getAttributeValue(0))
		{
			case "E": return new Cell(0);
			case "R": return new Cell(1);
			case "B": return new Cell(2);
			default: throw new XMLStreamException("Invalid Segregation cell type");
		}
	}

}
