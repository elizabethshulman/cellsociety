Refactoring Discussion
===

#### Duplication Refactoring
The PreyManager and PredatorManager were very similar (one handled shark movement, one handled fish movement). From these, we were able to create an abstract Manager class that encapulated the shared functionality of the two classes. We chose this route because many of the methods were similar enough to each other that a general manager class would remove most of the duplication.

There was also general refactoring done to the Container classes to move instance variables and some methods shared between those classes up from the subclass implementations to the abstract superclass. This decision was made because the inheritance hierarchy already existed (abstract class with subclass implementations) but the subclasses had additional shared methods and variables that were not accounted for when the subclasses were created.

#### Checklist Refactoring
We previously had an issue where HashMaps and ArrayLists were being returned or required as parameters instead of Maps and Lists. Thus, specific implementations of List and Map were replaced with mentions of the general class itself. This was a somewhat difficult process (the data structure we were using was a HashMap with ArrayList values)

The FileInfoExtractor class was moved to the same package as its subclasses. Its methods were changed from protected to public to allow the FileProcessor to continue accessing its methods. This was not an issue brought up by the SonarCube testing but was one that Andrew wished to resolved to match the package hierarchy used by the rest of the project.

#### General Refactoring

Most of the warnings in Code Smells were indentation issues that have been ignored. There is one instance of nested loops that was fixed; the other instance of nested loops was deemed unavoidable. For Java Notes, most of the warnings were requests for package info documentation that were ignored. There are a couple of requests to rethrow/log errors that are found via try/catch but those are handled by throwing new errors instead; these errors were also ignored.