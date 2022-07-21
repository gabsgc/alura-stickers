import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImdbContentExtractor implements ContentExtractor {
    @Override
    public List<Content> extractsContent(String json) {
        JsonParser parser = new JsonParser();
        List<Map<String, String>> attributesList = parser.parse(json);

        List<Content> contents = new ArrayList<>();

        for (Map<String, String> attribute : attributesList) {
            String title = attribute.get("title");
            String imageUrl = attribute.get("image")
                    .replace("@._V1_UX128_CR0,1,128,176_AL_", "");
            var content = new Content(title, imageUrl);

            contents.add(content);
        }
        return contents;
    }
}
