package bussin.gaspricescraper.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GasPrice {
    @EmbeddedId
    private GasPriceKey gasPriceKey;

    @DecimalMin(value = "0", inclusive = false,
            message = "Price must be positive")
    @Column(name = "Price", nullable = false)
    @NotNull
    private BigDecimal price;
}
