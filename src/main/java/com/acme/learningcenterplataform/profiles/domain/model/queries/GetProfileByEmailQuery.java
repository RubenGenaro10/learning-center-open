package com.acme.learningcenterplataform.profiles.domain.model.queries;

import com.acme.learningcenterplataform.profiles.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}
