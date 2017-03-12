package maketalents;

import java.util.Properties;

/**
 * Created by Baskak on 12.03.2017.
 */
public interface PropReaderIntf {
    String NOT_AVAILABLE = "N/A";
    String DEF_PHOTO_PATH = "default_photo.jpg";

    Properties getProperties();
}
