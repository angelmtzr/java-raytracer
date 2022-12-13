package com.amr.raytracer.objects;

import com.amr.raytracer.math.Intersection;
import com.amr.raytracer.math.Ray;
import com.amr.raytracer.math.Vector3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.amr.raytracer.math.Barycentric.getBarycentricCoordinates;
import static com.amr.raytracer.math.Vector3D.multiply;

public class Model3D extends Object3D {

    private ArrayList<Triangle> triangles;

    public Model3D(Vector3D position, Color color, ArrayList<Triangle> triangles) {
        super(position, color);
        setTriangles(triangles);
    }

    public ArrayList<Triangle> getTriangles() {
        return triangles;
    }

    public void setTriangles(ArrayList<Triangle> triangles) {
        Vector3D position = getPosition();
        HashSet<Vector3D> uniqueVertices = new HashSet<>();
        for(Triangle triangle : triangles){
            uniqueVertices.addAll(List.of(triangle.getVertices()));
        }

        for(Vector3D vertex : uniqueVertices){
            vertex.setX(vertex.getX() + position.getX());
            vertex.setY(vertex.getY() + position.getY());
            vertex.setZ(vertex.getZ() + position.getZ());
        }

        this.triangles = triangles;
    }

    @Override
    public Intersection getIntersection(Ray ray) {
        Intersection closestIntersection = null;
        for (Triangle triangle : getTriangles()) {
            Intersection intersection = triangle.getIntersection(ray);
            if (intersection == null) continue;
            intersection.setObject(this);
            Vector3D N = new Vector3D();
            double[] uVw = getBarycentricCoordinates(intersection.getPosition(), triangle);
            Vector3D[] normals = triangle.getNormals();
            for(int i = 0; i < uVw.length; i++) {
                N = Vector3D.add(N, multiply(normals[i], uVw[i]));
            }
            intersection.setNormal(N);
            double distance = intersection.getDistanceFrom(ray.getOrigin());
            if (closestIntersection == null) closestIntersection = intersection;
            else if (distance < closestIntersection.getDistanceFrom(ray.getOrigin())) {
                closestIntersection = intersection;
            }
        }
        return closestIntersection;
    }
}
