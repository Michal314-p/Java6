package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML private ListView<String> id_ksiazki;
    @FXML private ListView<String> autor_ksiazki;
    @FXML private ListView<String> tytul_ksiazki;
    @FXML private ListView<String> gatunek_ksiazki;
    @FXML private ListView<String> opis_ksiazki;

    @FXML private ListView<String> id_komiksu;
    @FXML private ListView<String> autor_komiksu;
    @FXML private ListView<String> tytul_komiksu;
    @FXML private ListView<String> gatunek_komiksu;
    @FXML private ListView<String> opis_komiksu;

    @FXML private TextField autor;
    @FXML private TextField tytul;
    @FXML private TextField gatunek;
    @FXML private TextField opis;

    @FXML private TextField id_usun;
    @FXML private TextField id_edytuj;
    @FXML private TextField szukaj_autor;
    @FXML private TextField szukaj_gatunek;
    @FXML private TextField szukaj_tytul;
    @FXML private TextField edytuj_opis_pole;

    private String st[] = { "Komiks", "Ksiazka"};

    @FXML  private SplitMenuButton dodaj_box;
    @FXML  private SplitMenuButton usun_box;
    @FXML  private SplitMenuButton edytuj_box;
    @FXML  private SplitMenuButton szukaj_gatunek_box;
    @FXML  private SplitMenuButton szukaj_tytul_box;
    @FXML  private SplitMenuButton szukaj_autor_box;

    private MenuItem wybor1 = new MenuItem("Ksiazka");
    private MenuItem wybor2 = new MenuItem("Komiks");
    private MenuItem wybor3 = new MenuItem("Ksiazka");
    private MenuItem wybor4 = new MenuItem("Komiks");
    private MenuItem wybor5 = new MenuItem("Ksiazka");
    private MenuItem wybor6 = new MenuItem("Komiks");
    private MenuItem wybor7 = new MenuItem("Ksiazka");
    private MenuItem wybor8 = new MenuItem("Komiks");
    private MenuItem wybor9 = new MenuItem("Ksiazka");
    private MenuItem wybor10 = new MenuItem("Komiks");
    private MenuItem wybor11 = new MenuItem("Ksiazka");
    private MenuItem wybor12 = new MenuItem("Komiks");

    private String wybierz = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        usun_box.getItems().addAll(wybor1,wybor2);
        dodaj_box.getItems().addAll(wybor3,wybor4);
        edytuj_box.getItems().addAll(wybor5,wybor6);
        szukaj_tytul_box.getItems().addAll(wybor7,wybor8);
        szukaj_gatunek_box.getItems().addAll(wybor9,wybor10);
        szukaj_autor_box.getItems().addAll(wybor11,wybor12);

        wybor1.setOnAction((e)-> {
           wybierz="Ksiazka";
        });
        wybor3.setOnAction((e)-> {
            wybierz="Ksiazka";
        });
        wybor5.setOnAction((e)-> {
            wybierz="Ksiazka";
        });
        wybor7.setOnAction((e)-> {
            wybierz="Ksiazka";
        });
        wybor9.setOnAction((e)-> {
            wybierz="Ksiazka";
        });
        wybor11.setOnAction((e)-> {
            wybierz="Ksiazka";
        });

        wybor2.setOnAction((e)-> {
            wybierz="Komiks";
        });
        wybor4.setOnAction((e)-> {
            wybierz="Komiks";
        });
        wybor6.setOnAction((e)-> {
            wybierz="Komiks";
        });
        wybor8.setOnAction((e)-> {
            wybierz="Komiks";
        });
        wybor10.setOnAction((e)-> {
            wybierz="Komiks";
        });
        wybor12.setOnAction((e)-> {
            wybierz="Komiks";
        });
    }

    public void wyczysc()
    {
        id_komiksu.getItems().clear();
        id_ksiazki.getItems().clear();
        autor_ksiazki.getItems().clear();
        autor_komiksu.getItems().clear();
        gatunek_ksiazki.getItems().clear();
        gatunek_komiksu.getItems().clear();
        opis_ksiazki.getItems().clear();
        opis_komiksu.getItems().clear();
        tytul_ksiazki.getItems().clear();
        tytul_komiksu.getItems().clear();
    }

    public void wyczysc_komiks()
    {
        id_komiksu.getItems().clear();
        autor_komiksu.getItems().clear();
        gatunek_komiksu.getItems().clear();
        opis_komiksu.getItems().clear();
        tytul_komiksu.getItems().clear();
    }

    public void wyczysc_ksiazka()
    {
        id_ksiazki.getItems().clear();
        autor_ksiazki.getItems().clear();
        gatunek_ksiazki.getItems().clear();
        opis_ksiazki.getItems().clear();
        tytul_ksiazki.getItems().clear();
    }

    public void wyszukaj_wszystko()
    {
        wyczysc();
        try{
            Baza_uzytkowe db = new Baza_uzytkowe();
            db.polacz_z_baza();
            Statement stmt=db.conn.createStatement();
            ResultSet rs=stmt.executeQuery("select * from ksiazki");
            while(rs.next())
            {
                id_ksiazki.getItems().add(String.valueOf(rs.getInt(1)));
                autor_ksiazki.getItems().add(rs.getString(2));
                tytul_ksiazki.getItems().add(rs.getString(3));
                gatunek_ksiazki.getItems().add(rs.getString(4));
                opis_ksiazki.getItems().add(rs.getString(5));
            }

            rs=stmt.executeQuery("select * from komiksy");
            while(rs.next())
            {
                id_komiksu.getItems().add(String.valueOf(rs.getInt(1)));
                autor_komiksu.getItems().add(rs.getString(2));
                tytul_komiksu.getItems().add(rs.getString(3));
                gatunek_komiksu.getItems().add(rs.getString(4));
                opis_komiksu.getItems().add(rs.getString(5));
            }

            db.rozlacz();
        }catch(Exception e){ System.out.println(e);}
    }



    public void dodaj()
    {
        try{
            Baza_uzytkowe db = new Baza_uzytkowe();
            db.polacz_z_baza();
            PreparedStatement stmt= null;
            if(wybierz=="Komiks")
            {
                stmt = db.conn.prepareStatement("insert into komiksy (autor, tytul, gatunek, opis) values (?,?,?,?)");
            }

            if(wybierz=="Ksiazka")
            {
                stmt = db.conn.prepareStatement("insert into ksiazki (autor, tytul, gatunek, opis) values (?,?,?,?)");
            }

            stmt.setString(1,autor.getText());
            stmt.setString(2,tytul.getText());
            stmt.setString(3,gatunek.getText());
            stmt.setString(4,opis.getText());
            stmt.executeUpdate();
            db.rozlacz();

            wyszukaj_wszystko();
        }catch(Exception e){ System.out.println(e);}
    }

    public void usun()
    {
        try{
            Baza_uzytkowe db = new Baza_uzytkowe();
            db.polacz_z_baza();
            PreparedStatement stmt= null;
            if(wybierz=="Komiks")
            {
                stmt = db.conn.prepareStatement("delete from komiksy where id = ?");
            }

            if(wybierz=="Ksiazka")
            {
                stmt = db.conn.prepareStatement("delete from ksiazki where id = ?");
            }

            stmt.setString(1,id_usun.getText());
            stmt.executeUpdate();
            db.rozlacz();

            wyszukaj_wszystko();
        }catch(Exception e){ System.out.println(e);}
    }

    public void edytuj()
    {
        try{
            Baza_uzytkowe db = new Baza_uzytkowe();
            db.polacz_z_baza();
            PreparedStatement stmt= null;
            if(wybierz=="Komiks")
            {
                stmt = db.conn.prepareStatement("update komiksy set opis = ? where id = ?");
            }

            if(wybierz=="Ksiazka")
            {
                stmt = db.conn.prepareStatement("update ksiazki set opis = ? where id = ?");
            }

            stmt.setString(2,id_edytuj.getText());
            stmt.setString(1,edytuj_opis_pole.getText());

            stmt.executeUpdate();
            db.rozlacz();

            wyszukaj_wszystko();
        }catch(Exception e){ System.out.println(e);}
    }

    public void szukaj_autora()
    {
        try{
            Baza_uzytkowe db = new Baza_uzytkowe();
            db.polacz_z_baza();
            PreparedStatement stmt= null;
            if(wybierz=="Komiks")
            {
                stmt = db.conn.prepareStatement("select * from komiksy where autor = ?");
            }

            if(wybierz=="Ksiazka")
            {
                stmt = db.conn.prepareStatement("select * from ksiazki where autor = ?");
            }

            stmt.setString(1,szukaj_autor.getText());

            ResultSet resultSet = null;
            resultSet = stmt.executeQuery();



            if(wybierz=="Ksiazka")
            {
                wyczysc_ksiazka();
                while(resultSet.next())
                {
                    id_ksiazki.getItems().add(String.valueOf(resultSet.getInt(1)));
                    autor_ksiazki.getItems().add(resultSet.getString(2));
                    tytul_ksiazki.getItems().add(resultSet.getString(3));
                    gatunek_ksiazki.getItems().add(resultSet.getString(4));
                    opis_ksiazki.getItems().add(resultSet.getString(5));
                }
            }

            if(wybierz=="Komiks")
            {
                wyczysc_komiks();
                while(resultSet.next())
                {
                    id_komiksu.getItems().add(String.valueOf(resultSet.getInt(1)));
                    autor_komiksu.getItems().add(resultSet.getString(2));
                    tytul_komiksu.getItems().add(resultSet.getString(3));
                    gatunek_komiksu.getItems().add(resultSet.getString(4));
                    opis_komiksu.getItems().add(resultSet.getString(5));
                }
            }
            db.rozlacz();

        }catch(Exception e){ System.out.println(e);}
    }

    public void szukaj_gatunek()
    {
        try{
            Baza_uzytkowe db = new Baza_uzytkowe();
            db.polacz_z_baza();
            PreparedStatement stmt= null;
            if(wybierz=="Komiks")
            {
                stmt = db.conn.prepareStatement("select * from komiksy where gatunek = ?");
            }

            if(wybierz=="Ksiazka")
            {
                stmt = db.conn.prepareStatement("select * from ksiazki where gatunek = ?");
            }

            stmt.setString(1,szukaj_gatunek.getText());

            ResultSet resultSet = null;
            resultSet = stmt.executeQuery();



            if(wybierz=="Ksiazka")
            {
                wyczysc_ksiazka();
                while(resultSet.next())
                {
                    id_ksiazki.getItems().add(String.valueOf(resultSet.getInt(1)));
                    autor_ksiazki.getItems().add(resultSet.getString(2));
                    tytul_ksiazki.getItems().add(resultSet.getString(3));
                    gatunek_ksiazki.getItems().add(resultSet.getString(4));
                    opis_ksiazki.getItems().add(resultSet.getString(5));
                }
            }

            if(wybierz=="Komiks")
            {
                wyczysc_komiks();
                while(resultSet.next())
                {
                    id_komiksu.getItems().add(String.valueOf(resultSet.getInt(1)));
                    autor_komiksu.getItems().add(resultSet.getString(2));
                    tytul_komiksu.getItems().add(resultSet.getString(3));
                    gatunek_komiksu.getItems().add(resultSet.getString(4));
                    opis_komiksu.getItems().add(resultSet.getString(5));
                }
            }
            db.rozlacz();


        }catch(Exception e){ System.out.println(e);}
    }

    public void szukaj_tytul()
    {
        try{
            Baza_uzytkowe db = new Baza_uzytkowe();
            db.polacz_z_baza();
            PreparedStatement stmt= null;
            if(wybierz=="Komiks")
            {
                stmt = db.conn.prepareStatement("select * from komiksy where tytul = ?");
            }

            if(wybierz=="Ksiazka")
            {
                stmt = db.conn.prepareStatement("select * from ksiazki where tytul = ?");
            }


            stmt.setString(1,szukaj_tytul.getText());

            ResultSet resultSet = null;
            resultSet = stmt.executeQuery();



            if(wybierz=="Ksiazka")
            {
                wyczysc_ksiazka();
                while(resultSet.next())
                {
                    id_ksiazki.getItems().add(String.valueOf(resultSet.getInt(1)));
                    autor_ksiazki.getItems().add(resultSet.getString(2));
                    tytul_ksiazki.getItems().add(resultSet.getString(3));
                    gatunek_ksiazki.getItems().add(resultSet.getString(4));
                    opis_ksiazki.getItems().add(resultSet.getString(5));
                }
            }

            if(wybierz=="Komiks")
            {
                wyczysc_komiks();
                while(resultSet.next())
                {
                    id_komiksu.getItems().add(String.valueOf(resultSet.getInt(1)));
                    autor_komiksu.getItems().add(resultSet.getString(2));
                    tytul_komiksu.getItems().add(resultSet.getString(3));
                    gatunek_komiksu.getItems().add(resultSet.getString(4));
                    opis_komiksu.getItems().add(resultSet.getString(5));
                }
            }


            db.rozlacz();

        }catch(Exception e){ System.out.println(e);}
    }

}

