
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.*;

class Model {
    static final String user = "sysdba";
    static final String password = "masterkey";
    static final String url = "jdbc:postgresql://localhost:5432/KPI";

    //Функция для подключения к БД
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static String findAllChair(String chair_id, String hall_id, String place, String row){
        List<Chair> list;

        String sql = "from Chair where";
        sql += " chair_id = " + (chair_id.length() > 0 ? chair_id : "chair_id");
        sql += " and hall_id = " + (hall_id.length() > 0 ? hall_id : "hall_id");
        sql += " and place = " + (place.length() > 0 ? place : "place");
        sql += " and row = " + (row.length() > 0 ? row : "row");

        try(Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            Query query = session.createQuery(sql);
            list = (List<Chair>) query.list();

            session.getTransaction().commit();
        } catch(Throwable ex){
            return ex.getMessage();
        }

        String result = "";
        for(Chair chair : list) { result += chair + "\n"; }

        return (result.length() > 0 ? result : "Нічого не знайдено!");
    }

    public static String findAllFilm(String film_id, String name, String duration){
        List<Film> list;

        String sql = "from Film where";
        sql += " film_id = " + (film_id.length() > 0 ? film_id : "film_id");
        sql += " and name like " + (name.length() > 0 ? ("'%" + name + "%'") : "name");
        sql += " and duration = " + (duration.length() > 0 ? ("'" + duration + "'") : "duration");

        try(Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            Query query = session.createQuery(sql);
            list = (List<Film>) query.list();

            session.getTransaction().commit();
        } catch(Throwable ex){
            return ex.getMessage();
        }

        String result = "";
        for(Film film : list) { result += film + "\n"; }

        return (result.length() > 0 ? result : "Нічого не знайдено!");
    }

    public static String findAllSession(String session_id, String film_id, String hall_id, String time, String price){
        List<Session1> list;

        String sql = "from Session1 where";
        sql += " session_id = " + (session_id.length() > 0 ? session_id : "session_id");
        sql += " and film_id = " + (film_id.length() > 0 ? film_id : "film_id");
        sql += " and hall_id = " + (hall_id.length() > 0 ? hall_id : "hall_id");
        sql += " and time = " + (time.length() > 0 ? ("'" + time + "'") : "time");
        sql += " and price = " + (price.length() > 0 ? price : "price");

        try(Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            Query query = session.createQuery(sql);
            list = (List<Session1>) query.list();

            session.getTransaction().commit();
        } catch(Throwable ex){
            return ex.getMessage();
        }

        String result = "";
        for(Session1 session1 : list) { result += session1 + "\n"; }

        return (result.length() > 0 ? result : "Нічого не знайдено!");
    }

    public static String findAllHall(String hall_id, String name){
        List<Hall> list;

        String sql = "from Hall where";
        sql += " hall_id = " + (hall_id.length() > 0 ? hall_id : "hall_id");
        sql += " and name like " + (name.length() > 0 ? ("'%" + name + "%'") : "name");

        try(Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            Query query = session.createQuery(sql);
            list = (List<Hall>) query.list();

            session.getTransaction().commit();
        } catch(Throwable ex){
            return ex.getMessage();
        }

        String result = "";
        for(Hall hall : list) { result += hall + "\n"; }

        return (result.length() > 0 ? result : "Нічого не знайдено!");
    }

    public static String findAllTicket(String ticket_id, String session_id, String chair_id, String isbought){
        List<Ticket> list;

        String sql = "from Ticket where";
        sql += " ticket_id = " + (ticket_id.length() > 0 ? ticket_id : "ticket_id");
        sql += " and session_id = " + (session_id.length() > 0 ? session_id : "session_id");
        sql += " and chair_id = " + (chair_id.length() > 0 ? chair_id : "chair_id");
        sql += " and isbought = " + (isbought.length() > 0 ? isbought : "isbought");

        try(Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            Query query = session.createQuery(sql);
            list = (List<Ticket>) query.list();

            session.getTransaction().commit();
        } catch(Throwable ex){
            return ex.getMessage();
        }

        String result = "";
        for(Ticket ticket : list) { result += ticket + "\n"; }

        return (result.length() > 0 ? result : "Нічого не знайдено!");
    }

    public static String createChair(String chair_id, String hall_id, String place, String row){
        Chair chair = new Chair();

        try(Session session = HibernateUtil.getSession())
        {
            session.beginTransaction();
            chair.setChair_id(Integer.parseInt(chair_id));
            chair.setHall_id(Integer.parseInt(hall_id));
            chair.setPlace(Integer.parseInt(place));
            chair.setRow(Integer.parseInt(row));

            session.save(chair);
            session.getTransaction().commit();
        } catch (Throwable ex)
        {
            return ex.getMessage();
        }

        return "Створено:\n"+ chair;
    }

    public static String createFilm(String film_id, String name, String duration){
        Film film = new Film();

        try(Session session = HibernateUtil.getSession()) {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

            session.beginTransaction();
            film.setFilm_id(Integer.parseInt(film_id));
            film.setName(name);
            film.setDuration(new Time(sdf.parse(duration).getTime()));

            session.save(film);
            session.getTransaction().commit();
        } catch (Throwable ex)
        {
            return ex.getMessage();
        }

        return "Створено:\n"+ film;
    }

    public static String createSession(String session_id, String film_id, String hall_id, String time, String price){
        Session1 session1 = new Session1();
        try(Session session = HibernateUtil.getSession()) {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

            session.beginTransaction();
            session1.setSession_id(Integer.parseInt(session_id));
            session1.setFilm_id(Integer.parseInt(film_id));
            session1.setHall_id(Integer.parseInt(hall_id));
            session1.setTime(new Time(sdf.parse(time).getTime()));
            session1.setPrice(Integer.parseInt(price));

            session.save(session1);
            session.getTransaction().commit();
        } catch (Throwable ex)
        {
            return ex.getMessage();
        }

        return "Створено:\n"+ session1;
    }

    public static String createHall(String hall_id, String name){
        Hall hall = new Hall();

        try(Session session = HibernateUtil.getSession()) {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

            session.beginTransaction();
            hall.setHall_id(Integer.parseInt(hall_id));
            hall.setName(name);

            session.save(hall);
            session.getTransaction().commit();
        } catch (Throwable ex)
        {
            return ex.getMessage();
        }

        return "Створено:\n"+ hall;

    }

    public static String createTicket(String ticket_id, String session_id, String chair_id, String isbought){
        Ticket ticket = new Ticket();
        try(Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            ticket.setTicket_id(Integer.parseInt(ticket_id));
            ticket.setSession_id(Integer.parseInt(session_id));
            ticket.setChair_id(Integer.parseInt(chair_id));
            ticket.setIsbought(Boolean.parseBoolean(isbought));

            session.save(ticket);
            session.getTransaction().commit();
        } catch (Throwable ex)
        {
            return ex.getMessage();
        }

        return "Створено:\n"+ ticket;
    }

    public static String deleteChair(String chair_id){
        Chair chair;

        try(Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            chair = session.get(Chair.class, Integer.parseInt(chair_id));
            session.delete(chair);

            session.getTransaction().commit();
        } catch (Throwable ex)
        {
            return ex.getMessage();
        }

        return "Видалено:\n" + chair;
    }

    public static String deleteFilm(String film_id){
        Film film;

        try(Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            film = session.get(Film.class, Integer.parseInt(film_id));
            session.delete(film);

            session.getTransaction().commit();
        } catch (Throwable ex)
        {
            return ex.getMessage();
        }

        return "Видалено:\n" + film;
    }

    public static String deleteSession(String session_id){
        Session1 session1;

        try(Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session1 = session.get(Session1.class, Integer.parseInt(session_id));

            session.delete(session1);
            session.getTransaction().commit();
        } catch (Throwable ex)
        {
            return ex.getMessage();
        }

        return "Видалено:\n" + session1;
    }

    public static String deleteHall(String hall_id){
        Hall hall;

        try(Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            hall = session.get(Hall.class, Integer.parseInt(hall_id));

            session.delete(hall);
            session.getTransaction().commit();
        } catch (Throwable ex)
        {
            return ex.getMessage();
        }

        return "Видалено:\n" + hall;
    }

    public static String deleteTicket(String ticket_id){
        Ticket ticket;

        try(Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            ticket = session.get(Ticket.class, Integer.parseInt(ticket_id));

            session.delete(ticket);
            session.getTransaction().commit();
        } catch (Throwable ex)
        {
            return ex.getMessage();
        }

        return "Видалено:\n" + ticket;
    }

    public static String genRandom(Connection connection, String count, int currentTab){
        if (count.equals("")) count = "20";
        if (!count.matches("\\d+")) {
            return "Некоректний ввід даних!";
        }
        String sql = "";

        switch (currentTab) {
            case 1:
                sql = "INSERT INTO public.chair (chair_id, hall_id, place, row) VALUES ((select max(chair_id) from chair) + 1, (select hall_id from hall order by random() limit 1), FLOOR(RANDOM() * 100), FLOOR(RANDOM() * 100))";
                break;
            case 2:
                sql = "INSERT INTO public.film (film_id, name, duration) VALUES ((select max(film_id) from film) + 1, substr(md5(random()::text), 0, 25), to_timestamp(random()*2147483647)::time)";
                break;
            case 3:
                sql = "INSERT INTO public.session (session_id, film_id, hall_id, time, price) VALUES ((select max(session_id) from session) + 1,(select film_id from film order by random() limit 1), (select hall_id from hall order by random() limit 1), to_timestamp(random()*2147483647)::time, FLOOR(RANDOM() * 100))";
                break;
            case 4:
                sql = "INSERT INTO public.hall (hall_id, name) VALUES ((select max(hall_id) from hall) + 1, substr(md5(random()::text), 0, 25))";
                break;
            case 5:
                sql = "INSERT INTO public.ticket (ticket_id, session_id, chair_id, isbought) VALUES ((select max(ticket_id) from ticket) + 1, (select session_id from session order by random() limit 1), (select chair_id from chair order by random() limit 1), random() > 0.5)";
                break;
            default:
                break;
        }

        for (int i = 0; i < parseInt(count); i++) {
            System.out.println(i);

            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        return "Об'єкти створені!";
    }

    public static String changeChair(String chair_id, String hall_id, String place, String row){
        try(Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            Chair chair = session.get(Chair.class, Integer.parseInt(chair_id));

            chair.setChair_id(Integer.parseInt(chair_id));
            chair.setHall_id(Integer.parseInt(hall_id));
            chair.setPlace(Integer.parseInt(place));
            chair.setRow(Integer.parseInt(row));

            session.update(chair);
            session.getTransaction().commit();
        } catch (Throwable ex)
        {
            return ex.getMessage();
        }

        return "Оновлено обьект з id = " + chair_id;
    }

    public static String changeFilm(String film_id, String name, String duration){
        try(Session session = HibernateUtil.getSession()) {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            session.beginTransaction();

            Film film = session.get(Film.class, Integer.parseInt(film_id));

            film.setFilm_id(Integer.parseInt(film_id));
            film.setName(name);
            film.setDuration(new Time(sdf.parse(duration).getTime()));

            session.update(film);
            session.getTransaction().commit();
        } catch (Throwable ex)
        {
            return ex.getMessage();
        }

        return "Оновлено обьект з id = " + film_id;
    }

    public static String changeSession(String session_id, String film_id, String hall_id, String time, String price){
        try(Session session = HibernateUtil.getSession()) {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            session.beginTransaction();

            Session1 session1 = session.get(Session1.class, Integer.parseInt(session_id));

            session1.setSession_id(Integer.parseInt(session_id));
            session1.setFilm_id(Integer.parseInt(film_id));
            session1.setHall_id(Integer.parseInt(hall_id));
            session1.setTime(new Time(sdf.parse(time).getTime()));
            session1.setPrice(Integer.parseInt(price));

            session.update(session1);
            session.getTransaction().commit();
        } catch (Throwable ex)
        {
            return ex.getMessage();
        }

        return "Оновлено обьект з id = " + session_id;
    }

    public static String changeHall(String hall_id, String name){
        try(Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            Hall hall = session.get(Hall.class, Integer.parseInt(hall_id));

            hall.setHall_id(Integer.parseInt(hall_id));
            hall.setName(name);

            session.update(hall);
            session.getTransaction().commit();
        } catch (Throwable ex)
        {
            return ex.getMessage();
        }

        return "Оновлено обьект з id = " + hall_id;
    }

    public static String changeTicket(String ticket_id, String session_id, String chair_id, String isbought){
        try(Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            Ticket ticket = session.get(Ticket.class, Integer.parseInt(ticket_id));

            ticket.setTicket_id(Integer.parseInt(ticket_id));
            ticket.setSession_id(Integer.parseInt(session_id));
            ticket.setChair_id(Integer.parseInt(chair_id));
            ticket.setIsbought(Boolean.parseBoolean(isbought));

            session.update(ticket);
            session.getTransaction().commit();
        } catch (Throwable ex)
        {
            return ex.getMessage();
        }

        return "Оновлено обьект з id = " + ticket_id;
    }

    public static String getInterface1(Connection connection, String time, String price) {
        if (!price.matches("\\d+")) {
            return "Введіть ціну!";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        try {
            Date Dateduration = sdf.parse(time);
        } catch (Exception ex) {
            return "Час заданий некроектно";
        }
        String sql = "Select f.name, s.time, s.price from public.film f, public.session s where f.film_id = s.film_id and";
        sql += " s.price < (?)";
        sql += " and s.time > (?)";

        System.out.println("sql: " + sql);
        String resultStr = "Результат пошуку по заданим параметрам:\n";

        long m = System.currentTimeMillis();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, parseInt(price));
            statement.setTime(2, Time.valueOf(time));

            try {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    resultStr += "-> Фільм '" + resultSet.getString(1) + "'";
                    resultStr += " на (" + resultSet.getTime(2) + "),";
                    resultStr += " ціна - " + resultSet.getInt(3);
                    resultStr += "\n";
                }
            } catch (Exception ex) {
                return ex.getMessage();
            }
        } catch (SQLException exception) {
            return exception.getMessage();
        }

        m = System.currentTimeMillis() - m;

        return (!resultStr.equals("Результат пошуку по заданим параметрам:\n") ? resultStr + "\nВитрачено " + m + " мілісекунд" : "Нічого не знайдено!");
    }

    public static String getInterface2(Connection connection, String film_name, String hall_name) {

        if (film_name.equals("") || hall_name.equals("")) {
            return "Не всі поля заповнення!";
        }

        String sql = "Select f.name, s.time, h.name from public.film f, public.session s, public.hall h where f.film_id = s.film_id and s.hall_id = h.hall_id and";
        sql += " f.name like ?";
        sql += " and h.name like ?";

        System.out.println("sql: " + sql);
        String resultStr = "Результат пошуку по заданим параметрам:\n";

        long m = System.currentTimeMillis();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, film_name);
            statement.setString(2, hall_name);

            try {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    resultStr += "-> Фільм '" + resultSet.getString(1) + "'";
                    resultStr += " на (" + resultSet.getTime(2) + "),";
                    resultStr += " в залі '" + resultSet.getString(3) + "'";
                    resultStr += "\n";
                }
            } catch (Exception ex) {
                return ex.getMessage();
            }
        } catch (SQLException exception) {
            return exception.getMessage();
        }

        m = System.currentTimeMillis() - m;

        return (!resultStr.equals("Результат пошуку по заданим параметрам:\n") ? resultStr + "\nВитрачено " + m + " мілісекунд" : "Нічого не знайдено!");
    }

    public static String getInterface3(Connection connection, String film_name, String time) {

        if (film_name.equals("") || time.equals("")) {
            return "Не всі поля заповнення!";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        try {
            Date Dateduration = sdf.parse(time);
        } catch (Exception ex) {
            return "Час заданий некроектно";
        }

        //SELECT COUNT(column_name) FROM table_name WHERE condition;
        String sql = "Select count(t.chair_id), f.name, s.time from public.ticket t, public.session s, public.film f where f.film_id = s.film_id and s.session_id = t.session_id and group by f.name, s.time";
        sql += " f.name like ?";
        sql += " and s.time < ?";
        sql += " GROUP BY f.name, s.time";

        System.out.println("sql: " + sql);
        String resultStr = "Результат пошуку по заданим параметрам:\n";

        long m = System.currentTimeMillis();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, film_name);
            statement.setTime(2, Time.valueOf(time));

            try {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    resultStr += "-> " + resultSet.getInt(1) + " місць на";
                    resultStr += " фільм '" + resultSet.getString(2) + "'";
                    resultStr += " час (" + resultSet.getTime(3) + ")";
                    resultStr += "\n";
                }
            } catch (Exception ex) {
                return ex.getMessage();
            }
        } catch (SQLException exception) {
            return exception.getMessage();
        }

        m = System.currentTimeMillis() - m;

        return (!resultStr.equals("Результат пошуку по заданим параметрам:\n") ? resultStr + "\nВитрачено " + m + " мілісекунд" : "Нічого не знайдено!");
    }

    public static String BTree(Connection connection) {
        String sql = "select count(*) from public.test_btree where id % 2 = 0";
        String result = sql + "\n";
        long m = System.currentTimeMillis();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    result += "-> " + resultSet.getInt(1) + "\n";
                }
            } catch (Exception ex) {
                return ex.getMessage();
            }
        } catch (SQLException exception) {
            return exception.getMessage();
        }
        m = System.currentTimeMillis() - m;
        result += "Time: " + m + " ms.\n\n";

        sql = "select count(*) from public.test_btree where id % 2 = 0 or test_text like 'a%'";
        result += sql + "\n";
        m = System.currentTimeMillis();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    result += "-> " + resultSet.getInt(1) + "\n";
                }
            } catch (Exception ex) {
                return ex.getMessage();
            }
        } catch (SQLException exception) {
            return exception.getMessage();
        }
        m = System.currentTimeMillis() - m;
        result += "Time: " + m + " ms.\n\n";

        sql = "select count(id), sum(id) from public.test_btree where test_text like '%x%' group by id % 4";
        result += sql + "\n";
        m = System.currentTimeMillis();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    result += "[count = " + resultSet.getInt(1) +"; sum = " + resultSet.getLong(2) +"]\n";
                }
            } catch (Exception ex) {
                return ex.getMessage();
            }
        } catch (SQLException exception) {
            return exception.getMessage();
        }
        m = System.currentTimeMillis() - m;
        result += "Time: " + m + " ms.\n\n";

        return result;
    }

    public static String GIN(Connection connection) {
        String sql = "select count(*) from public.test_gin where id % 2 = 0";
        String result = sql + "\n";
        long m = System.currentTimeMillis();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    result += "-> " + resultSet.getInt(1) + "\n";
                }
            } catch (Exception ex) {
                return ex.getMessage();
            }
        } catch (SQLException exception) {
            return exception.getMessage();
        }
        m = System.currentTimeMillis() - m;
        result += "Time: " + m + " ms.\n\n";

        sql = "select count(*) from public.test_gin where id % 2 = 0 or test_text[1] like '%f%'";
        result += sql + "\n";
        m = System.currentTimeMillis();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    result += "-> " + resultSet.getInt(1) + "\n";
                }
            } catch (Exception ex) {
                return ex.getMessage();
            }
        } catch (SQLException exception) {
            return exception.getMessage();
        }
        m = System.currentTimeMillis() - m;
        result += "Time: " + m + " ms.\n\n";

        sql = "select count(id), sum(id) from public.test_gin where test_text[2] like '%x%' group by id % 2";
        result += sql + "\n";
        m = System.currentTimeMillis();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    result += "[count = " + resultSet.getInt(1) +"; sum = " + resultSet.getLong(2) +"]\n";
                }
            } catch (Exception ex) {
                return ex.getMessage();
            }
        } catch (SQLException exception) {
            return exception.getMessage();
        }
        m = System.currentTimeMillis() - m;
        result += "Time: " + m + " ms.\n\n";

        return result;
    }
}

