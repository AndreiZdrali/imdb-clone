package org.example.utils.filters;

import org.example.production.Production;
import org.example.production.ProductionType;

public class ProductionTypeFilter<T extends Production> implements Filter<T> {
    private final ProductionType productionType;

    public ProductionTypeFilter(ProductionType productionType) {
        this.productionType = productionType;
    }

    public boolean check(Production object) {
        return object.getType() == productionType;
    }
}
