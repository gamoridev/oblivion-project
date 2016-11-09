
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hugarty
 */
public class Bloco {
    float vetor [] = new float [12];
    
    public void mostraBlocos (GLUT glut, GL2 gl, float x)
    {
        //CHAO
        gl.glPushMatrix();
            gl.glTranslatef(0f, -2f, x);
            gl.glPushMatrix();
                gl.glTranslatef(1.3f, 0f, 0f);
                gl.glColor3f(1f, vetor[0], vetor[0]);
                gl.glScalef(1f, .1f,1f);
                glut.glutSolidCube(1.2f);	
            gl.glPopMatrix();
            gl.glPushMatrix();
                gl.glTranslatef(0f, 0f, 0f);
                gl.glColor3f(1f, vetor[1], vetor[1]);
                gl.glScalef(1f, .1f,1f);
                glut.glutSolidCube(1.2f);	
            gl.glPopMatrix();
            gl.glPushMatrix();
                gl.glTranslatef(-1.3f, 0f, 0f);
                gl.glColor3f(1f, vetor[2], vetor[2]);
                gl.glScalef(1f, .1f,1f);
                glut.glutSolidCube(1.2f);	
            gl.glPopMatrix();
        gl.glPopMatrix();
        
        //PAREDE DIREITA
        gl.glPushMatrix();
            gl.glTranslatef(-2f, 0f, x);
            gl.glPushMatrix();
                gl.glTranslatef(0f, 1.3f, 0f);
                gl.glColor3f(1f, vetor[3], vetor[3]);
                gl.glScalef(.1f, 1f, 1f);
                glut.glutSolidCube(1.2f);	
            gl.glPopMatrix();
            gl.glPushMatrix();
                gl.glTranslatef(0f, 0f, 0f);
                gl.glColor3f(1f, vetor[4], vetor[4]);
                gl.glScalef(.1f, 1f, 1f);
                glut.glutSolidCube(1.2f);	
            gl.glPopMatrix();
            gl.glPushMatrix();
                gl.glTranslatef(0f, -1.3f, 0f);
                gl.glColor3f(1f, vetor[5], vetor[5]);
                gl.glScalef(.1f, 1f, 1f);
                glut.glutSolidCube(1.2f);	
            gl.glPopMatrix();
        gl.glPopMatrix();
        
        
        //TETO
        gl.glPushMatrix();
            gl.glTranslatef(0f, 2f, x);
            gl.glPushMatrix();
                gl.glTranslatef(-1.3f, 0f, 0f);
                gl.glColor3f(1f, vetor[6], vetor[6]);
                gl.glScalef(1f, .1f,1f);
                glut.glutSolidCube(1.2f);	
            gl.glPopMatrix();
            gl.glPushMatrix();
                gl.glTranslatef(0f, 0f, 0f);
                gl.glColor3f(1f, vetor[7], vetor[7]);
                gl.glScalef(1f, .1f,1f);
                glut.glutSolidCube(1.2f);	
            gl.glPopMatrix();
            gl.glPushMatrix();
                gl.glTranslatef(1.3f, 0f, 0f);
                gl.glColor3f(1f, vetor[8], vetor[8]);
                gl.glScalef(1f, .1f,1f);
                glut.glutSolidCube(1.2f);	
            gl.glPopMatrix();
        gl.glPopMatrix();
        
        //PAREDE ESQUERDA
        gl.glPushMatrix();
            gl.glTranslatef(2f, 0f, x);
            gl.glPushMatrix();
                gl.glTranslatef(0f, 1.3f, 0f);
                gl.glColor3f(1f, vetor[9], vetor[9]);
                gl.glScalef(.1f, 1f, 1f);
                glut.glutSolidCube(1.2f);	
            gl.glPopMatrix();
            gl.glPushMatrix();
                gl.glTranslatef(0f, 0f, 0f);
                gl.glColor3f(1f, vetor[10], vetor[10]);
                gl.glScalef(.1f, 1f, 1f);
                glut.glutSolidCube(1.2f);	
            gl.glPopMatrix();
            gl.glPushMatrix();
                gl.glTranslatef(0f, -1.3f, 0f);
                gl.glColor3f(1f, vetor[11], vetor[11]);
                gl.glScalef(.1f, 1f, 1f);
                glut.glutSolidCube(1.2f);	
            gl.glPopMatrix();
        gl.glPopMatrix();
        
    }
    
    public float getVetor0()
    {
        return vetor[0];
    }
    public float getVetor1()
    {
        return vetor[1];
    }
    public float getVetor2()
    {
        return vetor[2];
    }
    public float getVetor3()
    {
        return vetor[3];
    }
    public float getVetor4()
    {
        return vetor[4];
    }
    public float getVetor5()
    {
        return vetor[5];
    }
    public float getVetor6()
    {
        return vetor[6];
    }
    public float getVetor7()
    {
        return vetor[7];
    }
    public float getVetor8()
    {
        return vetor[8];
    }
    public float getVetor9()
    {
        return vetor[9];
    }
    public float getVetor10()
    {
        return vetor[10];
    }
    public float getVetor11()
    {
        return vetor[11];
    }
    
    public void setVetor0(float x)
    {
        vetor[0] = x;
    }
    public void setVetor1(float x)
    {
        vetor[1] = x;
    }
    public void setVetor2(float x)
    {
        vetor[2] = x;
    }
    public void setVetor3(float x)
    {
        vetor[3] = x;
    }
    public void setVetor4(float x)
    {
        vetor[4] = x;
    }
    public void setVetor5(float x)
    {
        vetor[5] = x;
    }
    public void setVetor6(float x)
    {
        vetor[6] = x;
    }
    public void setVetor7(float x)
    {
        vetor[7] = x;
    }
    public void setVetor8(float x)
    {
        vetor[8] = x;
    }
    public void setVetor9(float x)
    {
        vetor[9] = x;
    }
    public void setVetor10(float x)
    {
        vetor[10] = x;
    }
    public void setVetor11(float x)
    {
        vetor[11] = x;
    }
}
