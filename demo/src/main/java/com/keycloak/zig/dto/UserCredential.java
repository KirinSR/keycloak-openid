package com.keycloak.zig.dto;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.NonNull;

public class UserCredential implements Serializable {
    private static final long serialVersionUID = 2345L;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("isForceChange")
    private Boolean isForceChange = false;
    @JsonProperty("password")
    private @NonNull String password;
    @JsonProperty("realm")
    private String realm;
    @JsonProperty("otp")
    private String otp;

    public static UserCredentialBuilder builder() {
        return new UserCredentialBuilder();
    }

    @JsonProperty("userName")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("isForceChange")
    public void setIsForceChange(Boolean isForceChange) {
        this.isForceChange = isForceChange;
    }

    @JsonProperty("password")
    public void setPassword(@NonNull String password) {
        if (password == null) {
            throw new NullPointerException("password is marked non-null but is null");
        } else {
            this.password = password;
        }
    }

    @JsonProperty("realm")
    public void setRealm(String realm) {
        this.realm = realm;
    }

    @JsonProperty("otp")
    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getEmail() {
        return this.email;
    }

    public Boolean getIsForceChange() {
        return this.isForceChange;
    }

    public @NonNull String getPassword() {
        return this.password;
    }

    public String getRealm() {
        return this.realm;
    }

    public String getOtp() {
        return this.otp;
    }

    public UserCredential(String userName, String email, Boolean isForceChange, @NonNull String password, String realm, String otp) {
        if (password == null) {
            throw new NullPointerException("password is marked non-null but is null");
        } else {
            this.userName = userName;
            this.email = email;
            this.isForceChange = isForceChange;
            this.password = password;
            this.realm = realm;
            this.otp = otp;
        }
    }

    public UserCredential() {
    }

    public static class UserCredentialBuilder {
        private String userName;
        private String email;
        private Boolean isForceChange;
        private String password;
        private String realm;
        private String otp;

        UserCredentialBuilder() {
        }

        @JsonProperty("userName")
        public UserCredentialBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        @JsonProperty("email")
        public UserCredentialBuilder email(String email) {
            this.email = email;
            return this;
        }

        @JsonProperty("isForceChange")
        public UserCredentialBuilder isForceChange(Boolean isForceChange) {
            this.isForceChange = isForceChange;
            return this;
        }

        @JsonProperty("password")
        public UserCredentialBuilder password(@NonNull String password) {
            if (password == null) {
                throw new NullPointerException("password is marked non-null but is null");
            } else {
                this.password = password;
                return this;
            }
        }

        @JsonProperty("realm")
        public UserCredentialBuilder realm(String realm) {
            this.realm = realm;
            return this;
        }

        @JsonProperty("otp")
        public UserCredentialBuilder otp(String otp) {
            this.otp = otp;
            return this;
        }

        public UserCredential build() {
            return new UserCredential(this.userName, this.email, this.isForceChange, this.password, this.realm, this.otp);
        }

        public String toString() {
            return "UserCredential.UserCredentialBuilder(userName=" + this.userName + ", email=" + this.email + ", isForceChange=" + this.isForceChange + ", password=" + this.password + ", realm=" + this.realm + ", otp=" + this.otp + ")";
        }
    }
}
