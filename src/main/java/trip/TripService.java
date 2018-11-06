package trip;

import exception.UserNotLoggedInException;
import user.User;
import user.UserSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        Optional<User> loggedUser = getLoggerUser();
        return loggedUser
                .map(tripsOfFriend(user))
                .orElseThrow(UserNotLoggedInException::new);
    }

    private Function<User, List<Trip>> tripsOfFriend(User user) {
        return loggerUser -> {
            if (user.isFriendOf(loggerUser)) {
                return findTripsBy(user);
            } else {
                return noTrip();
            }
        };
    }

    private ArrayList<Trip> noTrip() {
        return new ArrayList<Trip>();
    }

    List<Trip> findTripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }

    Optional<User> getLoggerUser() {
        return Optional.ofNullable(UserSession.getInstance().getLoggedUser());
    }

}
