package models;

import com.avaje.ebean.Model;
import play.db.ebean.ModelsConfigLoader;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by Miguel, Roger, William on 03/05/2017.
 *
 * Clase para criação do banco de dados
 *
 * Tabela Cliente
 *
 * @Entity - para definir o tipo de clase;
 *
 * @Id - Id da tabela;
 *
 * @OneToOne - Um para Um;
 *
 * @JoinColumn - para fazer as ligações entre tabelas;
 *
 */

@Entity
public class Cliente extends Model {
    @Id
    public Integer id;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    public Usuario usuario_id;

}
