import java.util.*;
public class MovieRatingClient {
    public static void main(String[] args) {
        MovieRating bestMovie = new MovieRating("Gordon Ramsay Yelling ASMR (3 hour mix)");
        MovieRating decentMovie = new MovieRating("Learn Java immediately using this simple trick!");
        
        Set<MovieRating> movieTheater = new HashSet<>();
        movieTheater.add(decentMovie);
        movieTheater.add(bestMovie);

        bestMovie.addRating(1234.5678);
        decentMovie.addRating(4.0);
        decentMovie.addRating(5.0);

        System.out.println("Top movies to show:");
        for (MovieRating movie : movieTheater) {
            System.out.println(movie.movieTitle() + ": " + movie.averageRating());
        }
    }
}
