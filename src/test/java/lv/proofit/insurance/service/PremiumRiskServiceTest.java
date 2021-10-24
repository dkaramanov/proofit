package lv.proofit.insurance.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import lv.proofit.insurance.model.InsuranceObject;
import lv.proofit.insurance.model.InsuranceSubObject;
import lv.proofit.insurance.model.PolicyStatus;
import lv.proofit.insurance.model.PrivatePropertyPolicy;
import lv.proofit.insurance.model.Risk;

public class PremiumRiskServiceTest {

    private final PremiumCalculator service   = new PremiumCalculator(new RiskCalculator());

    private final Double            PREMIUM_1 = Double.valueOf(2.28);

    private final Double            PREMIUM_2 = Double.valueOf(17.13);

    @Test
    public void checkPolicyRiskSingleInsurance() {
        InsuranceSubObject sub1 = new InsuranceSubObject("sub1", 100.0, Risk.FIRE);
        InsuranceSubObject sub2 = new InsuranceSubObject("sub2", 8.0, Risk.THEFT);

        List<InsuranceSubObject> list = new ArrayList<>();
        list.add(sub1);
        list.add(sub2);

        InsuranceObject insurance = new InsuranceObject("Insurance 0", list);

        List<InsuranceObject> iList = new ArrayList<>();
        iList.add(insurance);

        PrivatePropertyPolicy policy = new PrivatePropertyPolicy("12-12", PolicyStatus.APPROVED, iList);

        assertEquals(service.calculate(policy), PREMIUM_1);
    }

    @Test
    public void checkPolicyRiskMultipleInsurances() {
        InsuranceSubObject sub1 = new InsuranceSubObject("sub1", 500.0, Risk.FIRE);
        InsuranceSubObject sub2 = new InsuranceSubObject("sub2", 102.51, Risk.THEFT);

        List<InsuranceSubObject> list1 = new ArrayList<>();
        list1.add(sub1);
        InsuranceObject insurance1 = new InsuranceObject("Insurance 1", list1);

        List<InsuranceSubObject> list2 = new ArrayList<>();
        list1.add(sub2);
        InsuranceObject insurance2 = new InsuranceObject("Insurance 2", list2);

        List<InsuranceObject> iList = new ArrayList<>();
        iList.add(insurance1);
        iList.add(insurance2);

        PrivatePropertyPolicy policy = new PrivatePropertyPolicy("12-12", PolicyStatus.APPROVED, iList);

        assertEquals(service.calculate(policy), PREMIUM_2);
    }

    @Test
    public void checkPolicyRiskMultipleRiskTypesInsurances() {
        InsuranceSubObject sub1 = new InsuranceSubObject("sub1", 300.0, Risk.FIRE);
        InsuranceSubObject sub2 = new InsuranceSubObject("sub2", 100.0, Risk.FIRE);
        InsuranceSubObject sub3 = new InsuranceSubObject("sub3", 100.0, Risk.FIRE);
        InsuranceSubObject sub4 = new InsuranceSubObject("sub4", 100.0, Risk.THEFT);
        InsuranceSubObject sub5 = new InsuranceSubObject("sub5", 2.51, Risk.THEFT);

        List<InsuranceSubObject> list1 = new ArrayList<>();
        list1.add(sub1);
        list1.add(sub4);
        InsuranceObject insurance1 = new InsuranceObject("Insurance 3", list1);

        List<InsuranceSubObject> list2 = new ArrayList<>();
        list1.add(sub2);
        list1.add(sub3);
        list1.add(sub5);
        InsuranceObject insurance2 = new InsuranceObject("Insurance 4", list2);

        List<InsuranceObject> iList = new ArrayList<>();
        iList.add(insurance1);
        iList.add(insurance2);

        PrivatePropertyPolicy policy = new PrivatePropertyPolicy("12-12", PolicyStatus.APPROVED, iList);

        assertEquals(service.calculate(policy), PREMIUM_2);
    }

}
