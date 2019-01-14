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

@Route
@StyleSheet("frontend://styles.css")
public class MainView extends VerticalLayout {
    VerticalLayout paletteLayout = new VerticalLayout();
    HorizontalLayout topLayout = new HorizontalLayout();
    VerticalLayout lines = new VerticalLayout();
    String color = "#1abc9c";
    CacheRepo cacheRepo;

    public MainView(PixelRepository pixelRepository) {
        cacheRepo = new CacheRepo(pixelRepository);
        refreshPixels();
        add(paletteLayout, topLayout, lines, new Button("", event -> draw()));
    }

    private void draw() {
        // Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.rotate(Math.toRadians(270), 200, 200);
        List<Pixel> pixels = cacheRepo.findAll();
        pixels.stream().forEach(pixel -> {
            g2d.setColor(getColorFromPixel(pixel));
            g2d.fillRect(getPos(pixel.getPosition().x), getPos(pixel.getPosition().y), 10, 10);
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

    private int getPos(int x) {
        return x * (400 / Application.SIZE);
    }

    private Color getColorFromPixel(Pixel pixel) {
        return hex2Rgb(pixel.getColor());
    }

    public static Color hex2Rgb(String colorStr) {
        return new Color(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));
    }

    private void refreshPixels() {
        topLayout.removeAll();
        lines.removeAll();
        HorizontalLayout line;
        buildPalette();
        List<Pixel> pixels = cacheRepo.findAll();
        for (int y = Application.SIZE - 1; y >= 0; --y) {
            int finalY = y;
            line = new HorizontalLayout();
            List<Pixel> pixelLine = getPixelsByLine(pixels, finalY);
            for (int x = 0; x < Application.SIZE; ++x) {
                int finalX = x;
                Pixel pix = getPixelInLine(pixelLine, finalX);
                addNewColorButton(line, pix);
            }
            lines.add(line);
        }
    }

    private void buildPalette() {
        HorizontalLayout hl = new HorizontalLayout();
        paletteLayout.add(hl);
        for (int i = 0; i < MyPalette.getColors().toArray().length; i++) {
            if (i % 40 == 0) {
                hl = new HorizontalLayout();
                paletteLayout.add(hl);
            }
            addNewPaletteButton(hl, MyPalette.getColors().get(i));
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
