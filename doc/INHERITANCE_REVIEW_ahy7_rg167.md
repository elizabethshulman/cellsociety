Inheritance Review
===

## Part 1
1. There is a single FileProcessor class that contains an instance of the FileInfoExtractor class. Each type of simulation will necessitate its own FileInfoExtractor that can take in specific global variables and cell states.
2. I am planning to build an abstract class FileInfoExtractor that can read global variables and cells from the file. A subclass will be used for each implementation because global variables and cell states can differ between programs.
3. I am trying to make the general structure of the file closed to modification but leave the cell states and global variables open to extension.
4. Exceptions can occur when the format is incorrectly formatted, cells have invalid states, or the file specifies a global variable that shouldn't exist. This will be handled by throwing an XMLStreamException that will detail what is wrong with the file.
5. I believe that this design is good because it makes the general structure of the data files fairly rigid but allows the user to implement their own simulations with multiple-state cells and/or multiple global variables.

Comments:

* Some of the exceptions that are thrown could probably just cause messages to appear instead. Throwing an exception seems like a rather drastic thing to do for a simple file misformatting.
* The FileProcessor does not account for global variables that don't correspond to numbers or additional information about the simulation.
## Part 2
1. The FileInfoExtractor creates an instance of the specific Cell subclass required for the simulation; the FileProcessor itself is created and stored by the Rules classes.
2. Both dependencies are based on the class behavior.
3. I don't think there are any ways to reduce these 2 dependencies.
4. The general file-reading logic is the same across all the classes; the two specific methods that I have left up for user extension are simulation-specific. In the plan, I intended for there to be multiple FileProcessor types but as I coded I realized that most of the processing code was basically the same.

Comments:

* The dependencies seem necessary. However, it is theoretically possible for a FileProcessor to be tied to a Rules subclass that doesn't match...
* The global variable fetching logic is similar enough that some of that code could remain in the FileProcessor class.
## Part 3
1. Use cases below:
    1. Read multiple global variables
    2. Create cells with multiple attributes
    3. Handle file misformatting
    4. Handle wrong file type even if file is correctly formatting
    5. Read in files for nonrectangular grids

2. Design problem that I'm most excited about: Given the file, return a HashMap of each cell and all its neighbors.
3. Design problem that I am worried about: For the Cell HashMap, keeping track of all the positions of the cells without having specific position attributes assigned to each cell.

Comments: None