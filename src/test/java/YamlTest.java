import com.blackcandletech.yaml.BCTYaml;
import com.blackcandletech.yaml.YamlFile;
import org.junit.jupiter.api.Test;

public class YamlTest {

    @Test
    public void isYamlSavingProperly() {
        YamlFile yamlFile = BCTYaml.getYamlFile("test", "yamltest", false);
        yamlFile.set("version.test", "1.0.0");
        yamlFile.saveYamlFile();
    }

}
