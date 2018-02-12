package fileInfoExtractorVariants;

import java.util.Map;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import cellVariants.Cell;
import cellVariants.SegregationCell;

public class SegregationFIE implements FileInfoExtractor {

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
	public void addDefaultGlobals(Map<String,Double> globals) {
		if(!globals.containsKey("satisfactionThreshold"))
			globals.put("satisfactionThreshold", 0.5);
	}

	@Override
	public Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		switch(xmlRead.getAttributeValue(0)) {
			case "E": return new SegregationCell(0);
			case "R": return new SegregationCell(1);
			case "B": return new SegregationCell(2);
			default: throw new XMLStreamException("Invalid Segregation cell type");
		}
	}

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
