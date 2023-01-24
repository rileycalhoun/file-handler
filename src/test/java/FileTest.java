import com.blackcandletech.filehandler.FileHandler;
import com.blackcandletech.filehandler.BCTFile;
import com.blackcandletech.filehandler.FileSettings;
import org.junit.jupiter.api.Test;

public class FileTest {

    @Test
    public void isYamlSavingProperly() {
        FileSettings settings = new FileSettings.Builder(FileSettings.FileType.YAML)
                .setPath("test").build();
        BCTFile yamlFile = FileHandler.getFile("test", settings);
        yamlFile.set("yaml.file.is.saving.properly", "yes");
        yamlFile.saveFile();

        BCTFile newFile = FileHandler.getFile("test", settings);
        assert newFile.getString("yaml.file.is.saving.properly").equals("yes");
    }

    @Test
    public void canYamlCopyFile() {
        FileSettings settings = new FileSettings.Builder(FileSettings.FileType.YAML)
                .setPath("test").build();
        BCTFile yamlFile = FileHandler.getFile("copy", settings);
        String test = yamlFile.getString("test");
        assert test.equals("working");
    }

    @Test
    public void isJsonSavingProperly() {
        FileSettings settings = new FileSettings.Builder(FileSettings.FileType.JSON)
                .setPath("test").build();
        BCTFile yamlFile = FileHandler.getFile("test", settings);
        yamlFile.set("json.file.is.saving.properly", "yes");
        yamlFile.saveFile();

        BCTFile newFile = FileHandler.getFile("test", settings);
        assert newFile.getString("json.file.is.saving.properly").equals("yes");
    }

    @Test
    public void canJsonCopyFile() {
        FileSettings settings = new FileSettings.Builder(FileSettings.FileType.JSON)
                .setPath("test").build();
        BCTFile yamlFile = FileHandler.getFile("copy", settings);
        String test = yamlFile.getString("test");
        assert test.equals("working");
    }

    @Test
    public void runAllTests() {
        canYamlCopyFile();
        isYamlSavingProperly();

        canJsonCopyFile();
        isJsonSavingProperly();
    }

}
