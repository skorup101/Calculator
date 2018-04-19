package UI;
import java.awt.*;

public class FrameLocalization { //class used to get the exact center screen location for JFrame
    private Dimension screenSize;
    int x, y;

    FrameLocalization(double fwidth, double fheight){
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        x=(int)((screenSize.getWidth()-fwidth)/2);
        y=(int)((screenSize.getHeight()-fheight)/2);
    }
}
