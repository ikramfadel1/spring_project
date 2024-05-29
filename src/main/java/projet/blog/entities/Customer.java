package projet.blog.entities;



import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import projet.blog.enums.CustomerState;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotBlank(message = "ERROR FIRSTNAME")
    private String firstName;
    private String LastName;
    @NotBlank(message = "ERROR EMAIL")
    @Email
    private String email;
    private String address;
    private CustomerState customerState = CustomerState.New;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Post1> post1s = new ArrayList<>();

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public @NotBlank(message = "ERROR FIRSTNAME") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "ERROR FIRSTNAME") String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public @NotBlank(message = "ERROR EMAIL") @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "ERROR EMAIL") @Email String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CustomerState getCustomerState() {
        return customerState;
    }

    public void setCustomerState(CustomerState customerState) {
        this.customerState = customerState;
    }

    public List<Post1> getPost1s() {
        return post1s;
    }

    public void setPost1s(List<Post1> post1s) {
        this.post1s = post1s;
    }
}
