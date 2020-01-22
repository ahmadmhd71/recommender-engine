import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.HashMap;

public class FirstRatings {

    public ArrayList<Movie> loadMovies(String fileName) {

        ArrayList<Movie> movies = new ArrayList<Movie>();

        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser) {
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String genre = record.get("genre");
            String director = record.get("director");
            String country = record.get("country");
            String poster = record.get("poster");
            int minutes = Integer.parseInt(record.get("minutes"));
            Movie movie = new Movie(id, title, year, genre, director, country, poster, minutes);
            movies.add(movie);
        }

        return movies;
    }

    public void testLoadMovies() {

        //load all movies
        ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
        System.out.println("Number of movies: " + movies.size());
        /*for(Movie movie : movies) {
            System.out.println(movie.toString());
        }*/

        //number of movies with genre comedy
        int numComedy = 0;
        for(Movie movie : movies) {
            if(movie.getGenres().contains("Comedy")) {
                numComedy++;
            }
        }
        System.out.println("Number of Comedy Movies: " + numComedy);

        //number of movies longer than 150 min
        int numLong = 0;
        for(Movie movie : movies) {
            if(movie.getMinutes() > 150) {
                numLong++;
            }
        }
        System.out.println("Number of Movies longer than 150 Minutes: " + numLong);

        //maximum number of movies for any director
        HashMap<String, Integer> directors = new HashMap<>();
        for(Movie movie : movies) {
            if(movie.getDirector().contains(",")) {
                String[] currentDirectors = movie.getDirector().split(",");
                for(int k = 0; k < currentDirectors.length; k++) {
                    String director = currentDirectors[k].trim();
                    if(!directors.containsKey(director)) {
                        directors.put(director, 1);
                    }
                    else {
                        directors.put(director, directors.get(director) + 1);
                    }
                }
            }
            else {
                if(!directors.containsKey(movie.getDirector())) {
                    directors.put(movie.getDirector(), 1);
                }
                else {
                    directors.put(movie.getDirector(), directors.get(movie.getDirector()) + 1);
                }
            }
        }
        int max = 0;
        for(String director : directors.keySet()) {
            if(directors.get(director) >= max) {
                max = directors.get(director);
            }
        }
        ArrayList<String> directorsMax = new ArrayList<>();
        for(String director : directors.keySet()) {
            if(directors.get(director) == max) {
                directorsMax.add(director);
            }
        }
        for(String director : directorsMax) {
            System.out.println(director + "\t" + directors.get(director));
        }
    }

    public ArrayList<Rater> loadRaters(String fileName) {

        ArrayList<Rater> raters = new ArrayList<>();

        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser) {
            boolean isInList = false;
            for(Rater rater : raters) {
                if(rater.getID().equals(record.get("rater_id"))) {
                    isInList = true;
                    break;
                }
            }
            if(!isInList) {
                Rater rater = new EfficientRater(record.get("rater_id"));
                rater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                raters.add(rater);
            }
            else {
                for(Rater rater : raters) {
                    if(rater.getID().equals(record.get("rater_id"))) {
                        rater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                    }
                }
            }
        }
        return raters;
    }

    public void testLoadRaters() {

        ArrayList<Rater> raters = loadRaters("data/ratings_short.csv");
        System.out.println("Number of raters: " + raters.size());

        /*for(Rater rater : raters) {
            System.out.println("Rater's ID: " + rater.getID() +
                    ", Number of ratings made: " + rater.getItemsRated().size());
            for(String rating : rater.getItemsRated()) {
                System.out.print("Movie ID: " + rating + ", Rating: " + rater.getRating(rating) + ", ");
            }
            System.out.println();
        }*/

        String id = "193";
        for(Rater rater : raters) {
            if(rater.getID().equals(id)) {
                System.out.println("Rater's ID: " + rater.getID() +
                        ", Number of ratings made: " + rater.getItemsRated().size());
                for(String rating : rater.getItemsRated()) {
                    System.out.print("Movie ID: " + rating + ", Rating: " + rater.getRating(rating) + ", ");
                }
                System.out.println();
            }
        }

        int max = 0;
        for(Rater rater : raters) {
            if(rater.getItemsRated().size() >= max) {
                max = rater.getItemsRated().size();
            }
        }
        ArrayList<Rater> maxRaters = new ArrayList<>();
        for(Rater rater : raters) {
            if(rater.getItemsRated().size() == max) {
                maxRaters.add(rater);
            }
        }
        System.out.println("Maximum number of ratings by a user: " + max);
        System.out.println("Raters with maximum number of ratings: ");
        for(Rater rater : maxRaters) {
            System.out.println("Rater's ID: " + rater.getID());
        }

        String movie = "1798709";
        int count = 0;
        for(Rater rater : raters) {
            for(String currentMovie : rater.getItemsRated()) {
                if(currentMovie.equals(movie)) {
                    count++;
                }
            }
        }
        System.out.println("Number of ratings for movie with ID " + movie +
                ": " + count);

        ArrayList<String> movies = new ArrayList<>();
        for(Rater rater : raters) {
            for(String item : rater.getItemsRated()) {
                boolean isInList = false;
                for(String currentMovie : movies) {
                    if(currentMovie.equals(item)) {
                        isInList = true;
                    }
                }
                if(!isInList) {
                    movies.add(item);
                }
            }
        }
        System.out.println("Number of unique movies rated: " + movies.size());


    }
}
