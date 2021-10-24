package lv.proofit.insurance.service.risk;

public final class TheftRisk extends RiskType {

    protected TheftRisk() {
        addPremium(new Premium(Double.valueOf(0), Double.valueOf(0.11)));
        addPremium(new Premium(Double.valueOf(15), Double.valueOf(0.05)));
    }
}
