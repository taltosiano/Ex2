package api;

import org.w3c.dom.Node;

import java.util.*;

public class Graph implements DirectedWeightedGraph{
    private HashMap<Integer,NodeData> nodes;
    private HashMap<Integer,HashMap<Integer,EdgeData>> edgesOut;
    private HashMap<Integer,HashMap<Integer,EdgeData>> edgesIn;
    private int EdgeNum=0;
    private int Mc=0;
    public Graph(){
        this.nodes = new HashMap<>();
        this.edgesOut = new HashMap<>();
        this.edgesIn = new HashMap<>();
    }
    public Graph(Graph g){
        this.nodes = new HashMap<>();
        this.edgesOut = new HashMap<>();
        this.edgesIn = new HashMap<>();
        for(Map.Entry<Integer,NodeData> entry: g.nodes.entrySet())
        {
            nodes.put(entry.getKey(), entry.getValue());
        }
        for(Map.Entry<Integer,HashMap<Integer,EdgeData>> first_obj : g.edgesOut.entrySet())
        {
            for(Map.Entry<Integer,EdgeData> second_obj : first_obj.getValue().entrySet())
            {
                edgesOut.get(first_obj.getKey()).put(second_obj.getKey(), second_obj.getValue());
            }
        }
        for(Map.Entry<Integer,HashMap<Integer,EdgeData>> first_obj : g.edgesIn.entrySet()) {
            for (Map.Entry<Integer, EdgeData> second_obj : first_obj.getValue().entrySet()) {
                edgesIn.get(first_obj.getKey()).put(second_obj.getKey(), second_obj.getValue());
            }
        }
    }
    @Override
    public NodeData getNode(int key) {
        return this.nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return this.edgesOut.get(src).get(dest);
    }


    @Override
    public void addNode(NodeData n) {
        if(!this.nodes.containsKey(n.getKey()))
        {
            this.nodes.put(n.getKey(),n);
            this.edgesOut.put(n.getKey(),new HashMap<Integer,EdgeData>());
            this.edgesIn.put(n.getKey(),new HashMap<Integer,EdgeData>());
            this.Mc++;
        }
    }

    @Override
    public void connect(int src, int dest, double w) {
        if (this.nodes.containsKey(src) && this.nodes.containsKey(dest) && !this.edgesOut.get(src).containsKey(dest)) {
            EdgeData edge = new Edge_Data(src, dest, w);
            this.edgesOut.get(src).put(dest,edge);
            this.edgesIn.get(dest).put(src,edge);
            this.EdgeNum++;
            this.Mc++;
        }
    }

    @Override
    public Iterator<NodeData> nodeIter() {

        return this.nodes.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        Collection<EdgeData> temp = new ArrayList<EdgeData>() ;

        Set<Integer> keysNodes = this.nodes.keySet();
        for (Integer nodeKey:keysNodes) {

          // temp.addAll(this.edgesOut.get(nodeKey).values());

            Collection<EdgeData> edges = this.edgesOut.get(nodeKey).values();
            for (EdgeData e:edges) {
                temp.add(e);
            }
        }
//        Iterator it = this.edgesOut.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry pair = (Map.Entry)it.next();
//            HashMap<Integer,EdgeData> hashTemp = (HashMap<Integer, EdgeData>) pair.getValue();
//            Iterator<EdgeData> it1 = hashTemp.values().iterator();
//            while (it1.hasNext()){
//                EdgeData edge= it1.next();
//                temp.add(edge);
//            }
//        }
        return temp.iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {

        return this.edgesOut.get(node_id).values().iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        if(this.nodes.containsKey(key)) {
            // sub edge num
            int sizeEdgeOut = this.edgesOut.get(key).size();
            int sizeEdgeIn = this.edgesIn.get(key).size();
            this.EdgeNum -= sizeEdgeOut;
            this.EdgeNum -= sizeEdgeIn;
            this.Mc += sizeEdgeOut + sizeEdgeIn;
            //clear
            this.edgesOut.get(key).clear();
            this.edgesIn.get(key).clear();
            this.Mc++;
            return this.nodes.remove(key);
        }
        return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        if(this.nodes.containsKey(src)&&this.nodes.containsKey(dest)&&this.edgesOut.get(src).containsKey(dest)){
            Mc++;
            EdgeNum--;
            return this.edgesOut.get(src).remove(dest);
        }
        return null;
    }

    @Override
    public int nodeSize() {
        return this.nodes.size();
    }

    @Override
    public int edgeSize() {
        //return this.edgesIn.size() * this.edgesOut.size();
        return this.EdgeNum;
    }

    @Override
    public int getMC() {
        return this.Mc;
    }
}
