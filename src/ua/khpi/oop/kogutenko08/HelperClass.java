package ua.khpi.oop.kogutenko08;

import java.io.*;
import java.util.Scanner;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * The type Helper class with console.
 */
public class HelperClass extends Object{

    private Array<Shops> save = new SaveArray<>();


    /**
     * Instantiates a new Helper class with console.
     */
    public HelperClass() { }

    /**
     * Gets info of helper object.
     *
     * @return the info of helper object
     */
    @Override
    public String toString()
    {
        String str = "";
        for(Shops el : save)
        {
            str += el.toString() + "\n";
        }
        return str;
    }

    /**
     * Print saves.
     */
    public void printSaves()
    {
        String str = "";
        for(Shops shops : save)
        {
            str += shops.toString();
        }
        System.out.println(str);
    }

    /**
     * Changed text.
     *
     * @throws Exception the exception
     */

    public void add()
    {
        Shops shops = new Shops();
        shops.add();
        save.add(shops);
    }
    public void remove()
    {
        printSaves();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of id: ");
        int id = sc.nextInt();
        save.remove(id - 1 );
        printSaves();
    }

    public void serialization()
    {
        //File file = ConsoleFile.MenuFillOut();
        try{
            XMLEncoder encoder = new XMLEncoder(
                    new BufferedOutputStream(
                            new FileOutputStream( ConsoleFile.MenuFillOut()+".xml")));

            encoder.writeObject(save.size());

            for(Shops shop : save)
                encoder.writeObject(shop);


            encoder.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserializtion() {
        //Array<Shop> container = new SaveArray<>();
        File file = ConsoleFile.MenuFillIn();///pathname
        try {
            FileInputStream fis = new FileInputStream(file);///pathname
            ObjectInputStream ois = new ObjectInputStream(fis);

            int count = ois.readInt();

            for(int i = 0; i < count; i++)
            {
                Shops shops = (Shops)ois.readObject();
                save.add(shops);
            }
            ois.close();
        }
        catch(FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();	}
        catch (ClassNotFoundException e) {e.printStackTrace();	}
    }
    public void deserializtionXML()
    {
        try{
                XMLDecoder decoder = new XMLDecoder(
                    new BufferedInputStream(
                            new FileInputStream(ConsoleFile.MenuFillIn())));

            int count = (int) decoder.readObject();

            for(int i = 0; i < count; i++)
            {
                Shops shops = (Shops)decoder.readObject();
                save.add(shops);
            }
            decoder.close();

        }
        catch(FileNotFoundException e) {e.printStackTrace();}
    }
}