package com.amr.raytracer.objects;

import com.amr.raytracer.math.Intersection;
import com.amr.raytracer.math.Ray;
import com.amr.raytracer.math.Vector3D;

import java.awt.*;

/**
 * The {@code Plane} class represents an infinite plane positioned horizontally in space.
 *
 * @author Ángel Martínez
 * @see Object3D
 * @see Intersectable
 */
public class Plane extends Object3D implements Intersectable {

    /**
     * Constructs a {@code Plane} object with the given properties.
     *
     * @param y     the y coordinate where the plane is positioned.
     * @param color the {@code Color}.
     */
    public Plane(double y, Color color) {
        super(new Vector3D(0, y, 0), color);
    }


    @Override
    public Intersection getIntersection(Ray ray) {
        double t = -(ray.getOrigin().getY() - getPosition().getY()) / ray.getDirection().getY();
        if (t <= 0 || !Double.isFinite(t)) return null;
        Vector3D P = ray.getPoint(t);
        return new Intersection(ray, this, P, new Vector3D(0, 1, 0));
    }
}
