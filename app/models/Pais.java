package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Miguel, Roger, William on 03/05/2017.
 *
 * Model criado para tabela Pais.
 */
@Entity
public class Pais extends Model{
    @Id
    public Integer id;
    public String nome;
}
