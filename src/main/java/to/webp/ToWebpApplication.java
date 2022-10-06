package to.webp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class ToWebpApplication implements ApplicationRunner {

    @Value("classpath:/javascript.jpg")
    private Resource javascript_jpg;

    @Value("classpath:/vscode.png")
    private Resource vscode_png;

    public static void main(String[] args) {
        SpringApplication.run(ToWebpApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.jpgToWebp();
        this.pngToWebp();
    }

    private void jpgToWebp() throws Exception {
        BufferedImage bufferedImage = ImageIO.read(this.javascript_jpg.getInputStream());
        Assert.notNull(bufferedImage, "bufferedImage must be not null");
        Path webpFile = Files.createFile(Paths.get("javascript.webp"));
        ImageIO.write(bufferedImage, "webp", webpFile.toFile());
    }

    private void pngToWebp() throws Exception {
        BufferedImage bufferedImage = ImageIO.read(this.vscode_png.getInputStream());
        Assert.notNull(bufferedImage, "bufferedImage must be not null");
        Path webpFile = Files.createFile(Paths.get("vscode.webp"));
        ImageIO.write(bufferedImage, "webp", webpFile.toFile());
    }
}
