# Shapes
Basic diagramming program in Java. Done as coursework for Computer Science II. GraphElement.java was given by the professor. Everything else was coded by me. 

What works:

- All buttons function as expected
- Can successfully create a Rectangle Node
- Can successfully create an Ellipse Node
- Can successfully draw and place an Edge Node
- Can successfully highlight/select an nodes by clicking it
- When two nodes overlap, when clicking, the one that is selected is the older object.
- A selected node can be removed from the array by right clicking on it
- A selected node can also be dragged and placed
- Clicking outside the selected node de-selects it and removes the ability to delete or move the node, until it is selected once more
- Can apply a label to Rectangle and Ellipse Nodes
- Can not apply a label to Edge Nodes
- Placed labels move around and are deleted with the nodes they have been placed on.

Issues:

- Small problem with selection. If an object is selected, and the user attempts to select another object without de-selecting the current one, one of two things will happen: If the object attempted to be selected is newer than the object previously selected, things will work as expected. The older object will be de-selected, and the newer one will be selected. However, if the second object to be selected is older than the one previously selected, both are selected. Only one is removed at a time, but if the objects are both selected and overlap, they will merge into one. Realized I could have avoided this by initializing an object for selected variables, but a little too late to actually implement the solution.
- Out of bounds exceptions during runtime that do not affect the program while running. No idea why. Tried to fix it by avoiding going into the end of the array, but that made it worse.

