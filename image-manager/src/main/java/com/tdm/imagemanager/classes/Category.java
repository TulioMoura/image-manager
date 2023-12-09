import java.util.ArrayList;
import java.util.Date;
import java.lang.Exception;
import java.lang.String;
import java.lang.invoke.StringConcatException;

public class Category{
    private String name;
    private Date dataInclusão;    
    private ArrayList<String> images;

    public String getName(){
        return name;
    }

    public String getDate(){
        return dataInclusão.toString();
    }

    public ArrayList<String>getImages(){
        return images;
    }

    public void setName(String name){
        this.name = name;
    }

    public void addImage(String id) throws Exception{
        if(images.contains(id)){
            throw new Exception("Image is already on category");
        }
        else{
            images.add(id);
        }
    }

    public void removeImage(String id) throws Exception{
        if(images.contains(id)){
            images.remove(id);
        }
        else{
            throw new Exception("Image isnt on category");
        }
    }

    public Category(String nome){
        this.setName(nome);
        this.dataInclusão = new Date(); 
        this.images = new ArrayList<String>();
    }
}