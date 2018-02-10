package fileInfoExtractorVariants;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

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
	public Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		switch(xmlRead.getAttributeValue(0))
		{
			case "E": return new PredatorPreyCell(0);
			case "F": return new PredatorPreyCell(1);
			case "S": return new PredatorPreyCell(2);
			default: throw new XMLStreamException("Invalid Predator-Prey cell type.");
		}
	}

	@Override
	public String getEncoding(int state) {
		switch(state)
		{
			case 2: return "S";
			case 1: return "F";
			default: return "E";
		}
	}

}
