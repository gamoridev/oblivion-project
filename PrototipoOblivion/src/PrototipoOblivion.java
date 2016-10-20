import com.jogamp.opengl.GLCapabilities;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class PrototipoOblivion{

    public static void main(String[] args) {
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
//        canvas.addGLEventListener(personagem);
        canvas.addKeyListener(render);

        FPSAnimator animator = new FPSAnimator(canvas, 60);
        animator.start();
    }    
}