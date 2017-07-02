package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Miguel, Roger, William on 03/05/2017.
 *
 * Clase para criação do banco de dados
 *
 * Tabela TipoPagamento
 */
@Entity
public class TipoPagamento extends Model{
    @Id
    public Integer id;
    public String tipo;
}
