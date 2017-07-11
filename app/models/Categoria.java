package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Miguel, Roger, William  on 29/04/2017.
 *
 * Model que cria a tabela categoria.
 */

@Entity
public class Categoria extends Model {

    @Id
    public Integer  id;

    public String descricao;
}
