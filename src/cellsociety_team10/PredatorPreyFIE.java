package cellsociety_team10;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class PredatorPreyFIE extends FileInfoExtractor{

	@Override
	public Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		if(xmlRead.getLocalName().matches("fishBreedTime|sharkBreedTime|sharkStarveTime")) {
			xmlRead.next();
			return Double.parseDouble(xmlRead.getText());
		}
		else throw new XMLStreamException("Invalid global variables in file.");
	}

	@Override
	public Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		switch(xmlRead.getAttributeValue(0))
		{
			case "E": return new Cell(0);
			case "F": return new Cell(1);
			case "S": return new Cell(2);
			default: throw new XMLStreamException("Invalid Predator-Prey cell type");
		}
	}

}
