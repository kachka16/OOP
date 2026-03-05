package ex02;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import ex01.Item2d;

public class ViewResult implements View {

    private static final String FNAME = "items.bin";
    private ArrayList<Item2d> items = new ArrayList<Item2d>();
    private static final int DEFAULT_NUM = 1;

    public ViewResult(){
        this(DEFAULT_NUM);
    }
    public ViewResult(int n){
        for( int i = 0; i<n; i++){
            items.add(new Item2d());
        }
    }

    public ArrayList<Item2d> getItems(){
         return items;
    }

    public int calc(String rows){
    char[] litters_golosni = {'a','A','U','u','E','e','O','o','Y','y','i','I'};
    int k = 0;
     for(char r : rows.toCharArray()){
        for( char l : litters_golosni)
            if(r == l){
                k++;
            }
      }
     return k;
    }

    public void init(String rows){
        items.clear();
        String[] word = rows.split(" ");
        for(String w : word){
            Item2d item = new Item2d();
            item.setX(w);
            item.setY(calc(w));

            items.add(item);
            
        }
    }

    @Override
    public void viewInit(String rows){
        init(rows);
        
    }

    @Override
    public void viewSave() throws IOException{
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME));
        os.writeObject(items);
        os.flush();
        os.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void viewRestore() throws Exception{
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
        items = (ArrayList<Item2d>) is.readObject();
        is.close();
    }

    @Override
    public void viewHeader(){
        System.out.println("\t \tResults: ");
    }

    @Override
    public void viewBody(){
        for(Item2d item : items){
            System.out.println(item);
        }
    }

    @Override
    public void viewFooter(){
        System.out.println("----------------------------------------------------\n \t\tEND");
    }
       
    @Override
    public void viewShow(){
        viewHeader();
        viewBody();
        viewFooter();
    }
}
