package api;

public class Node_Data implements NodeData {
    private  int _tag, _key;
    private double _weight;
    private String _info;
    private GeoLocation gl;
    public Node_Data(int key, GeoLocation gl){
        this.gl = gl;
        this._key = key;
        this._tag = 0;
        this._weight = 0;
        this._info = "";

    }
    public Node_Data(Node_Data n) {
        this.gl = n.gl;
        this._key = n._key;
        this._tag = n._tag;
        this._weight = n._weight;
        this._info = n._info;
    }
    @Override
    public int getKey() {
        return _key;
    }

    @Override
    public GeoLocation getLocation() {
        return gl;
    }

    @Override
    public void setLocation(GeoLocation p) {
       this.gl = new Geo_Location((Geo_Location) p);
    }

    @Override
    public double getWeight() {
        return _weight;
    }

    @Override
    public void setWeight(double w) {
        this._weight = w;
    }

    @Override
    public String getInfo() {
        return _info;
    }

    @Override
    public void setInfo(String s) {
        this._info = s;
    }

    @Override
    public int getTag() {
        return _tag;
    }

    @Override
    public void setTag(int t) {
        this._tag = t;
    }

    @Override
    public String toString() {
        return "api.Node_Data{" +
                "_tag=" + _tag +
                ", _key=" + _key +
                ", _weight=" + _weight +
                ", _info='" + _info + '\'' +
                ", gl=" + gl +
                '}';
    }

}
