package trip;

import exception.UserNotLoggedInException;
import user.User;
import user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = getLoggedUser();
        if (loggedUser != null) {
            if (user.isFriendWith(loggedUser)) {
                return findTripsBy(user);
            } else {
                return noTrip();
            }
        } else {
            throw new UserNotLoggedInException();
        }
    }

    ArrayList<Trip> noTrip() {
        return new ArrayList<Trip>();
    }

    List<Trip> findTripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }

    User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
