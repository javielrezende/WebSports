package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Miguel, Roger, William on 03/05/2017.
 *
 * Clase para criação do banco de dados
 *
 * Tabela Copa
 *
 * Entity - para definir o tipo de clase;
 *
 * Id - Id da tabela;
 *
 */

@Entity
public class Copa extends Model{

    @Id
    public Integer id;


}
