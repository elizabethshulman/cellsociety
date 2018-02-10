package fileInfoExtractorVariants;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import cellVariants.Cell;

public interface FileInfoExtractor {
	public Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException;
	public Cell getCell(XMLStreamReader xmlRead, String shape) throws XMLStreamException;
	public void writeCell(XMLStreamWriter myWriter, Cell cell) throws XMLStreamException;
}
