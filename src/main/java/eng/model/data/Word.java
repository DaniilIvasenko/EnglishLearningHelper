package eng.model.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Getter
@Setter
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private  String word;
    private  String wordTranslation ;
    private String wordDetail;

    private boolean isRemembered;
    @Temporal(TemporalType.DATE)
    private Date dateOfCreation;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "word")
    HardWord hardWord;

}
