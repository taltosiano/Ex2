package api;

import java.util.List;

public class GraphAlgorithms implements DirectedWeightedGraphAlgorithms{
    Graph _g;
    public GraphAlgorithms(Graph g){

        this._g = new Graph(g);
    }
    @Override
    public void init(Graph g) {

    }

    @Override
    public Graph getGraph() {

        return _g;
    }

    @Override
    public Graph copy() {
        Graph temp = new Graph();
        return temp;
    }

    @Override
    public boolean isConnected() {

        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {

        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {

        return null;
    }

    @Override
    public NodeData center() {

        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {

        return null;
    }

    @Override
    public boolean save(String file) {

        return false;
    }

    @Override
    public boolean load(String file) {

        return false;
    }
}
