package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by William on 01/05/2017.
 */

@Entity
public class Copa extends Model{

    @Id
    public Integer id;


}
