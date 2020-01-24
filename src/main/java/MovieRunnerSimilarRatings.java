import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {

    /**
     * This method uses the CSV filenames of movie information
     * and ratings information for initializing the MovieDatabase
     * and the RaterDatabase, prints the number of movies and number of raters from the two files by calling the
     * appropriate methods, prints a list of movies and their average ratings, for all those movies that have at least a
     * specified number of ratings, sorted by averages.
     */
    public void printAverageRatings() {

        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + RaterDatabase.size() + " raters");

        //Minimum number of raters for a specified of movies
        int minimalRaters = 35;
        ArrayList<Rating> ratings = fourthRatings.getAverageRatings(minimalRaters);
        System.out.println("Found " + ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating : ratings) {
            System.out.println(rating.getValue()
                    + " - " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    /**
     * This method uses the CSV filenames of movie information
     * and ratings information for
     * initializing the MovieDatabase and the RaterDatabase,
     * creates an AllFilters object that includes criteria based on movies that came out in a specified year or
     * later and have a specified genre as one of its genres. This method calls
     * getAverageRatingsByFilter to get an ArrayList of type Rating of all the movies
     * that have a specified number of minimal ratings and the two criteria based on
     * year and genre, prints the number of movies found, and for each movie, prints its
     * rating, its year, and its title on one line, and all its genres on the next line.
     */

    public void printAverageRatingsByYearAfterAndGenre() {

        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + RaterDatabase.size() + " raters");

        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(1990));
        allFilters.addFilter(new GenreFilter("Drama"));
        //Minimum number of raters for a specified of movies
        int minimalRaters = 8;
        ArrayList<Rating> ratings = fourthRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println(ratings.size() + " movie(s) matched");
        Collections.sort(ratings);
        for(Rating rating : ratings) {
            System.out.println(MovieDatabase.getTitle(rating.getItem())
                    + " - " + rating.getValue()
                    + " - " + MovieDatabase.getYear(rating.getItem()));
            System.out.println(MovieDatabase.getGenres(rating.getItem()));
        }
    }

    /**
     * This method uses the CSV filenames of movie information
     * and ratings information for
     * initializing the MovieDatabase and the RaterDatabase, and then calls getSimilarRatings for a particular rater ID, a
     * number for the top number of similar raters, and a number of minimal
     * rateSimilarRatings , and then lists recommended movies and their similarity
     * ratings.
     */

    public void printSimilarRatings() {

        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + RaterDatabase.size() + " raters");

        //Rater ID
        String id = "71";
        //Minimum number of similar raters for a particular movie
        int numSimilarRaters = 20;
        //Minimum number of raters for a particular movie
        int minimalRaters = 5;
        ArrayList<Rating> ratings = fourthRatings.getSimilarRatings(id, numSimilarRaters, minimalRaters);
        System.out.println(ratings.size() + " movie(s) matched");
        for(Rating rating : ratings) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + " - "
                    + ratings.get(0).getValue());
        }
    }

    /**
     * This method is similar to printSimilarRatings but also uses a genre filter and then
     * lists recommended movies and their similarity ratings, and for each movie prints
     * the movie and its similarity rating on one line and its genres on a separate line
     * below it.
     */

    public void printSimilarRatingsByGenre() {

        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + RaterDatabase.size() + " raters");

        //Rater ID
        String id = "71";
        //Minimum number of similar raters for a particular movie
        int numSimilarRaters = 20;
        //Minimum number of raters for a particular movie
        int minimalRaters = 5;
        Filter filter = new GenreFilter("Mystery");
        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, filter);
        System.out.println(ratings.size() + " movie(s) matched");
        for(Rating rating : ratings) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + " - "
                    + ratings.get(0).getValue());
            System.out.println(MovieDatabase.getGenres(rating.getItem()));
        }
    }

    /**
     * This method is similar to printSimilarRatings but also uses a director filter and
     * then lists recommended movies and their similarity ratings, and for each movie
     * prints the movie and its similarity rating on one line and its directors on a
     * separate line below it.
     */

    public void printSimilarRatingsByDirector() {

        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + RaterDatabase.size() + " raters");

        //Rater ID
        String id = "71";
        //Minimum number of similar raters for a particular movie
        int numSimilarRaters = 20;
        //Minimum number of raters for a particular movie
        int minimalRaters = 5;
        Filter filter = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, filter);
        System.out.println(ratings.size() + " movie(s) matched");
        for(Rating rating : ratings) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + " - "
                    + ratings.get(0).getValue());
            System.out.println(MovieDatabase.getDirector(rating.getItem()));
        }
    }

    /**
     * This method is similar to printSimilarRatings but also uses a genre
     * filter and a minutes filter and then lists recommended movies and their similarity
     * ratings, and for each movie prints the movie, its minutes, and its similarity rating
     * on one line and its genres on a separate line below it.
     */

    public void printSimilarRatingsByGenreAndMinutes() {

        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + RaterDatabase.size() + " raters");

        //Rater ID
        String id = "71";
        //Minimum number of similar raters for a particular movie
        int numSimilarRaters = 20;
        //Minimum number of raters for a particular movie
        int minimalRaters = 5;
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new GenreFilter("Drama"));
        allFilters.addFilter(new MinutesFilter(80, 160));
        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, allFilters);
        System.out.println(ratings.size() + " movie(s) matched");
        for(Rating rating : ratings) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + " - "
                    + MovieDatabase.getMinutes(rating.getItem()) + " - "
                    + ratings.get(0).getValue());
            System.out.println(MovieDatabase.getGenres(rating.getItem()));
        }
    }

    /**
     * This method is similar to printSimilarRatings but also uses a yearafter
     * filter and a minutes filter and then lists recommended movies and their
     * similarity ratings, and for each movie prints the movie, its year, its minutes, and
     * its similarity rating on one line.
     */

    public void printSimilarRatingsByYearAfterAndMinutes() {

        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + RaterDatabase.size() + " raters");

        //Rater ID
        String id = "71";
        //Minimum number of similar raters for a particular movie
        int numSimilarRaters = 20;
        //Minimum number of raters for a particular movie
        int minimalRaters = 5;
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(1975));
        allFilters.addFilter(new MinutesFilter(70, 200));
        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, allFilters);
        System.out.println(ratings.size() + " movie(s) matched");
        for(Rating rating : ratings) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + " - "
                    + MovieDatabase.getYear(rating.getItem()) + " - "
                    + MovieDatabase.getMinutes(rating.getItem()) + " - "
                    + ratings.get(0).getValue());
        }
    }
}
