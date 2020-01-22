
public class GenreFilter implements Filter {

    String genre;

    public GenreFilter(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean satisfies(String id) {

        if(MovieDatabase.getGenres(id).indexOf(genre) != -1) {
            return true;
        }
        return false;
    }
}
