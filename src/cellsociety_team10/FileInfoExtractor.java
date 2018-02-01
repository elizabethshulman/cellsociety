package cellsociety_team10;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public abstract class FileInfoExtractor {
	public abstract Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException;
	public abstract Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException;
}
