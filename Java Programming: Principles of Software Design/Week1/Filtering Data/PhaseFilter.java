public class PhaseFilter implements Filter{
    private String position;
    private String phrase;
    private String name;

    public PhaseFilter(String pos, String ph){
        position = pos;
        phrase = ph;
        name = "Phrase ";
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        if (position.equals("start")){
            return (qe.getInfo().startsWith(phrase));
        }
        else if (position.equals("end")){
            return (qe.getInfo().endsWith(phrase));
        }
        else if (position.equals("any")){
            return (qe.getInfo().indexOf(phrase) != -1);
        }
        return false;
    }

    public String getName() {
        return name;
    }
}


