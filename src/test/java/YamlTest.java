import com.blackcandletech.yaml.BCTYaml;
import com.blackcandletech.yaml.YamlFile;
import com.blackcandletech.yaml.YamlSettings;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class YamlTest {

    @Test
    public void isYamlSavingProperly() {
        YamlSettings settings = new YamlSettings.Builder(false)
                .setPath("test").build();
        YamlFile yamlFile = BCTYaml.getYamlFile("test", settings);
        yamlFile.set("test.test.test.test.ok.ok.1.1.1.1", "1");
        yamlFile.saveYamlFile();
    }

    @Test
    public void time() {
        Instant start = Instant.now();
        isYamlSavingProperly();
        Instant end = Instant.now();
        System.out.println(end.compareTo(start));
    }

}
