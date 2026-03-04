package ex01;

import java.io.Serializable;

public class Item2d implements Serializable{
    private transient int length_rows;
    private String x;
    private int y;
    private static final long serialVersionUID = 1L;
    public Item2d(){
        x = "";
        y = 0;
        length_rows = 0;
    } 
     public Item2d(String x, int y){
        this.x= x;
        this.y =y;
        this.length_rows = x.length();
    } 
    public void setX(String x){
        this.x = x;
        this.length_rows = x.length();
    }
    public String getX(){
        return x;
    }
    public void setY(int y){
       this.y = y;
    }
    public int getY(){
        return y;
    }
    public Item2d setXY(String x, int y){
        this.x = x;
        this.y = y;
        this.length_rows = x.length();
        return this;
    }
    public String toString(){
        return "Rows: " + x + "\nk: " + y + "\nLenght: " + length_rows;
    }
    public boolean equals(Object obj){
        if(this ==obj)
            return true;
        if(obj==null)
            return false;
        if(getClass()!=obj.getClass())
            return false;
        Item2d other = (Item2d) obj;
        if(y != other.y)
            return false;
        if(!x.equals(other.x))
            return false;

        return true;
    }
}