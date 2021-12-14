package Tests;

import api.GeoLocation;
import api.Geo_Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Geo_LocationTest {

    @Test
    void x() {
        GeoLocation g = new Geo_Location(4,4);
        Assertions.assertEquals(4,g.x());
        GeoLocation g1 = new Geo_Location(3,4,5);
        Assertions.assertEquals(3,g1.x());

    }

    @Test
    void y() {
        GeoLocation g = new Geo_Location(4,4);
        Assertions.assertEquals(4,g.y());
        GeoLocation g1 = new Geo_Location((Geo_Location) g);
        Assertions.assertEquals(4,g1.y());
    }

    @Test
    void z() {
        GeoLocation g = new Geo_Location(4,4,7);
        Assertions.assertEquals(7,g.z());
        GeoLocation g1 = new Geo_Location((Geo_Location) g);
        Assertions.assertEquals(7,g1.z());
    }

    @Test
    void distance() {
        GeoLocation g = new Geo_Location(4,4);
        GeoLocation g1 = new Geo_Location(4,4);
        Assertions.assertEquals(0.0,g.distance(g1));

    }

    @Test
    void distance2d() {
        GeoLocation g = new Geo_Location(1,2,3);
        GeoLocation g1 = new Geo_Location(4,4);
        Assertions.assertEquals(3.605551275463989, ((Geo_Location) g).distance2d(g1));
    }

    @Test
    void testToString() {
        GeoLocation g = new Geo_Location(1,2,3);
        Assertions.assertEquals("1.0,2.0,3.0",g.toString());
    }
}