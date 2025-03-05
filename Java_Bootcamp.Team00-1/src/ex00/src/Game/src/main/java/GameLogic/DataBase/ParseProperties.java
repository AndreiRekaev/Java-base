package GameLogic.DataBase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseProperties {
    public static Map<String, String> parserHandler(Path pathProperties) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(pathProperties.toFile()));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        Map<String, String> properties = new HashMap<>(lines.size());
        for(String l : lines) {
            String[] strings = l.split("\\s+");
            if(strings.length != 3) {
                properties.put(strings[0], " ");
            } else {
                properties.put(strings[0], strings[2]);
            }
        }
        return properties;
    }
}
