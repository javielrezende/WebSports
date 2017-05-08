package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.*;
/**
 * Created by Miguel on 08/05/2017.
 */
@Entity
public class Calendario {

    @Id
    public Integer id;
    public String title;
    public Calendar start;
    public Calendar end;
    public String color;
    public static Model.Finder<Integer,Calendario> find = new Model.Finder<>(Calendario.class);

}
