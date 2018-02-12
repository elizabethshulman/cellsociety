//implements file reading and writing for segregation simulation
//author: Andrew
package fileInfoExtractorVariants;

import java.util.Map;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import cellVariants.Cell;
import cellVariants.SegregationCell;

public class SegregationFIE implements FileInfoExtractor {
	//reads in satisfaction threshold; throws error if wrong variable or invalid value is found
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
	//adds default value of 0.5 as satisfaction threshold if one was not specified in the file
	public void addDefaultGlobals(Map<String,Double> globals) {
		if(!globals.containsKey("satisfactionThreshold"))
			globals.put("satisfactionThreshold", 0.5);
	}
	//reads in formatted cell state and returns cell
	@Override
	public Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		switch(xmlRead.getAttributeValue(0)) {
			case "E": return new SegregationCell(0);
			case "R": return new SegregationCell(1);
			case "B": return new SegregationCell(2);
			default: throw new XMLStreamException("Invalid Segregation cell type");
		}
	}
	//writes formatted cell state to file
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
