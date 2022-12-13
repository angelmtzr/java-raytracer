package com.amr.raytracer.rendering.lights;

import com.amr.raytracer.math.Vector3D;

import java.awt.*;

/**
 * The abstract class {@code Light} represents a generic light source in space. This class can be used to extend
 * functionality to other more specific types of light sources.
 *
 * @author Ángel Martínez
 */
public abstract class Light {

    private Color color;
    private double intensity;

    /**
     * Constructs a {@code Light} object with the given {@code Color} and intensity.
     *
     * @param color     the {@code Color}.
     * @param intensity the intensity.
     * @see Color
     */
    public Light(Color color, double intensity) {
        setColor(color);
        setIntensity(intensity);
    }

    /**
     * Returns the {@code Color} of this {@code Light}.
     *
     * @return the {@code Color} of this {@code Light}.
     * @see Color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the {@code Color} of this {@code Light}.
     *
     * @param color the {@code Color}.
     * @see Color
     * @see #getColor()
     */
    private void setColor(Color color) {
        this.color = color;
    }

    /**
     * Returns the intensity of this {@code Light}.
     *
     * @return the intensity of this {@code Light}.
     */
    public double getIntensity() {
        return intensity;
    }

    /**
     * Sets the intensity of this {@code Light}.
     *
     * @param intensity the intensity.
     * @see #getIntensity()
     */
    private void setIntensity(double intensity) {
        this.intensity = intensity;
    }

    /**
     * Calculates and returns the {@code Color} of this {@code Light} regarding its intensity and the given point
     * in space.
     *
     * @param point the point in space.
     * @return the {@code Color} of this {@code Light}.
     * @see Color
     * @see Vector3D
     */
    public abstract Color getColor(Vector3D point);

    /**
     * Calculates and returns the normalized direction of this {@code Light} regarding the given point in space.
     *
     * @param point the point in space.
     * @return the direction of this {@code Light}.
     * @see Vector3D
     */
    public abstract Vector3D getDirection(Vector3D point);
}
