package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Miguel, Roger, William *
 * Model para tabela de TipoPagamento.
 */
@Entity
public class TipoPagamento extends Model{
    @Id
    public Integer id;
    public String tipo;
}
