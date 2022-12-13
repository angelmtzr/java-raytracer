package com.amr.raytracer.math;

import com.amr.raytracer.objects.Triangle;

import static com.amr.raytracer.math.Vector3D.dot;
import static com.amr.raytracer.math.Vector3D.subtract;

/**
 * Utility class for handling mathematical barycentric coordinates.
 *
 * @author Ángel Martínez and Jafet Rodríguez
 */
public final class Barycentric {

    private Barycentric() {
    }

    public static double[] getBarycentricCoordinates(Vector3D point, Triangle triangle) {
        double u, v, w;
        Vector3D[] vertices = triangle.getVertices();
        Vector3D a = vertices[0];
        Vector3D b = vertices[1];
        Vector3D c = vertices[2];

        Vector3D v0 = subtract(b, a);
        Vector3D v1 = subtract(c, a);
        Vector3D v2 = subtract(point, a);
        double d00 = dot(v0, v0);
        double d01 = dot(v0, v1);
        double d11 = dot(v1, v1);
        double d20 = dot(v2, v0);
        double d21 = dot(v2, v1);
        double denominator = d00 * d11 - d01 * d01;
        v = (d11 * d20 - d01 * d21) / denominator;
        w = (d00 * d21 - d01 * d20) / denominator;
        u = 1.0 - v - w;

        return new double[]{u, v, w};
    }

}