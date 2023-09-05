package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("sphere")
                .contains("Sph")
                .startsWith("Sp")
                .startsWithIgnoringCase("s")
                .endsWith("re")
                .isEqualTo("Sphere");
    }

    @Test
    void isThisUNKNOWN() {
        Box box = new Box(1, 1);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("unknown")
                .contains("object")
                .startsWith("Unknown")
                .startsWithIgnoringCase("u")
                .endsWith("object")
                .isEqualTo("Unknown object");
    }

    @Test
    void numberOfVertices() {
        Box box = new Box(8, 6);
        int number = box.getNumberOfVertices();
        assertThat(number).isNotZero()
                .isPositive()
                .isEven()
                .isGreaterThan(7)
                .isLessThan(9)
                .isEqualTo(8);
    }

    @Test
    void numberOfVerticesMinusOne() {
        Box box = new Box(4, 0);
        int number = box.getNumberOfVertices();
        assertThat(number).isNotZero()
                .isNotPositive()
                .isLessThan(0)
                .isEqualTo(-1);
    }

    @Test
    void isExistTrue() {
        Box box = new Box(0, 1);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }

    @Test
    void isExistFalse() {
        Box box = new Box(8, -1);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }

    @Test
    void areaTetrahedron() {
        Box box = new Box(4, 5);
        double area = box.getArea();
        assertThat(area).isEqualTo(43.3d, withPrecision(0.01d))
                .isCloseTo(43d, withPrecision(0.5d))
                .isCloseTo(43.3d, Percentage.withPercentage(1.0d))
                .isGreaterThan(43.2d)
                .isLessThan(43.4d);
    }

    @Test
    void areaSphere() {
        Box box = new Box(0, 5);
        double area = box.getArea();
        assertThat(area).isEqualTo(314.16d, withPrecision(0.01d))
                .isCloseTo(314.2d, withPrecision(0.2d))
                .isCloseTo(314d, Percentage.withPercentage(0.1d))
                .isGreaterThan(314.1d)
                .isLessThan(314.2d);
    }
}