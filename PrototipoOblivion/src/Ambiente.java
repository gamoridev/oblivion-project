
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Ambiente implements GLEventListener, KeyListener {

    private final GLUT glut = new GLUT();
    private final GLU glu = new GLU();
    private float rquad = 0.0f, rquadAntigo = 0.0f;
    private float eixoX , eixoY, eixoZ;
    private float distancia = -5f;
    private int wView = 1000, yView = 750;

    /**
     *
     * @param gLDrawable Contém as características do objeto, atualizando-o a cada frame.
     */
    @Override
    public void display(GLAutoDrawable gLDrawable) {
        final GL2 gl = gLDrawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

        //Transformação do viewPort       
        gl.glViewport(0, 0, this.wView, this.yView);
        gl.glLoadIdentity(); 

        gl.glMatrixMode(GL2.GL_PROJECTION);        	
        gl.glLoadIdentity();

        //Projeção Ortogonal do objeto
//        gl.glOrtho(-3.0, 3.0, -3.0, 3.0, -7.0, 7.0);

        //Projeção Perspectiva do objeto
//        gl.glFrustum(-1, 1, -1, 1, 0.1, 100.0);
        glu.gluPerspective(45f, 1, 0.1, 1000);

        //Transformação de modelo
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); 
        glu.gluLookAt(0, 0, distancia, 0, 0, -0.1, 0, 1, 0);

        gl.glRotatef(rquad, eixoX, eixoY, eixoZ);
       
        //Chão
        gl.glPushMatrix();
            gl.glTranslatef( 0f , -0.8f, 0f);
            gl.glScalef(1.95f, .2f, 1.2f);
            gl.glColor3f(0f, 0f, 1f);
            glut.glutSolidCube(1f);
        gl.glPopMatrix();

        //Parede direita
        gl.glPushMatrix();
            gl.glTranslatef( -.85f , 0f, 0f);
            gl.glScalef(.2f, 1.85f, 20f);
            gl.glColor3f(1f, 1f, 0f);
            glut.glutSolidCube(1f);
        gl.glPopMatrix();

        //Parede esquerda
        gl.glPushMatrix();
            gl.glTranslatef( .85f , 0f, 0f);
            gl.glScalef(.2f, 1.85f, 20f);
            gl.glColor3f(0f, 1f, 1f);
            glut.glutSolidCube(1f);
        gl.glPopMatrix();

        //Teto
        gl.glPushMatrix();
            gl.glTranslatef( 0f , 0.8f, 0f);
            gl.glScalef(1.95f, .2f, 1.2f);
            gl.glColor3f(0f, 0f, 1f);
            glut.glutSolidCube(1f);
        gl.glPopMatrix();
        
        gl.glFlush();
    }
    
    public void displayChanged(GLAutoDrawable gLDrawable,
        boolean modeChanged, boolean deviceChanged) {
    }

    @Override
    public void init(GLAutoDrawable glad) {
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                atualizaRotacao(2f);
                eixoX = 1;
                eixoY = 0;
                eixoZ = 0;
                break;
            case KeyEvent.VK_RIGHT:
                atualizaRotacao(2f);
                eixoX = 0;
                eixoY = 1;
                eixoZ = 0;
                break;
            case KeyEvent.VK_A:
                break;
            case KeyEvent.VK_D:
//                atualizaRotacao(2f);
                break;
            case KeyEvent.VK_W:
                this.distancia += 1;
                System.out.println(distancia);
                break;
            case KeyEvent.VK_S:
                this.distancia -= 1;
                System.out.println(distancia);
                break;
//            case KeyEvent.VK_UP:
//                this.wView += 10;
//                this.yView += 10;
//                System.out.println(wView);
//                break;
            case KeyEvent.VK_DOWN:
                this.wView -= 10;
                this.yView -= 10;
                System.out.println(wView);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    private void atualizaRotacao(float giro){
        rquadAntigo = rquad;
        rquad += giro;
        System.out.println("Raio de rotação: "+ rquad);
    }
}
