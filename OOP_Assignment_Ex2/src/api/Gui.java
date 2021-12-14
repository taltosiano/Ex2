package api;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Gui extends JPanel implements ActionListener {


    private double minX, maxX, minY, maxY;
    private JMenuItem load;
    private JMenuItem save;
    private JMenuItem isConnected;
    private JMenuItem shortestDist;
    private JMenuItem shortestPath;
    private JMenuItem tsp;
    private JMenuItem center;
    private JMenuItem addNode;
    private JMenuItem removeNode;
    private JMenuItem addEdge;
    private JMenuItem removeEdge;


    private JMenu graph;
    private JMenu function;
    private JMenu edit;
    private JMenuBar menuBar;
    private JFileChooser openFileChooser;

    private DirectedWeightedGraphAlgorithms algo;
    private DirectedWeightedGraph _graph;
    double answerShortestPathDist=0;
     List<Integer> listShortest=new ArrayList<Integer>();
     List<NodeData> listTsp=new ArrayList<NodeData>();
    List<Integer> listTspInt=new ArrayList<Integer>();






    public Gui(DirectedWeightedGraph graph, DirectedWeightedGraphAlgorithms algo) {
        this._graph = graph;
        this.algo = algo;
        initMinMax(this._graph.nodeIter());
        addMenu();
        this.repaint();

    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //edges
        Iterator<EdgeData> it = this._graph.edgeIter();
        while (it.hasNext()) {
            EdgeData edge = it.next();
            GeoLocation nodeSrcLoc = this._graph.getNode(edge.getSrc()).getLocation();
            GeoLocation nodeDestLoc = this._graph.getNode(edge.getDest()).getLocation();
            g.setColor(Color.BLACK);
            g.drawLine((int) toPixelX(nodeSrcLoc.x()), (int) toPixelY(nodeSrcLoc.y()), (int) toPixelX(nodeDestLoc.x()), (int) toPixelY(nodeDestLoc.y()));
        }
        //nodes
        Iterator<NodeData> it1 = this._graph.nodeIter();
        while (it1.hasNext()) {
            NodeData node = it1.next();
            GeoLocation nodeSrcLoc = node.getLocation();
            g.setColor(Color.BLUE);
            g.fillOval((int) toPixelX(nodeSrcLoc.x()), (int) toPixelY(nodeSrcLoc.y()),10,10);
            g.drawString(""+node.getKey(),(int) toPixelX(nodeSrcLoc.x()), (int) toPixelY(nodeSrcLoc.y()));

        }
        if(answerShortestPathDist !=0){
            g.drawString("answerShortestPathDist :  "+answerShortestPathDist,500, 500);
        }
    }

    private void addMenu() {
        menuBar = new JMenuBar();
        graph = new JMenu("Graph");
        function = new JMenu("Function");
        edit = new JMenu("Edit");

        load = new JMenuItem("Load");
        load.addActionListener(this);

        save = new JMenuItem("Save");
        save.addActionListener(this);

        isConnected = new JMenuItem("isConnected");
        isConnected.addActionListener(this);

        shortestDist = new JMenuItem("shortestPathDist");
        shortestDist.addActionListener(this);

        shortestPath = new JMenuItem("shortestPath");
        shortestPath.addActionListener(this);

        tsp = new JMenuItem("tsp");
        tsp.addActionListener(this);

        center = new JMenuItem("center");
        center.addActionListener(this);

        addNode = new JMenuItem("addNode");
        addNode.addActionListener(this);

        removeNode = new JMenuItem("removeNode");
        removeNode.addActionListener(this);

        addEdge = new JMenuItem("addEdge");
        addEdge.addActionListener(this);

        removeEdge = new JMenuItem("removeEdge");
        removeEdge.addActionListener(this);

        graph.add(load);
        graph.add(save);
        function.add(isConnected);
        function.add(shortestDist);
        function.add(shortestPath);
        function.add(tsp);
        function.add(center);
        edit.add(addNode);
        edit.add(removeNode);
        edit.add(addEdge);
        edit.add(removeEdge);

        menuBar.add(graph);
        menuBar.add(function);
        menuBar.add(edit);
        this.add(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == load) {
            openFileChooser = new JFileChooser();
            openFileChooser.setCurrentDirectory(new File("C:\\Users\\user\\IdeaProjects\\OOP_Assignment_Ex2\\data"));
            int loadFile = openFileChooser.showOpenDialog(this);
            if (loadFile == JFileChooser.APPROVE_OPTION) {
                File selectedFile = openFileChooser.getSelectedFile();
                algo.load(selectedFile.getAbsolutePath());
            }
            this._graph = algo.getGraph();
            initMinMax(this._graph.nodeIter());
            this.repaint();
        } else if (e.getSource() == save) {
            openFileChooser = new JFileChooser();
            int saveFile = openFileChooser.showSaveDialog(null);
            if (saveFile == JFileChooser.APPROVE_OPTION) {
                File selectedFile = openFileChooser.getSelectedFile();
                algo.save(selectedFile.getAbsolutePath());
            }
        } else if (e.getSource() == shortestDist) {
            String src = JOptionPane.showInputDialog(this, "insert src node key");
            String dest = JOptionPane.showInputDialog(this, "insert dest node key:");
            int s = Integer.parseInt(src);
            int d = Integer.parseInt(dest);
            answerShortestPathDist = algo.shortestPathDist(s, d);
            this.repaint();
        } else if (e.getSource() == shortestPath) {
            String src = JOptionPane.showInputDialog(this, "insert src node key");
            String dest = JOptionPane.showInputDialog(this, "insert dest node key:");
            int s = Integer.parseInt(src);
            int d = Integer.parseInt(dest);
            if (algo.shortestPath(s, d) == null) {
                JOptionPane.showMessageDialog(this, "failed!");
            } else {
                for (NodeData n : algo.shortestPath(s, d)) {
                    this.listShortest.add(n.getKey());
                }
                JOptionPane.showMessageDialog(this, this.listShortest);
                this.repaint();
            }

        } else if (e.getSource() == addNode) {
            String pointX = JOptionPane.showInputDialog(this, "insert x:");
            String pointY = JOptionPane.showInputDialog(this, "insert y:");
            String pointZ = JOptionPane.showInputDialog(this, "insert z:");
            GeoLocation g = new Geo_Location(Double.parseDouble(pointX), Double.parseDouble(pointY), Double.parseDouble(pointZ));
            NodeData n = new Node_Data(this._graph.nodeSize(), g);
            this._graph.addNode(n);
            this.repaint();
        } else if (e.getSource() == removeNode) {
            String key = JOptionPane.showInputDialog(this, "insert the key of the node to remove:");
            int keyToRemove = Integer.parseInt(key);
            NodeData node = this._graph.removeNode(keyToRemove);
            if (node == null) {
                JOptionPane.showMessageDialog(this, "invalid key!");
            } else {
                this.repaint();
            }
        } else if (e.getSource() == addEdge) {
            String src = JOptionPane.showInputDialog(this, "insert src node key");
            String dest = JOptionPane.showInputDialog(this, "insert dest node key:");
            String weight = JOptionPane.showInputDialog(this, "insert weight:");
            int s = Integer.parseInt(src);
            int d = Integer.parseInt(dest);
            int w = Integer.parseInt(weight);
            if ((this._graph.getNode(s) == null) || (this._graph.getNode(d) == null)) {
                JOptionPane.showMessageDialog(this, "failed!");
            } else {
                this._graph.connect(s, d, w);
                this.repaint();
            }
        } else if (e.getSource() == removeEdge) {
            String src = JOptionPane.showInputDialog(this, "insert src node key");
            String dest = JOptionPane.showInputDialog(this, "insert dest node key:");
            int s = Integer.parseInt(src);
            int d = Integer.parseInt(dest);
            if (this._graph.removeEdge(s, d) == null) {
                JOptionPane.showMessageDialog(this, "failed!");
            } else {

                this.repaint();
            }
        } else if (e.getSource() == center) {
            JOptionPane.showMessageDialog(this, algo.center().toString());

        } else if (e.getSource() == isConnected) {
            Boolean ans = algo.isConnected();
            JOptionPane.showMessageDialog(this, ans);
        } else if (e.getSource() == tsp) {
            String key = "";
            while (!key.equals("done")) {
                key = JOptionPane.showInputDialog(this, "insert node key,in the end inset 'done':");
                if (!key.equals("done")) {
                    int keyToList = Integer.parseInt(key);
                    if(this._graph.getNode(keyToList)!=null) {
                        this.listTsp.add(this._graph.getNode(keyToList));
                        this.listTspInt.add(keyToList);
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "invalid key!");
                    }
                }
            }

                if (algo.tsp(listTsp) == null) {
                    JOptionPane.showMessageDialog(this, "failed!");
                } else {
                    JOptionPane.showMessageDialog(this, this.listTspInt);
                    this.repaint();
                }
            }

        }



    private void initMinMax(Iterator<NodeData> it) {
        minX = Double.MAX_VALUE;
        maxX = Double.MIN_VALUE;
        minY = Double.MAX_VALUE;
        maxY = Double.MIN_VALUE;

        while (it.hasNext()) {
            GeoLocation geo = it.next().getLocation();
            if (geo.x() < minX) minX = geo.x();
            if (geo.x() > maxX) maxX = geo.x();
            if (geo.y() < minY) minY = geo.y();
            if (geo.y() > maxY) maxY = geo.y();
        }
    }
    private double toPixelY(double y) {
        return ((this.getHeight() * (y - minY) / (maxY - minY)) / 1.5) + 20;
    }

    private double toPixelX(double x) {
        return ((this.getWidth() * ((x - minX) / (maxX - minX))) / 1.5) + 20;
    }

}

