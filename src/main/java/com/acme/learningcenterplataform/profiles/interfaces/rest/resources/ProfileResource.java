package com.acme.learningcenterplataform.profiles.interfaces.rest.resources;

public record ProfileResource(Long id,
                              String fullName,
                              String email,
                              String streetAddress) {
}
