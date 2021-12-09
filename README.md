# Ex2
Tal tosiano-208846600
Moran shalev-316220938


The purpose of our project:
Building a Directed weighted graph. The project contains 2 algorithms - for finding the shortest route in the graph.
•	Tarjan algorithm which we used to check if the graph is connected or not.
•	A Dijkstra's algorithm uses to find the shortest path in a weighted graph.

Design and implement two main interfaces:
       •	Interface of a weighted directed graph
       •	Interface of algorithms on graphs (weighted tuners).
The process of creating the project:
We built a system that builds a weighted graph by the classes:

• NodeData-represents the set of operations applicable on a  node (vertex) in a (directional)     
 weighted graph.
Every node has data (id-key,location,weight,info,tag) that helps us to representing the graph         in In the most accurate way. 

• EdgeData-represents the set of operations applicable on a directional  edge(src,dest,weight)  in a (directional) weighted graph.

• GeoLocation-represents a geo location(x,y,z).represents a Point3D data in the graph.

• Graph-represents a Directional Weighted Graph with all his elements(Nodes,Edeges)and   including many functions that updates his elements.

• GraphAlgorithms-represents a Directed (positive) Weighted Graph Theory Algorithms and      including many algorithms.
for the tsp function (Travelling salesman problem) we use the "tarjan" algoritem to solve  this problem.
 for the shortestPathDist function we use the dijkstra's algorithm to solve this problem.

