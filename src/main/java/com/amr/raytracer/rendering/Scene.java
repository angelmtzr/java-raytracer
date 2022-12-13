package com.amr.raytracer.rendering;

import com.amr.raytracer.objects.Object3D;
import com.amr.raytracer.rendering.cameras.Camera;
import com.amr.raytracer.rendering.lights.Light;

import java.util.ArrayList;

/**
 * The {@code Scene} class represents a scene with a camera, solid objects and lights positioned somewhere in space.
 * Instances of this class can be rendered using the {@code RenderEngine} class.
 *
 * @author Ángel Martínez
 * @see RenderEngine
 */
public class Scene {

    private ArrayList<Object3D> objects;
    private ArrayList<Light> lights;
    private Camera camera;

    /**
     * Constructs a {@code Scene} object with the given {@code Camera}. There will not be any solid objects and
     * lights at the moment. They can be added later on using the {@code addObject} and the {@code addLight} methods.
     *
     * @param camera the {@code Camera}.
     * @see Camera
     * @see #addObject(Object3D)
     * @see #addLight(Light)
     */
    private Scene(Camera camera, ArrayList<Object3D> objects, ArrayList<Light> lights) {
        setObjects(objects);
        setLights(lights);
        setCamera(camera);
    }

    /**
     * Constructs a {@code Scene} object with no {@code Camera}. There will not be any solid objects and
     * lights at the moment. They can be added later on using the {@code addObject} and the {@code addLight} methods.
     * The camera can be added using the {@code setCamera} method.
     *
     * @see #setCamera(Camera)
     * @see #addObject(Object3D)
     * @see #addLight(Light)
     */
    public Scene() {
        this(null, new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Returns the {@code ArrayList} of {@code Object3D} objects of this {@code Scene}.
     *
     * @return the {@code ArrayList} of {@code Object3D} objects of this {@code Scene}.
     * @see Object3D
     * @see #addObject(Object3D)
     */
    public ArrayList<Object3D> getObjects() {
        return objects;
    }

    /**
     * Sets the {@code ArrayList} of {@code Object3D} objects of this {@code Scene}.
     *
     * @param objects the new {@code ArrayList} of {@code Object3D} objects.
     * @see Object3D
     * @see #getObjects()
     * @see #addObject(Object3D)
     */
    private void setObjects(ArrayList<Object3D> objects) {
        this.objects = objects;
    }

    /**
     * Adds the given {@code Object3D} to the {@code ArrayList} of {@code Object3D} of this {@code Scene}.
     *
     * @param object the {@code Object3D} that will be added.
     * @see Object3D
     * @see #getObjects()
     */
    public void addObject(Object3D object) {
        getObjects().add(object);
    }

    /**
     * Returns the {@code ArrayList} of {@code Light} objects of this {@code Scene}.
     *
     * @return the {@code ArrayList} of {@code Light} objects of this {@code Scene}.
     * @see Light
     * @see #addLight(Light)
     */
    public ArrayList<Light> getLights() {
        return lights;
    }

    /**
     * Sets the {@code ArrayList} of {@code Light} objects of this {@code Scene}.
     *
     * @param lights the new {@code ArrayList} of {@code Light} objects.
     * @see Light
     * @see #getLights()
     * @see #addLight(Light)
     */
    private void setLights(ArrayList<Light> lights) {
        this.lights = lights;
    }

    /**
     * Adds the given {@code Light} to the {@code ArrayList} of {@code Light} of this {@code Scene}.
     *
     * @param light the {@code Light} that will be added.
     * @see Light
     * @see #getLights()
     */
    public void addLight(Light light) {
        getLights().add(light);
    }

    /**
     * Returns the {@code Camera} of this {@code Scene}.
     *
     * @return the {@code Camera} of this {@code Scene}.
     * @see Camera
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * Sets the {@code Camera} of this {@code Scene}.
     *
     * @param camera the new {@code Camera}.
     * @see Camera
     * @see #getCamera()
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}
