package api;

import java.util.*;

public class Graph implements DirectedWeightedGraph{
    private HashMap<Integer, NodeData> nodes;
    private HashMap<Integer,HashMap<Integer,EdgeData>> edgesOut;
    private HashMap<Integer,HashMap<Integer,EdgeData>> edgesIn;
    private int EdgeNum=0;
    private int Mc=0;
    public Graph(){
        this.nodes = new HashMap<>();
        this.edgesOut = new HashMap<>();
        this.edgesIn = new HashMap<>();
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
            this.edgesOut.put(n.getKey(),new HashMap<Integer, EdgeData>());
            this.edgesIn.put(n.getKey(),new HashMap<Integer,EdgeData>());
            this.Mc++;
        }
    }

    @Override
    public void connect(int src, int dest, double w) {
        if (src!=dest&&this.nodes.containsKey(src) && this.nodes.containsKey(dest) && !this.edgesOut.get(src).containsKey(dest)) {
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

            Collection<EdgeData> edges = this.edgesOut.get(nodeKey).values();
            for (EdgeData e:edges) {
                temp.add(e);
            }
        }
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
            Iterator<NodeData> it = this.nodeIter();
            while (it.hasNext()){
                Node_Data node= (Node_Data) it.next();
                if(node.getKey()!=key){
                    this.edgesOut.get(node.getKey()).remove(key);
                    this.edgesIn.get(node.getKey()).remove(key);
                }
            }

            this.edgesOut.remove(key);
            this.edgesIn.remove(key);
            this.Mc++;
            return this.nodes.remove(key);
        }
        return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        if(src!=dest&&this.nodes.containsKey(src)&&this.nodes.containsKey(dest)&&this.edgesOut.get(src).containsKey(dest)){
            Mc++;
            EdgeNum--;
            this.edgesIn.get(dest).remove(src);
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
        return this.EdgeNum;
    }

    @Override
    public int getMC() {
        return this.Mc;
    }

    @Override
    public String toString() {
        return "api.Graph{" +
                "nodes=" + nodes +
                ", edgesOut=" + edgesOut +
                ", edgesIn=" + edgesIn +
                ", NodeNum=" + this.nodeSize() +
                ", EdgeNum=" + EdgeNum +
                ", Mc=" + Mc +
                '}';
    }

    }

