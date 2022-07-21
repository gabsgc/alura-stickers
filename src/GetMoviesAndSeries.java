import java.util.List;

public class GetMoviesAndSeries {
    public static final String BOLD = "\u001b[1m";
    public static final String ITALIC = "\u001b[3m";
    public static final String TEXT_GREEN = "\u001b[32m";
    public static final String TEXT_BLUE = "\u001b[34m";
    public static final String TEXT_MAGENTA = "\u001b[35m";
    public static final String TEXT_CIANO = "\u001b[36m";
    public static final String TEXT_WHITE = "\u001b[37m";

    private static void showTopMovies() {
        System.out.println(ITALIC + TEXT_MAGENTA + "Top 250 Movies - IMDb");
        String getTopMovies = getMovies();
        getExtractedContent(getTopMovies);
    }

    private static void showPopularMovies() {
        System.out.println(ITALIC + TEXT_GREEN + "Most Popular Movies - IMDb");
        getExtractedContent(getPopularMovies());
    }

    private static void showPopularSeries() {
        System.out.println(ITALIC + TEXT_GREEN + "Most Popular Series - IMDb");
        getExtractedContent(getPopularSeries());
    }

    private static void showTopSeries() {
        System.out.println(ITALIC + TEXT_MAGENTA + "Most Top Series - IMDb");
        getExtractedContent(getSeries());
    }

    private static void getExtractedContent(String getContent) {
        ContentExtractor extractor = new ImdbContentExtractor();
        List<Content> contents = extractor.extractsContent(getContent);

        for (Content content : contents) {
            System.out.println(BOLD + TEXT_WHITE + "Title: " + TEXT_CIANO + content.getTitle());
            System.out.println(BOLD + TEXT_WHITE + "Poster: " + TEXT_BLUE + content.getImageUrl());
        }
    }
    private static String getMovies() {
        String url = "https://alura-imdb-api.herokuapp.com/movies";
        HttpApiClient http = new HttpApiClient();
        return http.getData(url);
    }

    private static String getPopularMovies()  {
        String url = "https://api.mocki.io/v2/549a5d8b/MostPopularMovies";
        HttpApiClient http = new HttpApiClient();
        return http.getData(url);
    }


    private static String getSeries() {
        String url = "https://api.mocki.io/v2/549a5d8b/Top250TVs";
        HttpApiClient http = new HttpApiClient();
        return http.getData(url);
    }

    private static String getPopularSeries()  {
        String url = "https://api.mocki.io/v2/549a5d8b/MostPopularTVs";
        HttpApiClient http = new HttpApiClient();
        return http.getData(url);
    }

    public static void main(String[] args) {
        showTopMovies();
        showPopularMovies();
        showTopSeries();
        showPopularSeries();
    }
}
