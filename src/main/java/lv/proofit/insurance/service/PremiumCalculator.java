package lv.proofit.insurance.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.proofit.insurance.model.PrivatePropertyPolicy;
import lv.proofit.insurance.model.Risk;

@Service
public class PremiumCalculator {

    private final RiskCalculator riskService;

    @Autowired
    public PremiumCalculator(RiskCalculator riskService) {
        this.riskService = riskService;
    }

    public double calculate(PrivatePropertyPolicy policy) {
        Map<Risk, BigDecimal> map = calculateAmountByRisk(policy);

        BigDecimal premium = new BigDecimal(0);
        for (Risk risk : map.keySet()) {
            BigDecimal amount = map.get(risk);
            double coefficient = riskService.getRiskCoefficient(amount.doubleValue(), risk);

            BigDecimal premiumForRisk = amount.multiply(new BigDecimal(coefficient));
            premium = premium.add(premiumForRisk);
        }

        return premium.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private Map<Risk, BigDecimal> calculateAmountByRisk(PrivatePropertyPolicy policy) {
        Map<Risk, BigDecimal> map = new HashMap<>();

        if (policy.getInsuranceObjects() != null) {
            policy.getInsuranceObjects().stream().flatMap(insurance -> insurance.getInsuranceSubObjects().stream()).collect(Collectors.toList()).forEach(
                    subInsurance -> {
                        BigDecimal amount;
                        if (map.containsKey(subInsurance.getRisk())) {
                            amount = map.get(subInsurance.getRisk());
                        } else {
                            amount = new BigDecimal(0);
                        }

                        amount = amount.add(new BigDecimal(subInsurance.getSum()));
                        map.put(subInsurance.getRisk(), amount);
                    });
        }

        return map;
    }
}
