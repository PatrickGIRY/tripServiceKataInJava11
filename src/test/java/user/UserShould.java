package user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserShould {

    private static final User PAUL = new User();
    private static final User PASCAL = new User();

    @Test
    void inform_when_the_users_are_not_friends() {

        User user = new User();
        user.addFriend(PAUL);

        assertThat(user.isFriendWith(PASCAL)).isFalse();
    }

    @Test
    void inform_when_the_users_are_friends() {

        User user = new User();
        user.addFriend(PAUL);

        assertThat(user.isFriendWith(PAUL)).isTrue();
    }

}