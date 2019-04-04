package trip;

import exception.UserNotLoggedInException;
import user.User;
import user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {

        User loggedUser = loggedUser();

		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}
		return user.isMyFriend(loggedUser) ? findTripsBy(user) : noTrip();
	}

	private List<Trip> noTrip() {
		return new ArrayList<>();
	}

	List<Trip> findTripsBy(User user) {
		return TripDAO.findTripsByUser(user);
	}

	User loggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
