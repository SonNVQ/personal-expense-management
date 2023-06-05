package Models;

import java.sql.Time;
import java.util.Date;
import java.util.List;
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
public class User {

    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Date dob;
    private Time created_time;
    private List<Role> roles;
    
}
