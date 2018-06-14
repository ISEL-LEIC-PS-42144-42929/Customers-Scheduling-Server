package com.customersscheduling.HALObjects;

import com.customersscheduling.Domain.Portfolio;
import org.springframework.hateoas.ResourceSupport;

public class PortfolioResource extends ResourceSupport {

    private Portfolio portfolio;

    public PortfolioResource(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
}
