package galaga.client;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ImageCache {
    private HashMap<String, Image> mapping;

    public ImageCache() {
        this.mapping = new HashMap<>();
    }

    public Image getImage(String imagePath) {
        Image image = this.mapping.get(imagePath);

        if (image == null) {
            ImageIcon imageIcon = new ImageIcon(imagePath);
            image = imageIcon.getImage();

            this.mapping.put(imagePath, image);
        }

        return image;
    }
}
