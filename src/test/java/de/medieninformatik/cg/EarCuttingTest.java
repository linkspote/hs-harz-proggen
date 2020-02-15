package de.medieninformatik.cg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EarCuttingTest {

    @Test
    void someMethod() {
        // One of the tests has to check the amount of edges of the hull shape and compare it to the amount of
        // triangles which were used to triangulate the shape. The result should be that the amount of triangles equals
        // the amount of edges minus two, i.e. when a polygon has 5 edges the triangulation algorithm should only create
        // 3 triangles.

        // Another method to test the application is to simply calculate the area of the triangles and the hull shape
        // and compare these against each other. To be successful they have to be equal or at least nearly equal.

        // The tests are not implemented as I ran out of time due to a private issue which consumed a lot of time. I'm
        // really sorry and I apologize for that circumstance.
    }
}