package com.amr.raytracer.rendering.cameras;

import com.amr.raytracer.math.Ray;
import com.amr.raytracer.math.Vector3D;

import static com.amr.raytracer.math.Vector3D.*;
import static java.lang.Math.*;

/**
 * The {@code PerspectiveCamera} class represents a camera in space that makes objects appear different depending
 * on how far they are. It inherits from the {@code Camera} class which provides the functionality of a generic camera.
 *
 * @author Ángel Martínez
 * @see Camera
 */
public class PerspectiveCamera extends Camera {

    private double verticalFovRadians;

    /**
     * Constructs a {@code PerspectiveCamera} object with the given properties.
     *
     * @param position           the position in space.
     * @param nearClippingPlane  the near clipping plane distance from the camera.
     * @param farClippingPlane   the far clipping plane distance from the camera.
     * @param yawDegrees         the rotation angle in degrees in the y-axis.
     * @param pitchDegrees       the rotation angle in degrees in the x-axis.
     * @param verticalFovDegrees the vertical field of view in degrees.
     */
    public PerspectiveCamera(Vector3D position, double yawDegrees, double pitchDegrees,
                             double nearClippingPlane, double farClippingPlane, double verticalFovDegrees) {
        super(position, toRadians(yawDegrees), toRadians(pitchDegrees), nearClippingPlane, farClippingPlane);
        setVerticalFovRadians(toRadians(verticalFovDegrees));
    }

    /**
     * Returns the field of view in radians of this {@code PerspectiveCamera}.
     *
     * @return the field of view in radians of this {@code PerspectiveCamera}.
     * @see #setVerticalFovRadians(double)
     */
    private double getVerticalFovRadians() {
        return verticalFovRadians;
    }

    /**
     * Sets the field of view in radians of this {@code PerspectiveCamera}.
     *
     * @param verticalFovRadians the new field of view in radians.
     * @see #getVerticalFovRadians()
     */
    private void setVerticalFovRadians(double verticalFovRadians) {
        this.verticalFovRadians = verticalFovRadians;
    }

    /**
     * Returns the {@code Ray} that should be cast to the given UV coordinates of an image plane taking into account the
     * properties of this {@code PerspectiveCamera}.
     *
     * @param u the horizontal screen coordinate.
     * @param v the vertical screen coordinate.
     * @return the {@code Ray} that should be cast to the given UV coordinates.
     * @see Ray
     */
    @Override
    public Ray makeRay(double u, double v) {
        Vector3D origin = new Vector3D(0, 0, -1 / tan(getVerticalFovRadians() / 2));
        Vector3D direction = normalize(subtract(new Vector3D(u, v, 0), origin)).rotateYP(getYawRadians(), getPitchRadians());
        return new Ray(add(origin, getPosition()), direction);
    }
}
