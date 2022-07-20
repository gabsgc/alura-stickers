import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static final String BOLD = "\u001b[1m";
    public static final String ITALIC = "\u001b[3m";
    public static final String TEXT_GREEN = "\u001b[32m";
    public static final String TEXT_YELLOW = "\u001b[33m";
    public static final String TEXT_BLUE = "\u001b[34m";
    public static final String TEXT_MAGENTA = "\u001b[35m";
    public static final String TEXT_CIANO = "\u001b[36m";
    public static final String TEXT_WHITE = "\u001b[37m";

    public static void main(String [] args) throws Exception {
        showTopMovies();
        showPopularMovies();
        showTopSeries();
        showPopularSeries();
    }

    private static void showTopMovies() throws IOException, InterruptedException {
        System.out.println(ITALIC + TEXT_MAGENTA + "Top 250 Movies - IMDb");
        String movies = getMovies();
        jsonParserAPI(movies);
    }

    private static void showPopularMovies() throws IOException, InterruptedException {
        System.out.println(ITALIC + TEXT_GREEN + "Most Popular Movies - IMDb");
        String response = getPopularMovies();
        jsonParserAPI(response);
    }

    private static void showPopularSeries() throws IOException, InterruptedException {
        System.out.println(ITALIC + TEXT_GREEN + "Most Popular Series - IMDb");
        String response = getPopularSeries();
        jsonParserAPI(response);
    }

    private static void showTopSeries() throws IOException, InterruptedException {
        System.out.println(ITALIC + TEXT_MAGENTA + "Most Top Series - IMDb");
        String response = getSeries();
        jsonParserAPI(response);
    }

    private static void jsonParserAPI(String response) {
        JsonParser parser = new JsonParser();
        List<Map<String, String>> apiList = parser.parse(response);

        for (Map<String, String> item : apiList) {
            System.out.println(BOLD + TEXT_WHITE + "Title: " + TEXT_CIANO + item.get("title"));
            System.out.println(BOLD +  TEXT_WHITE + "Poster: " + TEXT_BLUE + item.get("image"));
            System.out.println(BOLD +  TEXT_WHITE + "Rating: " + TEXT_YELLOW + item.get("imDbRating"));
        }
    }

    private static String getMovies() throws IOException, InterruptedException {
        String url = "https://alura-filmes.herokuapp.com/conteudos";
        return getResponse(url);
    }

    private static String getPopularMovies() throws IOException, InterruptedException {
        String url = "https://api.mocki.io/v2/549a5d8b/MostPopularMovies";
        return getResponse(url);
    }

    private static String getSeries() throws IOException, InterruptedException {
        String url = "https://api.mocki.io/v2/549a5d8b/Top250TVs";
        return getResponse(url);
    }

    private static String getPopularSeries() throws IOException, InterruptedException {
        String url = "https://api.mocki.io/v2/549a5d8b/MostPopularTVs";
        return getResponse(url);
    }

    private static String getResponse(String url) throws IOException, InterruptedException {
        URI uri = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
