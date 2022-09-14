package bussin.gaspricescraper.repository;

import bussin.gaspricescraper.model.GasPrice;
import bussin.gaspricescraper.model.GasPriceKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GasPriceRepository extends JpaRepository<GasPrice, GasPriceKey> {
//    @Query(value = "SELECT g.* FROM gas_price g INNER JOIN ( SELECT company, "
//            + "MAX(create_date) AS latest FROM gas_price GROUP BY company) AS "
//            + "grouped "
//            + "ON grouped.company = g.company AND grouped.latest = g"
//            + ".create_date;", nativeQuery = true)
//    List<GasPrice> findRecentPrices();
}
