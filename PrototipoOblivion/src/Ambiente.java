
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Ambiente implements GLEventListener {

    private GLUT glut = new GLUT();

    /**
     *
     * @param gLDrawable Contém as características do objeto, atualizando-o a
     * cada frame.
     */
    @Override
    public void display(GLAutoDrawable gLDrawable) {
        final GL2 gl = gLDrawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        final GL2 gl2 = gLDrawable.getGL().getGL2();
       
        //Chão
        
        gl.glPushMatrix();
            gl.glTranslatef( 0f , -0.8f, 0f);
            gl.glScalef(1.95f, .2f, 1.2f);
            gl.glColor3f(1f, 1f, 1f);
            glut.glutSolidCube(1f);
        gl.glPopMatrix();

//        //Parede direita
//        
        gl.glPushMatrix();
            gl.glTranslatef( -.85f , 0f, 0f);
            gl.glScalef(.2f, 1.85f, 1.2f);
            gl.glColor3f(1f, 1f, 1f);
            glut.glutSolidCube(1f);
        gl.glPopMatrix();
//        
//        
//        //Parede esquerda
        gl.glPushMatrix();
            gl.glTranslatef( .85f , 0f, 0f);
            gl.glScalef(.2f, 1.85f, 1.2f);
            gl.glColor3f(1f, 1f, 1f);
            glut.glutSolidCube(1f);
        gl.glPopMatrix();
//        
//        
//        //Teto
        gl.glPushMatrix();
            gl.glTranslatef( 0f , 0.8f, 0f);
            gl.glScalef(1.95f, .2f, 1.2f);
            gl.glColor3f(1f, 1f, 1f);
            glut.glutSolidCube(1f);
        gl.glPopMatrix();
//        
        
        gl.glFlush();
    }
    
    public void displayChanged(GLAutoDrawable gLDrawable,
        boolean modeChanged, boolean deviceChanged) {
    }

    @Override
    public void init(GLAutoDrawable glad) {
        GL2 gl = glad.getGL().getGL2();
        glut = new GLUT();
        gl.glClearColor(.0f, .0f, 0f, 0f);
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
        
    }
}
