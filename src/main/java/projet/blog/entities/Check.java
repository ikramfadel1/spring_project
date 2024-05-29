package projet.blog.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CHECK")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Check extends Payment {
    private String bankName;
    private String bankId;
}

