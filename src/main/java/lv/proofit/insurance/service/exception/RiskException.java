package lv.proofit.insurance.service.exception;

public class RiskException extends RuntimeException {

    public RiskException(String error) {
        super(error);
    }
    
    private static final long serialVersionUID = 1L;

}
