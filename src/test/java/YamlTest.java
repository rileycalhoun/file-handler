import com.blackcandletech.yaml.BCTYaml;
import com.blackcandletech.yaml.YamlFile;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class YamlTest {

    @Test
    public void isYamlSavingProperly() {
        YamlFile yamlFile = BCTYaml.getYamlFile("test", "yamltest", false);
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
