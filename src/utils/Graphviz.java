package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Graphviz {
    private String runPath = "";
    private String dotPath = "";
    private String runOrder="";
    private String dotCodeFile="dotcode.txt";
    private String resultGif="dotGif";
    private StringBuilder graph = new StringBuilder();

    Runtime runtime=Runtime.getRuntime();

    public Graphviz(String runPath,String dotPath) {
        this.runPath=runPath;
        this.dotPath=dotPath;
    }

    public void run() {
        File file=new File(runPath);
        file.mkdirs();
        writeGraphToFile(graph.toString(), runPath);
        creatOrder();
        try {
            runtime.exec(runOrder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void creatOrder(){
        runOrder+=dotPath+" ";
        runOrder+=runPath;
        runOrder+="\\"+dotCodeFile+" ";
        runOrder+="-T gif ";
        runOrder+="-o ";
        runOrder+=runPath;
        runOrder+="\\"+resultGif+".gif";
        System.out.println(runOrder);
    }

    public void writeGraphToFile(String dotcode, String filename) {
        try {
            File file = new File(filename+"\\"+dotCodeFile);
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(dotcode.getBytes());
            fos.close();
        } catch (java.io.IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public void start_graph() {
        graph.append("digraph G {\n") ;
    }
    public void addEdge(int i, int j, int value) {
        graph.append("\t"+i+"->"+j + "[label=\""+value+"\" arrowhead=\"none\"];" + "\n");
    }
    public void addRedEdge(int i, int j, int value) {
        graph.append("\t"+i+"->"+j + "[label=\""+value+"\" arrowhead=\"none\" color=\"red\"];" + "\n");
    }
    public void add(String line) {
        graph.append("\t"+line);
    }
    public void addln(String line) {
        graph.append("\t"+line + "\n");
    }
    public void addln() {
        graph.append('\n');
    }
    public void end_graph() {
        graph.append("}") ;
    }

//    public static void main(String[] args) {
//        Graphviz gViz=new Graphviz("graphvizdemo", "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe");
//        gViz.start_graph();
//        gViz.addEdge(0,1,1);
//        gViz.addEdge(0,2,2);
//        gViz.addEdge(1,2,3);
//        gViz.addEdge(2,1,3);
//        gViz.end_graph();
//        try {
//            gViz.run();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
