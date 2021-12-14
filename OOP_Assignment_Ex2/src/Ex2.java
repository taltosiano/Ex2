import api.*;

import javax.swing.*;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraph graph = new Graph();
        DirectedWeightedGraphAlgorithms ans = new GraphAlgorithms();
        ans.load(json_file);
        ans.init(graph);
        return graph;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraph graph = new Graph();
        DirectedWeightedGraphAlgorithms ans = new GraphAlgorithms();
        ans.load(json_file);
        ans.init(graph);
        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {

        JFrame init = new JFrame("Welcome to My Graph");
        init.setSize(800,600);

        DirectedWeightedGraphAlgorithms algo = new GraphAlgorithms();
        algo.load(json_file);

        Gui frame = new Gui(algo.getGraph(),algo);
        init.add(frame);
        init.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init.setVisible(true);
    }

    public static void main(String[] args) {

        String myJson = args[0];
//        getGrapg(myJson);
//        getGrapgAlgo(myJson);
        runGUI(myJson);
    }
}