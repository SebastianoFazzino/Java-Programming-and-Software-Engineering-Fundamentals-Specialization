public class DistanceFilter implements Filter{
    private Location location;
    private int maxDistance;
    private String name;

    public DistanceFilter(Location loc, int max){
        location = loc;
        maxDistance = max;
        name = "Distance ";
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return (qe.getLocation().distanceTo(location) < maxDistance );
    }

    public String getName() {
        return name;
    }
}
