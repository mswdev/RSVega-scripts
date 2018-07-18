package antiban.api.util;

import java.awt.*;

public class Random {

    /**
     * Gets a random point in the specified shape.
     *
     * @param shape The shape to get a random point.
     * @return A random point in the shape; null otherwise.
     * */
    public static Point getPointInShape(Shape shape) {
        if (shape == null)
            return null;

        final Rectangle RECTANGLE = shape.getBounds();
        final int X = (int) (RECTANGLE.getX() + RECTANGLE.getWidth() * Math.random());
        final int Y = (int) (RECTANGLE.getY() + RECTANGLE.getHeight() * Math.random());

        return new Point(X, Y);
    }

}
