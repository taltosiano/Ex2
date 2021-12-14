package Tests;

import api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GraphAlgorithmsTest {
//    GraphAlgorithms graphAlgorithmsG0=new GraphAlgorithms("data/G0.json");
//    GraphAlgorithms graphAlgorithmsG1=new GraphAlgorithms("data/G1.json");
//   GraphAlgorithms graphAlgorithmsG2=new GraphAlgorithms("data/G2.json");
//   GraphAlgorithms graphAlgorithmsG3=new GraphAlgorithms("data/G3.json");

    DirectedWeightedGraph graph = new Graph() ;

    GraphAlgorithms graphAlgo = new GraphAlgorithms();
    GraphAlgorithms graphAlgo1 = new GraphAlgorithms();



    void initGraph1() {
        graph = new Graph();
        NodeData a = new Node_Data(1,new Geo_Location(1,2,3));
        NodeData a1 = new Node_Data(2,new Geo_Location(1,2,3));
        NodeData a2 = new Node_Data(3,new Geo_Location(1,2,3));
        NodeData a3 = new Node_Data(4,new Geo_Location(1,2,3));
        graph.addNode(a);
        graph.addNode(a1);
        graph.addNode(a2);
        graph.addNode(a3);
        graph.connect(1,3,15);
        graph.connect(4,1,15);
        graph.connect(1,2,15);
        graphAlgo = new GraphAlgorithms();
        graphAlgo.init(graph);
    }
    void initGraph2() {
        graph = new Graph();
        NodeData a = new Node_Data(1,new Geo_Location(1,2,3));
        a.setLocation(new Geo_Location(0,0,0));
        NodeData a1 = new Node_Data(2,new Geo_Location(1,2,3));
        a1.setLocation(new Geo_Location(0,0,0));
        NodeData a2 = new Node_Data(3,new Geo_Location(1,2,3));
        a2.setLocation(new Geo_Location(0,0,0));

        graph.addNode(a);
        graph.addNode(a1);
        graph.addNode(a2);

        graph.connect(1,2,1);
        graph.connect(2,3,1.5);
        graph.connect(1,3,3);
        graphAlgo = new GraphAlgorithms();
        graphAlgo.init(graph);
    }

    void initGraph3() {
        graph = new Graph();
        NodeData a = new Node_Data(1,new Geo_Location(1,2,3));
        a.setLocation(new Geo_Location(0,0,0));
        NodeData a1 = new Node_Data(2,new Geo_Location(1,2,3));
        a1.setLocation(new Geo_Location(0,0,0));
        NodeData a2 = new Node_Data(3,new Geo_Location(1,2,3));
        a2.setLocation(new Geo_Location(0,0,0));

        graph.addNode(a);
        graph.addNode(a1);
        graph.addNode(a2);

        graph.connect(1,2,1);
        graph.connect(2,3,1.5);
        graph.connect(1,3,3);
        graph.connect(3,1,1);
        graphAlgo = new GraphAlgorithms();
        graphAlgo.init(graph);
    }
    @Test
    void getGraph() {
        initGraph1();
        graphAlgo.init(graph);
        DirectedWeightedGraph g = graphAlgo.getGraph();

        Assertions.assertEquals(g.nodeSize(),graph.nodeSize());
        Assertions.assertEquals(g.edgeSize(),graph.edgeSize());
        Assertions.assertEquals(g.getMC(),graph.getMC());
    }

    @Test
    void copy() {//not good
        initGraph2();
        DirectedWeightedGraph graph2 =  graphAlgo.copy();
        System.out.println(graph2.getMC());
        Assertions.assertEquals(graphAlgo.getGraph().getMC(),graph2.getMC());

    }


    @Test
    void shortestPathDist() {
        initGraph2();
        double w = graphAlgo.shortestPathDist(1,3);
        Assertions.assertEquals(w,2.5);
    }

    @Test
    void shortestPath() {
        initGraph2();
        List<NodeData> list = graphAlgo.shortestPath(1,3);
        List<NodeData> listEqual = new ArrayList<NodeData>();
        listEqual.add(graph.getNode(1));
        listEqual.add(graph.getNode(2));
        listEqual.add(graph.getNode(3));

        Assertions.assertEquals(list,listEqual);

        graphAlgo.init(graph);
        graphAlgo.load("data/G1.json");
      assertEquals(11, graphAlgo.shortestPath(1, 8).size());
    }
    @Test
    void save() {
        initGraph2();
        graphAlgo.save("data/MyGraphTest.json");

        DirectedWeightedGraph  graphEqual = graphAlgo.getGraph();

        graphAlgo.load("data/MyGraphTest.json");
        Iterator<NodeData> it1 = graphEqual.nodeIter();
        Iterator<NodeData> it2 = graphAlgo.getGraph().nodeIter();
        while (it1.hasNext()&&it2.hasNext()) {
            NodeData node1 = it1.next();
            NodeData node2 = it2.next();
            Assertions.assertEquals(node1.getKey(), node2.getKey());
        }
    }

    @Test
    void load() {
        graphAlgo = new GraphAlgorithms();
        graphAlgo.load("data/MyG4.json");

        double w = graphAlgo.shortestPathDist(1,3);
        Assertions.assertEquals(w,2.5);

        DirectedWeightedGraph graphEqual = graphAlgo.getGraph();
        initGraph2();
        Iterator<NodeData> it1 = graphEqual.nodeIter();
        Iterator<NodeData> it2 = graph.nodeIter();
        while (it1.hasNext()&&it2.hasNext()) {
            NodeData node1 = it1.next();
            NodeData node2 = it2.next();
            Assertions.assertEquals(node1.getKey(), node2.getKey());
        }
    }
    @Test
    void isConnected() {
            initGraph2();
            Assertions.assertFalse(graphAlgo.isConnected());
            initGraph3();
            Assertions.assertTrue(graphAlgo.isConnected());

        graphAlgo.init(graph);
        graphAlgo.load("data/G1.json");
        assertTrue(graphAlgo.isConnected());
    }

    @Test
    void center() {
        initGraph3();
        Assertions.assertEquals(graphAlgo.center().getKey(),3);
//        DirectedWeightedGraphAlgorithms algo = new GraphAlgorithms();
//        algo.load("data/LargeConnectedGraphs/1000Nodes.json");
//        DirectedWeightedGraph temp = algo.getGraph();
//        algo.save("test.json");
//        System.out.println(algo.center());

    }

    @Test
    void tsp() {
//        graphAlgo = new GraphAlgorithms();
//        graphAlgo.load("data/G1.json");
//        graphAlgo.save("test.json");
//        List<NodeData> list = new LinkedList<NodeData>();
//        list.add(graphAlgo.getGraph().getNode(0));
//        list.add(graphAlgo.getGraph().getNode(16));
//        Assertions.assertEquals(2,graphAlgo.tsp(list).size());
//        list.removeAll(list);
//        Assertions.assertTrue(list.isEmpty());
//        list.add(graphAlgo.getGraph().getNode(1));
//        list.add(graphAlgo.getGraph().getNode(3));
//        list.add(graphAlgo.getGraph().getNode(2));
//        list.add(graphAlgo.getGraph().getNode(6));
//        Assertions.assertEquals(3,graphAlgo.tsp(list).size());
        graphAlgo.init(graph);
        graphAlgo.load("data/G1.json");
        List < NodeData> tspCities=new ArrayList<>();
        tspCities.add(graphAlgo.getGraph().getNode(0));
        tspCities.add(graphAlgo.getGraph().getNode(10));
        List <NodeData> tspGraph1=graphAlgo.tsp(tspCities);
        int [] tspArray=new int[tspGraph1.size()];
        for(int i=0;i<tspArray.length;i++){
            tspArray[i]=tspGraph1.get(i).getKey();
        }
        int [] expectedArray={0,16,15,14,13,12,11,10,11,12,13,14,15,16,0};
        //Assertion.(tspArray,expectedArray);
    }



}