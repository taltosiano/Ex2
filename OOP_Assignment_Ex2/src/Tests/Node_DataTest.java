package Tests;

import api.Geo_Location;
import api.NodeData;
import api.Node_Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Node_DataTest {

    @Test
    void getKey() {
        NodeData node1 =new Node_Data(1,new Geo_Location(1,2,3));
        Assertions.assertEquals(1,node1.getKey());
        NodeData node2=new Node_Data((Node_Data) node1);
        Assertions.assertEquals(1,node2.getKey());

    }

    @Test
    void getLocation() {
        NodeData node1 =new Node_Data(1,new Geo_Location(1,2,3));
        Assertions.assertEquals(null,node1.getLocation());
        node1.setLocation(new Geo_Location(32,32));
        Assertions.assertEquals("32.0,32.0,0.0",node1.getLocation().toString());


    }

    @Test
    void setLocation() {
        NodeData node1 =new Node_Data(1,new Geo_Location(1,2,3));
        Assertions.assertEquals(null,node1.getLocation());
        node1.setLocation(new Geo_Location(32,32));
        Assertions.assertEquals("32.0,32.0,0.0",node1.getLocation().toString());
    }

    @Test
    void getWeight() {
        NodeData node1 =new Node_Data(1,new Geo_Location(1,2,3));
        Assertions.assertEquals(0,node1.getWeight());
        node1.setWeight(3);
        NodeData node2=new Node_Data((Node_Data) node1);
        Assertions.assertEquals(3.0,node2.getWeight());
    }

    @Test
    void setWeight() {
        NodeData node1 =new Node_Data(1,new Geo_Location(1,2,3));
        Assertions.assertEquals(0,node1.getWeight());
        node1.setWeight(3);
        NodeData node2=new Node_Data((Node_Data) node1);
        Assertions.assertEquals(3.0,node2.getWeight());
    }

    @Test
    void getInfo() {
        NodeData node1 =new Node_Data(1,new Geo_Location(1,2,3));
        Assertions.assertEquals("",node1.getInfo());

    }

    @Test
    void setInfo() {
        //need json
    }

    @Test
    void getTag() {
        NodeData node1 =new Node_Data(1,new Geo_Location(1,2,3));
        Assertions.assertEquals(0,node1.getTag());
        node1.setTag(3);
        NodeData node2=new Node_Data((Node_Data) node1);
        Assertions.assertEquals(3,node2.getTag());
    }

    @Test
    void setTag() {
        NodeData node1 =new Node_Data(1,new Geo_Location(1,2,3));
        Assertions.assertEquals(0,node1.getTag());
        node1.setTag(3);
        NodeData node2=new Node_Data((Node_Data) node1);
        Assertions.assertEquals(3,node2.getTag());
    }

    @Test
    void testToString() {
        NodeData node1 =new Node_Data(1,new Geo_Location(1,2,3));
        Assertions.assertEquals("Node_Data{_tag=0, _key=1, _weight=0.0, _info='', gl=null}",node1.toString());
        NodeData node2=new Node_Data((Node_Data) node1);
        Assertions.assertEquals("Node_Data{_tag=0, _key=1, _weight=0.0, _info='', gl=null}",node2.toString());
    }
}