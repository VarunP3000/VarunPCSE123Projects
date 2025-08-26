package MediaDebugging;
import java.util.*;

public class Book implements Media{
    private String title;
    private List<String> authors;
    private List<Integer> ratings;

    public Book(String title, String author){
        this.title = title;
        this.authors = new ArrayList<String>();
        this.authors.add(author);
        this.ratings = new ArrayList<Integer>();
    }

    public Book(String title, List<String> authors){
        this.title = title;
        this.authors = new ArrayList<String>();
        this.authors.addAll(authors);
        this.ratings = new ArrayList<Integer>();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<String> getArtists() {
        List<String> aux = new ArrayList<String>();
        aux.addAll(authors);
        return aux;
    }

    @Override
    public void addRating(int score) {
        ratings.add(score);
    }

    @Override
    public int getNumRatings() {
        return ratings.size();
    }

    @Override
    public double getAverageRating() {
        double averageRating = 0.0;
        double sum = 0.0;
        double num = 1.0;
        for(int rating: ratings){
            sum+=rating;
            averageRating = sum/num;
            num++;
        }
        return averageRating;
    }

}