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
            System.out.println(BOLD + TEXT_WHITE + "Title: " + TEXT_CIANO + content.title());
            System.out.println(BOLD + TEXT_WHITE + "Poster: " + TEXT_BLUE + content.imageUrl());
        }
    }
    private static String getMovies() {
        HttpApiClient http = new HttpApiClient();
        return http.getData(API_URL.IMDB_MOVIES.getUrl());
    }

    private static String getPopularMovies()  {
        HttpApiClient http = new HttpApiClient();
        return http.getData(API_URL.IMDB_TOP_MOVIES.getUrl());
    }


    private static String getSeries() {
        HttpApiClient http = new HttpApiClient();
        return http.getData(API_URL.IMDB_TOP_SERIES.getUrl());
    }

    private static String getPopularSeries()  {
        HttpApiClient http = new HttpApiClient();
        return http.getData(API_URL.IMDB_SERIES.getUrl());
    }

    public static void main(String[] args) {
        showTopMovies();
        showPopularMovies();
        showTopSeries();
        showPopularSeries();
    }
}
