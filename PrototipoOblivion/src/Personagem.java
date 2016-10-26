import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

public class Personagem {
    private float personagemX = 0f, personagemY = -1.5f, personagemZ = -28f;
    private float rotacao, rotaX, rotaY, rotaZ;
    public void mostraPersonagem(GLUT glut, GL2 gl)
    {
        gl.glPushMatrix();
            gl.glTranslatef(personagemX, personagemY, personagemZ);
            gl.glColor3f(0f, 0f, 0f);
            gl.glRotatef(rotacao, rotaX, rotaY, rotaZ);
            glut.glutSolidCube(.85f);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslatef(personagemX, personagemY, personagemZ);
            gl.glColor3f(1f, 0f, 0f);
            gl.glRotatef(rotacao, rotaX, rotaY, rotaZ);
            glut.glutSolidCube(.8f);
        gl.glPopMatrix();
        
        
        gl.glPushMatrix();
            gl.glTranslatef(personagemX, personagemY, personagemZ);
            gl.glColor3f(0f, 0f, 0f);
            gl.glRotatef(rotacao, rotaX, rotaY, rotaZ);
            glut.glutSolidCube(.75f);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslatef(personagemX, personagemY, personagemZ);
            gl.glColor3f(1f, 0f, 0f);
            gl.glRotatef(rotacao, rotaX, rotaY, rotaZ);
            glut.glutSolidCube(.70f);
        gl.glPopMatrix();
        
        
        
        
        
        gl.glPushMatrix();
            gl.glTranslatef(personagemX, personagemY, personagemZ);
            gl.glColor3f(0f, 0f, 0f);
            gl.glRotatef(rotacao, rotaX, rotaY, rotaZ);
            glut.glutSolidCube(.65f);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslatef(personagemX, personagemY, personagemZ);
            gl.glColor3f(1f, 0f, 0f);
            gl.glRotatef(rotacao, rotaX, rotaY, rotaZ);
            glut.glutSolidCube(.60f);
        gl.glPopMatrix();
        
        
        
        
        gl.glPushMatrix();
            gl.glTranslatef(personagemX, personagemY, personagemZ);
            gl.glColor3f(0f, 0f, 0f);
            gl.glRotatef(rotacao, rotaX, rotaY, rotaZ);
            glut.glutSolidCube(.55f);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslatef(personagemX, personagemY, personagemZ);
            gl.glColor3f(1f, 0f, 0f);
            gl.glRotatef(rotacao, rotaX, rotaY, rotaZ);
            glut.glutSolidCube(.5f);
        gl.glPopMatrix();
        
        
        
        gl.glPushMatrix();
            gl.glTranslatef(personagemX, personagemY, personagemZ);
            gl.glColor3f(0f, 0f, 0f);
            gl.glRotatef(rotacao, rotaX, rotaY, rotaZ);
            glut.glutSolidCube(.45f);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslatef(personagemX, personagemY, personagemZ);
            gl.glColor3f(1f, 0f, 0f);
            gl.glRotatef(rotacao, rotaX, rotaY, rotaZ);
            glut.glutSolidCube(.4f);
        gl.glPopMatrix();
        
        
        
        gl.glPushMatrix();
            gl.glTranslatef(personagemX, personagemY, personagemZ);
            gl.glColor3f(0f, 0f, 0f);
            gl.glRotatef(rotacao, rotaX, rotaY, rotaZ);
            glut.glutSolidCube(.35f);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslatef(personagemX, personagemY, personagemZ);
            gl.glColor3f(1f, 0f, 0f);
            gl.glRotatef(rotacao, rotaX, rotaY, rotaZ);
            glut.glutSolidCube(.3f);
        gl.glPopMatrix();
        
        
        
        gl.glPushMatrix();
            gl.glTranslatef(personagemX, personagemY, personagemZ);
            gl.glColor3f(0f, 0f, 0f);
            gl.glRotatef(rotacao, rotaX, rotaY, rotaZ);
            glut.glutSolidCube(.25f);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslatef(personagemX, personagemY, personagemZ);
            gl.glColor3f(1f, 0f, 0f);
            gl.glRotatef(rotacao, rotaX, rotaY, rotaZ);
            glut.glutSolidCube(.2f);
        gl.glPopMatrix();
        
        
        
        gl.glPushMatrix();
            gl.glTranslatef(personagemX, personagemY, personagemZ);
            gl.glColor3f(0f, 0f, 0f);
            gl.glRotatef(rotacao, rotaX, rotaY, rotaZ);
            glut.glutSolidCube(.15f);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslatef(personagemX, personagemY, personagemZ);
            gl.glColor3f(1f, 0f, 0f);
            gl.glRotatef(rotacao, rotaX, rotaY, rotaZ);
            glut.glutSolidCube(.1f);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslatef(personagemX, personagemY, personagemZ);
            gl.glColor3f(0f, 0f, 0f);
            gl.glRotatef(rotacao, rotaX, rotaY, rotaZ);
            glut.glutSolidCube(.05f);
        gl.glPopMatrix();
    }

    public float getRotaX() {
        return rotaX;
    }

    public void setRotaX(float rotaX) {
        this.rotaX = rotaX;
    }

    public float getRotaY() {
        return rotaY;
    }

    public void setRotaY(float rotaY) {
        this.rotaY = rotaY;
    }

    public float getRotaZ() {
        return rotaZ;
    }

    public void setRotaZ(float rotaZ) {
        this.rotaZ = rotaZ;
    }
    
    public float getRotacao() {
        return rotacao;
    }

    public void setRotacao(float rotacao) {
        this.rotacao = rotacao;
    }
    
    public float getPersonagemX ()
    {
        return personagemX;
    }
    public float getPersonagemY ()
    {
        return personagemY;
    }
    public float getPersonagemZ()
    {
        return personagemZ;
    }
    public void setPersonagemX (float x)
    {
        personagemX = x;
    }
    public void setPersonagemY (float x)
    {
        personagemY = x;
    }
    public void setPersonagemZ(float x)
    {
        personagemZ = x;
    }
    
    public void mostraCordPersonagem(){
        System.out.printf("Personagem \nX: %f\nY: %f\nZ: %f"
              ,personagemX, personagemY, personagemZ);
    }
}