package Tests;

import api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GraphTest {
    DirectedWeightedGraph graph1 = new Graph();
    NodeData a = new Node_Data(1,new Geo_Location(1,2,3));
    NodeData a1 = new Node_Data(2,new Geo_Location(1,2,3));
    NodeData a2 = new Node_Data(3,new Geo_Location(1,2,3));
    NodeData a3 = new Node_Data(4,new Geo_Location(1,2,3));

    @Test
    void getNode() {
        graph1.addNode(a);
        graph1.addNode(a1);
        String node_a="Node_Data{_tag=0, _key=1, _weight=0.0, _info='', gl=null}";
        Assertions.assertEquals(node_a,graph1.getNode(1).toString());
        String node_a1="Node_Data{_tag=0, _key=2, _weight=0.0, _info='', gl=null}";
        Assertions.assertEquals(node_a1,graph1.getNode(2).toString());
    }

    @Test
    void getEdge() {
        graph1.addNode(a);
        graph1.addNode(a1);
        graph1.addNode(a2);
        graph1.addNode(a3);
        graph1.connect(1,3,15);
        graph1.connect(4,1,15);
        graph1.connect(1,2,15);
        Assertions.assertEquals(1,graph1.getEdge(1,2).getSrc());
    }

    @Test
    void addNode() {
        graph1.addNode(a);
        graph1.addNode(a1);
        String node_a = "Node_Data{_tag=0, _key=1, _weight=0.0, _info='', gl=null}";
        Assertions.assertEquals(node_a, graph1.getNode(1).toString());
        String node_a1 = "Node_Data{_tag=0, _key=2, _weight=0.0, _info='', gl=null}";
        Assertions.assertEquals(node_a1, graph1.getNode(2).toString());
    }

    @Test
    void connect() {
        graph1.addNode(a);
        graph1.addNode(a1);
        graph1.addNode(a2);
        graph1.addNode(a3);
        graph1.connect(0,1,5);
        graph1.connect(1,0,5);
        graph1.connect(1,1,5);
        Assertions.assertEquals(0,graph1.edgeSize());
        graph1.connect(1,2,3);
        Assertions.assertEquals(1,graph1.edgeSize());
    }

    @Test
    void nodeIter() {
        graph1.addNode(a);
        graph1.addNode(a1);
        graph1.addNode(a2);
        graph1.addNode(a3);
        Iterator it=graph1.nodeIter();
        String nodeIter="Node_Data{_tag=0, _key=1, _weight=0.0, _info='', gl=null}";
        Assertions.assertEquals(nodeIter,graph1.nodeIter().next().toString());
    }

    @Test
    void edgeIter() {
//        graph1.addNode(a);
//        graph1.addNode(a1);
//        graph1.addNode(a2);
//        graph1.addNode(a3);
//        graph1.connect(1,3,15);
//        graph1.connect(4,1,15);
//        graph1.connect(1,2,15);
//        Iterator it=graph1.edgeIter();
//        String edgeIter="Edge_Data{_src=1, _dest=2, _tag=0, _weight=15.0, _info=''}";
//        Assertions.assertEquals(edgeIter,graph1.edgeIter().next().toString());
        graph1.addNode(a);
        graph1.addNode(a1);
        graph1.addNode(a2);
        graph1.addNode(a3);
        graph1.connect(a.getKey(), a1.getKey(), 4);
        graph1.connect(a.getKey(), a2.getKey(), 2);
        graph1.connect(a1.getKey(), a2.getKey(), 5);
        graph1.connect(a1.getKey(), a3.getKey(), 10);
        EdgeData a0_b1 = graph1.getEdge(0, 1);
        EdgeData a0_c2 = graph1.getEdge(0, 2);
        EdgeData b1_c2 = graph1.getEdge(1, 2);
        EdgeData b1_d3 = graph1.getEdge(1, 3);

        HashMap<Integer, HashMap<Integer, EdgeData>> new_edge_map = new HashMap<>();
        Iterator<EdgeData> all_edge_it = graph1.edgeIter();

        while(all_edge_it.hasNext())
        {
            EdgeData current = all_edge_it.next();
            if(!new_edge_map.containsKey(current.getSrc()))
            {
                HashMap<Integer, EdgeData> temp = new HashMap<>();
                new_edge_map.put(current.getSrc(),temp);
            }
            new_edge_map.get(current.getSrc()).put(current.getDest(),current);
        }

        assertTrue(new_edge_map.get(1).get(2).getSrc() == 0);
        assertTrue(new_edge_map.get(1).get(2).getDest() == 1);
        assertTrue(new_edge_map.get(1).get(2).getWeight() == 4);

        assertTrue(new_edge_map.get(1).get(3).getSrc() == 0);
        assertTrue(new_edge_map.get(1).get(3).getDest() == 2);
        assertTrue(new_edge_map.get(1).get(3).getWeight() == 2);

        assertTrue(new_edge_map.get(2).get(3).getSrc() == 1);
        assertTrue(new_edge_map.get(2).get(3).getDest() == 2);
        assertTrue(new_edge_map.get(2).get(3).getWeight() == 5);

        assertTrue(new_edge_map.get(2).get(4).getSrc() == 1);
        assertTrue(new_edge_map.get(2).get(4).getDest() == 3);
        assertTrue(new_edge_map.get(2).get(4).getWeight() == 10);




    }

    @Test
    void testEdgeIter() {
        graph1.addNode(a);
        graph1.addNode(a1);
        graph1.addNode(a2);
        graph1.addNode(a3);
        graph1.connect(1,3,15);
        graph1.connect(4,1,15);
        graph1.connect(1,2,15);
        Iterator it=graph1.edgeIter(1);
        String edgeIter="Edge_Data{_src=1, _dest=2, _tag=0, _weight=15.0, _info=''}";
        Assertions.assertEquals(edgeIter,graph1.edgeIter().next().toString());
    }

    @Test
    void removeNode() {
        graph1.addNode(a);
        graph1.addNode(a1);
        graph1.addNode(a2);
        graph1.addNode(a3);
        Assertions.assertEquals(4,graph1.nodeSize());
        graph1.removeNode(1);
        Assertions.assertEquals(3,graph1.nodeSize());

    }

    @Test
    void removeEdge() {
        graph1.addNode(a);
        graph1.addNode(a1);
        graph1.addNode(a2);
        graph1.addNode(a3);
        graph1.connect(1,3,15);
        graph1.connect(4,1,15);
        graph1.connect(1,2,15);
        Assertions.assertEquals(3,graph1.edgeSize());
        graph1.removeEdge(1,2);
        Assertions.assertEquals(2,graph1.edgeSize());

    }

    @Test
    void nodeSize() {
        graph1.addNode(a);
        graph1.addNode(a1);
        graph1.addNode(a2);
        graph1.addNode(a3);
        Assertions.assertEquals(4,graph1.nodeSize());
        graph1.removeNode(1);
        Assertions.assertEquals(3,graph1.nodeSize());
    }

    @Test
    void edgeSize() {
        graph1.addNode(a);
        graph1.addNode(a1);
        graph1.addNode(a2);
        graph1.addNode(a3);
        Assertions.assertEquals(0,graph1.edgeSize());
        graph1.connect(1,2,15);
        Assertions.assertEquals(1,graph1.edgeSize());
    }

    @Test
    void getMC() {
        graph1.addNode(a);
        graph1.addNode(a1);
        graph1.addNode(a2);
        graph1.addNode(a3);
        Assertions.assertEquals(4,graph1.getMC());
        graph1.connect(0,1,5);
        graph1.connect(1,0,5);
        graph1.connect(1,1,5);
        graph1.connect(1,3,15);
        graph1.connect(4,1,15);
        graph1.connect(1,2,15);
        Assertions.assertEquals(7,graph1.getMC());


    }

    @Test
    void testToString() {
        graph1.addNode(a);
        graph1.addNode(a1);
        graph1.addNode(a2);
        graph1.addNode(a3);
        graph1.connect(0,1,5);
        graph1.connect(1,0,5);
        graph1.connect(1,1,5);
        graph1.connect(1,3,15);
        graph1.connect(4,1,15);
        graph1.connect(1,2,15);
        String graphString="Graph{nodes={1=Node_Data{_tag=0, _key=1, _weight=0.0, _info='', gl=null}, 2=Node_Data{_tag=0, _key=2, _weight=0.0, _info='', gl=null}, 3=Node_Data{_tag=0, _key=3, _weight=0.0, _info='', gl=null}, 4=Node_Data{_tag=0, _key=4, _weight=0.0, _info='', gl=null}}, edgesOut={1={2=Edge_Data{_src=1, _dest=2, _tag=0, _weight=15.0, _info=''}, 3=Edge_Data{_src=1, _dest=3, _tag=0, _weight=15.0, _info=''}}, 2={}, 3={}, 4={1=Edge_Data{_src=4, _dest=1, _tag=0, _weight=15.0, _info=''}}}, edgesIn={1={4=Edge_Data{_src=4, _dest=1, _tag=0, _weight=15.0, _info=''}}, 2={1=Edge_Data{_src=1, _dest=2, _tag=0, _weight=15.0, _info=''}}, 3={1=Edge_Data{_src=1, _dest=3, _tag=0, _weight=15.0, _info=''}}, 4={}}, NodeNum=4, EdgeNum=3, Mc=7}";
        Assertions.assertEquals(graphString,graph1.toString());
    }

    @Test
    void Graph()
    {
        graph1.addNode(a);
        graph1.addNode(a1);
        graph1.addNode(a2);
        graph1.addNode(a3);
        graph1.connect(1,3,15);
        graph1.connect(4,1,15);
        graph1.connect(1,2,15);
        GraphAlgorithms algo = new GraphAlgorithms();
        algo.init(graph1);
        DirectedWeightedGraph new_graph = algo.copy();
        Assertions.assertEquals(graph1.getNode(1).getKey(),new_graph.getNode(1).getKey());
    }
}