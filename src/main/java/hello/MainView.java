package hello;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route
@StyleSheet("frontend://styles.css")
public class MainView extends VerticalLayout {
    List<String> colors = Stream.of("w3-flat-turquoise",
            "w3-flat-emerald",
            "w3-flat-peter-river",
            "w3-flat-amethyst",
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
            "w3-flat-asbestos").collect(Collectors.toList());
    HorizontalLayout topLayout = new HorizontalLayout();
    VerticalLayout lines = new VerticalLayout();
    String color = "w3-flat-sun-flower";
    PixelRepository pixelRepository;

    public MainView(PixelRepository pixelRepository) {
        this.pixelRepository = pixelRepository;
        refreshPixels();
        add(topLayout, lines);
    }

    private void refreshPixels() {
        topLayout.removeAll();
        lines.removeAll();
        HorizontalLayout line;
        colors.stream().forEach(color -> addNewPaletteButton(topLayout, color));
        int width = 40;
        List<Pixel> pixels = pixelRepository.findAll();
        for (int i = 0; i < width; ++i) {
            int finalI = i;
            line = new HorizontalLayout();
            Supplier<Stream<Pixel>> pixelLine = () -> pixels.stream().filter(pixel -> pixel.getPosition().x == finalI);
            for (int j = 0; j < width; ++j) {
                int finalJ = j;
                addNewColorButton(line, getPixel(pixelLine, finalJ));
            }
            lines.add(line);
        }
    }

    private Pixel getPixel(Supplier<Stream<Pixel>> pixelLine, int finalJ) {
        return pixelLine.get().filter(pixel -> pixel.getPosition().y == finalJ).findFirst().get();
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
        pixelRepository.save(pixel);
//        refreshPixels();
    }
}
