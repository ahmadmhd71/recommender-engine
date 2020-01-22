import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {

    public void printAverageRatings() {

        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + RaterDatabase.size() + " raters");

        int minimalRaters = 35;
        ArrayList<Rating> ratings = fourthRatings.getAverageRatings(minimalRaters);
        System.out.println("Found " + ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating : ratings) {
            System.out.println(rating.getValue()
                    + " - " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre() {

        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + RaterDatabase.size() + " raters");

        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(1990));
        allFilters.addFilter(new GenreFilter("Drama"));
        int minimalRaters = 8;
        ArrayList<Rating> ratings = fourthRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println(ratings.size() + " movie matched");
        /*Collections.sort(ratings);
        for(Rating rating : ratings) {
            System.out.println(rating.getValue()
                    + " - " + MovieDatabase.getTitle(rating.getItem())
                    + " - " + MovieDatabase.getYear(rating.getItem())
                    + " - " + MovieDatabase.getGenres(rating.getItem()));
        }*/
    }

    public void printSimilarRatings() {

        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + RaterDatabase.size() + " raters");

        String id = "71";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        ArrayList<Rating> ratings = fourthRatings.getSimilarRatings(id, numSimilarRaters, minimalRaters);
        System.out.println(MovieDatabase.getTitle(ratings.get(0).getItem()));
        System.out.println(ratings.get(0).getValue());
    }

    public void printSimilarRatingsByGenre() {

        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + RaterDatabase.size() + " raters");

        String id = "964";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        Filter filter = new GenreFilter("Mystery");
        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, filter);
        System.out.println(MovieDatabase.getTitle(ratings.get(0).getItem()));
        System.out.println(ratings.get(0).getValue());
    }

    public void printSimilarRatingsByDirector() {

        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + RaterDatabase.size() + " raters");

        String id = "120";
        int numSimilarRaters = 10;
        int minimalRaters = 2;
        Filter filter = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, filter);
        System.out.println(MovieDatabase.getTitle(ratings.get(0).getItem()));
        System.out.println(ratings.get(0).getValue());
    }

    public void printSimilarRatingsByGenreAndMinutes() {

        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + RaterDatabase.size() + " raters");

        String id = "168";
        int numSimilarRaters = 10;
        int minimalRaters = 3;
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new GenreFilter("Drama"));
        allFilters.addFilter(new MinutesFilter(80, 160));
        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, allFilters);
        System.out.println(MovieDatabase.getTitle(ratings.get(0).getItem()));
        System.out.println(ratings.get(0).getValue());
    }

    public void printSimilarRatingsByYearAfterAndMinutes() {

        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + RaterDatabase.size() + " raters");

        String id = "314";
        int numSimilarRaters = 10;
        int minimalRaters = 5;
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(1975));
        allFilters.addFilter(new MinutesFilter(70, 200));
        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, allFilters);
        System.out.println(MovieDatabase.getTitle(ratings.get(0).getItem()));
        System.out.println(ratings.get(0).getValue());
    }
}
