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

    public Double getRiskCoefficient(Double amount, Risk risk) {
        Double coefficient = Double.valueOf(0);

        Double lastAmount = null;
        RiskType riskType = convert(risk);
        for (Premium premium : riskType.getPremiums()) {
            if (amount.compareTo(premium.getAmount()) > 0 && (lastAmount == null || premium.getAmount().compareTo(lastAmount) > 0)) {
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
