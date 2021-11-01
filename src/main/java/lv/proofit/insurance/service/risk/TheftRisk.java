package lv.proofit.insurance.service.risk;

public final class TheftRisk extends RiskType {

    protected TheftRisk() {
        addPremium(new Premium(0.0, 0.11));
        addPremium(new Premium(15.0, 0.05));
    }
}
