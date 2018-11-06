package trip;

import exception.UserNotLoggedInException;
import user.User;
import user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<Trip>();
        User loggedUser = getLoggerUser();
		if (loggedUser != null) {
            boolean isFriend = user.isFriendOf(loggedUser);

			if (isFriend) {
                tripList = findTripsBy(user);
            }
			return tripList;
		} else {
			throw new UserNotLoggedInException();
		}
	}

    List<Trip> findTripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }

    User getLoggerUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
