Inheritance Review - Backend
===
sas118, ers58, tn74, gc124

## Part 1
* Our design is hiding the graph/grid transitions from the rest of the project.
* We intend to build abstract cell, rules and simulation classes. 
    * The rules class will take care of interaction between cells
    * The simulation class will take care of simulation-specific movement and updating the grid according to all cell's preferences.
    * The cells themselves will take care of their own "act" methods - their special action after interacting with their neighbors
* One thing we are trying to keep private in our code is the specifications of each cell. The only other part of the program that has to access the display instance variables of the cell is the GUI in order to display it appropriately.
* We might throw errors if a cell is told to interact with a type of cell that is not defined in the game, or if  a cell tries to move to a place that is not defined on the grid.
* We think our design is "good" because it splits up the separate parts of the simulation and makes it flexible if rules, cells, or simulations need to be changed. 

## Part 2
* Our area is closely linked to the file processing. As the file is read in, the program needs to store different simulation-specific values (probability a cell will catch fire, etc), and these values are necessary for updating the graph.
* Our area is also connected to the front end, as the visualization needs to be able to read in a cell's state and position.
* To minimize these dependencies, we intend to take in these values in the Interact/Rules constructors and store them, then keep all the graph update methods private and self-contained. Ideally, we should be able to take in a graph and return one that has been fully updated at each step.
* One pair of classes we have is the Interact and Rules classes. These are incredibly similar -- they take in values specific to the simulation, and apply them to our graph/grid.
* Where our classes differ is in the basic graph structure -- one team is using a 2D array, while another is using a HashMap. That said, our method functions are essentially the same.

## Part 3

* 5 use cases: 
    * To manage each frame shift. (From one state to the next).  
    * To manage the interaction from one cell to another.  (talk to the other cells)
    * To handle how one cell responds to interaction with other cells
    * To change a cell's state
    * To change a cell's location
* I am most excited about integrating the front end, back end, and XML reading, and being able to see our simulations put together
* I am most worried about implementing the rules for the predator-prey simulation
* I think my design is good because it is easily extendable, even beyond rectangular arrangements of cells