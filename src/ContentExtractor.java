import java.util.List;

public interface ContentExtractor {
    List<Content> extractsContent(String json);
}
