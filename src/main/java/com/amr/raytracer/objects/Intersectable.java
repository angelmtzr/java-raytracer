package com.amr.raytracer.objects;

import com.amr.raytracer.math.Intersection;
import com.amr.raytracer.math.Ray;

/**
 * The {@code Intersectable} interface assures that objects implementing it have a method to intersect with rays from a
 * raytracing algorithm. Also, such objects must implement a method to
 *
 * @author Ángel Martínez
 */
public interface Intersectable {

    /**
     * Calculates and returns the nearest {@code Intersection} of this {@code Object3D} and the given {@code Ray}. If there are no
     * intersections the return value will be {@code null}.
     *
     * @param ray the {@code Ray} to check for intersections.
     * @return the nearest {@code Intersection} of this {@code Object3D} and the given {@code Ray}. If no intersections,
     * {@code null}.
     * @see Intersection
     * @see Ray
     */
    Intersection getIntersection(Ray ray);

}
