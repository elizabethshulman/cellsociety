package cellsociety_team10;

public abstract class FileProcessor {
	private String filepath;
	private String author;
	private String title;
	private Cell[][] grid;
	public FileProcessor(){
		
	}
	public String getFilepath() {
		return filepath;
	}
	public String getAuthor() {
		return author;
	}
	public String getTitle() {
		return title;
	}
	public Cell[][] getGrid()
	{
		return grid;
	}
	public abstract void readFile(String file);

}
