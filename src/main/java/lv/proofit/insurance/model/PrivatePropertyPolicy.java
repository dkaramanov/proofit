package lv.proofit.insurance.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrivatePropertyPolicy {

    private String                number;

    private PolicyStatus          status;

    private List<InsuranceObject> insuranceObjects;
}
