package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Miguel, Roger, William  on 01/05/2017.
 *
 * Model que cria a tabela copa.
 */

@Entity
public class Copa extends Model{

    @Id
    public Integer id;


}
