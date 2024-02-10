package eng.model.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private  String word;
    @Column(name = "wordTranslation")
    private  String wordMainTranslation ;
    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<WordTranslations> additionalTranslations;
    private String wordDetail;

    private boolean isRemembered;


    @OneToOne(mappedBy = "word", cascade = CascadeType.ALL)
    HardWord hardWord;
}
