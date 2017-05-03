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
    @ManyToOne
    @JoinColumn(name="endereco_id")
    public Endereco endereco_id;



    public static Find<Long,Usuario> find = new Find<Long,Usuario>(){};

    public static PagedList<Usuario> page(int page, int pageSize) {
        return find.findPagedList(page, pageSize);
    }

}
