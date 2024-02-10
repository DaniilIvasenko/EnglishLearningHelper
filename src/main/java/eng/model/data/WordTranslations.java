package eng.model.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class WordTranslations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @ManyToOne()
    @JoinColumn(name = "word_id")
    private  Word word;
    private String translation;

    public WordTranslations(Word word, String translation) {
        this.word = word;
        this.translation = translation;
    }
}
