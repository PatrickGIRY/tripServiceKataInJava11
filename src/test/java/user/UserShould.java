package user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserShould {

    private static final User ALICE = new User();

    @Test
    void indicates_if_users_are_not_friends() {
        User user = new User();

        assertThat(user.isFriendOf(ALICE)).isFalse();
    }

    @Test
    void indicates_if_users_are_friends() {
        User user = new User();
        user.addFriend(ALICE);

        assertThat(user.isFriendOf(ALICE)).isTrue();
    }

}