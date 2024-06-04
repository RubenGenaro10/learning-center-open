package com.acme.learningcenterplataform.learning.domain.model.commands;

public record UpdateCourseCommand(Long id, String title, String description) {
}
