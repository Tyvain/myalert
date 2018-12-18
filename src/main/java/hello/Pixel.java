package hello;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.awt.*;

@Entity
public class Pixel {
    @Id
    Point position;
    String color;

    public Pixel() {
    }

    public Pixel(Point id, String color) {
        this.position = id;
        this.color = color;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
