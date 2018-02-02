package cellsociety_team10;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public abstract class FileInfoExtractor {
	protected abstract Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException;
	protected abstract Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException;
}
