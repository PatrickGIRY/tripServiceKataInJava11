package trip;


import exception.UserNotLoggedInException;
import org.junit.jupiter.api.Test;
import user.User;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TripServiceShould {

    private static final User GUEST = null;
    private static final User UNUSED_USER = null;

    @Test
    void throw_an_exception_when_user_not_logger_in() {
        TripService tripService = new TestableTripService();

        assertThatThrownBy(() -> tripService.getTripsByUser(UNUSED_USER))
                .isInstanceOf(UserNotLoggedInException.class);
    }

    private class TestableTripService extends TripService {

        @Override
        User getLoggedUser() {
            return GUEST;
        }
    }

}