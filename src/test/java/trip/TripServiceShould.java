package trip;

import exception.UserNotLoggedInException;
import org.junit.jupiter.api.Test;
import user.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TripServiceShould {


    private static final User UNUSED_USER = null;
    private static final User GUEST = null;
    private static final User REGISTRED_USER = new User();


    @Test
    void throw_exception_if_user_not_logged_in() {

        TripService tripService = new TestableTripService(GUEST);

        assertThatThrownBy(() ->
                tripService.getTripsByUser(UNUSED_USER))
                .isInstanceOf(UserNotLoggedInException.class);

    }


    @Test
    void return_empty_trip_list_when_user_has_no_friends()
    {
        TripService tripService = new TestableTripService(REGISTRED_USER);

        User noFriendUser = new User();
        assertThat(tripService.getTripsByUser(noFriendUser)).isEmpty();
    }


    @Test
    void return_empty_trip_list_when_user_in_not_a_friend_of_logged_user()
    {
        TripService tripService = new TestableTripService(REGISTRED_USER);

        User notMyFriendUser = new User();
        User friend = new User();
        notMyFriendUser.addFriend(friend);

        assertThat(tripService.getTripsByUser(notMyFriendUser)).isEmpty();

    }


    class TestableTripService extends TripService {

        private User loggedUser;


        TestableTripService(User loggedUser) {this.loggedUser = loggedUser;}


        @Override
        User loggedUser() {
            return loggedUser;
        }
    }
}
