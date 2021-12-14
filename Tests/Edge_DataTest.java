package Tests;

import api.Edge_Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Edge_DataTest {

    @Test
    void getSrc() {
        Edge_Data e=new Edge_Data(1,2,3);
        Assertions.assertEquals(1,e.getSrc());
        Edge_Data e1= new Edge_Data(e);
        Assertions.assertEquals(1,e1.getSrc());
    }

    @Test
    void getDest() {
        Edge_Data e=new Edge_Data(1,2,3);
        Assertions.assertEquals(2,e.getDest());
        Edge_Data e1= new Edge_Data(e);
        Assertions.assertEquals(2,e1.getDest());
    }

    @Test
    void getWeight() {
        Edge_Data e=new Edge_Data(1,2,3);
        Assertions.assertEquals(3,e.getWeight());
        Edge_Data e1= new Edge_Data(e);
        Assertions.assertEquals(3,e1.getWeight());
    }

    @Test
    void getInfo() {
        Edge_Data e=new Edge_Data(1,2,3);
        Assertions.assertEquals("",e.getInfo());

    }


    @Test
    void getTag() {
        Edge_Data e=new Edge_Data(1,2,3);
        Assertions.assertEquals(0,e.getTag());
    }

    @Test
    void setTag() {
        Edge_Data e=new Edge_Data(1,2,3);
        e.setTag(30);
        Assertions.assertEquals(30,e.getTag());
        Edge_Data e1= new Edge_Data(e);
        e1.setTag(e.getTag());
        Assertions.assertEquals(30,e1.getTag());
    }

    @Test
    void testToString() {
        Edge_Data e=new Edge_Data(1,2,3);
        e.setTag(30);
        Assertions.assertEquals("Edge_Data{_src=1, _dest=2, _tag=30, _weight=3.0, _info=''}",e.toString());

    }
}