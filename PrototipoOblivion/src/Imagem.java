
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Imagem {
    private int largura, altura;
    private TextureData td;
    private BufferedImage imagem;
    private ByteBuffer buffer;

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public TextureData getTd() {
        return td;
    }

    public void setTd(TextureData td) {
        this.td = td;
    }

    public BufferedImage getImagem() {
        return imagem;
    }

    public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }

    public void setBuffer(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    public void loadImage(String fileName) {
        // Tenta carregar o arquivo		
        imagem = null;
        try {
            imagem = ImageIO.read(new File("src\\" + fileName));
            // Obtém largura e altura
            largura = imagem.getWidth();
            altura = imagem.getHeight();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo " + fileName);
        }

        //Carrega a textura		
        try {
            InputStream stream = getClass().getResourceAsStream(fileName);
            td = TextureIO.newTextureData(GLProfile.getDefault(), stream, false, "jpg");
        } catch (IOException exc) {
            System.exit(1);
        }
        // ...e obtém um ByteBuffer a partir dela
        buffer = (ByteBuffer) td.getBuffer();
    }
}
