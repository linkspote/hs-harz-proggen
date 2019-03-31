import geometry.*;
import sets.*;

public class Main {

    public static void main(String[] args) {
        // TODO: - generische Klasse Sets für Schnitt und Vereinigungsmengen
        // TODO: - Sets soll <? extends T> und <? super T> bei Bildung der Schnitt und Vereinigungsmengen nutzen
        // TODO: - Beispielklassen implementieren, testen und ggf. erweitern
        // abstract public GeometrieObjekt { ... }
        // public Quader extends GeometrieObjekt { public Quader(int laenge, int breite, int hoehe) { .... } ....  }
        // public Rechteck extends Quader { public Rechteck(int laenge, breite) { super(laenge, breite, 0); } .... }
        // public Kugel extends GeometrieObjekt { public Kugel(int radius) { ... } .... }
        // public Kreis extends Kugel { public Kreis(int radius) { super(radius); } ... }
        // TODO: - Gleichheit zwischen den GeoObjekten beachten, Quader != Rechteck und Kreis != Kugel
        // TODO: - Klasse SetList<T> soll beim Einfügen nur Unikate annehmen
        // TODO: - Klasse SetList<T> hat ArrayList<T> als Basisklasse
        // TODO: - Methoden aus Sets sollen für alle SetList<T> funktionieren

        SetList<GeoObject> p1 = new SetList<>(), p2 = new SetList<>(), p3 = new SetList<>(), p4 = new SetList<>();
        p1.add(new Cuboid(5, 5, 5));
        p1.add(new Sphere(15));
        p1.add(new Circle(15));
        p2.add(new Circle(15));
        p2.add(new Cuboid(5, 5, 5));
        p2.add(new Rectangle(5, 5));

        Sets.getIntersection(p1, p2, p3);
        Sets.getUnion(p1, p2, p4);

        System.out.println("Intersection: " + p3);
        System.out.println("Union: " + p4);
    }

}