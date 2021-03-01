public class DepthFilter implements Filter{
    private int minDepth;
    private int maxDepth;
    private String name;

    public DepthFilter(int min, int max){
        minDepth = min;
        maxDepth = max;
        name = "Depth ";
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return (qe.getDepth() <= minDepth && qe.getDepth() >= maxDepth);
    }

    public String getName() {
        return name;
    }
}
