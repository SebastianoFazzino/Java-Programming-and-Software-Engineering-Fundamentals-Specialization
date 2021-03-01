import java.util.ArrayList;

public class MatchAll implements Filter{
    private ArrayList<Filter> filters;
    private String name;

    public MatchAll(){
        filters = new ArrayList<Filter>();
        name = "Filter used are: ";
    }

    public void addFilter(Filter f){
        filters.add(f);
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        for (Filter f : filters){
            if (!f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }

    @Override
    public String getName() {
        for (Filter f : filters){
            name += f.getName();
        }
        return name;
    }
}
