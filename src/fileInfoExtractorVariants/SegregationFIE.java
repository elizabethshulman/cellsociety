/**
 * implements file reading and writing for Segregation simulation
 * @author Andrew
 */
package fileInfoExtractorVariants;

import java.util.Map;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import cellVariants.Cell;
import cellVariants.SegregationCell;

public class SegregationFIE implements FileInfoExtractor {
	/**
	 * Get satisfaction threshold from file
	 * Throw exception for other variables or if satisfaction threshold is not between 0 and 1
	 */
	@Override
	public Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		if(xmlRead.getLocalName().equals("satisfactionThreshold")) {
			xmlRead.next();
			double d = Double.parseDouble(xmlRead.getText());
			if(d < 0 || d > 1)
				throw new XMLStreamException("Satisfaction threshold has invalid value.");
			return d;
		}
		throw new XMLStreamException("Invalid global variables in file.");
	}
	/**
	 * Adds default value of 0.5 for satisfaction threshold if one is not found in the file
	 */
	public void addDefaultGlobals(Map<String,Double> globals) {
		if(!globals.containsKey("satisfactionThreshold"))
			globals.put("satisfactionThreshold", 0.5);
	}
	/**
	 * Creates cell with specified state (E = empty, R and B are different populations)
	 * Throws exception for invalid cell state
	 */
	@Override
	public Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		switch(xmlRead.getAttributeValue(0)) {
			case "E": return new SegregationCell(0);
			case "R": return new SegregationCell(1);
			case "B": return new SegregationCell(2);
			default: throw new XMLStreamException("Invalid Segregation cell type");
		}
	}
	/**
	 * Writes cell state to output
	 */
	@Override
	public void writeCell(XMLStreamWriter myWriter, Cell cell) throws XMLStreamException {
		String s;
		switch(cell.getState()) {
			case 2: s = "B"; break;
			case 1: s = "R"; break;
			default: s = "E";
		}
		myWriter.writeAttribute("state", s);
		
	}


}
