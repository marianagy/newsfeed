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
        SKILL_VALIDATION_EXCEPTION(3000, "Skill Validation Exception"),
        SKILL_NOT_FOUND(3001, "Skill does not exist"),
        SKILLAREA_VALIDATION_EXCEPTION(4000, "Skill area is not valid"),
        SKILL_SKILLAREA_VALIDATION_EXCEPTION(40001, "Skill area is not defined and can't be used for this skill"),
        SKILLAREA_OR_SKILL_VALIDATION_EXCEPTION(4002, "Skill or Skillarea does not exist."),
        SKILL_IN_SKILLAREA_VALIDATION_EXCEPTION(4003, "Skill already exists in Skillarea."),
        SKILL_NOT_IN_SKILLAREA_VALIDATION_EXCEPTION(4004, "Skill does not exist in Skillarea, can't be removed."),
        PROFILE_VALIDATION_EXCEPTION(5001, "Validation Exception"),
        PROJEKT_VALIDATION_EXCEPTION(6001, "Projekt validation exception"),
        NAME_NOT_FOUND(6002, "A projekt with this name does not exist."),
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

