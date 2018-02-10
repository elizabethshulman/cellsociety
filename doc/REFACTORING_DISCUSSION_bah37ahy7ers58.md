Refactoring Discussion
===

#### Duplication Refactoring
The PreyManager and PredatorManager were very similar (one handled shark movement, one handled fish movement). From these, we were able to create an abstract Manager class that encapulated the shared functionality of the two classes. We chose this route because many of the methods were similar enough to each other that a general manager class would remove most of the duplication.

There was also general refactoring done to the Container classes to move instance variables and some methods shared between those classes up from the subclass implementations to the abstract superclass. This decision was made because the inheritance hierarchy already existed (abstract class with subclass implementations) but the subclasses had additional shared methods and variables that were not accounted for when the subclasses were created.

#### Checklist Refactoring
We previously had an issue where HashMaps and ArrayLists were being returned or required as parameters instead of Maps and Lists. Thus, specific implementations of List and Map were replaced with mentions of the general class itself. This was a somewhat difficult process (the data structure we were using was a HashMap with ArrayList values)

The FileInfoExtractor class was moved to the same package as its subclasses. Its methods were changed from protected to public to allow the FileProcessor to continue accessing its methods. This was not an issue brought up by the SonarCube testing but was one that Andrew wished to resolved to match the package hierarchy used by the rest of the project.

#### General Refactoring

For Code Smells, our only errors had to do with spacing issues (i.e., `Make this line start at column 9.`). We had 329 of those -- not much to say there. For Java Notes, we moved a single line of code into the superclass that was shared amongst classes and got rid of a deprecation warning inside the `calcShapeHeight()` function of SquareContainer. That was a super useful flag for us, as it was inside a method tucked in a file that we might not have caught again.  
