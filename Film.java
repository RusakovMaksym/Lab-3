import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table (name = "film")
public class Film implements Serializable{

    @Id
    @Column
    private int film_id;

    @Column
    private String name;

    @Column
    private Time duration;

    public int getFilm_id()
    {
        return film_id;
    }

    public void setFilm_id(int film_id)
    {
        this.film_id = film_id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Time getDuration()
    {
        return duration;
    }

    public void setDuration(Time duration)
    {
        this.duration = duration;
    }

    @Override
    public String toString(){
        return "Film {" +
                " film_id = " + film_id +
                " name = " + name +
                " duration = " + duration +
                " }";
    }

}
