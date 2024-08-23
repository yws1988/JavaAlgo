package geometry.helpers;

import datastructures.geometry.Point3DDouble;

public class Point3DHeper
{
    /// <summary>
    /// 3D rotation matrix
    /// </summary>
    /// <param name="pointToRotate"></param>
    /// <param name="centerPoint"></param>
    /// <param name="dir">The ratation direction, 0 is Z axis, 1 is x axis, 2 is y axis </param>
    /// <param name="angleInDegrees"></param>
    /// <returns>3D point after rotation</returns>
    public static Point3DDouble rotatePoint3D(Point3DDouble pointToRotate, Point3DDouble centerPoint, int dir, double angleInDegrees, boolean isAngle)
    {
        double angleInRadians = isAngle ? angleInDegrees * (Math.PI / 180) : angleInDegrees;
        double cosTheta = Math.cos(angleInRadians);
        double sinTheta = Math.sin(angleInRadians);
        double x = pointToRotate.x - centerPoint.x;
        double y = pointToRotate.y - centerPoint.y;
        double z = pointToRotate.z - centerPoint.z;
        double x1, y1, z1;

        if (dir == 0)
        {
            x1 = cosTheta * x - sinTheta * y + centerPoint.x;
            y1 = sinTheta * x + cosTheta * y + centerPoint.y;
            z1 = pointToRotate.z + centerPoint.z;
        }
        else if (dir == 1)
        {
            y1 = cosTheta * y - sinTheta * z + centerPoint.y;
            z1 = sinTheta * y + cosTheta * z + centerPoint.z;
            x1 = pointToRotate.x + centerPoint.x;
        }
        else
        {
            z1 = cosTheta * z - sinTheta * x + centerPoint.z;
            x1 = sinTheta * z + cosTheta * x + centerPoint.x;
            y1 = pointToRotate.z + centerPoint.z;
        }

        return new Point3DDouble(x1, y1, z1);
    }

    public static double distance(Point3DDouble point1, Point3DDouble point2)
    {
        return Math.sqrt(Math.pow(point1.x - point2.x, 2) + Math.pow(point1.y - point2.y, 2) + Math.pow(point1.z - point2.z, 2));
    }
}
