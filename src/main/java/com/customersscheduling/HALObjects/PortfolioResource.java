package com.customersscheduling.HALObjects;

import com.customersscheduling.Domain.Portfolio;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class PortfolioResource extends ResourceSupport {

    private Portfolio portfolio;

    public PortfolioResource(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public List<Link> getLinks(Link l) {
        add(l);
        return super.getLinks();
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
}
