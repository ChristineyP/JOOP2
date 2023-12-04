package polyhedra;

import java.io.StringReader;
import java.util.Scanner;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.hamcrest.core.IsNull;

import polyhedra.Point;
import polyhedra.Sphere;

/**
 * 1 - Does this piece of code perform the operations
 *     it was designed to perform?
 *
 * 2 - Does this piece of code do something it was not
 *     designed to perform?
 *
 * 1 Test per mutator
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestSphere {

    Sphere defaultSphere;

    @BeforeEach
    public void setUp()
    {
        defaultSphere = new Sphere();
    }

    @Test
    public void testDefaultConstructor()
    {
        assertThat(defaultSphere.getRadius(), closeTo(1, Point.EQ_THRESHOLD));
        assertThat(defaultSphere.getDiameter(), closeTo(2, Point.EQ_THRESHOLD));

        // BoundingBox...
        Point expectedPoint = new Point(2, 2, 2);
        Point point = (defaultSphere.getBoundingBox()).getUpperRightVertex();

        assertThat(point, equalTo(expectedPoint));

        // I am skipping toString in this test
    }

    @Test
    public void testNonDefaultConstructor()
    {
        Sphere sph = new Sphere(3);

        assertThat(sph.getRadius(), closeTo(3, Point.EQ_THRESHOLD));
        assertThat(sph.getDiameter(), closeTo(6, Point.EQ_THRESHOLD));

        // BoundingBox...
        Point expectedPoint = new Point(6, 6, 6);
        Point point = sph.getBoundingBox().getUpperRightVertex();

        assertThat(point, equalTo(expectedPoint));

        // I am skipping toString in this test
    }

    @Test
    public void testSetRadius()
    {
        Sphere sph = new Sphere(1);

        sph.setRadius(12);

        // Found a mistake (typo) in Sphere.setRadius
        assertThat(sph.getRadius(), closeTo(12, Point.EQ_THRESHOLD));
        assertThat(sph.getDiameter(), closeTo(24, Point.EQ_THRESHOLD));

        // BoundingBox...
        Point expectedPoint = new Point(24, 24, 24);
        Point point = sph.getBoundingBox().getUpperRightVertex();

        assertThat(point, equalTo(expectedPoint));

        // I am skipping toString in this test
    }

    @Test
    public void testClone()
    {
        Sphere sph = new Sphere(3);

        Sphere cpy = (Sphere) sph.clone();

        assertThat(cpy.getRadius(), closeTo(3, Point.EQ_THRESHOLD));
        assertThat(cpy.getDiameter(), closeTo(6, Point.EQ_THRESHOLD));

        // BoundingBox...
        Point expectedPoint = new Point(6, 6, 6);
        Point point = cpy.getBoundingBox().getUpperRightVertex();

        assertThat(point, equalTo(expectedPoint));

        // Check that the bounding box is not shared with `sph`
        assertThat(cpy.getBoundingBox(), not(sameInstance(sph.getBoundingBox())));

        // I am skipping toString in this test
    }

    @Test
    public void testRead()
    {
        Sphere sph = new Sphere();

        Scanner ins = new Scanner(new StringReader("4"));

        sph.read(ins);

        assertThat(sph.getRadius(), closeTo(4, Point.EQ_THRESHOLD));
        assertThat(sph.getDiameter(), closeTo(8, Point.EQ_THRESHOLD));

        // BoundingBox...
        Point expectedPoint = new Point(8, 8, 8);
        Point point = sph.getBoundingBox().getUpperRightVertex();

        assertThat(point, equalTo(expectedPoint));

        // I am skipping toString in this test
    }

    @Test
    public void testScale()
    {
        Sphere sph = new Sphere(5);

        sph.scale(2);

        assertThat(sph.getRadius(), closeTo(10, Point.EQ_THRESHOLD));
        assertThat(sph.getDiameter(), closeTo(20, Point.EQ_THRESHOLD));

        // BoundingBox...
        Point expectedPoint = new Point(20, 20, 20);
        Point point = sph.getBoundingBox().getUpperRightVertex();

        assertThat(point, equalTo(expectedPoint));

        // I am skipping toString in this test
    }

    @Test
    public void testToStringDefaultConstructor()
    {
        Sphere sph = defaultSphere;

        final String actualOutput = sph.toString();

        // Check the pieces of the resulting string (machine Floating Point)
        assertThat(actualOutput, startsWith("[Sphere] ("));
        assertThat(actualOutput, containsString((new Point(2, 2, 2)).toString()));
        assertThat(actualOutput, containsString(")->Radius: 1"));
        assertThat(actualOutput, containsString("Diameter: 2"));
    }

    @Test
    public void testToStringConstructor()
    {
        Sphere sph = new Sphere(3);

        final String actualOutput = sph.toString();

        // Check the pieces of the resulting string (machine Floating Point)
        assertThat(actualOutput, startsWith("[Sphere] ("));
        assertThat(actualOutput, containsString((new Point(6, 6, 6)).toString()));
        assertThat(actualOutput, containsString(")->Radius: 3"));
        assertThat(actualOutput, containsString("Diameter: 6"));
    }

    @Test
    public void testToStringAfterScale()
    {
        Sphere sph = new Sphere(3);
        sph.scale(2);

        final String actualOutput = sph.toString();

        // Check the pieces of the resulting string (machine Floating Point)
        assertThat(actualOutput, startsWith("[Sphere] ("));
        assertThat(actualOutput, containsString((new Point(12, 12, 12)).toString()));
        assertThat(actualOutput, containsString(")->Radius: 6"));
        assertThat(actualOutput, containsString("Diameter: 12"));
    }
}

