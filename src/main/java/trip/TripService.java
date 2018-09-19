package trip;

import exception.UserNotLoggedInException;
import user.User;
import user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = getLoggedUser();
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }

        List<Trip> tripList = new ArrayList<Trip>();
        if (user.isFriendsWith(loggedUser)) {
            tripList = tripsBy(user);
        }
        return tripList;

    }

    List<Trip> tripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }

    User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
