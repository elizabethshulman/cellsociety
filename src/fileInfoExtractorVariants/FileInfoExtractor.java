//the FileInfoExtractor reads and writes simulation-specific information to/from the file
//Author: Andrew Yeung
package fileInfoExtractorVariants;

import java.util.Map;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import cellVariants.Cell;

public interface FileInfoExtractor {
	//parse global variable, throw exception if necessary
	public Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException;
	//add any global variables not specified in the file
	public void addDefaultGlobals(Map<String,Double> globals);
	//parse cell-specific information and return a cell
	public Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException;
	//write cell-specific information from the cell to the file
	public void writeCell(XMLStreamWriter myWriter, Cell cell) throws XMLStreamException;
}
