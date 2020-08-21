import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

public class MyThread extends Thread {

    @Override
    public void run() {
        String ACCESS_TOKEN =
                "Your generated access token (Dropbox)";

        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        for(;;) {
            Uploader upload = new Uploader(client);
            upload.start();
            try {
                sleep(10000);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
