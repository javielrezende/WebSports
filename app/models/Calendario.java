package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Created by Miguel on 08/05/2017.
 */
@Entity
public class Calendario extends Model {

    @Id
    public Integer id;
    public String title;
    @Formats.DateTime(pattern = "yyyy-MM-dd HH:mm")
    public Date start;
    @Formats.DateTime(pattern = "yyyy-MM-dd HH:mm")
    public Date end;
    public String color;
    public static Model.Finder<Integer,Calendario> find = new Model.Finder<>(Calendario.class);


    public void setStart(String start) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        start = start.replace('T', ' ');
        formatter.setLenient(false);

        try {
            this.start = formatter.parse(start);
        } catch (ParseException e) {
            System.out.println(e);
        }
    }

    public void setEnd(String end) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        end = end.replace('T', ' ');
        formatter.setLenient(false);
        try {
            this.end = formatter.parse(end);
        } catch (ParseException e) {
            System.out.println(e);
        }
    }

}
