package user;

import trip.Trip;

import java.util.stream.Stream;

public class UserBuilder {
    private User user;

    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    private UserBuilder() {
        user = new User();
    }

    public UserBuilder friendsWith(User ...friends) {
        Stream.of(friends).forEach(user::addFriend);
        return this;
    }

    public UserBuilder withTrips(Trip ...trips) {
        Stream.of(trips).forEach(user::addTrip);
        return this;
    }

    public User build() {
        return user;
    }
}
