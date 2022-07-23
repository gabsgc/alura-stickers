import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String [] args) throws Exception {
        String url = String.valueOf(API_URL.IMDB_SERIES.getUrl());
        ContentExtractor extractor = new ImdbContentExtractor();

        HttpApiClient http = new HttpApiClient();
        String json = http.getData(url);

        List<Content> contents = extractor.extractsContent(json);

        generateSticker(contents);
    }

    private static void generateSticker(List<Content> contents) throws IOException {
        StickerGenerator generator = new StickerGenerator();

        for (Content content : contents) {
            String title = content.title();
            String fileName = title.replace(":", "-") + ".png";

            try {
                InputStream inputStream = new URL(content.imageUrl()).openStream();
                generator.create(inputStream, fileName);
                System.out.println(content.title());
            } catch (java.io.FileNotFoundException e) {
                System.out.println("Image not found.");
            }
        }
    }
}
