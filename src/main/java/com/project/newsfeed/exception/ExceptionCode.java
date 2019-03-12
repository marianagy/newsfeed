package com.project.newsfeed.exception;
    /**
     * Provides exception codes and description.
     */
    public enum ExceptionCode {

        //    Error codes are grouped by logic  -> 1XXX for user, 2xxx for permission, etc
        UNKNOWN_EXCEPTION(1000, "Unknown Exception"),
        USER_VALIDATION_EXCEPTION(1002, "Validation Exception"),
        EMAIL_EXISTS_ALREADY(1003, "Email already exists Exception"),
        EMAIL_NOT_FOUND(1004, "User is not registered with this email, or is not registered at all."),
        USERNAME_NOT_FOUND(1005, "Username is not found."),
        PASSWORD_NOT_VALID(1006, "Password not valid."),
        USER_DEACTIVATED(1007, "User deactivated"),
        TOKEN_EXPIRED(1008, "Token expired"),
        USER_PERMISSION_VALIDATION(2001, "User does not have this permission"),
        ROLE_DOESNT_EXIST(2002, "User does not have this permission"),
        PROFILE_VALIDATION_EXCEPTION(5001, "Validation Exception"),
        PROFILE_NOT_EXPORTED(7001, "Profile could not be exported to pdf."),
        NOTIFICATION_VALIDATION_EXCEPTION(7000, "This notification doesn't exist"),
        SEND_EMAIL_EXCEPTION(8000, "Exception appeared while sending email");
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

