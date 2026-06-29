package com.todo.app.validators;

import java.net.URI;
import java.net.URISyntaxException;

import com.todo.app.exceptions.InvalidTodoContentException;
import com.todo.app.models.enums.MediaType;

public class TodoContentValidator {

    private static final int MAX_TEXT_LENGTH = 20_000;
    private static final String CLOUDINARY_HOST = "res.cloudinary.com";
    private static final String CLOUDINARY_CLOUD_NAME = "dueadaib7";

    public static void validate(MediaType mediaType, String media) {

        switch (mediaType) {

            case TEXT -> validateText(media);

            case IMAGE -> validateCloudinaryUrl(
                    media,
                    "/image/upload/");

            case VIDEO -> validateCloudinaryUrl(
                    media,
                    "/video/upload/");

            case AUDIO -> validateCloudinaryUrl(
                    media,
                    "/video/upload/");
        }
    }

    private static void validateText(String media) {

        if (media == null || media.isBlank()) {
            throw new InvalidTodoContentException(
                    "Text media cannot be empty.");
        }

        if (media.length() > MAX_TEXT_LENGTH) {
            throw new InvalidTodoContentException(
                    "Text media cannot exceed 20,000 characters.");
        }
    }

    private static void validateCloudinaryUrl(
            String url,
            String expectedPath) {

        URI uri;

        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new InvalidTodoContentException(
                    "Invalid URL format.");
        }

        if (!"https".equalsIgnoreCase(uri.getScheme())) {
            throw new InvalidTodoContentException(
                    "Only HTTPS URLs are allowed.");
        }

        if (!CLOUDINARY_HOST.equalsIgnoreCase(uri.getHost())) {
            throw new InvalidTodoContentException(
                    "Media must come from Cloudinary.");
        }

        if (!uri.getPath().contains(expectedPath)) {
            throw new InvalidTodoContentException(
                    "The media type does not match the selected content type.");
        }

        if (!uri.getPath().startsWith("/" + CLOUDINARY_CLOUD_NAME + "/")) {
            throw new InvalidTodoContentException(
                    "Invalid Cloudinary account.");
        }
    }
}