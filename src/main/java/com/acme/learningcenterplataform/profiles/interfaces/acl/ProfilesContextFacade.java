package com.acme.learningcenterplataform.profiles.interfaces.acl;

import com.acme.learningcenterplataform.profiles.domain.model.commands.CreateProfileCommand;
import com.acme.learningcenterplataform.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.acme.learningcenterplataform.profiles.domain.model.queries.GetProfileByIdQuery;
import com.acme.learningcenterplataform.profiles.domain.model.valueobjects.EmailAddress;
import com.acme.learningcenterplataform.profiles.domain.services.ProfileCommandService;
import com.acme.learningcenterplataform.profiles.domain.services.ProfileQueryService;
import org.springframework.stereotype.Service;

@Service
public class ProfilesContextFacade {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfilesContextFacade(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    public Long createProfile(String firstName, String lastName, String email, String street, String number,
                              String city, String postalCode, String country) {
        var createProfileCommand = new CreateProfileCommand(firstName, lastName, email, street,
                                        number,city, postalCode, country);
        var profile = profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty()) return  0L;
        return profile.get().getId();
    }

    public Long fetchProfileIdByEmail(String email) {
        var getProfileByEmailQuery = new GetProfileByEmailQuery(new EmailAddress(email));
        var profile = profileQueryService.handle(getProfileByEmailQuery);
        if (profile.isEmpty() ) return  0L;
        return profile.get().getId();
    }
}
