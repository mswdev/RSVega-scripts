package org.api.client.screenshot;

import org.rspeer.script.Script;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.function.Consumer;

public class ScreenshotConsumer implements Consumer<Image> {

    private String name;

    ScreenshotConsumer(String name) {
        this.name = name;
    }

    @Override
    public void accept(Image image) {
        final BufferedImage buffered_image = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        final Graphics2D graphics_2d = buffered_image.createGraphics();
        graphics_2d.drawImage(image, 0, 0, null);
        graphics_2d.dispose();

        if (name == null || name.length() <= 0)
            name = new Date().toString();

        try {
            File img = new File(Script.getDataDirectory().toString(),
                    File.separator + "screenshots" + File.separator + name + ".png");
            if (!img.exists() && !img.mkdirs() && !img.createNewFile()) {
                return;
            }

            ImageIO.write(buffered_image, "png", img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

