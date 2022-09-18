# 2d Array Traversal Project
# Author: Delikan Zorbey Gokyildiz

* Explanation

This is a spring boot implementation of a 2d array traversal operation.

The Traversal logic is to travel the given 2d array from outer edges to the inner values in clockwise rotation, 
and rotating such that only the non-visited values will be visited in each rotation and at the end no value will be left unvisited.

Ex:
     
      → → → → → 
     [1, 2, 3, 4] ↓
      → → → → 
    ↑[5, 6, 7,↓ 8]↓
    ↑[9, 10, 11,↓ 12]↓
          ← ← ←  
    ↑[13, 14, 15, 16]↓
      ← ← ← ← ← ← ←

    
    [1]  -> startNode
    [10] -> endNode





* Application Run

There are 5 test scenarios under TraversalServiceTest class that can be explored and incremented upon. the Test cases are also executed and result visible on console when the checkout project is mvn installed as;

"mvn clean install -U";

