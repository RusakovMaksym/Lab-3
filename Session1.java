import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;


@Entity
@Table (name = "session")
public class Session1 implements Serializable{

    @Id
    @Column
    private int session_id;

    @Column
    private int film_id;

    @Column
    private int hall_id;

    @Column
    private Time time;

    @Column
    private int price;

    public int getSession_id()
    {
        return session_id;
    }

    public void setSession_id(int session_id)
    {
        this.session_id = session_id;
    }

    public int getFilm_id()
    {
        return film_id;
    }

    public void setFilm_id(int film_id)
    {
        this.film_id = film_id;
    }

    public int getHall_id()
    {
        return hall_id;
    }

    public void setHall_id(int hall_id)
    {
        this.hall_id = hall_id;
    }

    public Time getTime()
    {
        return time;
    }

    public void setTime(Time time)
    {
        this.time = time;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    @Override
    public String toString(){
        return "Session {" +
                " session_id = " + session_id +
                " film_id = " + film_id +
                " time = " + time +
                " price = " + price +
                " }";
    }
}
