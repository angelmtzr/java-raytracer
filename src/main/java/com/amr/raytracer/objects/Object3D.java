package com.amr.raytracer.objects;

import com.amr.raytracer.math.Vector3D;

import java.awt.*;

/**
 * The abstract class {@code Object3D} represents a generic solid object in space. It implements the
 * {@code Intersectable} interface which allows instances of this class to be intersected by a ray regarding a
 * raytracing algorithm. This class can be used to extend functionality to other more specific types of solid
 * objects.
 *
 * @author Ángel Martínez
 * @see Intersectable
 */
public abstract class Object3D implements Intersectable {

    private Vector3D position;
    private Color color;

    /**
     * Constructs a {@code Object3D} object with the given position in space and {@code Color}.
     *
     * @param position the position in space.
     * @param color    the {@code Color}.
     * @see Vector3D
     * @see Color
     */
    public Object3D(Vector3D position, Color color) {
        setPosition(position);
        setColor(color);
    }

    /**
     * Returns the position in space of this {@code Object3D}.
     *
     * @return the position in space of this {@code Object3D}.
     * @see Vector3D
     */
    public Vector3D getPosition() {
        return position;
    }

    /**
     * Sets the position in space of this {@code Object3D}.
     *
     * @param position the new position in space.
     * @see Vector3D
     * @see #getPosition()
     */
    private void setPosition(Vector3D position) {
        this.position = position;
    }

    /**
     * Returns the {@code Color} of this {@code Object3D}.
     *
     * @return the {@code Color} of this {@code Object3D}.
     * @see Color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the {@code Color} of this {@code Object3D}.
     *
     * @param color the new {@code Color}.
     * @see Color
     * @see #getColor()
     */
    protected void setColor(Color color) {
        this.color = color;
    }
}
