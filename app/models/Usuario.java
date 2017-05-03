package models;
import com.avaje.ebean.*;

import play.data.validation.Constraints;

import javax.persistence.*;

/**
 * Created by Miguel on 17/04/2017.
 */

@Entity
public class Usuario extends Model {
    @Id
    public Long id;
    public String nome;
    public String senha;
    public String email;
    public String cpf;
    public int endereco_id;



}
