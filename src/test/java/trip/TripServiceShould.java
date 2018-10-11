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
    private static final User ANOTHER_USER = new User();
    private static final Trip TO_ITALY = new Trip();
    private static final User REGISTRED_USER = new User();
    private static final Trip TO_LONDON = new Trip();

    private User loggerUser;

    @BeforeEach
    void setUp() {
        loggerUser = REGISTRED_USER;
    }

    @Test
    void throw_an_exception_when_user_is_not_logged_in() {
        TripService tripService = new TestableTripService();
        loggerUser = GUEST;
        assertThatThrownBy(() -> tripService.getTripsByUser(UNUSED_USER))
                .isInstanceOf(UserNotLoggedInException.class);
    }

    @Test
    void not_return_any_trips_when_users_are_not_friends() {
        TripService tripService = new TestableTripService();

        User friend = new User();
        friend.addFriend(ANOTHER_USER);
        friend.addTrip(TO_ITALY);

        List<Trip> trips = tripService.getTripsByUser(friend);

        assertThat(trips).isEmpty();
    }

    @Test
    void return_trips_when_users_are_friends() {
        TripService tripService = new TestableTripService();

        User friend = new User();
        friend.addFriend(ANOTHER_USER);
        friend.addFriend(loggerUser);
        friend.addTrip(TO_ITALY);
        friend.addTrip(TO_LONDON);

        List<Trip> trips = tripService.getTripsByUser(friend);

        assertThat(trips).contains(TO_ITALY, TO_LONDON);
    }

    private class TestableTripService extends TripService {
        @Override
        User getLoggedUser() {
            return loggerUser;
        }

        @Override
        List<Trip> findTripsBy(User user) {
            return user.trips();
        }
    }
}