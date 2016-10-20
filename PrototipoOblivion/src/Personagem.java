import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

public class Personagem {
    private float personagemX = 0f, personagemY = -1.5f, personagemZ = -28f;
    private float anguloBloco =0 ;
    public void mostraPersonagem(GLUT glut, GL2 gl)
    {
        gl.glPushMatrix();
            gl.glTranslatef(personagemX, personagemY, personagemZ);
            gl.glColor3f(1f, 0f, 0f);
            gl.glRotatef(anguloBloco, 0f, 0f, 1f);
            glut.glutSolidCube(.8f);
        gl.glPopMatrix();
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
}