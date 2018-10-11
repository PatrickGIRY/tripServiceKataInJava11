package user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserShould {

    private static final User PAUL = new User();

    @Test
    void return_false_if_the_user_is_not_friend_with() {
        User user = new User();
        boolean isFriendWith = user.isFriendWith(PAUL);

        assertThat(isFriendWith).isFalse();
    }

    @Test
    void return_true_if_the_user_is_friend_with() {

        User user = new User();
        user.addFriend(PAUL);

        boolean isFriendWith = user.isFriendWith(PAUL);

        assertThat(isFriendWith).isTrue();
    }

}