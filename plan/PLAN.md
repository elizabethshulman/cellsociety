Cell Society Design
===
### Introduction
Our objective with writing this program is to run any cellular automata simulation on a 2D grid.  

We would like to design our application to be flexible, incorporating user input for individualized design specifications as extensions of rules.  

We intend to leave closed the underlying structural components of our simulation engine, while allowing for open implementation changes of rule sets dependent on each simulation variant.  

---

### Overview

At our highest level, we intend to have an Engine class that manages initializing the simulation, the Timeline component, and the visualization.  

In our Visualization class, we intend to set the simulation stage with new scenes, built using a ControlPanel object and the current grid.

With our ControlPanel class, we intend to streamline the creation and functionality of the buttons on display during our simulation. 

The Grid class will contain the 2D array of Cells, an instance of the Rules class, and grid management methods for generating an updated 2D array.  

Our FileProcessor will be abstract; simulation type-specific extensions will read in an XML file and store key variables relevant to each simulation type.

Our Rules class is also abstract, but its instances will implement all of the simulation-specific logic, with conditions for changing cell state and updating the grid using the information stored in its corresponding FileProcessor extension. Methods within this class will be accessed via the Grid class. The Grid class will be instantiated with an instance of the Rules class, upholding the Open-Close principle because it allows us to extend the simulation by injecting a different instance of the Rules class into the constructor of Grid, without altering the overall functionality of the simulation.  

Each implementation will contain a Cell class, which holds information about a given cell's current position and state. We intend for this class to be abstract; extensions of it will specify specific values for cell state possibilities.  

The reason we decided to build class structure this way is because we see each of these classes as their own independent logical components. This also allows us the flexibility to build different Rules, FileProcessor, and Cell classes for new simulation types within a closed-to-modification simulation engine.


#### User Interface

We intend to include a visualization of our grid above four buttons spaced evenly across the bottom of the screen. These buttons are as follows:

* Start: This initiates the simulation and begins running the animation.
* Stop: This ends the simulation and resets the animation to its intial state.
* Pause: This temporarily stops the animation, allowing the user to examine a specific grid iteration.
* Next: This allows user to advance the animation by one iteration, updating the grid and pausing the simulation.

Depending on the state of our simulation, some of these buttons may be disabled (e.g. when simulation is active, the step button is disabled).

---
### Design Details

#### Engine

Within the Engine class, we intend to store an instance of Timeline; the 2D array containing the values needed to visualize our grid; an instance of Visualization, used to represent the grid values; and an iteration count, the number of times the grid has been updated.  

The methods required will include:
* Step, which is called consistently and is responsible for managing the grid and illustrating it with the Visualization instance.
* Stop: This ends the simulation and resets the animation to its intial state.
* Pause: This temporarily stops the animation, allowing the user to examine a specific grid iteration.
* Start: This resets, initializes, and begins the simulation.
* Select simulation: This function handles the logic for loading and initializing the proper simulation.

#### Visualization

In the Visualization class, we intend to store an instance of the stage. We intend to build scenes using the data passed in from our 2D grid array in combination with a ControlPanel class that contains user interface information.  

The methods required will include:
* Draw grid: Builds a new scene based on a grid argument containing an array of cell state values.
* Set buttons: Using the instance of ControlPanel, sets functional user interface buttons on the scene.

#### ControlPanel

Our intent with ControlPanel is to modularize our application. We plan to contain button construction within an instance of ControlPanel to avoid having individual button components loose in our Engine class.

#### Grid

In its constructor, Grid takes as an argument a gridInstantiator and an implementation-specific subclass of Rules. From these, Grid builds the 2D array that will ultimately be stored as an instance variable.  

The methods required will include:
* Build next grid: Modifies the current grid based on the current simulation's Rules instance.

#### FileProcessor

This class will be abstract; simulation-specific extensions will take in a file, from which they will:
* Read in and store the values necessary for their specific simulation types
* Build an initial 2D array based on the specific simulation type
* Store information about the author and title.

We intend to include the following methods:
* getAuthor: A getter for author information
* getGrid: A getter for the 2D array
* instantiateGrid: Build an initial 2D array based on the simulation-specific requirements read in the file.
* Implementation-specific getter methods 

#### Rules

We see Rules as solely a service class, storing no data. In Rules, we intend to have one primary method that, when given a grid, returns a grid with updated cells, per the implementation-specific conditions.

The only method we anticipate having is applyGridRules, which will build and return an updated array.

#### Cell

We see Cell as primarily a data class, storing a cell's position, possible cell states, and the color values corresponding to those states.

For methods, we only intend to have getters, to return cell values required for grid updates and visualization.

---

### Use Cases

#### Middle Cell Rules

We handle this within a subclass of Rules we're calling GameOfLifeRules. This instance has a method on it that gets called from within our buildNextGrid method, that applies the given rules to our current grid. This implementation will traverse every cell surrounding a given cell, provided that cell is "in bounds", i.e. its row is not less than 0 or greater than maximum row and its column is not less than 0 or greater than its maximum column.

#### Edge Cell Rules

The cell on the edge isn't a major issue for us, as we check that every neighbor is in bounds before attempting to access the cell or its contents. This is addressed in the previous section.

#### Next Generation

Within our step function in our Engine class, which gets called consistently, we make a call to buildNextGrid in our Grid instance which returns an updated grid. This new grid gets passed into drawGrid in our visualization class, which deplays the result graphically.

#### Simulation Parameter

This will be handled by the FileProcessor subclass that is specific to the Spreading of Fire simulation. One of the lines in the file will contain that value.

#### Switch Simulations

This will be handled in the ControlPanel class. The button will have a set on action method that resets the game and re-reads the appropriate file.

---

### Design Considerations

There was discussion over whether to make grid instantiation a method within our Engine class or to create a separate class for it. As we read through the specifications for each implementation’s XML file, we found other reasons to justify having a FileProcessor class — namely, reading in, storing, and outputting additional simulation information (e.g. the author’s name), as well as reading the specified grid dimensions and building an initial grid.

There was a brief time in which we struggled to justify our Visualization class, primarily as we went back and forth on where we should implement the visualization aspects of the simulations. We needed to figure out how we were going to control the flow of information, wanting it to only flow downward (as opposed to having to pass our instance of the Engine class down to Visualization in order to handle button clicks properly). Ultimately we settled this by deciding on a ControlPanel class that would get passed into Visualization and include the necessary UI components.

---

### Team Responsibilities

* Andrew: In charge of the file reading capabilities and file formatting, e.g. all the FileProcessor classes and any relevant code in other classes. Backup: Elizabeth

* Ben: In charge of visualization of the grid and the user interface, e.g. Visualization class and ControlPanel. Backup: Andrew

* Elizabeth: In charge of implementing the rules of each simulation, e.g. all Rules and Grid classes. Backup: Ben