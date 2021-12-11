import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table (name = "ticket")
public class Ticket implements Serializable{

    @Id
    @Column
    private int ticket_id;

    @Column
    private int session_id;

    @Column
    private int chair_id;

    @Column
    private Boolean isbought;

    public int getTicket_id()
    {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id)
    {
        this.ticket_id = ticket_id;
    }

    public int getSession_id()
    {
        return session_id;
    }

    public void setSession_id(int session_id)
    {
        this.session_id = session_id;
    }

    public int getChair_id()
    {
        return chair_id;
    }

    public void setChair_id(int chair_id)
    {
        this.chair_id = chair_id;
    }

    public Boolean getIsbought()
    {
        return isbought;
    }

    public void setIsbought(Boolean isbought)
    {
        this.isbought = isbought;
    }

    @Override
    public String toString(){
        return "Ticket {" +
                " ticket_id = " + ticket_id +
                " session_id = " + session_id +
                " chair_id = " + chair_id +
                " isbought = " + isbought +
                " }";
    }
}
