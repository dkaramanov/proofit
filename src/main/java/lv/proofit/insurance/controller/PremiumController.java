package lv.proofit.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import lv.proofit.insurance.model.PrivatePropertyPolicy;
import lv.proofit.insurance.service.PremiumCalculator;
import lv.proofit.insurance.service.exception.RiskException;

@Slf4j
@RestController
@RequestMapping("/")
public class PremiumController {

    @Autowired
    private PremiumCalculator premiumCalculator;

    @PostMapping(path = "/calculatePremium")
    public ResponseEntity<Double> calculatePremium(@RequestBody(required = true) PrivatePropertyPolicy policy) {
        Double premium = premiumCalculator.calculate(policy);
        log.debug("Successfull premium calculation -> {}", premium);
        return ResponseEntity.ok(premium);
    }

    @ExceptionHandler(RiskException.class)
    public ResponseEntity<String> handleRiskException(RiskException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
