package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

import javax.persistence.Entity;
import javax.persistence.Id;
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
        // Cria um padrão para formatar uma String em data
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        // Nas Strings recebidas pelo FullCalendar vem no formato "yyyy-MM-dd 'T' HH:mm"
        // Aqui só estou retirando o 'T' da String
        start = start.replace('T', ' ');

        // Quando for realizar o parse uma excessão precisa se tratada
        // Por isso do try catch
        try {
            this.start = formatter.parse(start);
        } catch (ParseException e) {
            System.out.println(e);
        }
    }

    public void setEnd(String end) {
        // Mesma coisa do de cima, isso podia virar um método né

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        end = end.replace('T', ' ');
        try {
            this.end = formatter.parse(end);
        } catch (ParseException e) {
            System.out.println(e);
        }
    }

}
