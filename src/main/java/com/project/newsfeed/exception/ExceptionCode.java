package com.project.newsfeed.exception;
    /**
     * Provides exception codes and description.
     */
    public enum ExceptionCode {

        //    Error codes are grouped by logic  -> 1XXX for user, 2xxx for permission, etc TODO: Schimba nume in nume bune
        UNKNOWN_EXCEPTION(1000, "Unknown Exception"),
        USER_VALIDATION_EXCEPTION(1002, "Validation Exception"),
        EMAIL_EXISTS_ALREADY(1003, "Email already exists"),
        EMAIL_VALIDATION_EXCEPTION(1004, "Email validation Exception"),
        EMAIL_NOT_FOUND(1005, "User is not registered with this email, or is not registered at all."),
        USERNAME_NOT_FOUND(1006, "Username is not found."),
        PASSWORD_NOT_VALID(1007, "Password not valid."),
        USER_DEACTIVATED(1008, "User deactivated"),
        USER_WITH_ID_NOT_FOUND(1009, "User deactivated"),
        PASSWORDS_DO_NOT_MATCH(1010, "Passwords do not match"),
        FIELD_VALUE_IS_NULL(1011, "Field value is null"),
        USERNAME_ALREADY_EXISTS(1012, "Username exists"),
        ARTICLE_NOT_FOUND(4013, "Article not found"),
        TAG_NOT_FOUND(4014, "Tag not found"),
        CATEGORY_NOT_FOUND(4015, "Category not found"),
        COMMENT_NOT_FOUND(4016, "Comment not found"),
        UPVOTE_NOT_FOUND(4017, "Upvote not found"),
        ALREADY_UPVOTED(4018, "User already upvoted"),
        TAGLIKE_NOT_FOUND(4019, "TagLike not found"),
        USER_PERMISSION_VALIDATION(2001, "User does not have this permission"),
        ROLE_DOESNT_EXIST(2002, "User does not have this permission"),
        PROFILE_DOES_NOT_EXIST(5002, "Profile not found");
        int id;
        String message;

        ExceptionCode(int id, String message) {
            this.id = id;
            this.message = message;
        }

        public int getId() {
            return id;
        }


        public String getMessage() {
            return message;
        }


    }

