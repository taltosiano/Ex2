package api;

public class Geo_Location implements GeoLocation {
    private double x, y, z;

    public Geo_Location(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 0;
    }
    public Geo_Location(double x, double y,double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Geo_Location(Geo_Location n) {
         this.x = n.x;
         this.y = n.y;
         this.z = n.z;

    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(GeoLocation g) {
        return Math.sqrt(Math.pow(x - g.x(), 2) + Math.pow(y - g.y(), 2) + Math.pow(z - g.z(), 2));
    }

    public double distance2d(GeoLocation g) {
        return Math.sqrt(Math.pow(x - g.x(), 2) + Math.pow(y - g.y(), 2) );
    }

    @Override
    public String toString() {
        return x + "," + y + "," + z ;
    }

}
