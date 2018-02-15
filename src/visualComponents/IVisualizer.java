package visualComponents;

import cellsociety_team10.Graph;

/**
 * @author benhubsch
 * 
 * This interface defines the API for any classes that would like to implement
 * some kind of visualization for cellular automata. It makes explicit the methods 
 * that the class exposes, while leaving substantial room for design freedom on 
 * the implementation side. These are the basic methods that a UI class must implement.
 *
 */
public interface Visualizer {
	/**
	 * This method is called after every iteration of the animation in order to perform
	 * the required steps necessary for displaying one sequence of the animation on the
	 * screen.
	 * 
	 * @param g This Graph object (and the API that it exposes) can be used in any way
	 * to seed the UI with information about the current state of the simulation.
	 */
	void visualize(Graph g);
	
	/**
	 * This method updates the state of the visualization based on the latest calcula-
	 * tions on an iteration to iteration basis. It is a very useful method to call
	 * particularly when the user makes changes that have no effect on the animation
	 * or iteration count, like in DIY mode.
	 * 
	 * @param g This Graph object (and the API that it exposes) can be used in any way
	 * to build the desired updated image.
	 */
	void updateGraph(Graph g);
	
	/**
	 * This method is called to reset the Visualizer once a simulation has terminated. 
	 * 
	 * @param backToStart This boolean specifies whether the user is returning back to
	 * the StartPage or is simply changing a setting in DIY mode.
	 */
	void reset(boolean backToStart);
	
	/**
	 * This method is called when a user enters DIY mode in order to add the Sidebar
	 * object to the Visualizer, which will allow a user to dynamically arrange the
	 * simulation's settings.
	 * 
	 * @param sidebar This is the Sidebar object.
	 */
	void addSidebar(Sidebar sidebar);
	
	/**
	 * This method is called when a new simulation is loaded and accurately sets the
	 * text on-screen containing information about the author and the title of the 
	 * running simulation. 
	 * 
	 * @param name This is the String containing the author and title of the current
	 * simulation.
	 */
	void changeName(String name);
}
