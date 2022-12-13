package com.amr.raytracer;

import com.amr.raytracer.math.Vector3D;
import com.amr.raytracer.objects.Object3D;
import com.amr.raytracer.objects.Plane;
import com.amr.raytracer.objects.Sphere;
import com.amr.raytracer.rendering.RenderEngine;
import com.amr.raytracer.rendering.Scene;
import com.amr.raytracer.rendering.cameras.PerspectiveCamera;
import com.amr.raytracer.rendering.lights.Light;
import com.amr.raytracer.rendering.lights.PointLight;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import static com.amr.raytracer.tools.ObjReader.loadModel;

/**
 * The {@code JavaRaytracer} class is the responsible for the principal functionality of this project's raytracer.
 * It contains a {@code main} function where all objects will be created so that they can be rendered, and a
 * {@code saveImage} function to save the rendered image in a file.
 *
 * @author Ángel Martínez
 * @version 0.2
 * @see Scene
 * @see Object3D
 * @see Light
 * @see RenderEngine
 * @see LocalDateTime
 */
public class JavaRaytracer {

    /**
     * Creates all the {@code Object3D} and {@code Light} for the {@code Scene}, as well as its {@code Camera}.
     * Then, it can then be rendered using the {@code RenderEngine} class and later be saved to a file. The current
     * {@code LocalDateTime} is printed to estimate the time the render took to be completed.
     *
     * @param args the arguments sent to the program via command line.
     */
    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());

        BufferedImage image;
        image = RenderEngine.render(getTeapotScene(), 400, 16f / 9);
        saveImage(image, "teapot.png");
        image = RenderEngine.render(getAppleScene(), 400, 16f / 9);
        saveImage(image, "apple.png");
        image = RenderEngine.render(getFloorSphereScene(), 400, 16f / 9);
        saveImage(image, "sphere.png");
        image = RenderEngine.render(getSpheresScene(), 400, 16f / 9);
        saveImage(image, "spheres.png");
        image = RenderEngine.render(getMikeScene(), 400, 16f / 9);
        saveImage(image, "mike.png");

        System.out.println(LocalDateTime.now());
    }

    /**
     * Saves the given {@code BufferedImage} as a PNG in a new file with the given name or overwrites it
     * if it already exists in the folder {@code renders/} of this project.
     *
     * @param image    the {@code BufferedImage} to save.
     * @param filename the name of the file.
     */
    public static void saveImage(BufferedImage image, String filename) {
        try {
            File file = new File("renders/" + filename);
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            System.err.println("There was an error saving the image." + e);
        }
    }

    public static Scene getTeapotScene() {
        Scene scene = new Scene();
        scene.setCamera(new PerspectiveCamera(new Vector3D(0, 2, -2), 0, 20,
                0.1,10000, 60));
        scene.addObject(new Plane(0, Color.GRAY));
        scene.addObject(loadModel("models/SmallTeapot.obj", new Vector3D(), Color.CYAN));
        scene.addLight(new PointLight(new Vector3D(0, 4, 0), Color.WHITE, 30));
        return scene;
    }

    public static Scene getSpheresScene() {
        Scene scene = new Scene();
        scene.setCamera(new PerspectiveCamera(new Vector3D(0, 2, -7), 0, 15,
                0.1,10000, 60));
        scene.addObject(new Plane(0, Color.DARK_GRAY));
        scene.addObject(new Sphere(new Vector3D(-6,1,0), 1, Color.ORANGE));
        scene.addObject(new Sphere(new Vector3D(-3,1,0), 1, Color.ORANGE));
        scene.addObject(new Sphere(new Vector3D(0,1,0), 1, Color.ORANGE));
        scene.addObject(new Sphere(new Vector3D(3,1,0), 1, Color.ORANGE));
        scene.addObject(new Sphere(new Vector3D(6,1,0), 1, Color.ORANGE));
        scene.addLight(new PointLight(new Vector3D(0, 6, 0), Color.WHITE, 100));
        return scene;
    }

    public static Scene getMikeScene() {
        Scene scene = new Scene();
        scene.setCamera(new PerspectiveCamera(new Vector3D(0, 5, -5), 0, 25,
                0.1, 10000, 60));
        scene.addObject(new Plane(0, Color.DARK_GRAY));
        scene.addObject(loadModel("models/mike.obj", new Vector3D(), new Color(128, 154, 111)));
        scene.addLight(new PointLight(new Vector3D(0, 4, 0), Color.WHITE, 3));
        return scene;
    }

    public static Scene getFloorSphereScene() {
        Scene scene = new Scene();
        scene.setCamera(new PerspectiveCamera(new Vector3D(0, 3, -4), 0, 20,
                0.1,10000, 60));
        scene.addObject(new Plane(0, Color.DARK_GRAY));
        scene.addObject(new Sphere(new Vector3D(0,1,0), 1, Color.GRAY));
        scene.addLight(new PointLight(new Vector3D(0, 4, 0), Color.WHITE, 3));
        return scene;
    }

    public static Scene getAppleScene() {
        Scene scene = new Scene();
        scene.setCamera(new PerspectiveCamera(new Vector3D(0, 5, -5), 0, 20,
                0.1,10000, 60));
        scene.addObject(new Plane(0, new Color(204, 156, 117)));
        scene.addObject(loadModel("models/Apple.obj", new Vector3D(), new Color(153, 0, 0)));
        scene.addLight(new PointLight(new Vector3D(0, 4, 0), Color.WHITE, 3));
        return scene;
    }
}
