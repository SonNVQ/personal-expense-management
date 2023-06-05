package Models;

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
public class Category {

    private int id;
    private String name;
    private String description;
}
