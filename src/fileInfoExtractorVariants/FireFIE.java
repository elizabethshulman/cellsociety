package fileInfoExtractorVariants;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import cellsociety_team10.Cell;
import cellsociety_team10.FileInfoExtractor;

public final class FireFIE extends FileInfoExtractor {

	@Override
	protected Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		if(xmlRead.getLocalName().equals("probCatchFire")) {
			xmlRead.next();
			return Double.parseDouble(xmlRead.getText());
		}
		else throw new XMLStreamException("Invalid global variables in file.");
	}

	@Override
	protected Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		int val = Integer.parseInt(xmlRead.getAttributeValue(0));
		if(val < 0 || val > 2)
			throw new XMLStreamException("Invalid Fire cell type.");
		return new Cell(val);
	}

}
