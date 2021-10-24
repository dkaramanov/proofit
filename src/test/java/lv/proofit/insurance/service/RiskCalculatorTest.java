package lv.proofit.insurance.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import lv.proofit.insurance.model.Risk;
import lv.proofit.insurance.service.exception.RiskException;

public class RiskCalculatorTest {

    private final Double         ZERO_COEF    = Double.valueOf(0);

    private final Double         FIRE_COEF_1  = Double.valueOf(0.014);

    private final Double         FIRE_COEF_2  = Double.valueOf(0.024);

    private final Double         THEFT_COEF_1 = Double.valueOf(0.11);

    private final Double         THEFT_COEF_2 = Double.valueOf(0.05);

    private final RiskCalculator service      = new RiskCalculator();

    @Test
    public void checkFireRisk() {
        assertEquals(service.getRiskCoefficient(Double.valueOf(0), Risk.FIRE), ZERO_COEF);

        assertEquals(service.getRiskCoefficient(Double.valueOf(50), Risk.FIRE), FIRE_COEF_1);

        assertEquals(service.getRiskCoefficient(Double.valueOf(100), Risk.FIRE), FIRE_COEF_1);

        assertEquals(service.getRiskCoefficient(Double.valueOf(200), Risk.FIRE), FIRE_COEF_2);
    }

    @Test
    public void checkTheftRisk() {
        assertEquals(service.getRiskCoefficient(Double.valueOf(0), Risk.THEFT), ZERO_COEF);

        assertEquals(service.getRiskCoefficient(Double.valueOf(10), Risk.THEFT), THEFT_COEF_1);

        assertEquals(service.getRiskCoefficient(Double.valueOf(15), Risk.THEFT), THEFT_COEF_1);

        assertEquals(service.getRiskCoefficient(Double.valueOf(50), Risk.THEFT), THEFT_COEF_2);
    }

    @Test(expected = RiskException.class)
    public void checkNullRisk() {
        service.getRiskCoefficient(Double.valueOf(0), null);
    }

}
