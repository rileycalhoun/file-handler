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
        yamlFile.set("yaml.file.is.saving.properly", "yes");
        yamlFile.saveYamlFile();

        YamlFile newFile = BCTYaml.getYamlFile("test", settings);
        assert newFile.getString("yaml.file.is.saving.properly").equals("yes");
    }

    @Test
    public void canYamlCopyFile() {
        YamlSettings settings = new YamlSettings.Builder(true)
                .setPath("test").build();
        YamlFile yamlFile = BCTYaml.getYamlFile("copy", settings);
        String test = yamlFile.getString("test");
        assert test.equals("working");
    }

    @Test
    public void runAllTests() {
        canYamlCopyFile();
        isYamlSavingProperly();
    }

}
