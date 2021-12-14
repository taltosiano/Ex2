package api;//import com.google.gson.*;
    //import com.google.gson.stream.JsonReader;
    //import com.google.gson.stream.JsonWriter;
    //import com.sun.jdi.IntegerValue;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.util.*;

    public class GraphAlgorithms implements DirectedWeightedGraphAlgorithms {

        private DirectedWeightedGraph graph;

        @Override
        public void init(DirectedWeightedGraph g) {
            this.graph = g;
        }

        @Override
        public DirectedWeightedGraph getGraph() {
            return this.graph;
        }

        @Override
        public DirectedWeightedGraph copy() {

            //return new api.Graph((api.Graph) this.graph);
            save("copy.json");
            DirectedWeightedGraph graph = new Graph();
            DirectedWeightedGraphAlgorithms graphAlgo = new GraphAlgorithms();
            graphAlgo.init(graph);
            graphAlgo.load("copy.json");
            return graphAlgo.getGraph();
        }

        Stack<NodeData> stack=new Stack<NodeData>();
        int sccCount=0;
        int id=0;
        @Override
        public boolean isConnected() {
                if( this.graph==null||this.graph.nodeSize()==0||this.graph.nodeSize()==1) {
                    return true;
                }
                initAllVertexs();
                stack=new Stack<NodeData>();
                sccCount=0;
                id=0;
                Iterator nodeIter=this.graph.nodeIter();
                while (nodeIter.hasNext()){
                    if(((NodeData) nodeIter.next()).getTag()==0) {
                        tarjan((NodeData) nodeIter.next());
                    }
                }
                if(sccCount==1) return true;
                return false;
            }
        private void tarjan(NodeData node) {

            stack.push(node);
            node.setInfo("true");
            id++;
            node.setTag(id);
            node.setWeight(id);//check
            if(this.getGraph().edgeIter(node.getKey())!=null) {
                Iterator edgeIter=this.graph.edgeIter(node.getKey());
                while (edgeIter.hasNext()){
                    NodeData to =this.getGraph().getNode(((EdgeData)edgeIter.next()).getDest());
                    if(to.getTag()==0) tarjan(to);
                    if(to.getInfo().equals("true")) node.setWeight(Math.min(node.getWeight(), to.getWeight()));
                }
            }

            if(node.getTag() == node.getWeight()) {//ask
                while(!stack.isEmpty()) {
                    NodeData node2=stack.pop();
                    //path
                    node2.setInfo("");
                    node2.setWeight(node.getTag());
                    if(node2==node) break;
                }
                sccCount++;
            }

        }


        @Override
        public double shortestPathDist(int src, int dest) {
            if (src == dest || this.graph.getNode(src) == null || this.graph.getNode(dest) == null) {
                return -1;
            }
            this.initAllVertexs();

            NodeData _src = this.graph.getNode(src);
            _src.setWeight(0);

            PriorityQueue<NodeData> q = new PriorityQueue<NodeData>(new Comparator<NodeData>() {
                public int compare(NodeData n1, NodeData n2) {
                    if (n1.getWeight() < n2.getWeight()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            });

            q.add(_src);

            while (!q.isEmpty()) {
                NodeData v = q.poll();
                Iterator<EdgeData> it = this.graph.edgeIter(v.getKey());
                while (it.hasNext()) {
                    EdgeData edge = it.next();
                    NodeData u = this.graph.getNode(edge.getDest());
                    double w = v.getWeight() + edge.getWeight();
                    if (w < u.getWeight() && v.getTag() != 1) {
                        u.setWeight(w);
                        q.add(u);
                    }
                }
                v.setTag(1);
            }

            NodeData _dest = this.graph.getNode(dest);
            if (_dest.getWeight() == Double.POSITIVE_INFINITY) {
                return -1;
            }

            return _dest.getWeight();
        }

        @Override
        public List<NodeData> shortestPath(int src, int dest) {

            if (src == dest || this.graph.getNode(src) == null || this.graph.getNode(dest) == null) {
                return null;
            }
            this.initAllVertexs();

            NodeData _src = this.graph.getNode(src);
            _src.setWeight(0);

            PriorityQueue<NodeData> q = new PriorityQueue<NodeData>(new Comparator<NodeData>() {
                public int compare(NodeData n1, NodeData n2) {
                    if (n1.getWeight() < n2.getWeight()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            });

            q.add(_src);

            while (!q.isEmpty()) {
                NodeData v = q.poll();
                Iterator<EdgeData> it = this.graph.edgeIter(v.getKey());
                while (it.hasNext()) {
                    EdgeData edge = it.next();
                    NodeData u = this.graph.getNode(edge.getDest());
                    double w = v.getWeight() + edge.getWeight();
                    if (w < u.getWeight() && v.getTag() != 1) {
                        u.setWeight(w);
                        u.setInfo(String.valueOf(v.getKey()));
                        q.add(u);
                    }
                }
                v.setTag(1);
            }

            NodeData _dest = this.graph.getNode(dest);
            if (_dest.getWeight() == Double.POSITIVE_INFINITY) {
                return null;
            }

            List<NodeData> answer = new ArrayList<NodeData>();
            answer.add(_dest);
            String parent = _dest.getInfo();
            while (!parent.equals("-1")) {
                NodeData node = this.graph.getNode(Integer.valueOf(parent));
                answer.add(node);
                parent = node.getInfo();
            }
            Collections.reverse(answer);
            return answer;
        }



        @Override
        public NodeData center() {
            if (!isConnected() && graph.nodeSize() == 0) {
                return null;
            }
            if (graph.nodeSize() == 1) {
                Iterator<NodeData> itOne = this.graph.nodeIter();
                return itOne.next();
            }
            double[][] dist = new double[getGraph().nodeSize()][getGraph().nodeSize()];
            for (int i = 0; i< getGraph().nodeSize();i++)
            {
                for (int j = 0; j< getGraph().nodeSize();j++)
                {
                    if(i==j)
                    {
                        dist[i][j] = 0;
                    }
                    else if (getGraph().getEdge(i,j) == null)
                    {
                        dist[i][j] = 20000000;
                    }
                    else {
                        dist[i][j] = getGraph().getEdge(i, j).getWeight();
                    }
                }
            }
            for(int k = 0; k< getGraph().nodeSize();k++) {
                for (int i = 0; i < getGraph().nodeSize(); i++) {
                    for (int j = 0; j < getGraph().nodeSize(); j++) {
                        if(dist[i][j] > dist[i][k] + dist[k][j])
                        {
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }
            int center = 0;
            double minimal_max_dist = Double.MAX_VALUE;
            for (int i = 0; i < getGraph().nodeSize(); i++)
            {
                double current_max_dist = 0;
                for (int j = 0 ; j< getGraph().nodeSize(); j++)
                {
                    if(dist[i][j]>current_max_dist)
                    {
                        current_max_dist = dist[i][j];
                    }
                }
                if(current_max_dist<minimal_max_dist)
                {
                    center = i;
                    minimal_max_dist = current_max_dist;
                }
            }
            return getGraph().getNode(center);
        }
        @Override
        public List<NodeData> tsp(List<NodeData> cities) {
            List<NodeData> Nodes = new ArrayList<NodeData>();
            List<NodeData> temp = new ArrayList<NodeData>();
            List<NodeData> res = new ArrayList<NodeData>();

            for (NodeData node : cities) {
                if (graph.edgeIter(node.getKey()) == null)//the cities list is not connect
                    throw new RuntimeException("the cities list is disconnect");
            }

            for (NodeData node : cities) {
                if (graph.getNode(node.getKey()) == null) {
                    return null;
                }
                Nodes.add(graph.getNode(node.getKey()));
            }
            //loop over Nodes list and add to temp list the shortest path between every node
            for (int i = 0; i < Nodes.size() -1 ; i++) {
                List<NodeData> list = new ArrayList<NodeData>();

                list = (ArrayList<NodeData>) shortestPath(Nodes.get(i).getKey(), Nodes.get(i + 1).getKey());

                if (list == null) {
                    return null;
                }
                for (int j = 0; j < list.size(); j++) {
                    temp.add(list.get(j));
                }
            }
            if (temp.size() % 2 == 0) {
                //loop over the list and check that there is no repeat on nodes in the path
                for (int i = 0; i < temp.size(); i++) {
                    if (res.contains(temp.get(i))){
                        continue;}
                    else if (i == temp.size() - 1) {//if it's the last element in the list
                        if (temp.get(i - 1) != temp.get(i)) {//check if the 2 nodes are equal
                            res.add(temp.get(i));
                        }
                    } else if (temp.get(i) == temp.get(i + 1)) {//if both nodes are equal -add one of them
                        res.add(temp.get(i));
                        i++;
                    }
                    else {
                        res.add(temp.get(i));
                    }
                }
            } else {
                for (int i = 0; i < temp.size() - 1; i++) {
                    if (res.contains(temp.get(i))){
                        continue;}
                    else if (i == temp.size() - 2) {
                        if (temp.get(i) == temp.get(i + 1)) {
                            res.add(temp.get(i));
                            i++;
                        }
                        else {
                            res.add(temp.get(i));
                            res.add(temp.get(i + 1));
                        }
                    }

                    if (temp.get(i) == temp.get(i + 1)) {
                        res.add(temp.get(i));
                        i++;
                    } else {
                        res.add(temp.get(i));
                    }
                }
            }
            return res;
        }



        @Override
        public boolean save(String file) {

            try {
                Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();
                FileOutputStream out= new FileOutputStream(file);
                JsonWriter write = new JsonWriter(new OutputStreamWriter(out));

                List<JsonObject> vertexs=new ArrayList<>();
                List<JsonObject> edges=new ArrayList<>();
                Iterator<NodeData> it = this.graph.nodeIter();
                while(it.hasNext()){
                    NodeData node = it.next();

                    JsonObject v=new JsonObject();

                    v.addProperty("pos",node.getLocation().toString());
                    v.addProperty("id",node.getKey());
                    vertexs.add(v);

                    Iterator<EdgeData> itEdge = this.graph.edgeIter(node.getKey());
                    while (itEdge.hasNext()){
                        EdgeData edge = itEdge.next();
                        JsonObject edgeJSON=new JsonObject();
                        edgeJSON.addProperty("src",edge.getSrc());
                        edgeJSON.addProperty("w",edge.getWeight());
                        edgeJSON.addProperty("dest",edge.getDest());
                        edges.add(edgeJSON);
                    }
                }

                JsonObject _dgrap =new JsonObject ();
                _dgrap.add("Edges", gson.toJsonTree(edges.toArray()));
                _dgrap.add("Nodes",  gson.toJsonTree(vertexs.toArray()));

                gson.toJson(_dgrap,write);
                write.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }

            @Override
            public boolean load (String file){
            try {
                FileInputStream input = new FileInputStream(file);

                JsonReader read = new JsonReader(new InputStreamReader(input));
                JsonObject elements = JsonParser.parseReader(read).getAsJsonObject();

                DirectedWeightedGraph temp_graph = new Graph();

                for(JsonElement node: elements.getAsJsonArray("Nodes")) {
                    int id = node.getAsJsonObject().get("id").getAsInt();
                    String[] pos= node.getAsJsonObject().get("pos").getAsString().split(",");
                    NodeData n=new Node_Data(id,new Geo_Location(Double.parseDouble(pos[0]),Double.parseDouble(pos[1]),Double.parseDouble(pos[2])));
                    temp_graph.addNode(n);
                }
                for(JsonElement edge: elements.getAsJsonArray("Edges")) {
                    temp_graph.connect(edge.getAsJsonObject().get("src").getAsInt(), edge.getAsJsonObject().get("dest").getAsInt(), edge.getAsJsonObject().get("w").getAsDouble());
                }
                this.init(temp_graph);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            }
                return true;
            }
            //Initialize all the nodes as INFINITE
            private void initAllVertexs () {
                Iterator<NodeData> it = this.graph.nodeIter();
                while (it.hasNext()) {
                    NodeData node = it.next();
                    node.setWeight(Double.POSITIVE_INFINITY);
                    node.setTag(0);
                    node.setInfo("-1");
                }
            }


            public static void main (String[]args){
                DirectedWeightedGraphAlgorithms algo = new GraphAlgorithms();
                algo.load("data/G1.json");


                DirectedWeightedGraph temp = algo.getGraph();
                System.out.println(algo.copy());
//                api.NodeData g = new api.Node_Data(5);
//                g.setLocation(new api.Geo_Location(2, 3, 0));
//                temp.addNode(g);
                algo.init(temp);
                algo.save("test.json");
                //api.DirectedWeightedGraph gNew=algo.copy();
                //System.out.println(algo.copy().getMC());
                System.out.println(algo.center());
    //        System.out.println(algo.shortestPathDist(1,3));
    //        System.out.println(algo.shortestPath(1,3));
    //        System.out.println(algo.getGraph());
            DirectedWeightedGraph graph = new Graph();
//            api.NodeData a = new api.Node_Data(1);
//            api.NodeData a1 = new api.Node_Data(2);
//            api.NodeData a2 = new api.Node_Data(3);
//
//            graph.addNode(a);
//            graph.addNode(a1);
//            graph.addNode(a2);
            graph.connect(1,2,1);
            graph.connect(2,3,2.5);
            graph.connect(1,3,3);
            algo.init(graph);
              // System.out.println(algo.copy().toString());
    //
    //        api.DirectedWeightedGraphAlgorithms algo =new api.GraphAlgorithms();
    //        algo.init(graph);
    //        System.out.println(algo.shortestPathDist(1,3));
    //        System.out.println(algo.shortestPath(1,3));




            }
        }

