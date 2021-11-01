package lv.proofit.insurance.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import lv.proofit.insurance.model.Risk;
import lv.proofit.insurance.service.exception.RiskException;
import lv.proofit.insurance.service.risk.Premium;
import lv.proofit.insurance.service.risk.RiskType;

@Service
public class RiskCalculator {

    private final Map<Risk, RiskType> riskCalculators = new HashMap<>();

    public RiskCalculator() {
        riskCalculators.put(Risk.FIRE, RiskType.FIRE_RISK);
        riskCalculators.put(Risk.THEFT, RiskType.THEFT_RISK);
    }

    public double getRiskCoefficient(double amount, Risk risk) {
        double coefficient = 0;
        double lastAmount = 0;
        
        RiskType riskType = convert(risk);
        for (Premium premium : riskType.getPremiums()) {
            if (amount > premium.getAmount() && (lastAmount == 0 || premium.getAmount() > lastAmount)) {
                lastAmount = premium.getAmount();
                coefficient = premium.getCoefficient();
            }
        }

        return coefficient;
    }

    private RiskType convert(Risk risk) throws RiskException {
        if (riskCalculators.containsKey(risk)) {
            return riskCalculators.get(risk);
        }
        throw new RiskException("Not supported risk type ::: " + risk);
    }
}
