
public class DirectorsFilter implements Filter {

    String[] directors;

    public DirectorsFilter(String directors) {
        this.directors = directors.split(",");
    }

    @Override
    public boolean satisfies(String id) {

        for(int k = 0; k < directors.length; k++) {
            if(MovieDatabase.getDirector(id).contains(directors[k].trim())) {
                return true;
            }
        }
        return false;
    }
}
