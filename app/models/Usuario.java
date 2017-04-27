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

    @Constraints.Required
    public String email;

    @Constraints.Required
    public String senha;

    public static Find<Long,Usuario> find = new Find<Long,Usuario>(){};

    public static PagedList<Usuario> page(int page, int pageSize) {
        return find.findPagedList(page, pageSize);
    }
}
