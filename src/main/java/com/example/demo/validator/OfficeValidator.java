package com.example.demo.validator;
import com.example.demo.model.Office;
import com.example.demo.validator.components.OfficeCordValidator;
import com.example.demo.validator.components.OfficeIdValidator;
import com.example.demo.validator.components.OfficeNameValidator;
import org.springframework.stereotype.Component;

@Component
public class OfficeValidator {
    private final OfficeNameValidator officeNameValidator;
    private final OfficeCordValidator officeCordValidator;
    private final OfficeIdValidator officeIdValidator;

    public OfficeValidator(OfficeNameValidator officeNameValidator, OfficeCordValidator officeCordValidator, OfficeIdValidator officeIdValidator) {
        this.officeNameValidator = officeNameValidator;
        this.officeCordValidator = officeCordValidator;
        this.officeIdValidator = officeIdValidator;
    }

    public void validate(Office office) {
        officeNameValidator.validate(office.getName());
        officeCordValidator.validate(office.getCordX());
        officeCordValidator.validate(office.getCordY());
    }
    public void validateToPatch(Office office) {
        officeNameValidator.validate(office.getName());
        officeCordValidator.validate(office.getCordX());
        officeCordValidator.validate(office.getCordY());
        officeIdValidator.validate(office.getId());
    }
}
