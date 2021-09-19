package proje;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Proje extends JFrame implements ActionListener{
    
    JButton jbt;
    JTextField jtfad,jtfsoyad,jtfmail,jtftel,jtf;
    JPanel jpnl;
    JLabel jlb;
  
    Proje(String s){
        /*Gui Tasarimi burada yapiliyor*/
        
        super(s);//Acilan sayfanin adi
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/*Acilan sayfayi 
        kapatinca kod calismasini bitirmeye yarayan kod */
        
        this.setLayout(null);
        
        jpnl=new JPanel();//Jpanel nesnesi olusturma
        jpnl.setBounds(0,0,1000,1000);//1000X1000 boyutunu ayarlama
        
        jpnl.setLayout(null);//Panelde oto yerlestirme kapatma
        
        this.add(jpnl);//paneli sayfaya ekleme
        
        jlb=new JLabel("Adi:");//Etiket nesnesi olusturma
        jlb.setBounds(30,50,100,30);//Etiket boyutlandirma ve yerini belirtme
        jpnl.add(jlb);//Etiket panele ekleme
        
        jtfad=new JTextField();//Text alani olusturma
        jtfad.setBounds(100,50,180,30);//Text alani boyutlandirma ve yerini belirtme
        jpnl.add(jtfad);//Text alani panele ekleme
        
        //Digerleride ayni oldugu icin yazmadim
        
        jlb=new JLabel("Soyadi:");
        jlb.setBounds(30,80,100,30);
        jpnl.add(jlb);
        
        jtfsoyad=new JTextField();
        jtfsoyad.setBounds(100,80,180,30);
        jpnl.add(jtfsoyad);
        
        jlb=new JLabel("Mail Adresi:");
        jlb.setBounds(30,110,100,30);
        jpnl.add(jlb);
        
        jtfmail=new JTextField();
        jtfmail.setBounds(100,110,180,30);
        jpnl.add(jtfmail);
        
        jlb=new JLabel("Tel No:");
        jlb.setBounds(30,140,100,30);
        jpnl.add(jlb);
        
        jtftel=new JTextField();
        jtftel.setBounds(100,140,180,30);
        jpnl.add(jtftel);
        
        BankaPersoneli BP4=new BankaPersoneli("","","",0);
        /*Banka personeli nesnesi olusturur*/

        jbt=new JButton("Musteri Ekle");//Buton olusturur
        jbt.setBounds(100,190,180,30);//Buton boyutlandirma ve yerini belirtme
        
        jbt.addActionListener( new ActionListener( ){
            public void actionPerformed( ActionEvent e ){//Butona fonksiyon atama
                if(jtfad.getText().equals("")||jtfsoyad.getText().equals("")||
                        jtfmail.getText().equals("")||jtftel.getText().equals("")){
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Once istenen verileri eksiksiz doldurunuz!!!",
                        "HATA MESAJI", 2);       
                }
                else{
                   Musteri M4=new Musteri(jtfad.getText(),jtfsoyad.getText()
                        ,jtfmail.getText(),Long.parseLong(jtftel.getText()));
                /*Musteri nesnesi olusturup kullanicinin girdigi verileri gerekli
                yerlere atar*/
                BP4.Musteri_Ekle(M4);//M4'u BP4'e ekler 
                }
            }
        });
        jpnl.add(jbt);//Butonu panele ekler
        
        
        jbt=new JButton("Musteri Bilgileri");
        jbt.setBounds(100,340,180,30);
        jbt.addActionListener( new ActionListener( ){
            public void actionPerformed( ActionEvent e ){
                if(BP4.musteriler.size()>0){
                    JOptionPane.showMessageDialog(null, BP4.musteriler.get(0),
                        "EKLENEN MUSTERI BILGILERI", 1);
                    /*Kullanicinin girdigi verileri yeni acacagi sayfada gosterir*/
                }
                else{
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Once musteri ekleyiniz!!!",
                        "HATA MESAJI", 2);    
                }
            }
        });
        jpnl.add(jbt);
        /*HesapEkle metodunu butonlara entegre etmeyecegim diye konsolda kullanilir 
        bir sekilde tasarladim bundan dolayi burada hesapEkle metodunu kullanmadim
        (Entegre etmek icin vaktim olmadi :( )*/
        jbt=new JButton("Hesap Ekle");//Buton olusturur
        jbt.setBounds(100,290,180,30);//Buton boyutlandirma ve yerini belirtme
        
        jbt.addActionListener( new ActionListener( ){
            public void actionPerformed( ActionEvent e ){//Butona fonksiyon atama
                
                for(;;){
                if(BP4.musteriler.size()>0){
                    String m = JOptionPane.showInputDialog(null,"Vadesiz hesap "
                            + "icin v ya da V harfini giriniz\n"
                            + "Yatirim hesabi icin y ya da Y harfini giriniz",
                            "HESAP OLUSTUR", JOptionPane.INFORMATION_MESSAGE); 
                    if(m.equals("v")||m.equals("V")){
                        VadesizHesap x=new VadesizHesap(0); 
                        BP4.musteriler.get(0).hesaplar.add(x);
                        JOptionPane.showMessageDialog(null, "Ibani "+x.getiban(),
                        "VADESIZ HESAP OLUSTU", 1);
                        break;
                    }
                    else if(m.equals("y")||m.equals("Y")){
                        YatirimHesabi x=new YatirimHesabi(0); 
                        BP4.musteriler.get(0).hesaplar.add(x);
                        JOptionPane.showMessageDialog(null, "Ibani "+x.getiban(),
                        "YATIRIM HESABI OLUSTU", 1);
                        break;
                    }
                    else{
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "Lutfen istenilene gore giriniz!!!",
                        "HATA MESAJI", 2);
                        
                    }
                }
                else{
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Once musteri olusturunuz!!!",
                        "HATA MESAJI", 2);
                    break;
                }
                }
            }
        });
        jpnl.add(jbt);//Butonu panele ekler
 
        jbt=new JButton("Kredi Ekle");//Buton olusturur
        jbt.setBounds(100,240,180,30);//Buton boyutlandirma ve yerini belirtme
        
        jbt.addActionListener( new ActionListener( ){
            public void actionPerformed( ActionEvent e ){//Butona fonksiyon atama
                if(BP4.musteriler.size()>0){
                    BP4.musteriler.get(0).krediKartiEkle();
                    
                    JOptionPane.showMessageDialog(null, "Kartiniz olusmustur",
                        "KREDI KARTI OLUSTU", 1);   
                    
                }
                else{
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Once musteri olusturunuz!!!",
                        "HATA MESAJI", 2);
                }
            }
        });
        jpnl.add(jbt);//Butonu panele ekler
        
        
        jbt=new JButton("Hesap Bilgileri");
        jbt.setBounds(10,390,180,30);
        jbt.addActionListener( new ActionListener( ){
            public void actionPerformed( ActionEvent e ){
                if(BP4.musteriler.size()>0){
                    if(BP4.musteriler.get(0).hesaplar.size()>0){ 
                        Integer[] ibanlar = new Integer[BP4.musteriler.get(0).hesaplar.size()];
                        for(int i=0;i<BP4.musteriler.get(0).hesaplar.size();i++){ 
                            ibanlar[i] = BP4.musteriler.get(0).hesaplar.get(i).iban ;
                        }
                        Integer n;
                        n = (Integer)JOptionPane.showInputDialog(null, "Lutfen bilgisini istediginiz hesap ibanini seciniz:",
                                "HESAP BILGISI", JOptionPane.QUESTION_MESSAGE, null, ibanlar, ibanlar[0]);
                        for(int i=0;i<BP4.musteriler.get(0).hesaplar.size();i++){
                            if(n.equals(BP4.musteriler.get(0).hesaplar.get(i).iban)){
                                JOptionPane.showMessageDialog(null, 
                            BP4.musteriler.get(0).hesaplar.get(i).toString(),
                            "HESAP GOSTER", 1);    
                            }
                        }
                    }
                    else{
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "Adiniza kayitli hesap yok once hesap aciniz!!!",
                            "HATA MESAJI", 2);
                    }
                }
                else{
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Once musteri olusturunuz!!!",
                        "HATA MESAJI", 2);
                }
            }
        });
        jpnl.add(jbt);
        
        jbt=new JButton("Kredi Bilgileri");
        jbt.setBounds(190,390,180,30);
        jbt.addActionListener( new ActionListener( ){
            public void actionPerformed( ActionEvent e ){
                if(BP4.musteriler.size()>0){
                    if(BP4.musteriler.get(0).krediKartlari.size()>0){ 
                        Integer[] ibanlar = new Integer[BP4.musteriler.get(0).krediKartlari.size()];
                        for(int i=0;i<BP4.musteriler.get(0).krediKartlari.size();i++){ 
                            ibanlar[i] = BP4.musteriler.get(0).krediKartlari.get(i).kartNumarasi ;
                        }
                        Integer n;
                        n = (Integer)JOptionPane.showInputDialog(null, "Lutfen bilgisini istediginiz kredi karti numarasi seciniz:",
                                "KREDI KART BILGISI", JOptionPane.QUESTION_MESSAGE, null, ibanlar, ibanlar[0]);
                        for(int i=0;i<BP4.musteriler.get(0).krediKartlari.size();i++){
                            if(n.equals(BP4.musteriler.get(0).krediKartlari.get(i).kartNumarasi)){
                                JOptionPane.showMessageDialog(null, 
                            BP4.musteriler.get(0).krediKartlari.get(i).toString(),
                            "KART GOSTER", 1);    
                            }
                        }
                    }
                    else{
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "Adiniza kayitli kredi karti yok once kart aciniz!!!",
                            "HATA MESAJI", 2);
                    }
                }
                else{
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Once musteri olusturunuz!!!",
                        "HATA MESAJI", 2);
                }
            }
        });
        jpnl.add(jbt);
 
        jlb=new JLabel("Adiniz Soyadiniz:");
        jlb.setBounds(400,600,100,30);
        jpnl.add(jlb);
        
        jtf=new JTextField("Ahmet Talha TURKMEN");
        jtf.setBounds(550,600,180,30);
        jtf.setHorizontalAlignment(JTextField.CENTER);//Texti merkeze alma 
        jpnl.add(jtf);
        
        jlb=new JLabel("Ogrenci Numaraniz:");
        jlb.setBounds(400,650,150,30);
        jpnl.add(jlb);
        
        jtf=new JTextField("20360859062");
        jtf.setHorizontalAlignment(JTextField.CENTER);
        jtf.setBounds(550,650,180,30);
        jpnl.add(jtf);
 
        this.setSize(800, 800);//Sayfa boyutu
        this.setVisible(true);//Sayfa gorunurlugu
    }
 
    public static void main(String[] args) {
        Proje o=new Proje("Banka uygulamasi");
        /*
        //Ornek kullanimlar
        BankaPersoneli BP1=new BankaPersoneli("","","",0);
        BankaPersoneli BP2=new BankaPersoneli("","","",0);
  
        BP1.setKisi("Ahmet","ADA","ahmet_ada@gmail.com",(long)(5444444444.));
   
        BP2.setKisi("Ece","TOPRAK","ece_toprak@gmail.com",(long)(5333333333.));
        
        Musteri M1=new Musteri("","","",0);
        Musteri M2=new Musteri("","","",0);
        M1.setKisi("Ali","KAZANMIS","ali_kazanmis@gmail.com",(long)(52222222222.));
        
        M2.setKisi("Veli","ZOR","veli_zor@gmail.com",(long)(51111111111.));
   
        Musteri M3=new Musteri("Ziya","ZAMAN","z_z@gmail.com",(long)(51111111111.));
        
        BP2.Musteri_Ekle(M3);
        
        BP1.Musteri_Ekle(M1);
        BP1.Musteri_Ekle(M2);
        
        M1.hesapEkle();
        M2.hesapEkle();
        M2.hesaplar.get(0).setHesap( 40.0);
        
        M1.krediKartiEkle();
        M1.krediKartiEkle();
        M1.hesaplar.get(0).setHesap( 100.0);
        M1.hesaplar.get(1).setHesap(250.0);
        
        M1.krediKartlari.get(0).setKrediKarti(20, 30, 40);
        M1.krediKartlari.get(1).setKrediKarti(200, 0, 400);
        M1.hesaplar.get(0).getiban();
        M2.hesaplar.get(0).getiban();
        VadesizHesap x=new VadesizHesap(0);
        
 
        x.paraTransferi(M1, M2);
        x.krediKartiBorcOdeme(M1);
        YatirimHesabi a=new YatirimHesabi(5);
        a.paraEkle(M2);
        a.paraEkle(M1);
        a.paraCek(M1,M2);
        System.out.println(M1.hesaplar.get(0).bakiye);
        System.out.println(M2.hesaplar.get(0).bakiye);
        M1.hesapSil();
        M1.krediKartiSil();
        */
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public class Kisi {//Kisi sinifi
    private String ad;
    private String soyad;
    private String email;
    private long telefonNumarasi;
    
    public Kisi(String ad,String soyad,String email,long telefonNumarasi){
        //Kisi constructoru
        this.ad=ad;
        this.soyad=soyad;
        this.email=email;
        this.telefonNumarasi=telefonNumarasi;
    }
    
    public void setKisi(String ad,String soyad,String email,long telefonNumarasi){
        // Kisi set metodu
        this.ad=ad;
        this.soyad=soyad;
        this.email=email;
        this.telefonNumarasi=telefonNumarasi;
    }
    //Kisi set metodlari
    public void setAd(String ad){
        this.ad=ad;
    }
    public void setSoyad(String soyad){
        this.soyad=soyad;
    }
    public void setMail(String email){
        this.email=email;
    }
    public void setTel(Long telefonNumarasi){
        this.telefonNumarasi=telefonNumarasi;
    }
    // Kisi get metodlari
    public String getAd() {
        return ad;
    }
    public String getSoyad() {
        return soyad;
    }
    public String getEmail() {
        return email;
    }
    public long gettelefonNumarasi() {
        return telefonNumarasi;
    }
    //Kisi toString metodu
    public String toString(){
        return("\n ad:"+ad+"\n soyad:"+soyad+"\n email:"+email+"\n Telefon No:"+telefonNumarasi);
    }
    }
    
    public static class BankaPersoneli extends Kisi{
    //BankaPersoneli sinifi Kisi'den miras aliyor
    private int personalID;
    private ArrayList <Musteri>musteriler;
   
    public BankaPersoneli(String ad, String soyad, String email, long telefonNumarasi) {
        //BankaPersoneli constructoru
        super(ad, soyad, email, telefonNumarasi);
        musteriler=new ArrayList <Musteri>();/*Her Banka Personeli icin arrayList olusturur*/
        Random rand = new Random();
        personalID = rand.nextInt(9000)+1000;/*Random personal ID veren kod.  
        Her BankaPersoneli nesnesi olustugunda random personal ID olusturuyor*/
    }
    /*Burada set metodu kullanmama sebebim personalID random olmasi gerekiyor
     zaten bunu kullanici degistiremeyecek*/
    
    //get metodu
    public int getpersonalID(){
        return personalID;
    }
    /*Musteri ekleme metodu banka personeline degisken olarak girilen Musteriyi ekler*/
    public void Musteri_Ekle(Musteri M){
        musteriler.add(M);
    }
    
    public String toString(){
        return(super.toString()+"\n PERSONAL ID:"+personalID);
    }
    }
    public static class Musteri extends Kisi{
    //Musteri sinifi Kisi'den miras aliyor
    private int musteriNumarasi;
    private ArrayList <BankaHesabi>hesaplar;
    private ArrayList <KrediKarti>krediKartlari;
    
    public Musteri(String ad, String soyad, String email, long telefonNumarasi){
        //Musteri sinifinin constructoru
        super(ad, soyad, email, telefonNumarasi);
        hesaplar=new ArrayList <BankaHesabi>();/*Her Musteri icin hesaplar arrayListi olusturur*/
        krediKartlari=new ArrayList <KrediKarti>();/*Her Musteri icin kredikartlari arrayListi olusturur*/
        Random rand = new Random();
        musteriNumarasi = rand.nextInt(90000)+10000;/*Random MusteriNumarasi veren kod.  
         Her Musteri nesnesi olustugunda random MusteriNumarasi olusturuyor*/   
    }
    /*Burada set metodu kullanmama sebebim musteriNumarasi random olmasi
    gerekiyor zaten bunu kullanici degistiremeyecek*/
    public int getmusteriNumarasi(){
        return musteriNumarasi;
    }
 
    public void hesapEkle(){//hesapEkle metodu-
        System.out.println(" "+this.musteriNumarasi+" Musteri nolu kisiye hesap"
                + " olusturuluyor ...");
        Scanner girdi_al=new Scanner(System.in);
        System.out.print(" Vadesiz Hesap ise V veya v harfini"
                + " Yatirim hesabi ise Y veya y harfini giriniz(iptal icin *):");
        for(;;){//Sonsuz dongu 
        String hesap=girdi_al.nextLine();
        if(hesap.equals("V") || hesap.equals("v")){
            VadesizHesap V=new VadesizHesap(0);//Vadesiz Hesap olusturur
            hesaplar.add(V); //hesaplara arraylistine ekler
            System.out.println("Vadesiz hesabiniz olustu! Ibani: "+V.getiban());
            
            break;//Kullanici v yada V girince kirilir
        }
        else if(hesap.equals("Y") || hesap.equals("y")){
            YatirimHesabi Y=new YatirimHesabi(0);//Yatirim Hesabi olusturur
            hesaplar.add(Y); //hesaplar arraylistine ekler
            System.out.println("Yatirim hesabiniz olustu! Ibani: "+Y.getiban());
            break;//Kullanici y yada Y girince kirilir
        }
        else if(hesap.equals("*")){
            System.out.println("Hesap olusturma iptal ediliyor ...");
            break;
        }
        else{//Kullanici v ,V ,y veya Y disi bir karakter girince else dongusu calisir
            System.out.print(" "+this.musteriNumarasi+" Numarali musteri icin "
                    + "lutfen yalnizca V veya v/Y veya y giriniz!!!:");
            Toolkit.getDefaultToolkit().beep();
        }
        } 
        
    }

    public void krediKartiEkle(){//Kredi karti ekleme metodu
         KrediKarti KK=new KrediKarti(50,0);
         krediKartlari.add(KK); 
    }
    
    public void hesapSil(){  //hesap silme metodu     
        Scanner girdi_al=new Scanner(System.in);
        System.out.println("Silmek istediginiz hesaplarinizin ibanlari");
                            //Iban bulma kolaylastirma adina
        for(int z=0;z<this.hesaplar.size();z++){
            System.out.println("Ibani-->"+this.hesaplar.get(z).iban+" Bakiyesi"
                    + "-->"+this.hesaplar.get(z).bakiye);
        }
        if(hesaplar.size()>0){//hesaplar arraylist boyutu 0'dan buyuk mu?
            System.out.print("Silmek istediginiz hesabinizin ibanini giriniz:");
            int x=girdi_al.nextInt();
            for(int i=0;i<hesaplar.size();i++){
                if(hesaplar.get(i).iban==x){//girilen iban ile kullanici ibani esit mi?
                    if(hesaplar.get(i).bakiye==0){//hesaptaki bakiye 0 mi?
                        hesaplar.remove(i);//0 ise hesap sil
                        System.out.println("Hesabiniz basari ile silindi.");
                        break;
                    }
                    else if(hesaplar.get(i).bakiye>0){// bakiye 0'dan buyuk mu?
                        System.out.println("Lutfen oncelikle hesap icindeki parayi"
                                + " baska hesaba aktariniz!!!");
                        break;
                    }
                }
            }
        }
        else{ //hesaplar arraylist boyutu 0'a esit yani elemani yok ise
            System.out.println("Adiniza kayitli hesap bulunamadi !!!");
            Toolkit.getDefaultToolkit().beep();
        }
    }
    
    public void krediKartiSil(){ //Bu metot krediKartiSil metodu
        //hesapSil metodu ile yapisi aynidir
        Scanner girdi_al=new Scanner(System.in);
        if(krediKartlari.size()>0){
            System.out.println("Kredi Kart Numaralari");
                //Kullanici bilgilendirme
            for(int b=0;b<this.krediKartlari.size();b++){
                System.out.println(this.krediKartlari.get(b).getkartNumarasi()
                        +" Borcu-->"+this.krediKartlari.get(b).guncelBorc+"TL");
            }
            System.out.print("Silmek istediginiz kredi kartinin numarasini giriniz: ");
            int x=girdi_al.nextInt();
            for(int i=0;i<krediKartlari.size();i++){
                if(krediKartlari.get(i).kartNumarasi==x){
                    if(krediKartlari.get(i).guncelBorc==0){
                        krediKartlari.remove(i);
                        System.out.println("Kredi kartiniz basari ile silindi.");
                        break;
                    }
                    else {
                        System.out.println("Lutfen oncelikle hesap borcunuzu odeyiniz!!!");
                        Toolkit.getDefaultToolkit().beep();
                        break;
                    }
                }
            } 
        }
        else{
            System.out.println("Adiniza kayitli kredi karti bulunamadi !!!");
            Toolkit.getDefaultToolkit().beep();
        }
    }
    public String toString(){
        return(super.toString()+"\n MUSTERİ NO:"+musteriNumarasi);
    }
    }
    
    public static class KrediKarti {//KrediKarti sinifi
    private int kartNumarasi;
    private double limit;
    private double guncelBorc;
    private double kullanilabilirLimit;
    
    
    public KrediKarti(double limit,double guncelBorc){
        //KrediKarti constructoru
        this.limit=limit;
        this.guncelBorc=guncelBorc;
        Random rand = new Random();
        kartNumarasi = rand.nextInt(900000)+100000;/*Random kartNumarasi veren kod.  
         Her KrediKarti nesnesi olustugunda random kartNumarasi olusturuyor*/
    }
    //KrediKarti set metodlari
    public void setLimit(double limit){
        this.limit=limit;
    }
    public void setguncelBorc(double guncelBorc){
        this.guncelBorc=guncelBorc;
    }
    public void setkullanilabilirLimit(double kullanilabilirLimit){
        this.kullanilabilirLimit=kullanilabilirLimit;
    }
    public void setKrediKarti(double limit,double guncelBorc,
            double kullanilabilirLimit){
        this.limit=limit;
        this.guncelBorc=guncelBorc;
        this.kullanilabilirLimit=kullanilabilirLimit;
    }
    //KrediKarti get metodlari
    public int getkartNumarasi(){
        return kartNumarasi;
    }
    public double getlimit(){
        return limit;
    }
    public double getguncelBorc(){
        return guncelBorc;
    }
    public double getkullanilabilirLimit(){
        return kullanilabilirLimit;
    }
    
    public String toString(){
        return (" Kart numarasi:"+kartNumarasi+"\n Limiti:"+limit+"\n Guncel Borcun"
                +"uz:"+guncelBorc+ "\n Kullanilabilir Limit:"+kullanilabilirLimit);
    }
    }
    public static class BankaHesabi {//BankaHesabi sinifi
    private int iban;
    private double bakiye;
    
    public BankaHesabi(double bakiye){
        //BankaHesabi constructoru
        this.bakiye=bakiye;
        Random rand = new Random();
        iban = rand.nextInt(9000000)+1000000;/*Random iban veren kod.  
         Her BankaHesabi nesnesi olustugunda random iban olusturuyor*/
    }
    //BankaHesabi set metodu
    public void setHesap(double bakiye){
        this.bakiye=bakiye; 
    }
    //BankaHesabi get metodlari
    public double getbakiye() {
        return bakiye;
    }
    public int getiban() {
        return iban;
    }
    public String toString(){
        return ("iban:"+iban+"\n Bakiye:"+bakiye);
    }
}
    public static class VadesizHesap extends BankaHesabi{
    //VadesizHesap sinifi BankaHesabi'nden miras aliyor
    private String hesapTuru;
    
    public VadesizHesap(double bakiye){
        super(bakiye);
        this.hesapTuru=" Vadesiz Hesap";/*hesapTuru'nu hesap olustururken 
        kullanacakmisiz ben biraz farkli dusunmustum hesap ekle metodunu farkli yaptim
        hesapTuru bos kalmasin diye boyle yaptim :D*/
    }
    public void paraTransferi(Musteri a,Musteri b){//Para transferi metodu
        Scanner girdi_al=new Scanner(System.in);
        int h=0;
        
        System.out.println("Vadesiz Hesaplariniz:");//Iban bulma kolaylastirma adina
        for(int p=0;p<a.hesaplar.size();p++){
            if(a.hesaplar.get(p) instanceof VadesizHesap){
                h++;/*Vadesiz hesap turundeki hesap sayisi icin*/  
                System.out.println(a.hesaplar.get(p).iban+" Ibanli "+
                             "hesabinizin bakiyesi-->"+a.hesaplar.get(p).bakiye+"TL");
            }    
        }
        if(h>0){
            if(b.hesaplar.size()>0){
                System.out.print("Lutfen gonderici hesabin ibanini giriniz"
                    + "(Sadece vadesiz hesap ibani giriniz(Vadesiz hesaplariniz usttedir)):");
                int c=girdi_al.nextInt();
                
                for(int j=0;j<a.hesaplar.size();j++){
                    if(a.hesaplar.get(j) instanceof VadesizHesap){/*Para gonderme islemi 
                        yalnizca vadesiz hesaptan yapiliyor diye kabul ettim 
                        ama alicinin hesap turu onemli degil*/
                        /*Gercek banka uygulamalarinda gonderen hesabin secilmesini ister 
                        bu projede secme yapamadigim icin gonderen kisinin ibanini aldim.*/
                        /*Gonderen kisinin bircok hesabi olabilir bu hesaplarindan birini 
                        secmek icin kullandim ibandan ibana transfer oldu :D*/

                        if(c==a.hesaplar.get(j).iban){/*Gonderen kisinin ibanlari 
                            arasinda girilen ibanin aramasini yapan if dongusu*/
                            System.out.println("Gondermek istediginiz kisinin hesaplari");
                            //Iban bulma kolaylastirma adina
                            for(int z=0;z<b.hesaplar.size();z++){
                                System.out.println("Ibani-->"+b.hesaplar.get(z).iban);
                            }
                            System.out.print("Lutfen alici hesabin ibani giriniz"
                                    + "(Yatirim hesabi veya vadesiz hesap olabilir"
                                    + "(Kisinin hesaplari yukaridadir)):");
                            int k=girdi_al.nextInt();
                            for(int i=0;i<b.hesaplar.size();i++){
                                if(k==b.hesaplar.get(i).iban){/*Alici kisinin hesaplari 
                                    arasinda girilen ibanin aramasini yapan if dongusu*/
                                    System.out.print("Lutfen transfer etmek istenilen"
                                            + " miktari giriniz:");
                                    double x=girdi_al.nextDouble();
                                    if(a.hesaplar.get(j).bakiye>=x){/*Gonderici hesapta
                                        bakiye kontrolu yapan if dongusu*/
                                        a.hesaplar.get(j).bakiye=a.hesaplar.get(j).bakiye-x;
                                        b.hesaplar.get(i).bakiye=b.hesaplar.get(i).bakiye+x;
                                        System.out.println(a.musteriNumarasi
                                                +" nolu musterinin "+c+" ibanli hesabindan "
                                                +b.musteriNumarasi +" nolu musterinin "
                                                +k+" ibanli hesabina "+x+" TL gonderilmistir.");

                                    }
                                    else{ /*Vadesiz hesap - bakiyeye dusemedigi icin
                                        bu else dongusunu yazdim*/
                                        System.out.println("Lutfen hesabinizda olan"
                                                + " miktara gore para giriniz!");
                                        Toolkit.getDefaultToolkit().beep();
                                        break;
                                    }
                                }
                            }
                        }  
                    }   
                }
            }
            else{
                System.out.println("Gondermek istediginiz kisinin hic hesabi yok!!!");
                Toolkit.getDefaultToolkit().beep();
            }
        }
        else{// Para gondermek isteyen kisinin vadesiz hesabi yoksa bu else calisir
            System.out.println("Adiniza kayitli vadesiz hesap bulunamadi!!! "
                    + "Once vadesiz hesap aciniz!!!");
            Toolkit.getDefaultToolkit().beep();
        }
    }
    public void krediKartiBorcOdeme(Musteri m){
        Scanner girdi_al=new Scanner(System.in);

        if(m.krediKartlari.size()>0){
            if(m.hesaplar.size()>0){
                System.out.println("Adiniza kayitli "+m.krediKartlari.size()+" adet kredi kartiniz var");
                System.out.println("Kredi Kart Numaralari");
                //Kullanici bilgilendirme
                for(int b=0;b<m.krediKartlari.size();b++){
                    System.out.println(m.krediKartlari.get(b).getkartNumarasi()
                            +" Borcu-->"+m.krediKartlari.get(b).guncelBorc+"TL");
                }
                System.out.print("Lutfen odemek istenilen kredi kartinin numarasi giriniz(Yukaridadir):");
                int c=girdi_al.nextInt();
                System.out.println("Adiniza kayitli "+m.hesaplar.size()+" adet hesabiniz var");
                System.out.println("Hesap ibanlari");
                //Kullanici bilgilendirme
                if(m.hesaplar.size()>0){
                    for(int b=0;b<m.hesaplar.size();b++){
                        System.out.println(m.hesaplar.get(b).iban+" Ibanli "+
                                 "hesabinizin bakiyesi-->"+m.hesaplar.get(b).bakiye+"TL");
                    }
                    System.out.print("Lutfen hangi hesapla odenecekse onun"
                                        + " ibanini giriniz(Yukaridadir):");
                                int k=girdi_al.nextInt();
                    for(int i=0;i<m.krediKartlari.size();i++){

                        for(int j=0;j<m.hesaplar.size();j++){
                            if(m.krediKartlari.get(i).kartNumarasi==c){/*Musterinin kredi 
                                kartlari arasinda girilen kart numarasi olup olmadigini 
                                kontrol eden if dongusu*/

                                if(m.hesaplar.get(j).iban==k){/*Musterinin hesaplari arasinda
                                    girilen ibanin olup olmadigini kontrol eden if dongusu*/

                                    System.out.print("Lutfen odemek istenen miktari giriniz:");
                                    double a=girdi_al.nextDouble();
                                    if(m.hesaplar.get(j).bakiye>=a){/*Girilen miktar,hesaptaki
                                        bakiyeye kucuk esitse bu if dongusu calisip borc odeme
                                        gerceklestiriliyor*/
                                        m.krediKartlari.get(i).guncelBorc=m.krediKartlari.get(i).guncelBorc-a;
                                        m.hesaplar.get(j).bakiye=m.hesaplar.get(j).bakiye-a;
                                        System.out.println(a+" TL odendi.");
                                        if(m.krediKartlari.get(i).guncelBorc>=0){
                                            System.out.println(m.krediKartlari.get(i)
                                                .kartNumarasi+" Kart numarali kartin Guncel Borcu --> "
                                                    +m.krediKartlari.get(i).guncelBorc+"TL");
                                        }
                                        if(m.krediKartlari.get(i).guncelBorc<0){/*Bu if 
                                            dongusu borcun - olamayacagi icin yazildi. 
                                            fazladan odenen para,odeme yapilan hesaba aktariliyor.
                                            Sonra borc sifirlaniyor.*/
                                            m.hesaplar.get(j).bakiye=m.hesaplar.get(j).bakiye
                                                +(Math.abs(m.krediKartlari.get(i).guncelBorc));
                                            m.krediKartlari.get(i).guncelBorc=0;
                                            System.out.println(m.krediKartlari.get(i)
                                                .kartNumarasi+" Kart numarali kartin Guncel Borcu --> "
                                                    +m.krediKartlari.get(i).guncelBorc+"TL");
                                        }
                                    }
                                    else{/*Bu else girilen miktar hesaptaki bakiyeden fazla
                                        ise calisiyor. Aslinda  hesaptaki bakiyeyi sifirlayip
                                        borcu hesaptaki miktara gore silebilirdim.*/
                                        System.out.println("Hesapta yeterli bakiye yok!!!");
                                        Toolkit.getDefaultToolkit().beep();
                                        break;
                                    }
                                } 
                            }
                        }
                    }
                }
                else{
                    System.out.println("Hesabiniz yok!!!");
                    Toolkit.getDefaultToolkit().beep();
                }
            }
            else{//Musterinin hesap sayisi 0 ise
                System.out.println("Adiniza kayitli hesap bulunamadi!!!Odeme yapamazsiniz");
                Toolkit.getDefaultToolkit().beep();
            }
        }
        else{ //Musterinin kredi karti sayisi 0 ise
            System.out.println("Adiniza kayitli kredi karti bulunamadi!!!");
            Toolkit.getDefaultToolkit().beep();
        }
    }
    public String toString(){
        return (hesapTuru+"\n "+super.toString());
    }
    }

    public static class YatirimHesabi extends BankaHesabi{
    //YatirimHesabi sinifi BankaHesabini miras almakta    
        
    private String hesapTuru;
     
    public YatirimHesabi(double bakiye){
        super(bakiye);
        this.hesapTuru=" Yatirim Hesabi";/*hesapTuru'nu hesap olustururken 
        kullanacakmisiz ben biraz farkli dusunmustum hesap ekle metodunu farkli 
        yaptim hesapTuru bos kalmasin diye boyle yaptim :D*/
    }
    public void paraEkle(Musteri m){/*ParaEkle metodu sadece kendi hesaplarin arasinda
        para gonderme islemini saglamakta*/
        //Yapisi benzer oldugu icin aciklama satiri fazla koymadim
        Scanner girdi_al=new Scanner(System.in);
        int h=0;
        System.out.println("Yatirim hesaplariniz:");
        for(int p=0;p<m.hesaplar.size();p++){
            if(m.hesaplar.get(p) instanceof YatirimHesabi){
                h++;/*Yatirim hesabi turundeki hesap sayisi icin*/ 
                System.out.println("Ibani-->"+m.hesaplar.get(p).iban+"Bakiyesi-->"
                        +m.hesaplar.get(p).bakiye);
            }  
        }
        if(h>0){
            System.out.print("Lutfen para eklemek istediginiz yatirim hesabinizin"
                    + " ibanini giriniz(sadece yatirim hesabinizi giriniz):");
            int a=girdi_al.nextInt();
            for(int i=0;i<m.hesaplar.size();i++){
                if(m.hesaplar.get(i) instanceof YatirimHesabi){
                    if(m.hesaplar.get(i).iban==a){
                        System.out.print("Lutfen hangi hesaptan para gonderecekseniz "
                        + "gonderdiginiz hesabin ibanini giriniz(Yatirim veya Vadesiz):");
                        int l=girdi_al.nextInt();
                        for(int j=0;j<m.hesaplar.size();j++){
                            if(m.hesaplar.get(j).iban==l){
                                System.out.print("Yatirim hesabiniza yuklemek "
                                + "istediginiz miktari giriniz:");
                                double x=girdi_al.nextDouble();
                                if(m.hesaplar.get(j).bakiye>=x){
                                    m.hesaplar.get(i).bakiye=m.hesaplar.get(i).bakiye+x;
                                    m.hesaplar.get(j).bakiye=m.hesaplar.get(j).bakiye-x;
                                    System.out.println(m.hesaplar.get(j).iban+" ibanli "
                                            + "hesabinizdan "+m.hesaplar.get(i).iban
                                            +" ibanli hesabiniza "+x+" TL gonderiliyor ...");
                                    System.out.println(m.hesaplar.get(j).iban+" ibanli "
                                            + "hesabinizdaki bakiye son durum:"
                                            +m.hesaplar.get(j).bakiye);
                                    System.out.println(m.hesaplar.get(i).iban+" ibanli "
                                            + "hesabinizdaki bakiye son durum:"
                                            +m.hesaplar.get(i).bakiye);
                                }
                                else{
                                    System.out.println("Hesapta yeterli bakiye yok!!!");
                                    Toolkit.getDefaultToolkit().beep();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        else{
            System.out.println("Adiniza kayitli yatirim hesabi bulunamadi!!!");
            Toolkit.getDefaultToolkit().beep();
        }
    }
    public void paraCek(Musteri a,Musteri b){
         Scanner girdi_al=new Scanner(System.in);
        int h=0;
        
        System.out.println("Yatirim Hesaplariniz:");//Iban bulma kolaylastirma adina
        for(int p=0;p<a.hesaplar.size();p++){
            if(a.hesaplar.get(p) instanceof YatirimHesabi){
                h++;/*Yatirim hesap turundeki hesap sayisi icin*/  
                System.out.println(a.hesaplar.get(p).iban+" Ibanli "+
                             "hesabinizin bakiyesi-->"+a.hesaplar.get(p).bakiye+"TL");
            }    
        }
        if(h>0){
            if(b.hesaplar.size()>0){
                System.out.print("Lutfen gonderici hesabin ibanini giriniz"
                    + "(Sadece yatirim hesap ibani giriniz(Yatirim hesaplariniz usttedir)):");
                int c=girdi_al.nextInt();
                
                for(int j=0;j<a.hesaplar.size();j++){
                    if(a.hesaplar.get(j) instanceof YatirimHesabi){/*Para cekme islemi 
                        yalnizca yatirim hesaptan yapiliyor diye kabul ettim 
                        ama alicinin hesap turu onemli degil*/
                        /*Gercek banka uygulamalarinda gonderen hesabin secilmesini ister 
                        bu projede secme yapamadigim icin gonderen kisinin ibanini aldim.*/
                        /*Gonderen kisinin bircok hesabi olabilir bu hesaplarindan birini 
                        secmek icin kullandim ibandan ibana transfer oldu :D*/

                        if(c==a.hesaplar.get(j).iban){/*Gonderen kisinin ibanlari 
                            arasinda girilen ibanin aramasini yapan if dongusu*/
                            System.out.println("Gondermek istediginiz kisinin hesaplari");
                            //Iban bulma kolaylastirma adina
                            for(int z=0;z<b.hesaplar.size();z++){
                                System.out.println("Ibani-->"+b.hesaplar.get(z).iban);
                            }
                            System.out.print("Lutfen alici hesabin ibani giriniz"
                                    + "(Yatirim hesabi veya vadesiz hesap olabilir(Kisinin hesaplari yukaridadir)):");
                            int k=girdi_al.nextInt();
                            for(int i=0;i<b.hesaplar.size();i++){
                                if(k==b.hesaplar.get(i).iban){/*Alici kisinin hesaplari 
                                    arasinda girilen ibanin aramasini yapan if dongusu*/
                                    System.out.print("Lutfen transfer etmek istenilen"
                                            + " miktari giriniz:");
                                    double x=girdi_al.nextDouble();
                                    if(a.hesaplar.get(j).bakiye>=x){/*Gonderici hesapta
                                        bakiye kontrolu yapan if dongusu*/
                                        a.hesaplar.get(j).bakiye=a.hesaplar.get(j).bakiye-x;
                                        b.hesaplar.get(i).bakiye=b.hesaplar.get(i).bakiye+x;
                                        System.out.println(a.musteriNumarasi
                                                +" nolu musterinin "+c+" ibanli hesabindan "
                                                +b.musteriNumarasi +" nolu musterinin "
                                                +k+" ibanli hesabina "+x+" TL gonderilmistir.");

                                    }
                                    else{ /*yatirim hesabi - bakiyeye dusemedigi icin
                                        bu else dongusunu yazdim*/
                                        System.out.println("Lutfen hesabinizda olan"
                                                + " miktara gore para giriniz!");
                                        Toolkit.getDefaultToolkit().beep();
                                        break;
                                    }
                                }
                            }
                        }  
                    }   
                }
            }
            else{
                System.out.println("Gondermek istediginiz kisinin hic hesabi yok!!!");
                Toolkit.getDefaultToolkit().beep();
            }
        }
        else{// Para gondermek isteyen kisinin yatirim hesabi yoksa bu else calisir
            System.out.println("Adiniza kayitli yatirim hesabi bulunamadi!!! "
                    + "Once yatirim hesabi aciniz!!!");
            Toolkit.getDefaultToolkit().beep();
        }      
    }
    public String toString(){
        return (hesapTuru+"\n "+super.toString());
    }
    }   
}

