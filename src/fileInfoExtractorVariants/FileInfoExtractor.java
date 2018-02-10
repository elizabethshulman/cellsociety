package fileInfoExtractorVariants;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import cellVariants.Cell;

public abstract class FileInfoExtractor {
	public abstract Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException;
	public abstract Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException;
}
