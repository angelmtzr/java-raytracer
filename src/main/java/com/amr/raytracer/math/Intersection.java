package com.amr.raytracer.math;

import com.amr.raytracer.objects.Object3D;

import static com.amr.raytracer.math.Vector3D.magnitude;
import static com.amr.raytracer.math.Vector3D.subtract;

public class Intersection {

    private Ray ray;
    private Object3D object;
    private Vector3D position;
    private Vector3D normal;
    /*
    public Intersection(Ray ray, Object3D object, Vector3D position) {
        setRay(ray);
        setObject(object);
        setPosition(position);
        setNormal(object.getNormalAt(position));
    }*/

    public Intersection(Ray ray, Object3D object, Vector3D position, Vector3D normal) {
        setRay(ray);
        setObject(object);
        setPosition(position);
        setNormal(normal);
    }

    /**
     * Returns the {@code Ray} of this {@code Intersection}.
     *
     * @return the {@code Ray} of this {@code Intersection}.
     */
    public Ray getRay() {
        return ray;
    }

    /**
     * Sets the {@code Ray} of this {@code Intersection}.
     *
     * @param ray the new {@code Ray}.
     */
    private void setRay(Ray ray) {
        this.ray = ray;
    }

    /**
     * Returns the position in space of this {@code Intersection}.
     *
     * @return the position in space of this {@code Intersection}.
     */
    public Vector3D getPosition() {
        return position;
    }

    /**
     * Sets the position in space of this {@code Intersection}.
     *
     * @param position the new position in space.
     */
    private void setPosition(Vector3D position) {
        this.position = position;
    }

    /**
     * Returns the normal of this {@code Intersection}.
     *
     * @return the normal of this {@code Intersection}.
     */
    public Vector3D getNormal() {
        return normal;
    }

    /**
     * Sets the normal of this {@code Intersection}.
     *
     * @param normal the new normal.
     */
    public void setNormal(Vector3D normal) {
        this.normal = normal;
    }

    /**
     * Returns the {@code Object3D} of this {@code Intersection}.
     *
     * @return the {@code Object3D} of this {@code Intersection}.
     */
    public Object3D getObject() {
        return object;
    }

    /**
     * Sets the {@code Object3D} of this {@code Intersection}.
     *
     * @param object the new {@code Object3D}.
     */
    public void setObject(Object3D object) {
        this.object = object;
    }

    /**
     * Calculates and returns the distance from the given point to the position of this {@code Intersection}.
     *
     * @param point the {@code Vector3D} point to measure the distance from.
     * @return the distance from the given point to the position of this {@code Intersection}.
     */
    public double getDistanceFrom(Vector3D point) {
        return magnitude(subtract(getPosition(), point));
    }
}
