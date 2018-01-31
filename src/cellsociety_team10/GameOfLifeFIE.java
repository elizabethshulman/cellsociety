package cellsociety_team10;

import javax.xml.stream.XMLStreamReader;

public class GameOfLifeFIE extends FileInfoExtractor {

	@Override
	public void getGlobalVars(XMLStreamReader xmlRead) {
		// TODO Auto-generated method stub

	}

	@Override
	public Cell getCell(XMLStreamReader xmlRead) {
		return new Cell(Integer.parseInt(xmlRead.getAttributeValue(0)));
	}

}
