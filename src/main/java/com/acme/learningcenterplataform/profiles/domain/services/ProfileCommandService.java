package com.acme.learningcenterplataform.profiles.domain.services;

import com.acme.learningcenterplataform.profiles.domain.model.aggregates.Profile;
import com.acme.learningcenterplataform.profiles.domain.model.commands.CreateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
}
