Cell Society README.md
==

#### Ben Hubsch, Andrew Yeung, Elizabeth Shulman

1/25/18 - 2/15/18
Hours worked: 125  



## Roles
#### Ben Hubsch:  
Handled components related to, broadly speaking, visualization

#### Andrew Yeung:  
Responsible for writing and processing XML files

#### Elizabeth Shulman:  
Implemented simulation-specific rules for cell and grid updates  



## Resources Used
* [Open Closed Principle](https://www2.cs.duke.edu/courses/compsci308/spring18/readings/ocp.pdf)
* [Why Java Interfaces Are So Very Cool](https://www2.cs.duke.edu/courses/compsci308/spring18/readings/java_garage_ch17.pdf)
* [Replace Conditional with Polymorphism](https://sourcemaking.com/refactoring/replace-conditional-with-polymorphism)
*  [Commonality Analysis](https://www2.cs.duke.edu/courses/compsci308/spring18/readings/Coplien_Chap2.pdf)
*  [StackOverflow](https://stackoverflow.com)
*  Diane Hu
*  George Bernard
*  Ashka Stephen  


## Using the Program
*  File used to start: Main
*  Known bugs:
    *  Running the program on Mac OS (High Sierra) will generate  a warning that the class FIFinderSyncExtensionHost is implemented in 2 different places. This is tied to JavaFX's FileChooser class and is unavoidable when using a FileChooser. The message itself has no effect on functionality
*  Errors program should handle
    *  XML Errors
        *  Invalid cell states
        *  Invalid global variables (either type or value)
        *  Required global variable(s) not specified
*  XML resource files required by project are stored in data/simulations.
*  Best example data files
    *  gameoflife2.xml
    *  fire3_triangle.xml
    *  predatorprey3.xml
    *  segregation2.xml    


## Design Decisions and Assumptions
#### Within Rules:
*  Rock-Paper-Scissors
    *  When a cell overtakes an empty or dead neighbor, it is assumed that a new cell is created with one less health point than the parent cell. 
*  Foraging Ants
    *  To accomodate non-rectangular cell shapes, the orientation element of this simulation was disregarded. Instead, an ant's next location was selected by prioritizing pheromone levels.
#### Within XML:
*  Random Distributions
    *  All XML files will be for specific states only. There will be a "make custom grid" option that allows for random distributions.   
    

## Impressions of the Assignment
*  We felt this project was adequately-sized as an introduction to teamwork in a programming setting.
*  The only feedback we had was that the second sprint felt considerably more difficult and time-intensive than the first.