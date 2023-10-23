package guru.springframework.spring6restmvc.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class Customer {

    private String firstName;
    private String lastName;
    private Integer id;

}
