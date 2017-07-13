package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Miguel, Roger, William *
 * Model criado para tabela Pais.
 */
@Entity
public class Pais extends Model{
    @Id
    public Integer id;
    public String nome;
}
