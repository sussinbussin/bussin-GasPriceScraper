package bussin.gaspricescraper.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
public class GasPriceKey implements Serializable {
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createDate", nullable = false)
    @NotNull
    private Date createDate;

    @NotNull
    @Column(name = "type", nullable = false)
    private Type type;

    @NotNull
    @Size(min = 1)
    @Column(name = "company", nullable = false)
    private String company;

    /**
     * Creates a GasPriceKey with the current time.
     * @param type Type of petrol
     * @param company Company selling the petrol
     */
    public GasPriceKey(Type type, String company) {
        this.createDate = new Date(System.currentTimeMillis());
        this.type = type;
        this.company = company;
    }

    public enum Type {
        /**
         * Diesel fuel.
         */
        TypeDiesel,
        /**
         * 92 fuel.
         */
        Type92,
        /**
         * 95 fuel.
         */
        Type95,
        /**
         * 98 fuel.
         */
        Type98,
        /**
         * Premium fuel.
         */
        TypePremium
    }
}
