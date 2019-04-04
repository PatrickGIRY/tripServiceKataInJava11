package trip;

import exception.UserNotLoggedInException;
import org.junit.jupiter.api.Test;
import user.User;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TripServiceShould {

    @Test
    void throw_exception_if_user_not_logged_in() {

        TripService tripService = new TestableTripService();

        assertThatThrownBy(() ->
                tripService.getTripsByUser(null))
                .isInstanceOf(UserNotLoggedInException.class);

    }

    class TestableTripService extends TripService {

        @Override
        User loggedUser() {
            return null;
        }
    }
}