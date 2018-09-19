package trip;

import exception.UserNotLoggedInException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.User;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TripServiceShould {

    private final User UNUSED_USER = null;
    private final User GUEST = null;

    private TripService tripService;

    @BeforeEach
    void setUp() {
        tripService = new TestableTripService();
    }

    @Test
    void throw_an_exception_when_user_not_logged_in() {

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