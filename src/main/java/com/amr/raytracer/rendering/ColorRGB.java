package com.amr.raytracer.rendering;

import java.awt.*;

public final class ColorRGB {

    public static Color add(Color... colors) {
        int r = 0, g = 0, b = 0;
        for (Color color : colors) {
            r += color.getRed();
            g += color.getGreen();
            b += color.getBlue();
        }
        return new Color(clamp(r), clamp(g), clamp(b));
    }

    public static Color multiply(Color c1, Color c2) {
        int r = clamp(c1.getRed() * c2.getRed());
        int g = clamp(c1.getGreen() * c2.getGreen());
        int b = clamp(c1.getBlue() * c2.getBlue());
        return new Color(r, g, b);
    }

    public static Color multiply(Color c, double t) {
        int r = clamp((int) (c.getRed() * t));
        int g = clamp((int) (c.getGreen() * t));
        int b = clamp((int) (c.getBlue() * t));
        return new Color(r, g, b);
    }

    private static int clamp(int value) {
        if (value > 255) return 255;
        else if (value < 0) return 0;
        return value;
    }
}
