package project.laptop.model.dto.offerDTO;

public class SearchOfferDto {
    private String model;
    private Integer minPrice;
    private Integer maxPrice;

    public String getModel() {
        return model;
    }

    public SearchOfferDto setModel(String model) {
        this.model = model;
        return this;
    }



    public Integer getMinPrice() {
        return minPrice;
    }

    public SearchOfferDto setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public SearchOfferDto setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public boolean isEmpty() {
        return (model == null || model.trim().isEmpty()) &&
                minPrice == null &&
                maxPrice == null;
    }

    @Override
    public String toString() {
        return "SearchOfferDto{" +
                "model='" + model + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                '}';
    }


}
