/**
 * implements file reading and writing for PredatorPrey simulation
 * @author Andrew
 */
package fileInfoExtractorVariants;

import java.util.Map;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import cellVariants.Cell;
import cellVariants.PredatorPreyCell;

public class PredatorPreyFIE implements FileInfoExtractor{
	/**
	 * reads in breeding and starving times from the file
	 * throws an exception if times are not positive integers
	 */
	@Override
	public Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		if(xmlRead.getLocalName().matches("fishBreedTime|sharkBreedTime|sharkStarveTime")) {
			xmlRead.next();
			double d =  Double.parseDouble(xmlRead.getText());
			if(d % 1 != 0.0 || d < 0)
				throw new XMLStreamException("Noninteger global variable specified");
			return d;
		}
		throw new XMLStreamException("Invalid global variables in file.");
	}
	/**
	 * Individually adds breeding and starving times if they are absent from the file
	 */
	@Override
	public void addDefaultGlobals(Map<String,Double> globals) {
		if(!globals.containsKey("sharkBreedTime"))
			globals.put("sharkBreedTime", 10.0);
		if(!globals.containsKey("fishBreedTime"))
			globals.put("fishBreedTime", 5.0);
		if(!globals.containsKey("sharkStarveTime"))
			globals.put("sharkStarveTime", 5.0);
	}
	/**
	 * gets Cell state letter (E = empty, F = fish, S = shark) and returns appropriate cell
	 * throws exception if an invalid state is specified
	 */
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
	/**
	 * Writes state to file with the same state convention as the file reading
	 */
	@Override
	public void writeCell(XMLStreamWriter myWriter, Cell cell) throws XMLStreamException {
		String s;
		switch(cell.getState()) {
			case 2: s = "S"; break;
			case 1: s = "F"; break;
			default: s = "E";
		}
		myWriter.writeAttribute("state", s);
		
	}


}
