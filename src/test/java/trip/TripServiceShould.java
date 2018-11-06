package trip;


import exception.UserNotLoggedInException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TripServiceShould {

    private static final User GUEST = null;
    private static final User UNUSED_USER = null;
    private static final Trip TO_LONDON = new Trip();
    private static final User REGISTERED_USER = new User();

    private User loggedUser;

    private TripService tripService;

    @BeforeEach
    void setUp() {
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
        loggedUser = REGISTERED_USER;

        User friend = new User();
        friend.addTrip(TO_LONDON);

        assertThat(tripService.getTripsByUser(friend)).isEmpty();
    }

    private class TestableTripService extends TripService {
        @Override
        User getLoggerUser() {
            return loggedUser;
        }
    }
}