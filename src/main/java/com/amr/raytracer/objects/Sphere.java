package com.amr.raytracer.objects;

import com.amr.raytracer.math.Intersection;
import com.amr.raytracer.math.Ray;
import com.amr.raytracer.math.Vector3D;

import java.awt.*;

import static com.amr.raytracer.math.Vector3D.*;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * The {@code Sphere} class represents a sphere in space with a specific radius and all the properties of an
 * {@code Object3D}. Known for being one of the easiest objects to raytrace.
 *
 * @author Ángel Martínez
 * @see Object3D
 */
public class Sphere extends Object3D {

    private double radius;

    /**
     * Constructs a {@code Sphere} object a the given position, with the given radius and {@code Color}.
     *
     * @param position the position in space.
     * @param radius the radius.
     * @param color  the {@code Color}.
     * @see Object3D#Object3D(Vector3D, Color)
     * @see Sphere#setRadius(double)
     * @see Color
     */
    public Sphere(Vector3D position, double radius, Color color) {
        super(position, color);
        setRadius(radius);
    }

    /**
     * Returns the radius of this {@code Sphere}.
     *
     * @return the radius of this {@code Sphere}.
     * @see Sphere#setRadius(double)
     */
    private double getRadius() {
        return radius;
    }

    /**
     * Sets the radius of this {@code Sphere}.
     *
     * @param radius the new radius.
     * @see Sphere#getRadius()
     */
    private void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * Returns the nearest {@code Intersection} of this {@code Sphere} and the given {@code Ray}. If there are no
     * intersections the return value will be {@code null}.
     *
     * @param ray the {@code Ray} to check for intersections.
     * @return the nearest {@code Intersection} of this {@code Sphere} and the given {@code Ray}. If no intersections,
     * {@code null}.
     * @see Intersection
     * @see Ray
     * @see Vector3D
     */
    @Override
    public Intersection getIntersection(Ray ray) {
        double t = dot(subtract(getPosition(), ray.getOrigin()), ray.getDirection());
        Vector3D P = ray.getPoint(t);
        double y = magnitude(subtract(getPosition(), P));
        if (y >= getRadius()) return null;
        double x = sqrt(pow(getRadius(), 2) - pow(y, 2));
        double t1 = t - x;
        if (t1 <= 0) return null;
        P = ray.getPoint(t1);
        Vector3D N = normalize(subtract(P, getPosition()));
        return new Intersection(ray, this, P, N);
    }
}
