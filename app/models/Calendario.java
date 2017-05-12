package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.util.*;
/**
 * Created by Miguel on 08/05/2017.
 */
@Entity
public class Calendario extends Model {

    @Id
    public Integer id;
    public String title;
    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date start;
    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date end;
    public String color;
    public static Model.Finder<Integer,Calendario> find = new Model.Finder<>(Calendario.class);

}
