package hello;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route
@StyleSheet("frontend://styles.css")
public class MainView extends VerticalLayout {
    List<String> colors = Stream.of("_1abc9c",
            "_22ecc71",
            "_3498db",
            "_9b59b6",
            "w3-flat-wet-asphalt",
            "w3-flat-green-sea",
            "w3-flat-nephritis",
            "w3-flat-belize-hole",
            "w3-flat-wisteria",
            "w3-flat-midnight-blue",
            "w3-flat-sun-flower",
            "w3-flat-carrot",
            "w3-flat-alizarin",
            "w3-flat-clouds",
            "w3-flat-concrete",
            "w3-flat-orange",
            "w3-flat-pumpkin",
            "w3-flat-pomegranate",
            "w3-flat-silver",
            "_7f8c8d").collect(Collectors.toList());
    HorizontalLayout topLayout = new HorizontalLayout();
    VerticalLayout lines = new VerticalLayout();
    String color = "#1abc9c";
    CacheRepo cacheRepo;

    public MainView(PixelRepository pixelRepository) {
        cacheRepo = new CacheRepo(pixelRepository);
        refreshPixels();
        add(topLayout, lines);
    }

    private void draw() {
        // Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();
        List<Pixel> pixels = cacheRepo.findAll();
        pixels.stream().forEach(pixel -> {
            g2d.setColor(getColorFromPixel(pixel));
            g2d.fillRect(pixel.getPosition().x * (400/Application.SIZE), pixel.getPosition().y * (400/Application.SIZE), 20, 20);
        });

        g2d.dispose();
        // Save as PNG
        File file = new File("myimage.png");
        try {
            ImageIO.write(bufferedImage, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Color getColorFromPixel(Pixel pixel) {
        return hex2Rgb(pixel.getColor());
    }

    public static Color hex2Rgb(String colorStr) {
        return new Color(
                Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
                Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
                Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
    }

    private void refreshPixels() {
        topLayout.removeAll();
        lines.removeAll();
        HorizontalLayout line;
        colors.stream().forEach(color -> addNewPaletteButton(topLayout, color));
        topLayout.add(new Button("save", event -> draw()));
        List<Pixel> pixels = cacheRepo.findAll();
        for (int x = 0; x < Application.SIZE; ++x) {
            int finalX = x;
            line = new HorizontalLayout();
            List<Pixel> pixelLine = getPixelsByLine(pixels, finalX);
            for (int y = 0; y < Application.SIZE; ++y) {
                int finalY = y;
                Pixel pix = getPixelInLine(pixelLine, finalY);
                addNewColorButton(line, pix);
            }
            lines.add(line);
        }
    }

    private List<Pixel> getPixelsByLine(List<Pixel> pixels, int lineNumber) {
        return pixels.stream()
                .filter(pixel -> pixel.getPosition().x == lineNumber)
                .collect(Collectors.toList());
    }

    private Pixel getPixelInLine(List<Pixel> pixelLine, int y) {
        return pixelLine.stream().filter(pixel -> pixel.getPosition().y == y).findFirst().get();
    }

    private void addNewPaletteButton(HorizontalLayout topLayout, String color) {
        Button btn = new Button();
        btn.addClassName(color);
        topLayout.add(btn);
        btn.addClickListener(event -> this.color = color);
    }

    private void addNewColorButton(HorizontalLayout topLayout, Pixel pixel) {
        Button btn = new Button();
        btn.addClassName(pixel.color);
        topLayout.add(btn);
        btn.addClickListener(event -> save(pixel, btn));
    }

    private void save(Pixel pixel, Button btn) {
        btn.removeClassName(pixel.getColor());
        btn.addClassName(this.color);
        pixel.setColor(this.color);
        cacheRepo.save(pixel);
    }
}
