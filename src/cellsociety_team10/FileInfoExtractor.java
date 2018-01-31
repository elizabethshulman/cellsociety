package cellsociety_team10;

import javax.xml.stream.XMLStreamReader;

public abstract class FileInfoExtractor {
	public abstract void getGlobalVars(XMLStreamReader xmlRead);
	public abstract Cell getCell(XMLStreamReader xmlRead);
}
