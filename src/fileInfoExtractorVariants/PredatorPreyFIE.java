package fileInfoExtractorVariants;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import cellVariants.*;
import cellsociety_team10.FileInfoExtractor;

public class PredatorPreyFIE extends FileInfoExtractor{

	@Override
	protected Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		if(xmlRead.getLocalName().matches("fishBreedTime|sharkBreedTime|sharkStarveTime")) {
			xmlRead.next();
			return Double.parseDouble(xmlRead.getText());
		}
		else throw new XMLStreamException("Invalid global variables in file.");
	}

	@Override
	protected Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		switch(xmlRead.getAttributeValue(0))
		{
			case "E": return new PredatorPreyCell(0);
			case "F": return new PredatorPreyCell(1);
			case "S": return new PredatorPreyCell(2);
			default: throw new XMLStreamException("Invalid Predator-Prey cell type.");
		}
	}

}
