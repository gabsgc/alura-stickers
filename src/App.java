import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String [] args) throws Exception {
        boolean menu = true;

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Bem-vindo(a) ao Alura Stickers");
            System.out.println("Escolha a opção desejada: ");
            System.out.println("1 - Criar stickers de TOP Filmes");
            System.out.println("2 - Criar stickers de TOP Séries");
            System.out.println("3 - Criar stickers de Filmes mais populares");
            System.out.println("4 - Criar stickers de Séries mais populares");
            System.out.println("5 - Criar stickers de Linguagens de programação");
            System.out.println("6 - Sair");

            var selected = scanner.nextInt();

            if(selected == 1) {
                System.out.println("Informe a palavra que deseja escrever na figurinha:");
                var word = scanner.next();
                generateIMDBApiStickers(API_URL.IMDB_TOP_MOVIES, word);
            }
            else if(selected == 2) {
                System.out.println("Informe a palavra que deseja escrever na figurinha:");
                var word = scanner.next();
                generateIMDBApiStickers(API_URL.IMDB_TOP_SERIES, word);
            }
            else if(selected == 3) {
                System.out.println("Informe a palavra que deseja escrever na figurinha:");
                var word = scanner.next();
                generateIMDBApiStickers(API_URL.IMDB_MOVIES, word);
            }
            else if(selected == 4) {
                System.out.println("Informe a palavra que deseja escrever na figurinha:");
                var word = scanner.next();
                generateIMDBApiStickers(API_URL.IMDB_SERIES, word);
            }
            else if(selected == 5) {
                System.out.println("Informe a palavra que deseja escrever na figurinha:");
                var word = scanner.next();

                String url = String.valueOf(API_URL.PROGRAMMING_LANGUAGES.getUrl());
                ContentExtractor extractor = new LanguageContentExtractor();
                HttpApiClient http = new HttpApiClient();
                String json = http.getData(url);
                List<Content> contents = extractor.extractsContent(json);

                var color = selectWordColor();

                callStickerGenerator(contents, word, color);
            }
            else if(selected == 6) {
                System.out.println("Encerrando o Alura Stickers...");
                scanner.close();
                menu = false;
            }
            else {
                System.out.println("Por favor, escolha uma opção entre 1 e 5.");
            }

        } while (menu);
    }

    private static void generateIMDBApiStickers(API_URL api, String word) throws IOException {
        String url = String.valueOf(api.getUrl());
        ContentExtractor extractor = new ImdbContentExtractor();
        HttpApiClient http = new HttpApiClient();
        String json = http.getData(url);

        List<Content> contents = extractor.extractsContent(json);

        var color = selectWordColor();

        callStickerGenerator(contents, word, color);
    }

    private static void callStickerGenerator(List<Content> contents, String word, int color) throws IOException {
        StickerGenerator generator = new StickerGenerator();

        for (Content content : contents) {
            String title = content.title();
            String fileName = title.replace(":", "-") + ".png";

            try {
                InputStream inputStream = new URL(content.imageUrl()).openStream();
                generator.create(inputStream, fileName, word, color);
                System.out.printf("Figurinha de %s gerada com sucesso!%n", content.title());
            } catch (java.io.FileNotFoundException e) {
                System.out.println("Image not found.");
            }
        }
    }

    private static int selectWordColor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha a cor desejada: ");
        System.out.println("1 - Azul");
        System.out.println("2 - Laranja");
        System.out.println("3 - Rosa");
        System.out.println("4 - Vermelho");
        System.out.println("5 - Verde");

        return scanner.nextInt();
    }
}
