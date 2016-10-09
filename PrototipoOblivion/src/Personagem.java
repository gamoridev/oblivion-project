
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Personagem implements GLEventListener, KeyListener {

    private GLUT glut = new GLUT();

    /**
     *
     * @param gLDrawable
     * Contém as características do objeto, atualizando-o a cada frame.
     */
    @Override
    public void display(GLAutoDrawable gLDrawable) {
        final GL2 gl = gLDrawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        
        gl.glPushMatrix();
            gl.glColor3f(1f, 0.411765f, 0.705882f);
            glut.glutSolidCube(.20f);
        gl.glPopMatrix();
        
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable gLDrawable,
            boolean modeChanged, boolean deviceChanged) {

    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        glut = new GLUT();
        gl.glClearColor(.0f, .0f, 0f, 0f);
    }


    @Override
    public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width,
            int height) {
    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        // TODO Auto-generated method stub		
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub		
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}