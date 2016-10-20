import com.jogamp.opengl.GL2;

public class Ambiente 
{
    private float ambX = 0f, ambY = 0f, ambZ = 0f;
    
    public void mostraAmbiente(GL2 gl)
    {
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
    }
    
    public void setAmbienteX (float x){
        ambX = x;
    }
    public void setAmbienteY (float x){
        ambY = x;
    }
    public void setAmbienteZ (float x){
        ambZ = x;
    }
    
    public float getAmbienteX (){
        return ambX;
    }
    public float getAmbienteY (){
        return ambY;
    }
    public float getAmbienteZ (){
        return ambZ;
    }
}