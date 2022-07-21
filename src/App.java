import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String [] args) throws Exception {
        String url = "https://api.mocki.io/v2/549a5d8b/MostPopularMovies";
        ContentExtractor extractor = new ImdbContentExtractor();

        HttpApiClient http = new HttpApiClient();
        String json = http.getData(url);

        List<Content> contents = extractor.extractsContent(json);

        generateSticker(contents);
    }

    private static void generateSticker(List<Content> contents) throws IOException {
        StickerGenerator generator = new StickerGenerator();

        for (Content content : contents) {
            String movieName = content.getTitle();
            String fileName = movieName.replace(":", "-") + ".png";

            try {
                InputStream inputStream = new URL(content.getImageUrl()).openStream();
                generator.create(inputStream, fileName);
                System.out.println(content.getTitle());
            } catch (java.io.FileNotFoundException e) {
                System.out.println("Image not found.");
            }
        }
    }
}
