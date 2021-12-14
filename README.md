# OOP_Ex2

## Authors
**Tal tosiano-208846600**  
**Moran shalev-316220938**


## The purpose of our project
Building a Directed weighted graph. 
The project contains 2 algorithms - for finding the shortest route in the graph.

+ Tarjan algorithm which we used to check if the graph is connected or not.

+ A Dijkstra's algorithm uses to find the shortest path in a weighted graph.

### Design and implement two main interfaces:

+ Interface of a weighted directed graph

+ Interface of algorithms on graphs (weighted tuners).
        
## The process of creating the project:
We built a system that builds a weighted graph by the classes:

### NodeData
represents the set of operations applicable on a  node (vertex) in a (directional)     
 weighted graph.
Every node has data (id-key,location,weight,info,tag) that helps us to representing the graph         in In the most accurate way. 

### EdgeData
represents the set of operations applicable on a directional  edge(src,dest,weight)  in a (directional) weighted graph.

### GeoLocation
represents a geo location(x,y,z).represents a Point3D data in the graph.

### Graph
represents a Directional Weighted Graph with all his elements(Nodes,Edeges)and   including many functions that updates his elements.

### GraphAlgorithms
represents a Directed (positive) Weighted Graph Theory Algorithms and including many algorithms.
for the tsp function (Travelling salesman problem) we use the "tarjan" algoritem to solve  this problem.
for the shortestPathDist function we use the dijkstra's algorithm to solve this problem.

### Gui
A visual presentation of the graph and the actions we can perform on it.

## our GUI screenshot:
example of loading G1.json
![ourGui](https://user-images.githubusercontent.com/94299489/145985384-7603cae4-34b8-4a8e-a96a-f7f5e08b30d5.jpeg)

### How to load & run the gui
We load the graph with the "load" button. There are a variety of actions we can perform on the graph, each button performs another action. for example: if we click on the "addNode" button, a vertex will be added to the graph. If we click on the "theShortestDist" buttoמ we will see on the screen the answer to the shortest way from node to another.

## Performance analysis of our algorithms:

|   |  1000 | 10000  | 100000  |
| ------------ | ------------ | ------------ | ------------ |
|  isConnected |  72 ms |  265 |  3 sec 745 ms |
|  shortestpath |  99 ms |  287 ms |  5 sec 443 ms |
| load |   94 ms |  289 ms | 5 sec 538 ms  |
| save |   27 ms |  34 ms | 43 ms  |
| tsp |   100 ms |  285 ms | 5 sec 426 ms  |



## our uml diagram:

![UML_Ex2](https://user-images.githubusercontent.com/94299489/146005679-d56cd273-0a1a-46e8-bc21-796432eee292.jpeg)
