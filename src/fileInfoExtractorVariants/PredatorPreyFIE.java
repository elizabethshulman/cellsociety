//implements file reading and writing for Predator Prey Simulation
//author: Andrew

package fileInfoExtractorVariants;

import java.util.Map;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import cellVariants.Cell;
import cellVariants.PredatorPreyCell;

public class PredatorPreyFIE implements FileInfoExtractor{
	//reads in breeding and starving times for the simulation
	//throws error if invalid global variable of invalid variable value is found
	@Override
	public Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		if(xmlRead.getLocalName().matches("fishBreedTime|sharkBreedTime|sharkStarveTime")) {
			xmlRead.next();
			double d =  Double.parseDouble(xmlRead.getText());
			if(d % 1 != 0.0)
				throw new XMLStreamException("Noninteger global variable specified");
			return d;
		}
		throw new XMLStreamException("Invalid global variables in file.");
	}
	//add default breeding/starving variables as neccessary
	@Override
	public void addDefaultGlobals(Map<String,Double> globals) {
		if(!globals.containsKey("sharkBreedTime"))
			globals.put("sharkBreedTime", 10.0);
		if(!globals.containsKey("fishBreedTime"))
			globals.put("fishBreedTime", 5.0);
		if(!globals.containsKey("sharkStarveTime"))
			globals.put("sharkStarveTime", 5.0);
	}
	//read in cell state and return correct cell
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
	//formats cell state and writes cell state to file
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
