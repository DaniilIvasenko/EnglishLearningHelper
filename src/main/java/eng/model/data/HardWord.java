package eng.model.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * сложным словом считается:
 * 1. слово, которое впервые добавлено в БД
 * 2. слово, которое поле isRemembered = false
 * 3. слово, которое повторно было добавлено в БД
 */
@Entity
@Table(name = "hard_words")
@Getter
@Setter
public class HardWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne()
    @JoinColumn(name = "word_id")
    private Word word;

    private  int repeatCounter;



}
