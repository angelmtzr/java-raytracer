package com.amr.raytracer.objects;

import com.amr.raytracer.math.Intersection;
import com.amr.raytracer.math.Ray;
import com.amr.raytracer.math.Vector3D;

import static com.amr.raytracer.math.Vector3D.*;

public class Triangle implements Intersectable {

    private static final double EPSILON = 0.0000001;

    private Vector3D[] vertices;
    private Vector3D[] normals;

    public Triangle(Vector3D v1, Vector3D v2, Vector3D v3) {
        this(new Vector3D[]{v1, v2, v3}, null);
    }

    public Triangle(Vector3D[] vertices, Vector3D[] normals) {
        setVertices(vertices);
        setNormals(normals);
    }

    public Vector3D[] getVertices() {
        return vertices;
    }

    private void setVertices(Vector3D[] vertices) {
        this.vertices = vertices;
    }

    public Vector3D[] getNormals() {
        return normals;
    }

    public void setNormals(Vector3D[] normals) {
        this.normals = normals;
    }

    @Override
    public Intersection getIntersection(Ray ray) {
        Vector3D[] vert = getVertices();
        Vector3D edge1 = subtract(vert[1], vert[0]);
        Vector3D edge2 = subtract(vert[2], vert[0]);
        Vector3D h = cross(ray.getDirection(), edge2);
        double a = dot(edge1, h);
        if (a > -EPSILON && a < EPSILON) return null;
        double f = 1 / a;
        Vector3D s = subtract(ray.getOrigin(), vert[0]);
        double u = f * dot(s, h);
        if (u < 0 || u > 1) return null;
        Vector3D q = cross(s, edge1);
        double v = f * dot(ray.getDirection(), q);
        if (v < 0 || u + v > 1) return null;
        double t = f * dot(edge2, q);
        if (t <= EPSILON) return null;
        Vector3D P = ray.getPoint(t);
        return new Intersection(ray, null, P, null);
    }
/*
    @Override
    public Vector3D getNormalAt(Vector3D point) {
        Vector3D normal = new Vector3D();
        Vector3D[] normals = getNormals();
        if(normals == null){
            Vector3D[] vert = getVertices();
            Vector3D v = subtract(vert[1], vert[2]);
            Vector3D w = subtract(vert[0], vert[2]);
            normal = normalize(cross(v,w));
        }
        return normal;
    }*/
}
