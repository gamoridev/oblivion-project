import com.jogamp.opengl.GLCapabilities;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Test extends JFrame {
    public JButton play = new JButton("PLAY");
    public JButton exit= new JButton("EXIT");
    
    public Test(){
        super("Oblivion Project");
        setLayout(new FlowLayout(FlowLayout.CENTER));
        play.setBackground(Color.red);
        add(play);
        exit.setBackground(Color.red);
        add(exit);
        Handler manipulador = new Handler();
        play.addActionListener(manipulador);
        exit.addActionListener(manipulador);
    }
    private class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == play)
            {
                Test.this.setVisible(false);
                GLProfile glp = GLProfile.getDefault();
                GLCapabilities caps = new GLCapabilities(glp);
                GLCanvas canvas = new GLCanvas(caps);

                Frame frame = new Frame("Protótipo Oblivion");

                frame.setSize(1000, 750);
                frame.setLocationRelativeTo(frame);
                frame.add(canvas);
                frame.setVisible(true);
                frame.setResizable(false);

                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
                Renderiza render = new Renderiza();
                canvas.addGLEventListener(render);
                canvas.addKeyListener(render);

                FPSAnimator animator = new FPSAnimator(canvas, 60);
                animator.start();
            }
            if(e.getSource() == exit)
            {
               System.exit(0);
            }
        }
    }
}
