package cellsociety_team10;

import java.io.File;
import java.util.HashMap;
import java.util.ResourceBundle;

import graphVariants.Graph;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import rulesVariants.Rules;
import visualComponents.ControlPanel;
import visualComponents.RulesFactory;
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
	private ControlPanel myControlPanel;
	private Sidebar mySidebar;

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

		myAnimation = new Timeline();
		myControlPanel = new ControlPanel(myAnimation, 
				e -> play(), 
				e -> pause(), 
				e -> end(), 
				e -> next());

		myStage.setScene(myStartScene);
		myStage.setTitle(myResources.getString("Title"));
		myStage.show();
	}

	private void setupAnimation() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step());
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		myAnimation.getKeyFrames().add(frame);
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
		resetEngine();
		myStage.setScene(myStartScene);
		myStage.setWidth(Visualization.SCREEN_WIDTH);
	}

	private void next() {
		myAnimation.pause();
		step();
	}

	private void showFileChooser(String directory) {
		String source = SIM_FOLDER + directory;
		myFileChooser.setInitialDirectory(new File(source));
		File f = myFileChooser.showOpenDialog(myStage);
		if (f != null) {
			handleChosenFile(f);
		}
	}

	private void resetEngine() {
		myAnimation.setRate(ANIM_RATE);
		myAnimation.stop();

		myVis.reset();
	}

	private void handleChosenFile(File filename) {
		FileProcessor fp;
		try {
			fp = new FileProcessor(filename.getAbsolutePath());
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid filepath.");
		}

		Rules curr_rules = myRulesFactory.createRules(fp.getType(), fp.getGlobalVars());
		myGraph = new Graph(curr_rules, fp);

		myVis = new Visualization(myControlPanel);
		setupAnimation();

		resetEngine();

		myVis.amendHeader(createHeaderText(fp.getTitle(), fp.getAuthor()));
		myVis.visualizeGraph(myGraph);
		myStage.setScene(myVis.getScene());
	}

	public void changeSimulation(String simulation) {
		HashMap<String, String> sim_map = new HashMap<>();
		String default_dir = "data/simulations/default/";

		sim_map.put(myResources.getString("LifeButton"), default_dir + "life/life_square.xml");
		sim_map.put(myResources.getString("FireButton"), default_dir + "fire/fire_square.xml");
		sim_map.put(myResources.getString("PredButton"), default_dir + "predator/predatorprey_square.xml");
		sim_map.put(myResources.getString("SegButton"), default_dir + "segregation/segregation_square.xml");

		FileProcessor fp;
		try {
			fp = new FileProcessor(new File(sim_map.get(simulation)).getAbsolutePath());
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid filepath.");
		}
		Rules curr_rules = myRulesFactory.createRules(fp.getType(), fp.getGlobalVars());
		myGraph = new Graph(curr_rules, fp);

		setupAnimation();

		resetEngine();
		
		myVis.visualizeGraph(myGraph);
		myStage.setScene(myVis.getScene());
	}

	private void setupDIY() {
		FileProcessor fp;
		try {
			File filename = new File("data/simulations/default/life/life_square.xml");
			fp = new FileProcessor(filename.getAbsolutePath());
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid filepath.");
		}

		Rules curr_rules = myRulesFactory.createRules(fp.getType(), fp.getGlobalVars());
		myGraph = new Graph(curr_rules, fp);

		myVis = new Visualization(myControlPanel, myStage);
		setupAnimation();

		resetEngine();

		mySidebar = new Sidebar(myResources, this);
		myVis.addSidebar(mySidebar);

		myVis.amendHeader(createHeaderText(myResources.getString("Greatest"), myResources.getString("You")));
		myVis.visualizeGraph(myGraph);
		myStage.setScene(myVis.getScene());
	}

	private String createHeaderText(String title, String author) {
		return String.format("%s %s %s", title, myResources.getString("By"), author);
	}
}
