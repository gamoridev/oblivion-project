import java.awt.Color;
import javax.swing.JFrame;

public class PrototipoOblivion{

    public static void main(String[] args) {
        Test obj = new Test();
        obj.setCursor(12);
        obj.getContentPane().setBackground( Color.BLACK);
        obj.setSize(300, 100);
        obj.setLocationRelativeTo(null);
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}