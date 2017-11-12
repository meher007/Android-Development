package entities;

/**
 * Created by home on 28/07/2017.
 */
import java.io.Serializable;

/**
 * Created by home on 27/07/2017.
 */
public class ViewCad implements Serializable {

    private String id;
    private String name;
    //private BigDecimal price;
    private int pageid;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getpageid() {
        return pageid;
    }

    public void setpageid(int pageid) {
        this.pageid = pageid;
    }

    public ViewCad(String id, String name, int pageid) {
      this.id = id;
    this.name = name;
   this.pageid = pageid;
   }
}

