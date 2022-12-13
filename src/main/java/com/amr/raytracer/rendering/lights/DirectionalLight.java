package com.amr.raytracer.rendering.lights;

import com.amr.raytracer.math.Vector3D;
import com.amr.raytracer.rendering.Scene;

import java.awt.*;

import static com.amr.raytracer.math.Vector3D.*;

/**
 * The {@code DirectionalLight} class represents a light source in space with a particular direction and no position.
 * It can be part of a {@code Scene} and it has everything the {@code Light} class does.
 *
 * @author Ángel Martínez
 * @see Light
 * @see Scene
 */
public class DirectionalLight extends Light {

    private Vector3D direction;

    public DirectionalLight(Vector3D direction, Color color, double intensity) {
        super(color, intensity);
        setDirection(direction);
    }

    private Vector3D getDirection() {
        return direction;
    }

    private void setDirection(Vector3D direction) {
        this.direction = magnitude(direction) == 1 ? direction : normalize(direction);
    }

    @Override
    public Color getColor(Vector3D point) {
        Color color = getColor();
        int r = (int) (color.getRed() * getIntensity());
        int g = (int) (color.getGreen() * getIntensity());
        int b = (int) (color.getBlue() * getIntensity());
        if (r > 255) r = 255;
        if (g > 255) g = 255;
        if (b > 255) b = 255;
        return new Color(r, g, b);
    }

    @Override
    public Vector3D getDirection(Vector3D point) {
        return negative(getDirection());
    }
}
