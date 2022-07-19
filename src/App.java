import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static final String BOLD = "\u001b[1m";
    public static final String ITALIC = "\u001b[3m";
    public static final String TEXT_YELLOW = "\u001b[33m";
    public static final String TEXT_BLUE = "\u001b[34m";
    public static final String TEXT_MAGENTA = "\u001b[35m";
    public static final String TEXT_CIANO = "\u001b[36m";
    public static final String TEXT_WHITE = "\u001b[37m";

    public static void main(String [] args) throws Exception {
        String url = "https://alura-filmes.herokuapp.com/conteudos";
        URI uri = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        JsonParser parser = new JsonParser();
        List<Map<String, String>> moviesList = parser.parse(body);

        System.out.println(ITALIC + TEXT_MAGENTA + "Top 250 Movies - IMDb");
        for (Map<String, String> movie : moviesList) {
            System.out.println(BOLD + TEXT_WHITE + "Title: " + TEXT_CIANO + movie.get("title"));
            System.out.println(BOLD +  TEXT_WHITE + "Poster: " + TEXT_BLUE + movie.get("image"));
            System.out.println(BOLD +  TEXT_WHITE + "Rating: " + TEXT_YELLOW + movie.get("imDbRating"));
        }
    }
}
