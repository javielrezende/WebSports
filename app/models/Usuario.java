package models;
import com.avaje.ebean.*;
import org.hibernate.validator.constraints.br.CPF;


import javax.persistence.*;

/**
 * Created by Miguel on 17/04/2017.
 */

@Entity
public class Usuario extends Model {
    @Id
    public Integer id;
    public String nome;
    public String senha;
    public String email;
    @CPF
    public String cpf;
    @ManyToOne
    @JoinColumn(name="endereco_id")
    public Endereco endereco_id;

    public static Finder<Integer,Usuario> find = new Finder<>(Usuario.class);

    public static PagedList<Usuario> page(int page, int pageSize) {
        return find.findPagedList(page, pageSize);
    }

    public void setEndereco_id(int endereco_id) {
        this.endereco_id = Endereco.find.byId(endereco_id);
    }
}
