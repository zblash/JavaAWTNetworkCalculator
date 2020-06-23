import com.yusufcancelik.*;

import java.awt.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            CalcFrame frame = new CalcFrame();
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            frame.setSize(new Dimension(800, 500));
            frame.setTitle("16-701-010 Yusuf Can Celik Ip Network/Broadcast ID Calculator");
            frame.setResizable(false);
            frame.setLocation(screenSize.width / 4, screenSize.height / 4);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}
