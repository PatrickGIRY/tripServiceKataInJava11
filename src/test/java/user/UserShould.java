package user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static user.UserBuilder.aUser;

class UserShould {

    private static final User MARTIN = new User();
    private static final User PASCAL = new User();

    @Test
    void inform_when_users_are_not_fiends() {
        User user = aUser().friendsWith(MARTIN).build();

        assertThat(user.isFriendWith(PASCAL)).isFalse();
    }

    @Test
    void inform_when_users_are_fiends() {
        User user = aUser().friendsWith(MARTIN, PASCAL).build();

        assertThat(user.isFriendWith(PASCAL)).isTrue();
    }
}