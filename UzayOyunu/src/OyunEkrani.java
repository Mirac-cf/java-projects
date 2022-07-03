
import java.awt.HeadlessException;
import javax.swing.JFrame;


public class OyunEkrani extends JFrame{
    public static void main(String[] args) {
        OyunEkrani ekran = new OyunEkrani("Uzay Oyunu");
        
        ekran.setVisible(false);
        ekran.setFocusable(false);
        
        ekran.setSize(800,600);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Oyun oyun = new Oyun();
        
        oyun.requestFocus();  //requestFocus klavye islemlerini anlar
        oyun.addKeyListener(oyun);  //addKeyListener klavyeden islemleri alir
        
        oyun.setFocusable(true); // odagi jpanel'e verdik
        oyun.setFocusTraversalKeysEnabled(false);
        
        ekran.add(oyun);
        ekran.setVisible(true);
        
    }

    public OyunEkrani(String title) throws HeadlessException {
        super(title);
    }
    
}
