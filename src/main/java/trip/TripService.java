package trip;

import exception.UserNotLoggedInException;
import user.User;
import user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = getLoggerUser();
        if (isLogged(loggedUser)) {
            if (user.isFriendOf(loggedUser)) {
                return findTripsBy(user);
            } else {
                return noTrip();
            }
        } else {
            throw new UserNotLoggedInException();
        }
    }

    private boolean isLogged(User loggedUser) {
        return loggedUser != null;
    }

    private ArrayList<Trip> noTrip() {
        return new ArrayList<Trip>();
    }

    List<Trip> findTripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }

    User getLoggerUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
