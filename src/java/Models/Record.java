package Models;

import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author nguyenson
 */
@Data
@AllArgsConstructor
@Builder
public class Record {

    private int id;
    private String name;
    private String type;
    private Time time;
    private String description;
    
}
