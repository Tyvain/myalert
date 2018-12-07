package hello;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.Arrays;
import java.util.List;

@Route
@StyleSheet("frontend://styles.css")
public class MainView extends VerticalLayout {
    List<String> colors = Arrays.asList("w3-flat-turquoise",
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
            "w3-flat-asbestos");

    public MainView() {
        HorizontalLayout topLayout = new HorizontalLayout();
        colors.stream().forEach(color -> addNewColorButton(topLayout, color));
        Span main = new Span();
        for (int i = 0; i < 1600; i++) {
            if (i % 40 == 0) {
                add(main);
                main = new Span();
            }
            addButton(main);
        }
        add(topLayout, main);
    }

    private void addNewColorButton(HorizontalLayout topLayout, String color) {
        Button btn = new Button();
        btn.addClassName(color);
        topLayout.add(btn);
    }

    private void addButton(Span main) {
        Button btn = new Button();
        btn.addClickListener(event -> click(event));
        main.add(btn);
    }

    private void click(ClickEvent<Button> event) {
        event.getSource()
                .addClassName("w3-flat-asbestos");
    }
}
