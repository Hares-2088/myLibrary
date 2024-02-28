package org.bessam.mylocallibraryws.common;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
public class AuthorIdentifier {
    private String authorId;

    public AuthorIdentifier() {
        this.authorId = java.util.UUID.randomUUID().toString();
    }
}
