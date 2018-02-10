package cellsociety_team10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;

import javax.xml.stream.XMLStreamException;

import graphVariants.Graph;
import graphVariants.GraphFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import rulesVariants.RulesFactory;
import visualComponents.ControlPanel;
import visualComponents.Sidebar;
import visualComponents.StartPage;
import visualComponents.Visualization;

public class Engine {
	private static final double ANIM_RATE = 2.5;
	private static final int MILLISECOND_DELAY = 500;
	private static final String SIM_FOLDER = "data/simulations/";
	private static final String LANGUAGE = "English";

	private ResourceBundle myResources;
	private Timeline myAnimation;        
	private Graph myGraph;
	private Visualization myVis;
	private Scene myStartScene;
	private Stage myStage;
	private FileChooser myFileChooser;
	private RulesFactory myRulesFactory;
	private GraphFactory myGraphFactory;
	private ControlPanel myControlPanel;
	private Sidebar mySidebar;
	private FileProcessor myFileProcessor;

	public void initializeSimulation(Stage stage) {
		myStage = stage;

		myResources = ResourceBundle.getBundle(LANGUAGE);

		myStartScene = new StartPage(myResources,
				e -> showFileChooser("predator"),
				e -> showFileChooser("segregation"),
				e -> showFileChooser("life"), 
				e -> showFileChooser("fire"),
				e -> setupDIY()).getScene();

		myFileChooser = new FileChooser();
		myFileChooser.setTitle(myResources.getString("FileTitle"));
		
		myRulesFactory = new RulesFactory();
		myGraphFactory = new GraphFactory(myRulesFactory);

		myAnimation = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step());
		myAnimation.getKeyFrames().add(frame);
		
		myControlPanel = new ControlPanel(myAnimation, 
				e -> play(), 
				e -> pause(), 
				e -> end(), 
				e -> next(),
				e -> save());
		myVis = new Visualization(myControlPanel, stage);

		myStage.setScene(myStartScene);
		myStage.setTitle(myResources.getString("Title"));
		myStage.show();
	}

	private void resetAnimation() {
		myAnimation.stop();
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		
		myAnimation.setRate(ANIM_RATE);
	}

	private void step() {
		if (myGraph.isDead()) {
			myAnimation.pause();
			myAnimation.setRate(0);
			myControlPanel.disableButtons();
			return;
		}
		myGraph.buildNextGrid();
		myVis.visualizeGraph(myGraph);
		myStage.setScene(myVis.getScene());
	}

	private void pause() {
		myAnimation.pause();
	}

	private void play() {
		myAnimation.play();
	}

	private void end() {
		myAnimation.stop();
		myStage.setScene(myStartScene);
		myStage.setWidth(Visualization.SCREEN_WIDTH);
		myVis.reset(true);
	}

	private void next() {
		myAnimation.pause();
		step();
	}
	
	private void save() {
		File saved_file = myFileChooser.showSaveDialog(myStage);
		if (saved_file != null) {
			try {
				myFileProcessor.saveGridState(myGraph.getCells(), saved_file);
			} catch (FileNotFoundException e) {
				// clean this up!!
				e.printStackTrace();
			} catch (XMLStreamException ee) {
				ee.printStackTrace();
			}
			
		}
	}

	private void showFileChooser(String directory) {
		String source = SIM_FOLDER + directory;
		myFileChooser.setInitialDirectory(new File(source));
		File f = myFileChooser.showOpenDialog(myStage);
		if (f != null) {
			loadSimulation(f);
		}
	}
	
	private void initFileProcessor(File file) {
		try {
			myFileProcessor = new FileProcessor(file);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid filepath.");
		}
	}

	public void loadSimulation(File file) {
		initFileProcessor(file);
		myGraph = myGraphFactory.createGraph(myFileProcessor);
		
		resetAnimation();
		
		myVis.reset(false);
		
		myVis.amendHeader(createHeaderText(myGraph.getTitle(), myGraph.getAuthor()));
		myVis.visualizeGraph(myGraph);
		myStage.setScene(myVis.getScene());
	}


	private void setupDIY() {
		File file = new File("data/simulations/life/gameoflife2.xml");
		loadSimulation(file);

		mySidebar = new Sidebar(myResources, this, myGraph);
		myVis.addSidebar(mySidebar);
	}

	private String createHeaderText(String title, String author) {
		return String.format("%s %s %s", title, myResources.getString("By"), author);
	}
}
