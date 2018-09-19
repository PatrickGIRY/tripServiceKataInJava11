package trip;

import user.User;

import java.util.stream.Stream;

class UserBuilder {
    private User[] friends = new User[] {};
    private Trip[] trips = new Trip[] {};

    static UserBuilder aUser() {
        return new UserBuilder();
    }

    private UserBuilder() {
    }

    UserBuilder friendsWith(User... friends) {
        this.friends = friends;
        return this;
    }

    UserBuilder withTrips(Trip... trips) {
        this.trips = trips;
        return this;
    }

    User build() {
        var user = new User();
        addTripsTo(user);
        addFriendsTo(user);
        return user;
    }

    private void addTripsTo(User user) {
        Stream.of(trips).forEach(user::addTrip);
    }

    private void addFriendsTo(User user) {
        Stream.of(friends).forEach(user::addFriend);
    }
}
