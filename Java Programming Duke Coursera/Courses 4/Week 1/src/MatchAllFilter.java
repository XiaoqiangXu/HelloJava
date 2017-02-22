import java.util.ArrayList;

/**
 * Created by Xiaoqiang on 2017/1/24.
 */
public class MatchAllFilter implements Filter{
    private ArrayList<Filter> filters;
    public MatchAllFilter(){
        filters  = new ArrayList<>();
    }
    public void addFilter(Filter filter){
        filters.add(filter);
    }
    public boolean satisfies(QuakeEntry qe){
        for (Filter flt:filters){
            if (!flt.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
    public String getName(){
        StringBuilder out = new StringBuilder();
        for (Filter flt:filters){
            out.append(" ");
            out.append(flt.getName());
        }
        return out.toString();
    }
}
