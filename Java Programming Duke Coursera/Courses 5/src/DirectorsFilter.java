import java.util.ArrayList;

/**
 * Created by Xiaoqiang on 2017/2/24.
 */
public class DirectorsFilter implements Filter {

    private ArrayList<String> myDirectors;

    public DirectorsFilter(String input){
        myDirectors = new ArrayList<>();
        int startIndex = 0;
        while (input.indexOf(",",startIndex)!=-1){
            String currDirector = input.substring(startIndex,input.indexOf(",",startIndex));
            myDirectors.add(currDirector);
            startIndex = input.indexOf(",",startIndex)+1;
        }
        String lastDirector = input.substring(startIndex,input.length());
        myDirectors.add(lastDirector);
    }

    public boolean satisfies(String id) {
        for (String curDirector:myDirectors){
            if (MovieDatabase.getDirector(id).indexOf(curDirector)!=-1){
                return true;
            }
        }
        return false;
    }
}
