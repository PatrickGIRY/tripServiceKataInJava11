package user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserShould {


    @Test
    void test_if_user_is_my_friend()
    {

        User user = new User();
        User friend = new User();
        user.addFriend(friend);

        assertThat(user.isMyFriend(friend)).isTrue();
    }

    @Test
    void test_if_user_is_not_my_friend(){
        User user = new User();
        User friend = new User();

        assertThat(user.isMyFriend(friend)).isFalse();
    }

}