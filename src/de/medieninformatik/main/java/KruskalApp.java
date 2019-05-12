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
import java.util.Set;
import java.util.function.Consumer;

/**
 *
 */
public class KruskalApp extends Application {
    private Graph g;
    private Graph minSpanTree;

    /**
     *
     */
    public KruskalApp() {
        g = new Graph();
        minSpanTree = new Graph();
    }

    /**
     *
     */
    @Override
    public void init() {
        List<String> args = getParameters().getUnnamed(); // Kommandozeilenparameter
        model(args.isEmpty() ? "graph.dat" : args.get(0));
    }

    /**
     *
     * @param primaryStage
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
        primaryStage.setTitle("Kruskal");
        primaryStage.show();
    }

    /**
     *
     * @param fname
     */
    private void model(String fname) {
        Path path = Paths.get(fname);

        if(Files.isRegularFile(path)) {
            try {
                List<String> lines = Files.readAllLines(path);
                g = Graph.read(lines);
                minSpanTree = GraphAlgo.kruskal(g);
                System.out.println("Done");
            }
            catch(Exception e) {
                System.err.println(e.getMessage());
                System.exit(-1);
            }
        }
    }
}