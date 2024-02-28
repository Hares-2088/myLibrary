package org.bessam.mylocallibraryws.common;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
@AllArgsConstructor
public class MemberIdentifier {
    private String memberId;

    public MemberIdentifier() {
        this.memberId = UUID.randomUUID().toString();
    }
}
