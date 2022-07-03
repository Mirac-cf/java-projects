
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CalisanIslemleri {
    private Connection con;    
    private Statement statement;
    private ResultSet rs;
    private PreparedStatement preparedStatement;

    public CalisanIslemleri(){
        String url = "jdbc:mysql://" + Database.host + ":" + Database.port + "/" + Database.db_ismi;
        //?useUnicode=true&characterEncoding=utf8 
        try {
            con = DriverManager.getConnection(url, Database.kullanıcı_adi, Database.parola);
            System.out.println("Baglanti basarili...");
        } catch (SQLException ex) {
            System.out.println("Baglanti basarisiz...");
            ex.printStackTrace();          
        }
    }
    
    public boolean girisYap(String kullanıcı_adi,String parola){
        String sorgu = "Select * from adminler where username = ? and password = ?";
        
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, kullanıcı_adi);
            preparedStatement.setString(2, parola);
            rs = preparedStatement.executeQuery();
            
            return rs.next();
            //eger bir deger gosteriyorsa sorgu deger dondurmustur ve true doner false ise tam tersi 
            
        } catch (SQLException ex) {
            Logger.getLogger(CalisanIslemleri.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void calisanGuncelle(int id,String yeni_ad,String yeni_soyad,String yeni_departman,String yeni_maas){
        String sorgu = "Update calisanlar Set ad = ? ,soyad = ? , departman = ? , maas = ? where id =?";
    
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, yeni_ad);
            preparedStatement.setString(2, yeni_soyad);
            preparedStatement.setString(3, yeni_departman);
            preparedStatement.setString(4, yeni_maas);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CalisanIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public void calisanSil(int id){
        String sorgu = "Delete from calisanlar where id = "+id; 
        try {
            statement = con.createStatement();
            statement.executeUpdate(sorgu);
        } catch (SQLException ex) {
            Logger.getLogger(CalisanIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void calisanEkle(String ad,String soyad,String departman,String maas){
        String sorgu = "Insert Into calisanlar (ad, soyad, departman,maas) VALUES (?,?,?,?)";
        
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, ad);
            preparedStatement.setString(2, soyad);
            preparedStatement.setString(3, departman);
            preparedStatement.setString(4, maas);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CalisanIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ArrayList<Calisan> CalisanlariGetir(){
        ArrayList<Calisan> cikti = new ArrayList<Calisan>();
        
        String sorgu = "Select * from calisanlar"; 
        
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(sorgu);
            
            while(rs.next()){
                int id = rs.getInt("id");
                String ad = rs.getNString("ad");
                String soyad = rs.getString("soyad");
                String departman = rs.getString("departman");
                String maas = rs.getString("maas");
                
                cikti.add(new Calisan(id, ad, soyad, departman, maas));   
            }
            return cikti;
            
        } catch (SQLException ex) {
            Logger.getLogger(CalisanIslemleri.class.getName()).log(Level.SEVERE, null, ex); 
            return null;
        }     
    }
    
    
}
