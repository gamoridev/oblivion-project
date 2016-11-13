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

    Bloco fileira1 =  new Bloco();
    Bloco fileira2 =  new Bloco();
    Bloco fileira3 =  new Bloco();
    Bloco fileira4 =  new Bloco();
    Bloco fileira5 =  new Bloco();
    Bloco fileira6 =  new Bloco();
    Bloco fileira7 =  new Bloco();
    Bloco fileira8 =  new Bloco();
    Bloco fileira9 =  new Bloco();
    Bloco fileira10 =  new Bloco();
    Bloco fileira11 =  new Bloco();
    Bloco fileira12 =  new Bloco();
    
    private final GLUT glut = new GLUT();
    private final GLU glu = new GLU();
    private final float rquad = 0.0f, rquadAntigo = 0.0f;
    private float eixoX = 0f, eixoY = 0f, eixoZ= -34f;
    private final float velocidadeX = 4.0f, velocidadeY = 0.0f, velocidadeZ = 0.0f;
    private final float auxY = 0, auxX = 0;
    private boolean pula = true, startF, startD, startE, CD = true, pisca = true;
    boolean morte = false;
    private boolean giroEsquerda = false, giroDireita = false; 
//    private float ambiente.getAnguloAmb() = 0, anguloBloco = 0 ;
    private int wView = 1000, yView = 750, contPisca = 0;
    int contSaltos = 0, contPulo = 0;
    float variavelBOA;
    float piscaBloco = 0;
    char direcaoPulo;
    String posicaoAtual = "centro";
    
       
    //VARIAVEIS TEXTURA
    private int idTextura[];
    private GL2 gl;
    Random aleatorio = new Random();
    
    
    int vetorCerto[] = {aleatorio.nextInt(3),
    1,
    aleatorio.nextInt(3),
    1,
    aleatorio.nextInt(3),
    1,
    aleatorio.nextInt(3),
    1,
    aleatorio.nextInt(3),
    1,
    aleatorio.nextInt(3)};

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
        glu.gluLookAt(eixoX, eixoY, eixoZ, 0, 0, 30, 0, 1, 0);
        
        
        setaRotacao();
        contSaltos ++;
        
        gl.glPushMatrix();
            gl.glRotatef(ambiente.getAnguloAmb(),0f, 0f, 1f);
//            gl.glEnable(GL.GL_TEXTURE_2D);          
                ambiente.mostraAmbiente(gl);      
////            gl.glDisable(GL.GL_TEXTURE_2D);	
//            gl.glPushMatrix();
//                gl.glTranslatef(0f, -2f, -28f);
//                gl.glColor3f(1f, 1f, 1f);
//                gl.glScalef(1f, .1f,1f);
//                glut.glutSolidCube(1.3f);	
//            gl.glPopMatrix();
                
            fileira12.mostraBlocos(glut, gl, 21.5f);
            fileira11.mostraBlocos(glut, gl, 17f);
            fileira10.mostraBlocos(glut, gl, 12.5f);
            fileira9.mostraBlocos(glut, gl, 8f);   
            fileira8.mostraBlocos(glut, gl, 3.5f);
            fileira7.mostraBlocos(glut, gl, -1f);
            fileira6.mostraBlocos(glut, gl, -5.5f);
            fileira5.mostraBlocos(glut, gl, -10f);
            fileira4.mostraBlocos(glut, gl, -14.5f);
            fileira3.mostraBlocos(glut, gl, -19f);
            fileira2.mostraBlocos(glut, gl, -23.5f);
            fileira1.mostraBlocos(glut, gl, -28f);
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
        /*
        *  Faz o blocos certos piscarem por um curto período de tempo.
        */
        piscaBlocosCertos();
        gl.glFlush();
    }
    
    public void displayChanged(GLAutoDrawable gLDrawable,
        boolean modeChanged, boolean deviceChanged) {
    }

    @Override
    public void init(GLAutoDrawable glad) {
        /*
        * Vetores da fileira do respawn.
        */
        fileira1.setVetor0(1);
        fileira1.setVetor1(0);
        fileira1.setVetor2(1);
        
        
        
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
                    contPulo++;
                    direcaoPulo = 'f';
                    verificaPulo(false, true, false);
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
                    contPulo++;
                    direcaoPulo = 'e';
                    verificaPulo(true, false, false);
                    break;
                case KeyEvent.VK_D:
                    if(personagem.getPersonagemX() < -1.1f && personagem.getPersonagemX() > -1.3f ){
                        CD = false;
                        giroDireita = true;
                    }else{
                        startD = true;
                        CD = false;
                    }
                    contPulo++;
                    direcaoPulo = 'd';
                    verificaPulo(false, false, true);
                    break;
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                    break;
                case KeyEvent.VK_F:
                        personagem.mostraCordPersonagem();
                    break;
                case KeyEvent.VK_J:
                        fileira1.setVetor0(0);
                    break;
                case KeyEvent.VK_B:
                        fileira1.setVetor0(0);
                        fileira1.setVetor1(0);
                        fileira1.setVetor2(0);
                        fileira1.setVetor3(0);
                        fileira1.setVetor4(0);
                        fileira1.setVetor5(0);
                        fileira1.setVetor6(0);
                        fileira1.setVetor7(0);
                        fileira1.setVetor8(0);
                        fileira1.setVetor9(0);
                        fileira1.setVetor10(0);
                        fileira1.setVetor11(0);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(CD){
            switch (e.getKeyCode()) {
                case KeyEvent.VK_F:
                        fileira1.setVetor2(1);
                    break;
                case KeyEvent.VK_J:
                        fileira1.setVetor0(1);
                    break;
                case KeyEvent.VK_B:
                    fileira1.setVetor0(1);
                    fileira1.setVetor1(1);
                    fileira1.setVetor2(1);
                    fileira1.setVetor3(1);
                    fileira1.setVetor4(1);
                    fileira1.setVetor5(1);
                    fileira1.setVetor6(1);
                    fileira1.setVetor7(1);
                    fileira1.setVetor8(1);
                    fileira1.setVetor9(1);
                    fileira1.setVetor10(1);
                    fileira1.setVetor11(1);
                    break;
            default:
                    break;
            }
        }
    }
   
    private void moveAmbienteFrente(){
        personagem.setPersonagemZ(personagem.getPersonagemZ() + .15f);
        eixoZ += .15f;
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
    
    private void piscaBlocosCertos() {
        if (contPisca < 41){
            switch (vetorCerto[0]) {
                case 0:
                    pisca(fileira2, 0);
                    break;
                case 1:
                    pisca(fileira2, 1);
                    break;
                case 2:
                    pisca(fileira2, 2);
                    break;
                default:
                    break;
            }
        }
        if (contPisca > 41 && contPisca < 84){
            switch (vetorCerto[1]) {
                case 0:
                    pisca(fileira3, 0);
                    break;
                case 1:
                    pisca(fileira3, 1);
                    break;
                case 2:
                    pisca(fileira3, 2);
                    break;
                default:
                    break;
            }
        }
        if (contPisca > 84 && contPisca < 127){
            switch (vetorCerto[2]) {
                case 0:
                    pisca(fileira4, 0);
                    break;
                case 1:
                    pisca(fileira4, 1);
                    break;
                case 2:
                    pisca(fileira4, 2);
                    break;
                default:
                    break;
            }
        }
        if (contPisca > 127 && contPisca < 171){
            switch (vetorCerto[3]) {
                case 0:
                    pisca(fileira5, 0);
                    break;
                case 1:
                    pisca(fileira5, 1);
                    break;
                case 2:
                    pisca(fileira5, 2);
                    break;
                default:
                    break;
            }
        }
        if (contPisca > 171 && contPisca < 214){
            switch (vetorCerto[4]) {
                case 0:
                    pisca(fileira6, 0);
                    break;
                case 1:
                    pisca(fileira6, 1);
                    break;
                case 2:
                    pisca(fileira6, 2);
                    break;
                default:
                    break;
            }
        }
        if (contPisca > 214 && contPisca < 257){
            switch (vetorCerto[5]) {
                case 0:
                    pisca(fileira7, 0);
                    break;
                case 1:
                    pisca(fileira7, 1);
                    break;
                case 2:
                    pisca(fileira7, 2);
                    break;
                default:
                    break;
            }
        }
        if (contPisca > 257 && contPisca < 300){
            switch (vetorCerto[6]) {
                case 0:
                    pisca(fileira8, 0);
                    break;
                case 1:
                    pisca(fileira8, 1);
                    break;
                case 2:
                    pisca(fileira8, 2);
                    break;
                default:
                    break;
            }
        }
        if (contPisca > 300 && contPisca < 343){
            switch (vetorCerto[7]) {
                case 0:
                    pisca(fileira9, 0);
                    break;
                case 1:
                    pisca(fileira9, 1);
                    break;
                case 2:
                    pisca(fileira9, 2);
                    break;
                default:
                    break;
            }
        }
        if (contPisca > 343 && contPisca < 386){
            switch (vetorCerto[8]) {
                case 0:
                    pisca(fileira10, 0);
                    break;
                case 1:
                    pisca(fileira10, 1);
                    break;
                case 2:
                    pisca(fileira10, 2);
                    break;
                default:
                    break;
            }
        }
        if (contPisca > 386 && contPisca < 429){
            switch (vetorCerto[9]) {
                case 0:
                    pisca(fileira11, 0);
                    break;
                case 1:
                    pisca(fileira11, 1);
                    break;
                case 2:
                    pisca(fileira11, 2);
                    break;
                default:
                    break;
            }
        }
        if (contPisca > 429 && contPisca < 471){
            switch (vetorCerto[10]) {
                case 0:
                    pisca(fileira12, 0);
                    break;
                case 1:
                    pisca(fileira12, 1);
                    break;
                case 2:
                    pisca(fileira12, 2);
                    break;
                default:
                    break;
            }
        }
        contPisca++;
    }
    
    private void pisca(Bloco fileira, int vetorCerto) {
        if (pisca && piscaBloco >= 0 && piscaBloco <= 1) {
            piscaBloco += 0.1;
            switch (vetorCerto) {
                case 0:
                    fileira.setVetor0(piscaBloco);
                    break;
                case 1:
                    fileira.setVetor1(piscaBloco);
                    break;
                case 2:
                    fileira.setVetor2(piscaBloco);
                    break;
                default:
                    break;
            }
        } else {
            pisca = false;
            piscaBloco -= 0.1;
            switch (vetorCerto) {
                case 0:
                    fileira.setVetor0(piscaBloco);
                    break;
                case 1:
                    fileira.setVetor1(piscaBloco);
                    break;
                case 2:
                    fileira.setVetor2(piscaBloco);
                    break;
                default:
                    break;
            }
            if (piscaBloco <= 0) {
                pisca = true;
                piscaBloco += 0.1;
                switch (vetorCerto) {
                    case 0:
                        fileira.setVetor0(piscaBloco);
                        break;
                    case 1:
                        fileira.setVetor1(piscaBloco);
                        break;
                    case 2:
                        fileira.setVetor2(piscaBloco);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    /*
    * Verifica se o personagem pulou no bloco correto
    * Ele começa na primeira fileira
    */
    
    public void verificaPulo(boolean E, boolean F, boolean D) {
        String posicao = "C";
        switch (contPulo) {
            // Primeiro pulo - segunda fileira
            case 1:
                switch (vetorCerto[0]) {
                    case 0:
                        if (E) {
                            posicao = "E";
                        } else {
                            System.out.println("VOCE MORREU");
                        }
                        break;
                    case 1:
                        if (F) {
                            posicao = "C";
                        } else {
                            System.out.println("VOCE MORREU");
                        }
                        break;
                    case 2:
                        if (D) {
                            posicao = "D";
                        } else {
                            System.out.println("VOCE MORREU");
                        }
                        break;
                }
                break;
            // Segundo pulo - terceira fileira
            case 2:
                switch (vetorCerto[1]) {
                    case 1:
                        switch (posicao) {
                            case "E":
                                if (D) {
                                    posicao = "C";
                                } else {
                                    System.out.println("VOCE MORREU");
                                }
                                break;
                            case "C":
                                if (F) {
                                    posicao = "C";
                                } else {
                                    System.out.println("VOCE MORREU");
                                }
                                break;
                            case "D":
                                if (E) {
                                    posicao = "C";
                                } else {
                                    System.out.println("VOCE MORREU");
                                }
                                break;
                        }
                        break;
                }
        }
    }
}
