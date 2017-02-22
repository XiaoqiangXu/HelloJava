/**
 * Created by Xiaoqiang on 2017/1/24.
 */
public class PhraseFilter implements Filter{
    private String where;
    private String phrase;
    public PhraseFilter(String W,String Ph){
        where = W;
        phrase = Ph;
    }
    public boolean satisfies(QuakeEntry qe){
        int idx = qe.getInfo().indexOf(phrase);
        if (where.equals("start")&&idx ==0){
            return true;
        }else if(where.equals("end")&&idx ==qe.getInfo().length()-phrase.length()){
            return true;
        }else if(where.equals("any")&&idx!=-1){
            return true;
        }
        return false;
    }
    public String getName(){
        return "Phrase";
    }
}
