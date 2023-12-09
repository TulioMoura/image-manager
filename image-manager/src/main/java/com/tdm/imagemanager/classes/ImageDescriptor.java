import java.util.ArrayList;
import java.util.Date;
import java.lang.Exception;
import java.lang.String;

public class ImageDescriptor{
    private String uuid;
    private Date uploadDate;
    private ArrayList<String> characteristics;
    private Image thumbnail;

    public String getId(){
        return uuid;
    }
    public Date getDate(){
        return uploadDate;
    }
    public ArrayList<String> getCharacteristics(){
        return characteristics;
    }
    public Image getThumbnail{
        return thumbnail;
    }

    public void addCharacteristic(String charcateristic){
        characteristics.add(charcateristic);
    }

    public void removeCharacteristic(int index){
        characteristics.remove(index);
    }

    public void removeAllCharacteristics(){
        characteristics = new ArrayList<String>;
    }

    public void setThumbnail(Image thumbnail){
        this.thumbnail = thumbnail;
    }

    public ImageDescriptor(String uuid,ArrayList<String> characteristics, Image thumbnail){
        this.characteristics = characteristics;
        this.thumbnail = thumbnail;
        this.uuid = uuid;
    }
}