package Tests;

import api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GraphAlgorithmsTest {

    DirectedWeightedGraph graph = new Graph();

    DirectedWeightedGraphAlgorithms graphAlgo = new GraphAlgorithms();
    GraphAlgorithms graphAlgo1 = new GraphAlgorithms();


    void initGraph1() {
        graph = new Graph();
        NodeData a = new Node_Data(1, new Geo_Location(1, 2, 3));
        NodeData a1 = new Node_Data(2, new Geo_Location(1, 2, 3));
        NodeData a2 = new Node_Data(3, new Geo_Location(1, 2, 3));
        NodeData a3 = new Node_Data(4, new Geo_Location(1, 2, 3));
        graph.addNode(a);
        graph.addNode(a1);
        graph.addNode(a2);
        graph.addNode(a3);
        graph.connect(1, 3, 15);
        graph.connect(4, 1, 15);
        graph.connect(1, 2, 15);
        graphAlgo = new GraphAlgorithms();
        graphAlgo.init(graph);
    }

    void initGraph2() {
        graph = new Graph();
        NodeData a = new Node_Data(1, new Geo_Location(1, 2, 3));
        a.setLocation(new Geo_Location(0, 0, 0));
        NodeData a1 = new Node_Data(2, new Geo_Location(1, 2, 3));
        a1.setLocation(new Geo_Location(0, 0, 0));
        NodeData a2 = new Node_Data(3, new Geo_Location(1, 2, 3));
        a2.setLocation(new Geo_Location(0, 0, 0));

        graph.addNode(a);
        graph.addNode(a1);
        graph.addNode(a2);

        graph.connect(1, 2, 1);
        graph.connect(2, 3, 1.5);
        graph.connect(1, 3, 3);
        graphAlgo = new GraphAlgorithms();
        graphAlgo.init(graph);
    }

    void initGraph3() {
        graph = new Graph();
        NodeData a = new Node_Data(1, new Geo_Location(1, 2, 3));
        a.setLocation(new Geo_Location(0, 0, 0));
        NodeData a1 = new Node_Data(2, new Geo_Location(1, 2, 3));
        a1.setLocation(new Geo_Location(0, 0, 0));
        NodeData a2 = new Node_Data(3, new Geo_Location(1, 2, 3));
        a2.setLocation(new Geo_Location(0, 0, 0));

        graph.addNode(a);
        graph.addNode(a1);
        graph.addNode(a2);

        graph.connect(1, 2, 1);
        graph.connect(2, 3, 1.5);
        graph.connect(1, 3, 3);
        graph.connect(3, 1, 1);
        graphAlgo = new GraphAlgorithms();
        graphAlgo.init(graph);
    }

    @Test
    void getGraph() {
        initGraph1();
        graphAlgo.init(graph);
        DirectedWeightedGraph g = graphAlgo.getGraph();

        Assertions.assertEquals(g.nodeSize(), graph.nodeSize());
        Assertions.assertEquals(g.edgeSize(), graph.edgeSize());
        Assertions.assertEquals(g.getMC(), graph.getMC());
    }

    @Test
    void copy() {//not good
        initGraph2();
        DirectedWeightedGraph graph2 = graphAlgo.copy();
        System.out.println(graph2.getMC());
        Assertions.assertEquals(graphAlgo.getGraph().getMC(), graph2.getMC());

    }


    @Test
    void shortestPathDist() {
        initGraph2();
        double w = graphAlgo.shortestPathDist(1, 3);
        Assertions.assertEquals(w, 2.5);
    }

    @Test
    void shortestPath() {
        initGraph2();
        List<NodeData> list = graphAlgo.shortestPath(1, 3);
        List<NodeData> listEqual = new ArrayList<NodeData>();
        listEqual.add(graph.getNode(1));
        listEqual.add(graph.getNode(2));
        listEqual.add(graph.getNode(3));

        Assertions.assertEquals(list, listEqual);

        graphAlgo.init(graph);
        graphAlgo.load("data/G1.json");

        assertEquals(11, graphAlgo.shortestPath(1, 8).size());
    }

    @Test
    void save() {
        initGraph2();
        graphAlgo.save("data/MyGraphTest.json");


        DirectedWeightedGraph graphEqual = graphAlgo.getGraph();

        graphAlgo.load("data/MyGraphTest.json");

        Iterator<NodeData> it1 = graphEqual.nodeIter();
        Iterator<NodeData> it2 = graphAlgo.getGraph().nodeIter();
        while (it1.hasNext() && it2.hasNext()) {
            NodeData node1 = it1.next();
            NodeData node2 = it2.next();
            Assertions.assertEquals(node1.getKey(), node2.getKey());
        }
    }

    @Test
    void load() {
        graphAlgo = new GraphAlgorithms();
        graphAlgo.load("data/MyG4.json");
        //graphAlgo.load("data/LargeConnectedGraphs/1000Nodes.json");

        double w = graphAlgo.shortestPathDist(1, 3);
        Assertions.assertEquals(w, 2.5);

        DirectedWeightedGraph graphEqual = graphAlgo.getGraph();
        initGraph2();
        Iterator<NodeData> it1 = graphEqual.nodeIter();
        Iterator<NodeData> it2 = graph.nodeIter();
        while (it1.hasNext() && it2.hasNext()) {
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

        //     graphAlgo.load("data/LargeConnectedGraphs/100000.json");
        assertTrue(graphAlgo.isConnected());


    }

    @Test
    void center() {
        graphAlgo.init(graph);

        graphAlgo.load("data/G1.json");
        System.out.println(graphAlgo.center().getKey());

        graphAlgo.load("data/G2.json");
        System.out.println(graphAlgo.center().getKey());

        graphAlgo.load("data/G3.json");
        System.out.println(graphAlgo.center().getKey());
    }

    @Test
    void tsp() {
        graphAlgo = new GraphAlgorithms();
        graphAlgo.load("data/G1.json");
        graphAlgo.save("test.json");
        List<NodeData> list = new LinkedList<NodeData>();
        list.add(graphAlgo.getGraph().getNode(0));
        list.add(graphAlgo.getGraph().getNode(16));
        Assertions.assertEquals(2, graphAlgo.tsp(list).size());
        list.removeAll(list);
        Assertions.assertTrue(list.isEmpty());
        list.add(graphAlgo.getGraph().getNode(1));
        list.add(graphAlgo.getGraph().getNode(3));
        list.add(graphAlgo.getGraph().getNode(2));
        list.add(graphAlgo.getGraph().getNode(6));
        Assertions.assertEquals(3, graphAlgo.tsp(list).size());
        graphAlgo.init(graph);

    }
}