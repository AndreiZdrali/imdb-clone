package org.example.user;

import org.example.utils.UserInformationHelper;

public class UserFactory {
    public User<?> getUser(AccountType userType, User.Information information) {
        String username = UserInformationHelper.generateUsername(information.getName());

        if (userType == AccountType.Regular) {
            return new Regular.RegularBuilder(username, 0, information, AccountType.Regular).build();
        }
        if (userType == AccountType.Contributor) {
            return new Contributor.ContributorBuilder(username, 0, information, AccountType.Contributor).build();
        }
        if (userType == AccountType.Admin) {
            return new Admin.AdminBuilder(username, 0, information, AccountType.Admin).build();
        }
        return null;
    }
}
