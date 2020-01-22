import java.util.ArrayList;
import java.util.Collections;

public class FourthRatings {

    private double getAverageByID(String id, int minimalRaters) {

        double sum = 0;
        int count = 0;
        for(Rater rater : RaterDatabase.getRaters()) {
            if(rater.hasRating(id)) {
                sum += rater.getRating(id);
                count++;
            }
        }
        if(count >= minimalRaters) {
            return sum / count;
        }
        return 0.0;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {

        ArrayList<Rating> ratings = new ArrayList<>();
        for(String movieId : MovieDatabase.filterBy(new TrueFilter())) {
            double average = getAverageByID(movieId, minimalRaters);
            if(average != 0) {
                ratings.add(new Rating(movieId, average));
            }
        }
        return ratings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {

        ArrayList<Rating> ratings = new ArrayList<>();
        for(String movieId : MovieDatabase.filterBy(filterCriteria)) {
            double average = getAverageByID(movieId, minimalRaters);
            if(average != 0) {
                ratings.add(new Rating(movieId, average));
            }
        }
        return ratings;
    }

    private double dotProduct(Rater me, Rater r) {

        double dotProduct = 0;
        for(String movieId : me.getItemsRated()) {
            if(r.hasRating(movieId)) {
                dotProduct += (me.getRating(movieId) - 5) * (r.getRating(movieId) - 5);
            }
        }
        return dotProduct;
    }

    private ArrayList<Rating> getSimilarities(String id) {

        ArrayList<Rating> ratings = new ArrayList<>();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : RaterDatabase.getRaters()) {
            if(!r.getID().equals(id)) {
                double dotProduct = dotProduct(me, r);
                if(dotProduct >= 0) {
                    //System.out.println(dotProduct);
                    ratings.add(new Rating(r.getID(), dotProduct));
                }
            }
        }

        Collections.sort(ratings, Collections.reverseOrder());
        return ratings;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {

        ArrayList<Rating> similarities = getSimilarities(id);

        ArrayList<Rating> ratings = new ArrayList<>();
        for(String movieId : MovieDatabase.filterBy(new TrueFilter())) {
            double sum = 0;
            int count = 0;
            for(int k = 0; k <= numSimilarRaters; k++) {
                if(RaterDatabase.getRater(similarities.get(k).getItem()).hasRating(movieId)) {
                    double similarRating = similarities.get(k).getValue();
                    double raterRating = RaterDatabase.getRater(similarities.get(k).getItem()).getRating(movieId);
                    sum += similarRating * raterRating;
                    count++;
                }
            }
            if(count >= minimalRaters) ratings.add(new Rating(movieId, sum/count));
        }
        Collections.sort(ratings, Collections.reverseOrder());
        return ratings;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {

        ArrayList<Rating> similarities = getSimilarities(id);

        ArrayList<Rating> ratings = new ArrayList<>();
        for(String movieId : MovieDatabase.filterBy(filterCriteria)) {
            double sum = 0;
            int count = 0;
            for(int k = 0; k <= numSimilarRaters; k++) {
                if(RaterDatabase.getRater(similarities.get(k).getItem()).hasRating(movieId)) {
                    double similarRating = similarities.get(k).getValue();
                    double raterRating = RaterDatabase.getRater(similarities.get(k).getItem()).getRating(movieId);
                    sum += similarRating * raterRating;
                    count++;
                }
            }
            if(count >= minimalRaters) ratings.add(new Rating(movieId, sum/count));
        }
        Collections.sort(ratings, Collections.reverseOrder());
        return ratings;
    }
}
