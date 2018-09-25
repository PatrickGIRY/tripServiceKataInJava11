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
    private static final User REGISTERED_USER = new User();
    private static final User ANOTHER_USER = new User();
    private static final Trip TO_ITALY = new Trip();
    private User loggedUser;

    @Test
    void throw_an_exception_when_user_not_logger_in() {
        TripService tripService = new TestableTripService();
        loggedUser = GUEST;

        assertThatThrownBy(() -> tripService.getTripsByUser(UNUSED_USER))
                .isInstanceOf(UserNotLoggedInException.class);
    }

    @Test
    void not_return_any_trips_when_users_are_not_friends() {
        TripService tripService = new TestableTripService();
        loggedUser = REGISTERED_USER;

        User friend = new User();
        friend.addFriend(ANOTHER_USER);
        friend.addTrip(TO_ITALY);

        List<Trip> trips = tripService.getTripsByUser(friend);

        assertThat(trips).isEmpty();

    }

    private class TestableTripService extends TripService {

        @Override
        User getLoggedUser() {
            return loggedUser;
        }
    }

}