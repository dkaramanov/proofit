package lv.proofit.insurance.service.risk;

public final class FireRisk extends RiskType {

    protected FireRisk() {
        addPremium(new Premium(Double.valueOf(0), Double.valueOf(0.014)));
        addPremium(new Premium(Double.valueOf(100), Double.valueOf(0.024)));
    }
}
