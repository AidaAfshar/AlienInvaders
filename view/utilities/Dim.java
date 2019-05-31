package view.utilities;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Dim {

    private static Dimension getDim = Toolkit.getDefaultToolkit().getScreenSize();

    public static final int MAX_X = getDim.width;
    public static final int MAX_Y = getDim.height;
    public static final int CENTER_X = (int) MAX_X/2 ;
    public static final int CENTER_Y = (int) MAX_Y/2 ;

}
