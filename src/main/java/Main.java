
public class Main {

    public static void main(String[] args) {
        
        //MovieRunnerSimilarRatings movieRunnerSimilarRatings = new MovieRunnerSimilarRatings();
        //movieRunnerSimilarRatings.printAverageRatings();
        //movieRunnerSimilarRatings.printAverageRatingsByYearAfterAndGenre();
        //movieRunnerSimilarRatings.printSimilarRatings();
        //movieRunnerSimilarRatings.printSimilarRatingsByGenre();
        //movieRunnerSimilarRatings.printSimilarRatingsByDirector();
        //movieRunnerSimilarRatings.printSimilarRatingsByGenreAndMinutes();
        //movieRunnerSimilarRatings.printSimilarRatingsByYearAfterAndMinutes();
        RecommendationRunner recommendationRunner = new RecommendationRunner();
        recommendationRunner.printRecommendationsFor("485");
    }
}
