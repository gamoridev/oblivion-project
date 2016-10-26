import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Renderiza  implements GLEventListener, KeyListener
{
    Personagem personagem = new Personagem();
    Ambiente ambiente = new Ambiente();
    Imagem imagem = new Imagem();
    private final GLUT glut = new GLUT();
    private final GLU glu = new GLU();
    private float rquad = 0.0f, rquadAntigo = 0.0f;
    private float eixoX = 0f, eixoY = 0f, eixoZ= -34f;
    private float velocidadeX = 4.0f, velocidadeY = 0.0f, velocidadeZ = 0.0f;
    private float auxY = 0, auxX = 0;
    private boolean pula = true, startF, startD, startE, CD = true;
    private boolean giroEsquerda = false, giroDireita = false; 
//    private float ambiente.getAnguloAmb() = 0, anguloBloco = 0 ;
    private int wView = 1000, yView = 750;
    int contSaltos = 0;
    float variavelBOA;
       
    //VARIAVEIS TEXTURA
    private int idTextura[];
    private GL2 gl;
    Random aleatorio = new Random();

    @Override
    public void display(GLAutoDrawable gLDrawable) {
        final GL2 gl = gLDrawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
        
        gl.glMatrixMode(GL2.GL_PROJECTION);        	
        gl.glLoadIdentity();
        glu.gluPerspective(50f, 1, 0.1, 100);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); 
        glu.gluLookAt(eixoX, eixoY, eixoZ, 0, 0, -0.1, 0, 1, 0);
        
        
        setaRotacao();
        contSaltos ++;
        
        gl.glPushMatrix();
            gl.glRotatef(ambiente.getAnguloAmb(),0f, 0f, 1f);
            gl.glEnable(GL.GL_TEXTURE_2D);          
                ambiente.mostraAmbiente(gl);      
            gl.glDisable(GL.GL_TEXTURE_2D);	
            
            personagem.mostraPersonagem(glut, gl);
        gl.glPopMatrix();
        
        if (startF){
            jumpFrente();
            moveAmbienteFrente();
        }
        if (startD){
            jumpDireita();
            moveAmbienteFrente();
        }
        if (startE){
            jumpEsquerda();
            moveAmbienteFrente();
        }
        if (giroEsquerda){
            giraAmbEsquerda();
        }
        if (giroDireita){
            giraAmbDireita();
        }
        personagem.setRotacao(personagem.getRotacao()+ variavelBOA);
        gl.glFlush();
    }
    
    public void displayChanged(GLAutoDrawable gLDrawable,
        boolean modeChanged, boolean deviceChanged) {
    }

    @Override
    public void init(GLAutoDrawable glad) {
        // TUDO ISSO AQUI PRA TEXTURA DO AMBIENTE
        // Comandos de inicialização para textura
        imagem.loadImage("te.jpg");// TEM 2 ARQUIVOS O TE E O TE2
        gl = glad.getGL().getGL2();
        
        // Gera identificador de textura
        idTextura = new int[10];
        gl.glGenTextures(1, idTextura, 1);

        // Especifica qual é a textura corrente pelo identificador 
        gl.glBindTexture(GL.GL_TEXTURE_2D, idTextura[1]);	

        // Envio da textura para OpenGL
        gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, 3, imagem.getLargura(), imagem.getAltura(), 
                        0, GL.GL_BGR, GL.GL_UNSIGNED_BYTE, imagem.getBuffer());

        // Define os filtros de magnificação e minificação 
        gl.glTexParameteri(GL.GL_TEXTURE_2D,GL.GL_TEXTURE_MIN_FILTER,GL.GL_LINEAR);	
        gl.glTexParameteri(GL.GL_TEXTURE_2D,GL.GL_TEXTURE_MAG_FILTER,GL.GL_LINEAR);
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
        if(CD){
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    eixoY += 1;
                    break;
                case KeyEvent.VK_DOWN:
                    eixoY -= 1;
                    break;
                case KeyEvent.VK_LEFT:
                    eixoX += 1;
                    break;
                case KeyEvent.VK_RIGHT:
                    eixoX -= 1;
                    break;
                case KeyEvent.VK_H:
                    eixoZ += 1;
                    break;
                case KeyEvent.VK_G:
                    eixoZ -= 1;
                    break;
                case KeyEvent.VK_W:
                    startF = true;
                    CD = false;
                    break;
                case KeyEvent.VK_S:
                    ambiente.setAnguloAmb(ambiente.getAnguloAmb()-5);
                    break;
                case KeyEvent.VK_A:
                    if(personagem.getPersonagemX() > 1.1f && personagem.getPersonagemX() < 1.3f ){
                        CD = false;
                        giroEsquerda =true;
                    }else{
                        startE = true;
                        CD = false;
                    }
                    break;
                case KeyEvent.VK_D:
                    if(personagem.getPersonagemX() < -1.1f && personagem.getPersonagemX() > -1.3f ){
                        CD = false;
                        giroDireita = true;
                    }else{
                        startD = true;
                        CD = false;
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                    break;
                case KeyEvent.VK_F:
                    personagem.mostraCordPersonagem();
                    break;
                case KeyEvent.VK_J:
                    ambiente.mostraRotacaoAmbiente();
                    break;
                case KeyEvent.VK_B:
                    giraAmbEsquerda();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
   
    private void moveAmbienteFrente(){
        ambiente.setAmbienteZ(ambiente.getAmbienteZ() - .15f);
    }
    
    private void pula(float velocidadeY, float velocidadeX){
        personagem.setPersonagemY(personagem.getPersonagemY()+ velocidadeY) ;
        personagem.setPersonagemX(personagem.getPersonagemX() + velocidadeX) ;
    }
    
    private void jumpFrente(){
        if (pula && personagem.getPersonagemY() <= auxY){
            pula(.1f,0);
        } else {
            pula = false;
            pula(-.1f,0);
            if (personagem.getPersonagemY() == -1.5f){ 
                pula = true;
                startF = false;
                CD = true;
            }
        }
    }
    
    private void jumpDireita(){
        if (pula && personagem.getPersonagemY() <= auxY){
            pula(.1f,-.04000f);
        } else {
            pula = false;
            pula(-.1f,-.04000f);
            if (personagem.getPersonagemY() == -1.5f){ 
                pula = true;
                startD = false;
                CD = true;
            }
        }
    } 
    private void jumpEsquerda(){
        if (pula && personagem.getPersonagemY() <= auxY){
            pula(.1f,.04f);
        } else {
            pula = false;
            pula(-.1f,.04f);
            if (personagem.getPersonagemY() == -1.5f){ 
                pula = true;
                startE = false;
                CD = true;
            }
        }
    }
    private void giraAmbEsquerda(){
        if(ambiente.getAnguloAmb() == -90.000000f){
            CD = true;
            giroEsquerda = false;
        }else
        {
           ambiente.setAnguloAmb(ambiente.getAnguloAmb()-5);
        }
    }
    private void giraAmbDireita(){
        if(ambiente.getAnguloAmb() == 90.000000f){
            CD = true;
            giroDireita = false;
        }else
        {
           ambiente.setAnguloAmb(ambiente.getAnguloAmb()+5);
        }
    }

    private void setaRotacao() {
        if(contSaltos == 30)
        {
            contSaltos = 0;
            int x = aleatorio.nextInt(7);
            
            if( x == 0)
            {
                variavelBOA = 5;
                personagem.setRotaX(1);
                personagem.setRotaY(0);
                personagem.setRotaZ(0);
            }
            if( x == 1)
            {
                variavelBOA = 5;
                personagem.setRotaX(0);
                personagem.setRotaY(1);
                personagem.setRotaZ(0);
            }
            if( x == 2)
            {
                variavelBOA = 5;
                personagem.setRotaX(0);
                personagem.setRotaY(0);
                personagem.setRotaZ(1);
            } 
            if( x == 0)
            {
                variavelBOA = -5;
                personagem.setRotaX(1);
                personagem.setRotaY(0);
                personagem.setRotaZ(0);
            }
            if( x == 1)
            {
                variavelBOA = -5;
                personagem.setRotaX(0);
                personagem.setRotaY(1);
                personagem.setRotaZ(0);
            }
            if( x == 2)
            {
                variavelBOA = -5;
                personagem.setRotaX(0);
                personagem.setRotaY(0);
                personagem.setRotaZ(1);
            }
        }
        
    }
}