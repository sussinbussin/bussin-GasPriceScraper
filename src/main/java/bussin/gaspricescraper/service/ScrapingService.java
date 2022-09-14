package bussin.gaspricescraper.service;

import bussin.gaspricescraper.model.GasPrice;
import bussin.gaspricescraper.model.GasPriceKey;
import bussin.gaspricescraper.repository.GasPriceRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class ScrapingService {
    private GasPriceRepository gasPriceRepository;

    /**
     * Brands listed on the website.
     */
    private static final String[] BRANDS = {"Esso", "Shell", "SPC", "Caltex",
            "Sinopec"};

    public ScrapingService(GasPriceRepository gasPriceRepository) {
        this.gasPriceRepository = gasPriceRepository;
    }

    /**
     * Scrapes https://www.motorist.sg/petrol-prices.
     * @throws IOException
     */
    @EventListener(ApplicationReadyEvent.class)
    private void scrape() throws IOException {
        Document doc = Jsoup
                .connect("https://www.motorist.sg/petrol-prices").get();

        Elements rows = doc
                .getElementsByClass("fuel_comparison_table").get(0)
                .selectXpath("tbody").select(".text-center");

        for (Element row : rows) {
            Elements tds =
                    row.getElementsByTag("tr").select("td");
            String type = tds.first().html();

            for (int i = 1; i < tds.size(); i++) {
                String price = tds.get(i).html().substring(1);
                if (!price.isEmpty()) {
                    gasPriceRepository.save(new GasPrice(new GasPriceKey(
                            GasPriceKey.Type.valueOf("Type" + type),
                            BRANDS[i - 1]),
                            new BigDecimal(price)));
                }
            }
        }
    }
}
