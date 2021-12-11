import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table (name = "chair")
public class Chair implements Serializable{

    @Id
    @Column
    private int chair_id;

    @Column
    private int hall_id;

    @Column
    private int place;

    @Column
    private int row;

    public int getChair_id()
    {
        return chair_id;
    }

    public void setChair_id(int chair_id)
    {
        this.chair_id = chair_id;
    }

    public int getHall_id()
    {
        return hall_id;
    }

    public void setHall_id(int hall_id)
    {
        this.hall_id = hall_id;
    }

    public int getPlace()
    {
        return place;
    }

    public void setPlace(int place)
    {
        this.place = place;
    }

    public int getRow()
    {
        return row;
    }

    public void setRow(int row)
    {
        this.row = row;
    }

    @Override
    public String toString(){
        return "Chair {" +
                " chair_id = " + chair_id +
                " hall_id = " + hall_id +
                " place = " + place +
                " row = " + row +
                " }";
    }

}
