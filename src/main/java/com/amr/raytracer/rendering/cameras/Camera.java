package com.amr.raytracer.rendering.cameras;

import com.amr.raytracer.math.Ray;
import com.amr.raytracer.math.Vector3D;

/**
 * The abstract class {@code Camera} represents a generic camera in space that can look at objects in different ways.
 * This class can be used to extend functionality to other more specific types of cameras.
 *
 * @author Ángel Martínez
 */
public abstract class Camera {

    private Vector3D position;
    private double yawRadians;
    private double pitchRadians;
    private double nearClippingPlane;
    private double farClippingPlane;

    /**
     * Constructs a {@code Camera} object with the given properties.
     *
     * @param position          the position in space.
     * @param yawRadians        the rotation angle in radians in the z-axis.
     * @param pitchRadians      the rotation angle in radians in the x-axis.
     * @param nearClippingPlane the near clipping plane distance from the camera.
     * @param farClippingPlane  the far clipping plane distance from the camera.
     */
    public Camera(Vector3D position, double yawRadians, double pitchRadians,
                  double nearClippingPlane, double farClippingPlane) {
        setPosition(position);
        setYawRadians(yawRadians);
        setPitchRadians(pitchRadians);
        setNearClippingPlane(nearClippingPlane);
        setFarClippingPlane(farClippingPlane);
    }

    /**
     * Returns the position in space of this {@code Camera}.
     *
     * @return the position in space of this {@code Camera}.
     */
    public Vector3D getPosition() {
        return position;
    }

    /**
     * Sets the position in space of this {@code Camera}.
     *
     * @param position the new position in space.
     * @see #getPosition()
     */
    private void setPosition(Vector3D position) {
        this.position = position;
    }

    public double getYawRadians() {
        return yawRadians;
    }

    private void setYawRadians(double yawRadians) {
        this.yawRadians = yawRadians;
    }

    public double getPitchRadians() {
        return pitchRadians;
    }

    private void setPitchRadians(double pitchRadians) {
        this.pitchRadians = pitchRadians;
    }

    /**
     * Returns the near clipping plane distance from this {@code Camera}.
     *
     * @return the near clipping plane distance from this {@code Camera}.
     */
    public double getNearClippingPlane() {
        return nearClippingPlane;
    }

    /**
     * Sets the near clipping distance plane from this {@code Camera}.
     *
     * @param nearClippingPlane the new near clipping plane distance from the camera.
     * @see #getNearClippingPlane()
     */
    private void setNearClippingPlane(double nearClippingPlane) {
        this.nearClippingPlane = nearClippingPlane;
    }

    /**
     * Returns the far clipping plane distance from this {@code Camera}.
     *
     * @return the far clipping plane distance from this {@code Camera}.
     */
    public double getFarClippingPlane() {
        return farClippingPlane;
    }

    /**
     * Sets the far clipping plane distance from this {@code Camera}.
     *
     * @param farClippingPlane the new far clipping plane distance from the camera.
     * @see #getFarClippingPlane()
     */
    public void setFarClippingPlane(double farClippingPlane) {
        this.farClippingPlane = farClippingPlane;
    }

    /**
     * Returns the {@code Ray} that should be cast to the given UV coordinates of an image plane taking into account the
     * properties of this {@code Camera}.
     *
     * @param u the horizontal screen coordinate.
     * @param v the vertical screen coordinate.
     * @return the {@code Ray} that should be cast to the given UV coordinates.
     * @see Ray
     */
    public abstract Ray makeRay(double u, double v);
}
