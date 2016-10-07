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

        Frame frame = new Frame("CUBO 3D");
        
        frame.setSize(1000, 1000);
        frame.add(canvas);
        frame.setVisible(true);
        frame.setResizable(false);
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        Personagem personagem = new Personagem();
        Ambiente ambiente = new Ambiente();
        
        canvas.addGLEventListener(ambiente);
//        canvas.addGLEventListener(personagem);
//        canvas.addKeyListener(personagem);

        FPSAnimator animator = new FPSAnimator(canvas, 30);
        animator.start();
    }    
}