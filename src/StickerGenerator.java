import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class StickerGenerator {

    public void create(InputStream inputStream, String fileName) throws IOException {
        BufferedImage originalImage = ImageIO.read(inputStream);

        int stickerWidth = 400;
        int resizedHeight = (originalImage.getHeight() * stickerWidth) / originalImage.getWidth();
        int newHeight = resizedHeight + 50;
        BufferedImage newImage = new BufferedImage(stickerWidth, newHeight, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, stickerWidth, resizedHeight, null);

        var font = new Font("Comic Sans MS", Font.BOLD, 50);
        String phrase = "TOPZERA";

        TextLayout textLayout = new TextLayout(phrase, font, graphics.getFontRenderContext());
        Shape shape = textLayout.getOutline(null);

        graphics.setFont(font);
        graphics.setColor(Color.ORANGE);
        FontMetrics phraseSize = graphics.getFontMetrics(font);

        float x = ((float)(newImage.getWidth() / 2) - ((float) phraseSize.stringWidth(phrase)) / 2);
        int y = newImage.getHeight() - 10;

        graphics.translate(x, y);
        graphics.setStroke(new BasicStroke(4));
        graphics.draw(shape);
        graphics.fill(shape);

        File stickers = new File("stickers/%s".formatted(fileName));
        stickers.mkdirs();

        ImageIO.write(newImage, "png", stickers);
    }
}