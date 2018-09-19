package user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static user.UserBuilder.aUser;

class UserShould {

    private static final User MARTIN = new User();
    private static final User PASCAL = new User();

    @Test
    void inform_when_users_are_not_friends() {
        User user = aUser().friendsWith(MARTIN).build();

        assertThat(user.isFriendsWith(PASCAL)).isFalse();
    }

    @Test
    void inform_when_users_are_friends() {
        User user = aUser().friendsWith(MARTIN, PASCAL).build();

        assertThat(user.isFriendsWith(PASCAL)).isTrue();
    }

}