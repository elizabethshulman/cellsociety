package fileInfoExtractorVariants;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import cellVariants.Cell;

public interface FileInfoExtractor {
	public Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException;
	public Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException;
	public String getEncoding(int state);
}
