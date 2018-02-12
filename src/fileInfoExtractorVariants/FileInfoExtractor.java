/**
 * This interface is responsible for handling how each simulation reads simulation-specific information such as global variables and cells.
 * It is also responsible for ensuring that cell states and any additional attributes are correctly written to the file.
 * @author Andrew
 */
package fileInfoExtractorVariants;

import java.util.Map;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import cellVariants.Cell;

public interface FileInfoExtractor {
	/**
	 * This method returns the value of the global variable in the pointer and error checks for fileReader
	 * @param xmlRead the XMLStreamReader, with pointer set to the global variable name
	 * @return the variable associated with the global variable
	 * @throws XMLStreamException if the name of the variable is invalid or the variable value is invalid
	 */
	public Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException;
	/**
	 * Adds default values for any missing global variables to the map of global variables
	 * @param globals the list of all global variables read from the file
	 */
	public void addDefaultGlobals(Map<String,Double> globals);
	/**
	 * 
	 * @param xmlRead the XMLStreamReader, with pointer set to the cell
	 * @return a Cell with the correct state and any relevant attributes
	 * @throws XMLStreamException if invalid cell state exists
	 */
	public Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException;
	/**
	 * 
	 * @param myWriter the XMLStreamWriter, with pointer set to the empty
	 * @param cell the Cell that needs to have its information written to the file
	 * @throws XMLStreamException if cell is null
	 */
	public void writeCell(XMLStreamWriter myWriter, Cell cell) throws XMLStreamException;
}
