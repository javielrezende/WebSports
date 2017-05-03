package models;

import com.avaje.ebean.Model;
import play.db.ebean.ModelsConfigLoader;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by William on 03/05/2017.
 */
@Entity
public class Cliente extends Model {
    @Id
    public Long id;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    public Usuario usuario_id;

}
