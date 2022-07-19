import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {

    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_ATTRIBUTES_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");
    public List<Map<String, String>> parse(String json) {
        Matcher matcher = REGEX_ITEMS.matcher(json);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Items not found.");
        }

        String[] items = matcher.group(1).split("\\},\\{");

        List<Map<String, String>> data = new ArrayList<>();

        for (String item : items) {
            Map<String, String> itemAttributes = new HashMap<>();
            Matcher matcherAttributesJson = REGEX_ATTRIBUTES_JSON.matcher(item);
            while (matcherAttributesJson.find()) {
                String attributes = matcherAttributesJson.group(1);
                String value = matcherAttributesJson.group(2);
                itemAttributes.put(attributes, value);
            }
            data.add(itemAttributes);
        }

        return data;
    }
}
