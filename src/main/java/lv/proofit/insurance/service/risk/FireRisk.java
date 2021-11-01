package lv.proofit.insurance.service.risk;

public final class FireRisk extends RiskType {

    protected FireRisk() {
        addPremium(new Premium(0.0, 0.014));
        addPremium(new Premium(100.0, 0.024));
    }
}
