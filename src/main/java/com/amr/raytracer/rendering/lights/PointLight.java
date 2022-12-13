package com.amr.raytracer.rendering.lights;

import com.amr.raytracer.rendering.ColorRGB;
import com.amr.raytracer.math.Vector3D;
import com.amr.raytracer.rendering.Scene;

import java.awt.*;

import static com.amr.raytracer.math.Vector3D.*;
import static java.lang.Math.PI;
import static java.lang.Math.pow;

/**
 * The {@code PointLight} class represents a light source in space with a particular position, but it illuminates in
 * all directions. It can be part of a {@code Scene} and it has everything the {@code Light} class does.
 *
 * @author Ángel Martínez
 * @see Light
 * @see Scene
 */
public class PointLight extends Light {

    private Vector3D position;

    public PointLight(Vector3D position, Color color, double intensity) {
        super(color, intensity);
        setPosition(position);
    }

    public Vector3D getPosition() {
        return position;
    }

    private void setPosition(Vector3D position) {
        this.position = position;
    }

    @Override
    public Color getColor(Vector3D point) {
        double r2 = pow(magnitude(subtract(getPosition(), point)), 2);
        double denominator = 4 * PI * r2;
        return ColorRGB.multiply(getColor(), getIntensity() / denominator);
    }

    @Override
    public Vector3D getDirection(Vector3D point) {
        return normalize(subtract(getPosition(), point));
    }
}
