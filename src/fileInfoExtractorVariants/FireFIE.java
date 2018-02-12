//this class allows file reading/writing regarding the Spreading of Fire simulation
//author: Andrew
package fileInfoExtractorVariants;

import java.util.Map;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import cellVariants.Cell;
import cellVariants.FireCell;

public final class FireFIE implements FileInfoExtractor {
	//parses the probCatchFire variable and throws exception if variable is invalid
	@Override
	public Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		if(xmlRead.getLocalName().equals("probCatchFire")) {
			xmlRead.next();
			double d = Double.parseDouble(xmlRead.getText());
			if(d < 0 || d > 1)
				throw new XMLStreamException("Fire catching probability has invalid value.");
			return d;
		}
		throw new XMLStreamException("Invalid global variables in file.");
	}
	//sets probCatchFire if not present in the file
	@Override
	public void addDefaultGlobals(Map<String,Double> globals) {
		if(!globals.containsKey("probCatchFire"))
			globals.put("probCatchFire", 0.5);
	}
	//creates cell with valid state, throws exception if invalid
	@Override
	public Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		int val = Integer.parseInt(xmlRead.getAttributeValue(0));
		if(val < 0 || val > 2) {
			throw new XMLStreamException("Invalid Fire cell type.");
		}
		return new FireCell(val);
	}
	//writes cell state to file
	@Override
	public void writeCell(XMLStreamWriter myWriter, Cell cell) throws XMLStreamException {
		myWriter.writeAttribute("state", Integer.toString(cell.getState()));
	}

}
