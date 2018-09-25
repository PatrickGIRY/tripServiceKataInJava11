package user;

import trip.Trip;

import java.lang.module.ModuleDescriptor;
import java.util.stream.Stream;

public class UserBuilder {
    private User[] friends;
    private Trip[] trips;

    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    private UserBuilder() {}

    public UserBuilder friendsWith(User ...friends) {
        this.friends = friends;
        return this;
    }

    public UserBuilder withTrips(Trip ...trips) {
        this.trips = trips;
        return this;
    }

    public User build() {
        User user = new User();
        addFriendsTo(user);
        addTripsTo(user);
        return user;
    }

    private void addTripsTo(User user) {
        Stream.of(trips).forEach(user::addTrip);
    }

    private void addFriendsTo(User user) {
        Stream.of(friends).forEach(user::addFriend);
    }
}
