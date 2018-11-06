package trip;


import exception.UserNotLoggedInException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TripServiceShould {

    private static final User GUEST = null;
    private static final User UNUSED_USER = null;
    private static final Trip TO_LONDON = new Trip();
    private static final User REGISTERED_USER = new User();
    private static final Trip TO_ITALY = new Trip();

    private User loggedUser;

    private TripService tripService;

    @BeforeEach
    void setUp() {
        loggedUser = REGISTERED_USER;
        tripService = new TestableTripService();
    }

    @Test
    void throw_an_exception_when_the_user_is_not_logged_in() {
        loggedUser = GUEST;
        assertThatThrownBy(() -> tripService.getTripsByUser(UNUSED_USER))
                .isInstanceOf(UserNotLoggedInException.class);
    }

    @Test
    void not_return_any_trip_when_users_are_not_friends() {

        User friend = new User();
        friend.addTrip(TO_LONDON);

        assertThat(tripService.getTripsByUser(friend)).isEmpty();
    }

    @Test
    void return_trips_when_users_are_friends() {

        User friend = new User();
        friend.addFriend(loggedUser);
        friend.addTrip(TO_LONDON);
        friend.addTrip(TO_ITALY);

        assertThat(tripService.getTripsByUser(friend))
                .containsOnly(TO_LONDON, TO_ITALY);
    }

    private class TestableTripService extends TripService {
        @Override
        User getLoggerUser() {
            return loggedUser;
        }

        @Override
        List<Trip> findTripsBy(User user) {
            return user.trips();
        }
    }
}