package com.amr.raytracer.rendering.cameras;

import com.amr.raytracer.math.Ray;
import com.amr.raytracer.math.Vector3D;

public class OrthographicCamera extends Camera {

    /**
     * Constructs a {@code OrthographicCamera} object with the given volumetric frustum clipping planes and all the
     * other properties are default as the {@code Camera} class.
     *
     * @param left   the left clipping plane.
     * @param right  the right clipping plane.
     * @param top    the top clipping plane.
     * @param bottom the bottom clipping plane.
     * @param near   the near clipping plane.
     * @param far    the far clipping plane.
     */
    public OrthographicCamera(Vector3D position, double left, double right, double top, double bottom, double near, double far) {
        super(position, 0, 0, near, far);
    }

    @Override
    public Ray makeRay(double u, double v) {
        return null;
    }

}
