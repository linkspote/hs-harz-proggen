package de.medieninformatik.cg;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EarCutting extends Application {

    private Polygon poly = new Polygon();
    private List<Point> lsPoints = new ArrayList<>();
    private List<Polygon> lsTriangles = new ArrayList<>();

    private Stroke stroke = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    private Color color = new Color(255, 0, 0, 64);

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Group root = new Group();
        Canvas canvas = new Canvas(600, 480);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.getButton() == MouseButton.PRIMARY) {
                    lsPoints.add(new Point((int) e.getX(), (int) e.getY()));
                    System.out.println(lsPoints.get(lsPoints.size() - 1));
                    gc.setStroke(javafx.scene.paint.Color.BLUE);
                    gc.beginPath();
                    gc.lineTo(lsPoints.get(lsPoints.size()-1).x, lsPoints.get(lsPoints.size()-1).y);
                    gc.stroke();
                }
                else if (e.getButton() == MouseButton.SECONDARY)
                    System.out.println("Triangulieren");
                else if (e.getButton() == MouseButton.MIDDLE)
                    System.out.println("Aufräumen");
            }
        });

        root.getChildren().add(canvas);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Interactive Ear Cutting");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    /**
     *
     * @param g2d
     */
    private void drawPolygon(Graphics2D g2d) {
        g2d.setStroke(stroke);

        for (int i = 0; i < lsPoints.size(); i++) {
            Point p1 = lsPoints.get(i % lsPoints.size());
            Point p2 = lsPoints.get((i + 1) % lsPoints.size());
            g2d.setColor(Color.BLUE);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    /**
     *
     * @param g2d
     */
    private void drawTriangles(Graphics2D g2d) {
        g2d.setStroke(stroke);

        for (Polygon triangle : lsTriangles) {
            g2d.setColor(color);
            g2d.fillPolygon(triangle);
            g2d.setColor(Color.RED);
            g2d.drawPolygon(triangle);
        }
    }
}
