import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table (name = "hall")
public class Hall implements Serializable{

    @Id
    @Column
    private int hall_id;

    @Column
    private String name;


    public int getHall_id()
    {
        return hall_id;
    }

    public void setHall_id(int hall_id)
    {
        this.hall_id = hall_id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }


    @Override
    public String toString(){
        return "Hall {" +
                " hall_id = " + hall_id +
                " name = " + name +
                " }";
    }

}
