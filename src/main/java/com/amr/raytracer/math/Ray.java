package com.amr.raytracer.math;

import static com.amr.raytracer.math.Vector3D.*;
import static com.amr.raytracer.math.Vector3D.multiply;

/**
 * The {@code Ray} class represents a ray with an origin and direction. It's commonly used for raytracing and/or
 * ray casting algorithms.<br>
 * <b>IMPORTANT: The direction of a ray, generally speaking, should be normalized due it's mathematical equation.
 * This class makes sure of that by normalizing the direction whenever it is set only if it's not yet a unit vector.</b>
 *
 * @author Ángel Martínez
 * @see Vector3D
 */
public class Ray {

    private Vector3D origin;
    private Vector3D direction;

    /**
     * Constructs a {@code Ray} object with the given origin and direction.
     *
     * @param origin the origin.
     * @param direction the direction.
     * @see Ray#setOrigin(Vector3D)
     * @see Ray#setDirection(Vector3D)
     */
    public Ray(Vector3D origin, Vector3D direction) {
        setOrigin(origin);
        setDirection(direction);
    }

    /**
     * Returns the origin of this {@code Ray}.
     *
     * @return the origin of this {@code Ray}.
     * @see Ray#setOrigin(Vector3D)
     */
    public Vector3D getOrigin() {
        return origin;
    }

    /**
     * Sets the origin of this {@code Ray}.
     *
     * @param origin the new origin.
     * @see Ray#getOrigin()
     */
    private void setOrigin(Vector3D origin) {
        this.origin = origin;
    }

    /**
     * Returns the direction of this {@code Ray}.
     *
     * @return the direction of this {@code Ray}.
     * @see Ray#setDirection(Vector3D)
     */
    public Vector3D getDirection() {
        return direction;
    }

    /**
     * Sets the direction of this {@code Ray}. The vector is normalized if it is not unitary yet.
     *
     * @param direction the new direction.
     * @see Ray#getDirection()
     */
    private void setDirection(Vector3D direction) {
        this.direction = magnitude(direction) == 1 ? direction : normalize(direction);
    }

    /**
     * Returns the {@code Vector3D} point in this {@code Ray} that corresponds to the given {@code t} value.
     * The mathematical equation used to calculate the result is the following. <br>
     * <br>
     * Equation: {@code point = origin + direction * t}
     *
     * @param t the {@code t} value.
     * @return the {@code Vector3D} representing the at the given {@code t} value.
     * @see Vector3D
     */
    public Vector3D getPoint(double t) {
        return add(getOrigin(), multiply(getDirection(), t));
    }
}
