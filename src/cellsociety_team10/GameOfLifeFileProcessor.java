package cellsociety_team10;

import java.io.FileNotFoundException;

import javax.xml.stream.XMLStreamException;

public class GameOfLifeFileProcessor extends FileProcessor {

	public GameOfLifeFileProcessor(String fpath) throws FileNotFoundException, XMLStreamException {
		super(fpath);
		myClass = "Game Of Life";
	}

	public static void main(String[] args)
	{
		try {
			FileProcessor fp = new GameOfLifeFileProcessor("/Users/andrew/Documents/workspace/cellsociety_team10/data/gameoflife2.xml");
			fp.readFile();
			Cell[][] g = fp.getGrid();
			for(int a = 0; a < g.length; a++)
			{
				for(int b = 0; b < g[a].length; b++)
					System.out.print(g[a][b].getState());
				System.out.println();
			}
		} catch (FileNotFoundException | XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
