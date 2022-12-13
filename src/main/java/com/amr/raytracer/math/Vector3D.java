package com.amr.raytracer.math;

import static java.lang.Math.*;

/**
 * The {@code Vector3D} class represents a 3D vector with {@code x}, {@code y}, and {@code z} coordinates.
 * This class also provides useful methods to do operations with vectors such as: addition, subtraction, scalar
 * multiplication, dot product, cross product, among others.
 *
 * @author Ángel Martínez
 */
public class Vector3D {

    private double x;
    private double y;
    private double z;

    /**
     * Constructs a {@code Vector3D} object with the given x, y, and z coordinates.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @param z the z coordinate.
     * @see #Vector3D(double)
     * @see #Vector3D()
     */
    public Vector3D(double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    /**
     * Constructs a {@code Vector3D} object assigning the given decimal value to all the coordinates.
     *
     * @param d the decimal value.
     * @see #Vector3D(double, double, double)
     * @see #Vector3D()
     */
    public Vector3D(double d) {
        this(d, d, d);
    }

    /**
     * Constructs a {@code Vector3D} object assigning 0 to all the coordinates.
     *
     * @see #Vector3D(double, double, double)
     * @see #Vector3D(double)
     */
    public Vector3D() {
        this(0);
    }

    /**
     * Returns the x coordinate of this {@code Vector3D}.
     *
     * @return the x coordinate of this {@code Vector3D}.
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the x coordinate of this {@code Vector3D}.
     *
     * @param x the new value for the x coordinate.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Returns the y coordinate of this {@code Vector3D}.
     *
     * @return the y coordinate of this {@code Vector3D}.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the y coordinate of this {@code Vector3D}.
     *
     * @param y the new value for the y coordinate.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Returns the z coordinate of this {@code Vector3D}.
     *
     * @return the z coordinate of this {@code Vector3D}.
     */
    public double getZ() {
        return z;
    }

    /**
     * Sets the z coordinate of this {@code Vector3D}.
     *
     * @param z the new value for the z coordinate.
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Calculates and returns the length of this {@code Vector3D}.
     *
     * @return the length of this {@code Vector3D}.
     * @see #magnitude(Vector3D)
     */
    public double length() {
        return sqrt(dot(this, this));
    }

    /**
     * Returns a string representation of this {@code Vector3D}.
     *
     * @returns a string representation of this {@code Vector3D}.
     */
    @Override
    public String toString() {
        return "Vector3D{" +
                "x=" + getX() +
                ", y=" + getY() +
                ", z=" + getZ() +
                '}';
    }

    // Operations with vectors

    /**
     * Calculates and returns the dot product of the given vectors.
     *
     * @param a the first vector.
     * @param b the second vector.
     * @return the dot product of the given vectors.
     */
    public static double dot(Vector3D a, Vector3D b) {
        return a.getX() * b.getX() + a.getY() * b.getY() + a.getZ() * b.getZ();
    }

    /**
     * Calculates and returns the cross product of the given vectors.
     *
     * @param a the first vector.
     * @param b the second vector.
     * @return the cross product of the given vectors.
     */
    public static Vector3D cross(Vector3D a, Vector3D b) {
        return new Vector3D(
                a.getY() * b.getZ() - a.getZ() * b.getY(),
                a.getZ() * b.getX() - a.getX() * b.getZ(),
                a.getX() * b.getY() - a.getY() * b.getX()
        );
    }

    /**
     * Calculates and returns the magnitude of the given {@code Vector3D}.
     *
     * @param a the vector.
     * @return the magnitude of the given {@code Vector3D}.
     * @see #length()
     */
    public static double magnitude(Vector3D a) {
        return sqrt(dot(a, a));
    }

    /**
     * Calculates and returns the sum of the given vectors.
     *
     * @param a the first vector.
     * @param b the second vector.
     * @return the sum of the given vectors.
     */
    public static Vector3D add(Vector3D a, Vector3D b) {
        return new Vector3D(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ() + b.getZ());
    }

    /**
     * Calculates and returns the subtraction of the given vectors.
     *
     * @param a the first vector.
     * @param b the second vector.
     * @return the subtraction of the given vectors.
     */
    public static Vector3D subtract(Vector3D a, Vector3D b) {
        return new Vector3D(a.getX() - b.getX(), a.getY() - b.getY(), a.getZ() - b.getZ());
    }

    /**
     * Calculates and returns a new {@code Vector3D} with all of its components multiplied by the given decimal value.
     *
     * @param a the vector.
     * @param d the decimal value.
     * @return the multiplication of the given vector and the given decimal value.
     */
    public static Vector3D multiply(Vector3D a, double d) {
        return new Vector3D(a.getX() * d, a.getY() * d, a.getZ() * d);
    }

    /**
     * Calculates and returns a new {@code Vector3D} with all of its components divied by the given decimal value.
     *
     * @param a the vector.
     * @param d the decimal value.
     * @return the division of the given vector and the given decimal value.
     */
    public static Vector3D divide(Vector3D a, double d) {
        return multiply(a, 1 / d);
    }

    /**
     * Calculates and returns a normalized version of the given {@code Vector3D}
     *
     * @param v the vector.
     * @return a normalized version of this {@code Vector3D}.
     */
    public static Vector3D normalize(Vector3D v) {
        return divide(v, magnitude(v));
    }

    public static Vector3D negative(Vector3D v) {
        return new Vector3D(-v.getX(), -v.getY(), -v.getZ());
    }

    public Vector3D rotateYP(double yaw, double pitch) {

        // Rotate around X axis (pitch)
        double y = getY() * cos(pitch) - getZ() * sin(pitch);
        double z = getY() * sin(pitch) + getZ() * cos(pitch);

        // Rotate around the Y axis (yaw)
        double x = getX() * cos(yaw) + z * sin(yaw);
        z = -getX() * sin(yaw) + z * cos(yaw);

        return new Vector3D(x, y, z);
    }
}
