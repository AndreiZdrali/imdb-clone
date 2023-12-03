package org.example.user;

public class UserFactory {
    public User getUser(AccountType userType) {
        User.Information information = new User.Information.InformationBuilder()
                .setCredentials(new Credentials("username", "password"))
                .setName("name")
                .setCountry("country")
                .setAge(18)
                .setGender("male")
                .setBirthDate("2000-01-01")
                .build();

        if (userType == AccountType.Regular) {
            return new Regular.RegularBuilder("username", 0, information, AccountType.Regular).build();
        }
        if (userType == AccountType.Contributor) {
            return new Contributor.ContributorBuilder("username", 0, information, AccountType.Contributor).build();
        }
        if (userType == AccountType.Admin) {
            return new Admin.AdminBuilder("username", 0, information, AccountType.Admin).build();
        }
        return null;
    }
}
