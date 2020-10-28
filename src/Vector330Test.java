import com.sun.source.doctree.SystemPropertyTree;
import org.junit.jupiter.api.Test;

import java.util.Scanner;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class Vector330Test {

    Vector330 test;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        test = new Vector330(4,4);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        test = null;
    }

    @org.junit.jupiter.api.Test
    void add() {
        Vector330 v = new Vector330(4,5);
        Vector330 sum = test.add(v);
        System.out.println(sum);
    }

    @org.junit.jupiter.api.Test
    void subtract() {
        Vector330 v = new Vector330(4,4);
        Vector330 sum = test.subtract(v);
        System.out.println(sum);
    }

    @org.junit.jupiter.api.Test
    void dotProduct() {
        Vector330 v = new Vector330(4,4);
        double sum = test.dotProduct(v);
        System.out.println(sum);
    }

    @org.junit.jupiter.api.Test
    void direction() {
        double direction = test.direction();
        System.out.println(direction);
    }

    @org.junit.jupiter.api.Test
    void scale() {
        Vector330 v = test.scale(4);
        System.out.println(v);
    }

    @org.junit.jupiter.api.Test
    void magnitude() {
        double mag = test.magnitude();
        System.out.println(mag);
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        Vector330 v = new Vector330(3.6,4.0);
        if(test.equals(v)) {
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }
        System.out.println("test");
    }

    @org.junit.jupiter.api.Test
    void normalize() {
        Vector330 v = test.normalize();
        System.out.println(v);
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        //this works and is tested along the way
        System.out.println(test);
    }

    @org.junit.jupiter.api.Test
    void parseVector() throws Exception {
        Scanner s = new Scanner(System.in);
        Vector330 test = Vector330.parseVector(s);
        System.out.print(test);

    }

    @Test
    void getX() {
        double x = test.getX();
        System.out.print(x);
    }

    @Test
    void getXint() {
        int x = test.getXint();
        System.out.print(x);
    }

    @Test
    void getXlong() {
        long x = test.getXlong();
        System.out.print(x);
    }

    @Test
    void getY() {
        double y = test.getY();
        System.out.print(y);
    }

    @Test
    void getYint() {
        int y = test.getYint();
        System.out.print(y);
    }

    @Test
    void getYlong() {
        long y = test.getYlong();
        System.out.print(y);
    }

    @Test
    void setX() {
        test.setX(3.0);
        System.out.print(test);
    }

    @Test
    void setXint() {
        test.setXint(15);
        System.out.print(test);
    }

    @Test
    void setXlong() {
        test.setXlong(372);
        System.out.print(test);
    }

    @Test
    void setY() {
        test.setY(4.1);
        System.out.print(test);
    }

    @Test
    void setYint() {
        test.setYint(43);
        System.out.print(test);
    }

    @Test
    void setYlong() {
        test.setYlong(41331);
        System.out.print(test);
    }
}