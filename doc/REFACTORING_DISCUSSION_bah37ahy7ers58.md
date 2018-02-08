Refactoring Discussion
===

#### Duplication Refactoring

#### Checklist Refactoring

#### General Refactoring
For Code Smells, our only errors had to do with spacing issues (i.e., `Make this line start at column 9.`). We had 329 of those -- not much to say there. For Java Notes, we moved a single line of code into the superclass that was shared amongst classes and got rid of a deprecation warning inside the `calcShapeHeight()` function of SquareContainer. That was a super useful flag for us, as it was inside a method tucked in a file that we might not have caught again.  