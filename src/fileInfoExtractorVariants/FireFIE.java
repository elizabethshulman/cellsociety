/**
 * this class allows file reading/writing regarding the Spreading of Fire simulation
 * @author Andrew
 */

package fileInfoExtractorVariants;

import java.util.Map;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import cellVariants.Cell;
import cellVariants.FireCell;

public class FireFIE implements FileInfoExtractor {
	/**
	 * gets the probCatchFire variable
	 * Throws an exception if another variable is found or if probCatchFire is not between 0 and 1.
	 */
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
	/**
	 * sets probCatchFire to 0.5 if it was not read in
	 */
	@Override
	public void addDefaultGlobals(Map<String,Double> globals) {
		if(!globals.containsKey("probCatchFire"))
			globals.put("probCatchFire", 0.5);
	}
	/**
	 * creates a cell from the file: 0 = empty, 1 = tree, 2 = burning
	 * throws an exception if an invalid cell state is specified
	 */
	@Override
	public Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		int val = Integer.parseInt(xmlRead.getAttributeValue(0));
		if(val < 0 || val > 2) {
			throw new XMLStreamException("Invalid Fire cell type.");
		}
		return new FireCell(val);
	}
	/**
	 * writes the cell state directly to the file using the same state convention as used in file reading
	 */
	@Override
	public void writeCell(XMLStreamWriter myWriter, Cell cell) throws XMLStreamException {
		myWriter.writeAttribute("state", Integer.toString(cell.getState()));
	}

}
