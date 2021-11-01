package lv.proofit.insurance.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import lv.proofit.insurance.model.Risk;
import lv.proofit.insurance.service.exception.RiskException;

public class RiskCalculatorTest {

    private final double         ZERO_COEF    = 0;

    private final double         FIRE_COEF_1  = 0.014;

    private final double         FIRE_COEF_2  = 0.024;

    private final double         THEFT_COEF_1 = 0.11;

    private final double         THEFT_COEF_2 = 0.05;

    private final RiskCalculator service      = new RiskCalculator();

    @Test
    public void checkFireRisk() {
        assertTrue(service.getRiskCoefficient(0, Risk.FIRE) == ZERO_COEF);

        assertTrue(service.getRiskCoefficient(50, Risk.FIRE) == FIRE_COEF_1);

        assertTrue(service.getRiskCoefficient(100, Risk.FIRE) == FIRE_COEF_1);

        assertTrue(service.getRiskCoefficient(200, Risk.FIRE) == FIRE_COEF_2);
    }

    @Test
    public void checkTheftRisk() {
        assertTrue(service.getRiskCoefficient(0, Risk.THEFT) == ZERO_COEF);

        assertTrue(service.getRiskCoefficient(10, Risk.THEFT) == THEFT_COEF_1);

        assertTrue(service.getRiskCoefficient(15, Risk.THEFT) == THEFT_COEF_1);

        assertTrue(service.getRiskCoefficient(50, Risk.THEFT) == THEFT_COEF_2);
    }

    @Test(expected = RiskException.class)
    public void checkNullRisk() {
        service.getRiskCoefficient(0, null);
    }

}
