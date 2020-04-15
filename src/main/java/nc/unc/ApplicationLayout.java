package nc.unc;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.RouterLayout;

public final class ApplicationLayout extends Div implements RouterLayout {

    public ApplicationLayout() {
        Main main = new Main();
        main.setId("content");
        add(main);
    }
}