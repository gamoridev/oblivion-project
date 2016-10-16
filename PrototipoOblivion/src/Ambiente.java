import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.event.MenuKeyEvent;

/*
    PARA MOVIMENTAR O PERSONAGEM SE USA O W, A e D
    PARA MOVIMENTAR A CAMERA SE USA AS SETINHAS, G e H
    
*/
public class Ambiente implements GLEventListener, KeyListener {

    private final GLUT glut = new GLUT();
    private final GLU glu = new GLU();
    private float rquad = 0.0f, rquadAntigo = 0.0f;
    private float eixoX = 0f, eixoY = 0f, eixoZ= -34f;
    private float blocoX = 0f, blocoY = -1.5f, blocoZ = -28f;
    private float velocidadeX = 4.0f, velocidadeY = 0.0f, velocidadeZ = 0.0f;
    private float auxY = 0, auxX = 0;
    private boolean pula = true, startF, startD, startE, CD = true;
    private boolean giroEsquerda = false, giroDireita = false;
    private float ambX = 0f, ambY = 0f, ambZ = 0f;
    private float anguloAmb = 0, anguloBloco =0 ;
    private int wView = 1000, yView = 750;
    int contSaltos = 0;
    
    //VARIAVEIS TEXTURA
    private int largura, altura;
    private TextureData td;
    private BufferedImage imagem;
    private ByteBuffer buffer;
    private int idTextura[];
    private GL2 gl;
    

    @Override
    public void display(GLAutoDrawable gLDrawable) {
        final GL2 gl = gLDrawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

        //Transformação do viewPort       
        //gl.glViewport(0, 0, this.wView, this.yView);
        //gl.glLoadIdentity(); 

        gl.glMatrixMode(GL2.GL_PROJECTION);        	
        gl.glLoadIdentity();

        //Projeção Ortogonal do objeto
//        gl.glOrtho(-3.0, 3.0, -3.0, 3.0, -7.0, 7.0);

        //Projeção Perspectiva do objeto
//        gl.glFrustum(-1, 1, -1, 1, 0.1, 100.0);
        glu.gluPerspective(50f, 1, 0.1, 100);
        
        //Transformação de modelo
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); 
        glu.gluLookAt(eixoX, eixoY, eixoZ, 0, 0, -0.1, 0, 1, 0);
        
        gl.glPushMatrix();
            gl.glRotatef(anguloAmb,0f, 0f, 1f);
            //Ambiente
            gl.glEnable(GL.GL_TEXTURE_2D);
            gl.glPushMatrix();

                gl.glTranslatef(ambX, ambY, ambZ);
                gl.glBegin (GL2.GL_QUADS);

                //Parede Direita
                gl.glColor3f(1f,1f,1f);
                gl.glTexCoord2f(0.0f, 0.0f);     gl.glVertex3f(-2f, 2f, 30f);
                gl.glTexCoord2f(-3.0f, 0.0f);    gl.glVertex3f(-2f, 2f, -30f);
                gl.glTexCoord2f(-3.0f, -3.0f);   gl.glVertex3f(-2f, -2f, -30f);
                gl.glTexCoord2f(0.0f, -3.0f);    gl.glVertex3f(-2f, -2f, 30f);

                //Parede esquerda
                gl.glColor3f(1f,1f,1f);
                gl.glTexCoord2f(0.0f, 0.0f);     gl.glVertex3f(2f, 2f, 30f);
                gl.glTexCoord2f(-3.0f, 0.0f);    gl.glVertex3f(2f, 2f, -30f);
                gl.glTexCoord2f(-3.0f, -3.0f);   gl.glVertex3f(2f, -2f, -30f);
                gl.glTexCoord2f(0.0f, -3.0f);    gl.glVertex3f(2f, -2f, 30f);

                //Teto
                gl.glColor3f(1f,1f,1f);
                gl.glTexCoord2f(0.0f, 0.0f);     gl.glVertex3f(-2f, 2f, 30f);
                gl.glTexCoord2f(-3.0f, 0.0f);    gl.glVertex3f(-2f, 2f, -30f);
                gl.glTexCoord2f(-3.0f, -3.0f);   gl.glVertex3f(2f, 2f, -30f);
                gl.glTexCoord2f(0.0f, -3.0f);    gl.glVertex3f(2f, 2f, 30f);

                //Chão
                gl.glColor3f(1f,1f,1f);
                gl.glTexCoord2f(0.0f, 0.0f);     gl.glVertex3f(-2f, -2f, 30f);
                gl.glTexCoord2f(-3.0f, 0.0f);    gl.glVertex3f(-2f, -2f, -30f);
                gl.glTexCoord2f(-3.0f, -3.0f);   gl.glVertex3f(2f, -2f, -30f);
                gl.glTexCoord2f(0.0f, -3.0f);    gl.glVertex3f(2f, -2f, 30f);

                gl.glEnd();
            gl.glPopMatrix();
            gl.glDisable(GL.GL_TEXTURE_2D);	

            //Personagem
            
            gl.glPushMatrix();
                gl.glTranslatef(blocoX, blocoY, blocoZ);
                gl.glColor3f(1f, 0f, 0f);
                gl.glRotatef(anguloBloco, 0f, 0f, 1f);
                glut.glutSolidCube(.8f);
            gl.glPopMatrix();
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
        System.out.printf("Bloco X:%.2f  -  Bloco Y:%.2f  -  Bloco Z:%.2f\n", blocoX, blocoY, blocoZ);
        gl.glFlush();
    }
    
    public void displayChanged(GLAutoDrawable gLDrawable,
        boolean modeChanged, boolean deviceChanged) {
    }

    @Override
    public void init(GLAutoDrawable glad) {
        //TUDO ISSO AQUI PRA TEXTURA DO AMBIENTE
        // Comandos de inicialização para textura
        loadImage("te.jpg");// TEM 2 ARQUIVOS O TE E O TE2
        gl = glad.getGL().getGL2();
        
        // Gera identificador de textura
        idTextura = new int[10];
        gl.glGenTextures(1, idTextura, 1);

        // Especifica qual é a textura corrente pelo identificador 
        gl.glBindTexture(GL.GL_TEXTURE_2D, idTextura[1]);	

        // Envio da textura para OpenGL
        gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, 3, largura, altura, 
                        0, GL.GL_BGR, GL.GL_UNSIGNED_BYTE, buffer);

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
                    anguloAmb -= 5;
                    break;
                case KeyEvent.VK_A:
                    if(blocoX > 1.1f && blocoX < 1.3f ){
                        CD = false;
                        giroEsquerda =true;
                    }else{
                        startE = true;
                        CD = false;
                    }
                    break;
                case KeyEvent.VK_D:
                    if(blocoX < -1.1f && blocoX > -1.3f ){
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
                    mostraCordPersonagem();
                    mostraRotacaoAmbiente();
                    break;
                case KeyEvent.VK_B:
                    giraAmbEsquerda();
                    break;
                case KeyEvent.VK_U:
                    anguloBloco += 5f;
                    break;
                    
                default:
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
   
    public void loadImage(String fileName)
    {
        // Tenta carregar o arquivo		
        imagem = null;
        try {
                imagem = ImageIO.read(new File("src\\" + fileName));
                // Obtém largura e altura
                largura  = imagem.getWidth();
                altura = imagem.getHeight();
        }
        catch (IOException e) {
                JOptionPane.showMessageDialog(null,"Erro na leitura do arquivo "+fileName);
        }

        //Carrega a textura		
        try {
                InputStream stream = getClass().getResourceAsStream(fileName);
                td = TextureIO.newTextureData(GLProfile.getDefault(), stream, false, "jpg");
        }
        catch (IOException exc) {
                System.exit(1);
        }
        // ...e obtém um ByteBuffer a partir dela
        buffer = (ByteBuffer) td.getBuffer();
    }
    
    private void moveAmbienteFrente(){
        ambZ -= .15;
    }
    
    private void pula(float velocidadeY, float velocidadeX){
        blocoY += velocidadeY;
        blocoX += velocidadeX;
    }
    
    private void jumpFrente(){
        if (pula && blocoY <= auxY){
            pula(.1f,0);
        } else {
            pula = false;
            pula(-.1f,0);
            if (blocoY == -1.5f){ 
                pula = true;
                startF = false;
                CD = true;
            }
        }
    }
    
    private void jumpDireita(){
        if (pula && blocoY <= auxY){
            pula(.1f,-.04000f);
        } else {
            pula = false;
            pula(-.1f,-.04000f);
            if (blocoY == -1.5f){ 
                pula = true;
                startD = false;
                CD = true;
            }
        }
    }

    private void jumpEsquerda(){
        if (pula && blocoY <= auxY){
            pula(.1f,.04f);
        } else {
            pula = false;
            pula(-.1f,.04f);
            if (blocoY == -1.5f){ 
                pula = true;
                startE = false;
                CD = true;
            }
        }
    }
    private void mostraCordPersonagem(){
        System.out.printf("Personagem \nX: %f\nY: %f\nZ: %f\n anguloZ: %f\n"
                        ,blocoX, blocoY, blocoZ, anguloBloco);
    }
    
    private void mostraRotacaoAmbiente(){
        System.out.printf("Ambiente\nZ: %f\n", anguloAmb);
    }
    private void giraAmbEsquerda(){
        if(anguloAmb == -90.000000f){
            animacaoQueda();
            CD = true;
            giroEsquerda = false;
        }else
        {
           anguloAmb -= 5;
        }
    }
     private void giraAmbDireita(){
        if(anguloAmb == 90.000000f){
            animacaoQueda();
            CD = true;
            giroDireita = false;
        }else
        {
           anguloAmb += 5;
        }
    }
    private void animacaoQueda() {
        anguloBloco += 180f;
        blocoX += .4f;
        blocoY += .4f;
    }
}