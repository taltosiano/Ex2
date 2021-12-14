package api;

public class Edge_Data implements EdgeData {
    private int _src, _dest, _tag;
    private double _weight;
    private String _info;
    public Edge_Data(int src, int dest, double weight){
        this._src = src;
        this._dest = dest;
        this._weight = weight;
        this._info="";
        this._tag=0;

    }
    public Edge_Data(Edge_Data e){
        this._src = e._src;
        this._dest = e._dest;
        this._tag = e._tag;
        this._weight = e._weight;
        this._info = e._info;
    }

    @Override
    public int getSrc() {
        return this._src;
    }

    @Override
    public int getDest() {
        return this._dest;
    }

    @Override
    public double getWeight() {
        return this._weight;
    }

    @Override
    public String getInfo() {
        return this._info;
    }

    @Override
    public void setInfo(String s) {
        this._info = s;
    }

    @Override
    public int getTag() {
        return this._tag;
    }

    @Override
    public void setTag(int t) {
        this._tag = t;
    }

    @Override
    public String toString() {
        return "api.Edge_Data{" +
                "_src=" + _src +
                ", _dest=" + _dest +
                ", _tag=" + _tag +
                ", _weight=" + _weight +
                ", _info='" + _info + '\'' +
                '}';
    }


}
