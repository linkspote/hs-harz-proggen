package de.medieninformatik.oop;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;

/**
 *  The <code>KruskalApp</code> class is used to create a <code>Graph</code> from a given file as well as a according
 *  minimal spanning tree calculated with the Kruskal Algorithm.
 */
public class KruskalApp extends Application {
    private Graph g;
    private Graph minSpanTree;

    // Algorithm to use for generating the graph; 0 - Error, 1 - Dijkstra, 2 - Kruskal
    private int algo;

    /**
     * Constructor of <code>KruskalApp</code> class. Initializes two separate instances of the <code>Graph</code> class.
     */
    public KruskalApp() {
        g = new Graph();
        minSpanTree = new Graph();
    }

    /**
     * Initializes application prior to the actual start.
     */
    @Override
    public void init() {
        List<String> args = getParameters().getUnnamed(); // Kommandozeilenparameter

        // Init loop variable
        // 0 - false input
        // 1 - dijkstra algo
        // 2 - kruskal algo
        algo = 0;

        do {
            // Output to inform user about options
            System.out.println(
                    "Bitte geben Sie nachfolgend an, welchen Algorithmus Sie zur grafischen Darstellung nutzen moechten.\n"
                            + "D - Dijkstra-Algorithmus\n"
                            + "K - Kruskal-Algorithmus\n\n"
                            + "Eingabe: "
            );

            // Init scanner and store input
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            // Check input of user for correct algorithm selection
            if (input.equals("D") || input.equals("d")) {
                System.out.println("Sie haben den Dijkstra-Algorithmus ausgewaehlt. Der Graph wird in Kuerze angezeigt.");
                algo = 1;
            }
            // Check input of user for correct algorithm selection
            else if (input.equals("K") || input.equals("k")) {
                System.out.println("Sie haben den Kruskal-Algorithmus ausgewaehlt. Der Graph wird in Kuerze angezeigt.");
                algo = 2;
            }
            // User typed something wrong
            else {
                System.out.println("Fehler: Ihre Eingabe war nicht korrekt, bitte wiederholen Sie den Vorgang.");
            }
        }
        while (algo == 0);

        model(args.isEmpty() ? "graph.dat" : args.get(0));
    }

    /**
     *  This method is used to draw the Vertices and Edges of a <code>Graph</code> onto a <code>Canvas</code>.
     * @param primaryStage Primary stage for the application, onto which the scene is set
     */
    @Override
    public void start(Stage primaryStage) {
        Set<Vertex> v = g.getVertices();
        final Vertex[] vertex = new Vertex[v.size()]; // Stelle Reihenfolge der Vertices sicher
        v.forEach( vert -> vertex[vert.getVertexId()] = vert );
        final int maxX = v.stream().mapToInt(Vertex::getX).max().orElse(200);
        final int maxY = v.stream().mapToInt(Vertex::getY).max().orElse(200);
        final int scale = 200;
        final int offset = 30;

        final Canvas canvas = new Canvas(maxX*scale+2*offset, maxY*scale+2*offset);
        final GraphicsContext graphics = canvas.getGraphicsContext2D();
        graphics.setStroke(Color.BLACK);
        graphics.setLineWidth(1);

        // Zeichne eine Kante
        Consumer<Edge> lines = edge -> {
            Vertex v1 = vertex[edge.getStartVertex().getVertexId()];
            Vertex v2 = vertex[edge.getEndVertex().getVertexId()];
            int x1 = v1.getX()*scale + offset;
            int y1 = v1.getY()*scale + offset;
            int x2 = v2.getX()*scale + offset;
            int y2 = v2.getY()*scale + offset;
            graphics.strokeLine(x1, y1, x2, y2);
            if(graphics.getStroke() == Color.BLACK) {
                graphics.strokeText(Double.toString(edge.getWeight()), (x1 + x2) / 2, (y1 + y2) / 2);
            }
        };
        g.getEdges().forEach( lines ); // Zeichne Kantes des Graphen

        graphics.setStroke(Color.RED);
        graphics.setLineWidth(3);
        minSpanTree.getEdges().forEach( lines ); // Zeichne Kanten des Baumes

        graphics.setFill(Color.LIGHTBLUE); // Markiere Vertices
        graphics.setStroke(Color.GREEN);
        graphics.setLineWidth(1);
        g.getVertices().forEach(vtx -> {
            int x = vtx.getX()*scale+offset/2;
            int y = vtx.getY()*scale+offset/2;
            graphics.fillOval(x,y, offset, offset);
            graphics.strokeText(Integer.toString(vtx.getVertexId()), x+offset/4, y+2*offset/3);
        });

        Group root = new Group();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle((algo == 1) ? "Dijkstra" : "Kruskal");
        primaryStage.show();
    }

    /**
     *  Creates a intial <code>Graph</code> from data given by a file. It also creates a minimal spanning tree based on
     *  that graph using the Kruskal Algorithm in the <code>GraphAlgo</code> class.
     * @param fname Path of file which contains the data for creating a <code>Graph</code>
     */
    private void model(String fname) {
        Path path = Paths.get(fname);

        if(Files.isRegularFile(path)) {
            try {
                List<String> lines = Files.readAllLines(path);
                g = Graph.read(lines);

                Vertex firstVertex = null;
                for (Vertex v: g.getVertices()) {
                    if(v.getVertexId() == 0) {
                        firstVertex = v;
                        break;
                    }
                }

                // Make sure the correct algorithm gets used to generate the graph
                switch (algo) {
                    case 1:
                        minSpanTree = GraphAlgo.dijkstra(g, firstVertex);
                        break;
                    case 2:
                    default:
                        minSpanTree = GraphAlgo.kruskal(g);
                        break;
                }

                System.out.println("Done");
            }
            catch(Exception e) {
                System.err.println(e.getMessage());
                System.exit(-1);
            }
        }
    }
}