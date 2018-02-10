package fileInfoExtractorVariants;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import cellVariants.Cell;
import cellVariants.FireCell;

public final class FireFIE implements FileInfoExtractor {

	@Override
	public Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		if(xmlRead.getLocalName().equals("probCatchFire")) {
			xmlRead.next();
			return Double.parseDouble(xmlRead.getText());
		}
		throw new XMLStreamException("Invalid global variables in file.");
	}

	@Override
	public Cell getCell(XMLStreamReader xmlRead, String shape) throws XMLStreamException {
		int val = Integer.parseInt(xmlRead.getAttributeValue(0));
		if(val < 0 || val > 2) {
			throw new XMLStreamException("Invalid Fire cell type.");
		}
		return new FireCell(val, shape);
	}

	@Override
	public String getEncoding(int state) {
		return Integer.toString(state);
	}

}
