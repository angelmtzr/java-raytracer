package com.amr.raytracer.rendering;

import com.amr.raytracer.math.Intersection;
import com.amr.raytracer.math.Ray;
import com.amr.raytracer.math.Vector3D;
import com.amr.raytracer.objects.Object3D;
import com.amr.raytracer.rendering.cameras.Camera;
import com.amr.raytracer.rendering.lights.Light;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.amr.raytracer.math.Vector3D.*;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import static java.lang.Math.max;
import static java.lang.Math.pow;

/**
 * The {@code RenderEngine} class can render {@code Scene} objects using raytracing. It handles from the creation of
 * the image to save the render at to the computation of the colors for each pixel of the image regarding reflections,
 * refraction, lightning, etc.
 *
 * @author Ángel Martínez
 * @see Scene
 */
public final class RenderEngine {

    /**
     * Computes and returns the render of the given {@code Scene} as a {@code BufferedImage} with the dimensions that
     * correspond to the given resolution and aspect ratio.
     *
     * @param scene       the {@code Scene} to render.
     * @param resolution  the vertical resolution in pixels.
     * @param aspectRatio the aspect ratio.
     * @return a {@code BufferedImage} containing the render.
     * @see Scene
     * @see BufferedImage
     */
    public static BufferedImage render(Scene scene, int resolution, double aspectRatio) {
        BufferedImage image = new BufferedImage((int) (resolution * aspectRatio), resolution, TYPE_INT_RGB);

        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        for (int x = 0; x < imageWidth; x++) {
            for (int y = 0; y < imageHeight; y++) {
                double[] uv = getScreenCoordinates(x, y, imageWidth, imageHeight);
                Color color = computeColor(scene, uv[0], uv[1]);
                image.setRGB(x, y, color.getRGB());
            }
        }

        return image;
    }

    /**
     * Calculates and returns the UV screen coordinates that correspond with the given image pixel coordinate and
     * the given image dimensions.
     *
     * @param x      the horizontal pixel coordinate.
     * @param y      the vertical pixel coordinate.
     * @param width  the width of the image.
     * @param height the height of the image.
     * @return an array with the corresponding UV screen coordinates.
     */
    private static double[] getScreenCoordinates(int x, int y, int width, int height) {
        double u, v;
        if (width > height) {
            u = (double) (x - width / 2 + height / 2) / height * 2 - 1;
            v = -((double) y / height * 2 - 1);
        } else {
            u = (double) x / width * 2 - 1;
            v = -((double) (y - height / 2 + width / 2) / width * 2 - 1);
        }
        return new double[]{u, v};
    }

    /**
     * Calculates and returns the {@code Color} that should be colored at the given UV coordinates of the screen
     * regarding the given {@code Scene}.
     *
     * @param scene the {@code Scene}.
     * @param u     the horizontal screen coordinate.
     * @param v     the vertical screen coordinate.
     * @return the {@code Color} that should be colored.
     */
    private static Color computeColor(Scene scene, double u, double v) {
        Camera camera = scene.getCamera();
        Ray ray = camera.makeRay(u, v);
        return raycast(ray, scene, null);
    }

    private static Color raycast(Ray ray, Scene scene, Object3D caster) {
        Camera camera = scene.getCamera();
        Intersection intersection = getClosestIntersection(ray, scene, caster);
        if (intersection == null) return Color.BLACK; // Background is black

        Color pixelColor = Color.BLACK;
        Color ambient = getAmbient(intersection);
        pixelColor = ColorRGB.add(pixelColor, ambient);
        Object3D object = intersection.getObject();
        Vector3D P = intersection.getPosition();

        for (Light light : scene.getLights()) {
            Vector3D L = light.getDirection(P);
            Ray rayToLight = new Ray(P, L);
            if (getClosestIntersection(rayToLight, scene, object) != null) continue;
            Color diffuse = getDiffuse(intersection, light);
            Color specular = getSpecular(intersection, camera, light);
            pixelColor = ColorRGB.add(pixelColor, diffuse, specular);
        }

        Ray reflectionRay = getReflectionRay(intersection, scene);
        Color reflectionColor = raycast(reflectionRay, scene, object);
        pixelColor = ColorRGB.add(pixelColor, reflectionColor);
        return pixelColor;
    }

    /**
     * Calculates and returns the closest {@code Intersection} regarding the origin of the given {@code Ray} and all
     * the {@code Object3D} in this {@code Scene}. Intersections that may occur outside the clipping planes of the
     * {@code Camera} of this scene will be omitted, except if it is a shadow ray.
     *
     * @param ray    the cast {@code Ray}.
     * @param scene  the scene.
     * @param caster the {@code Object3D} that cast the ray.
     * @return the closest {@code Intersection}. If no intersection, {@code null}.
     * @see Intersection
     * @see Ray
     * @see Object3D
     * @see Camera
     */
    private static Intersection getClosestIntersection(Ray ray, Scene scene, Object3D caster) {
        double nearPlane = scene.getCamera().getNearClippingPlane();
        double farPlane = scene.getCamera().getFarClippingPlane();
        Intersection closestIntersection = null;
        for (Object3D object : scene.getObjects()) {
            // Avoid colliding with yourself
            if (object.equals(caster)) continue;
            Intersection intersection = object.getIntersection(ray);
            if (intersection == null) continue;
            double distance = intersection.getDistanceFrom(ray.getOrigin());
            // Intersections outside the clipping planes range of the camera are omitted
            // Only when the camera is the caster (when caster is null)
            if (caster == null && distance <= nearPlane || distance >= farPlane)
                continue;
            if (closestIntersection == null) closestIntersection = intersection;
            else if (distance < closestIntersection.getDistanceFrom(ray.getOrigin())) {
                closestIntersection = intersection;
            }
        }
        return closestIntersection;
    }

    /**
     * Calculates and returns the ambient color at the given intersection.
     *
     * @param intersection the intersection.
     * @return the ambient color.
     */
    private static Color getAmbient(Intersection intersection) {
        Color objectColor = intersection.getObject().getColor();
        double ambient = 0.3; // TODO: Get from object material
        return ColorRGB.multiply(objectColor, ambient);
    }

    /**
     * Calculates and returns the diffuse color regarding the given intersection and light.
     *
     * @param intersection the intersection.
     * @param light the light.
     * @return the diffuse color.
     */
    private static Color getDiffuse(Intersection intersection, Light light) {
        Vector3D N = intersection.getNormal();
        Vector3D P = intersection.getPosition();
        Vector3D L = light.getDirection(P);
        Color lightColor = light.getColor(P);
        Color objectColor = intersection.getObject().getColor();
        Color diffuseColor = ColorRGB.multiply(objectColor, 0.18); // TODO: Get from individual objects
        return ColorRGB.multiply(ColorRGB.add(diffuseColor, lightColor), max(0, dot(N, L)));
    }

    /**
     * Calculates and returns the specular color regarding the given intersection, camera and light.
     *
     * @param intersection the intersection.
     * @param camera the camera.
     * @param light the light.
     * @return the specular color.
     */
    private static Color getSpecular(Intersection intersection, Camera camera, Light light) {
        Vector3D P = intersection.getPosition();
        Vector3D V = normalize(subtract(camera.getPosition(), P));
        Vector3D L = light.getDirection(P);
        Vector3D H = normalize(add(L, V));
        Vector3D N = intersection.getNormal();
        Color objectColor = intersection.getObject().getColor();
        Color lightColor = light.getColor(P);
        Color specularColor = ColorRGB.multiply(objectColor, 1.9); // TODO: Get from individual objects
        double phongExponent = 200; // TODO: Get from individual objects
        double shininess = pow(dot(N, H), phongExponent);
        return ColorRGB.multiply(ColorRGB.multiply(specularColor, lightColor), shininess);
    }

    private static Ray getReflectionRay(Intersection intersection, Scene scene) {
        Camera camera = scene.getCamera();
        Vector3D N = intersection.getNormal();
        Vector3D P = intersection.getPosition();
        Vector3D D = normalize(subtract(P, camera.getPosition()));
        Vector3D R = subtract(D , multiply(N, 2 * dot(D, N)));
        return new Ray(P, R);
    }
}
