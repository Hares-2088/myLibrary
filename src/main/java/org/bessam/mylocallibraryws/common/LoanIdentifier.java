package org.bessam.mylocallibraryws.common;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
@AllArgsConstructor
public class LoanIdentifier {
    private String loanId;

    public LoanIdentifier() {
        this.loanId = UUID.randomUUID().toString();
    }
}
