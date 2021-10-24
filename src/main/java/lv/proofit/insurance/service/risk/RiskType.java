package lv.proofit.insurance.service.risk;

import java.util.ArrayList;
import java.util.List;

public abstract class RiskType {

    public final static FireRisk  FIRE_RISK  = new FireRisk();

    public final static TheftRisk THEFT_RISK = new TheftRisk();

    protected final List<Premium> premiums   = new ArrayList<>();

    protected final void addPremium(Premium premium) {
        premiums.add(premium);
    }

    public final List<Premium> getPremiums() {
        return new ArrayList<>(premiums);
    }
}
