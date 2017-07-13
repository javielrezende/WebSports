package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Miguel, Roger, William *
 * Model que cria a tabela copa.
 */

@Entity
public class Copa extends Model{

    @Id
    public Integer id;


}
