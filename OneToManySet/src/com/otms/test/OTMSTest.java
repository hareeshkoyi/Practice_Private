package com.otms.test;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.otms.bo.RestaurantBo;
import com.otms.entities.Restaurant;
import com.otms.entities.Review;
import com.otms.util.HibernateUtil;

public class OTMSTest {
	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Transaction transaction = null;
		Session session = null;
		boolean flag = false;

		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();

			// saveRestaurant(session);

			/*
			 * Restaurant restaurant = (Restaurant)
			 * session.get(Restaurant.class, 1700); session.delete(restaurant);
			 */

			/*
			 * Restaurant restaurant = (Restaurant)
			 * session.get(Restaurant.class, 1720); Review review = (Review)
			 * session.get(Review.class, 1730);
			 * restaurant.getReviews().remove(review);
			 * session.update(restaurant);
			 */

			// showRestaurants(session);
			// showRestaurantsByType(session, "Multi-Cusine");
			// showRestaurantsByLocation(session, "Ameerpet");
			// showRestaurantsByName(session, "An");
			// showNoOfRestaurantsByTypeAndLocation(session, "Local",
			// "ameerpet");
			// showAllRestaurantBos(session, "Local");
			// showRestaurantsByReviewedBy(session, "Ramu");
			// showAllReviewsForRestaurant(session, "Bluefox");
			// showAllReviewsForRestaurantToday(session);'
			// showRestaurantsWhichHasAtleatOneReivew(session);
			// showAllRestaurantsWithReviews(session);
			// showPopularRestaurants(session, 4);
			// showRestaurantsAboveAverage(session);
			// showReviewsByRatingAndReviewedBy(session, 4, "Rajesh");
			// deleteLowRatingReviews(session, 3);
			// updateRatingForReview(session, 5, 5);
			// insertFromRestaurant(session, 2);
			// getNoOfRestaurantsByLocation(session);
			// getRestaurantsNamedQuery(session);
			// showRestaurantsByTypeAndLocation(session, "Local", "Ameerpet");
			// showReviewsByRating(session, 3, 5);
			// showComments(session, "Bluefox", "Rajesh");
			// showRestaurantNameAndLocationByRating(session, 3);
			// showNoOfRestaurantsByRating(session, 3);
			// showRestaurantsUsingDetachedCriteria(session, "Local");
			showRestaurantsByNativeSql(session);
			flag = true;
		} finally {
			if (transaction != null) {
				if (flag) {
					transaction.commit();
				} else {
					transaction.rollback();
				}
			}
			HibernateUtil.closeSessionFactory();
		}

	}

	private static void showRestaurantsByNativeSql(Session session) {
		SQLQuery query = null;

		query = session
				.createSQLQuery("select res_nm, location from restaurant");
		// query.addEntity(Restaurant.class);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> records = query.list();
		for (Map<String, Object> record : records) {
			for (String col : record.keySet()) {
				System.out.println(col + "- " + record.get(col));
			}
		}

	}

	private static void showRestaurantsUsingDetachedCriteria(Session session,
			String restaurantType) {
		DetachedCriteria dc = DetachedCriteria.forClass(Restaurant.class).add(
				Restrictions.like("restaurantType", restaurantType));
		Criteria c = dc.getExecutableCriteria(session);
		printRestaurants(c.list());
	}

	private static void showNoOfRestaurantsByRating(Session session, int rating) {
		Criteria c = session.createCriteria(Restaurant.class)
				.createAlias("reviews", "rv")
				.add(Restrictions.ge("rv.rating", rating))
				.setProjection(Projections.rowCount());
		long rowCount = (long) c.list().get(0);
		System.out.println(rowCount);
	}

	private static void showRestaurantNameAndLocationByRating(Session session,
			int rating) {
		ProjectionList pl = Projections.projectionList();
		pl.add(Projections.property("restaurantName"));
		pl.add(Projections.property("location"));

		Criteria c = session.createCriteria(Restaurant.class)
				.createAlias("reviews", "rv")
				.add(Restrictions.ge("rv.rating", rating)).setProjection(pl);
		List<Object[]> rows = c.list();
		for (Object[] row : rows) {
			System.out.println("Name : " + row[0] + " location : " + row[1]);
		}

	}

	private static void showComments(Session session, String restaurantName,
			String reviewBy) {
		Criteria c = session
				.createCriteria(Restaurant.class)
				.createAlias("reviews", "rv")
				.add(Restrictions.ilike("restaurantName", restaurantName))
				.add(Restrictions.and(Restrictions.ilike("rv.reviewBy",
						reviewBy)))
				.setProjection(Projections.property("rv.comments"));
		List<String> comments = c.list();
		System.out.println(comments);
	}

	private static void showRestaurantsByTypeAndLocation(Session session,
			String type, String location) {
		Criteria c = session
				.createCriteria(Restaurant.class)
				.add(Restrictions.ilike("restaurantType", type))
				.add(Restrictions.and(Restrictions.ilike("location", location)));
		List<Restaurant> restaurants = c.list();
		printRestaurants(restaurants);
	}

	private static void showReviewsByRating(Session session, int minRating,
			int maxRating) {
		Criteria c = session.createCriteria(Review.class).add(
				Restrictions.between("rating", minRating, maxRating));
		List<Review> reviews = c.list();
		for (Review r : reviews) {
			System.out.println(r.getComments());
		}
	}

	private static void getRestaurantsNamedQuery(Session session) {
		Query query = session.getNamedQuery("getAllRestaurants");
		List<Restaurant> restaurants = query.list();
		printRestaurants(restaurants);
	}

	private static void getNoOfRestaurantsByLocation(Session session) {
		/*
		 * Query query = session .createQuery(
		 * "select count(r), r.location from Restaurant r group by r.location order by r.location"
		 * ); List<Object[]> rows = query.list(); for (Object[] row : rows) {
		 * System.out.println("count : " + row[0] + "  name : " + row[1]); }
		 */
		Criteria c = session.createCriteria(Restaurant.class).setProjection(
				Projections.projectionList()
						.add(Projections.property("location"))
						.add(Projections.rowCount())
						.add(Projections.groupProperty("location")));
		List<Object[]> rows = c.list();
		for (Object[] row : rows) {
			System.out.println("location : " + row[0] + " count : " + row[1]);
		}
	}

	private static void insertFromRestaurant(Session session, int restaurantNo) {
		Query query = null;
		query = session
				.createQuery("insert into Restaurant1(restaurantNo, restaurantName, location, restaurantType, contactNo, emailAddress) select r.restaurantNo, r.restaurantName, r.location, r.restaurantType, r.contactNo, r.emailAddress from Restaurant r");
		// query.setParameter("rNo", restaurantNo);
		int r = query.executeUpdate();
		System.out.println(r);
	}

	private static void updateRatingForReview(Session session, int reviewId,
			int rating) {
		Query query = null;

		query = session
				.createQuery("update Review rv set rv.rating = :rating where rv.reviewNo = :reviewId");
		query.setParameter("reviewId", reviewId);
		query.setParameter("rating", rating);
		int rows = query.executeUpdate();
		System.out.println("rows : " + rows);
	}

	private static void deleteLowRatingReviews(Session session, int rating) {
		Query query = null;

		query = session
				.createQuery("delete from Review rv where rv.rating < :rating");
		query.setParameter("rating", rating);
		int rows = query.executeUpdate();
		System.out.println("rows : " + rows);
	}

	/**
	 * Named Parameters
	 * 
	 * @param session
	 * @param rating
	 * @param reviewedBy
	 */
	private static void showReviewsByRatingAndReviewedBy(Session session,
			int rating, String reviewedBy) {
		/*
		 * Query query = null;
		 * 
		 * query = session .createQuery(
		 * "select rv from Review rv where rv.rating > :rating and rv.reviewBy like :reviewBy"
		 * ); query.setParameter("rating", rating);
		 * query.setParameter("reviewBy", reviewedBy); List<Review> reviews =
		 * query.list(); for (Review review : reviews) {
		 * System.out.println(review.getComments()); }
		 */

		printRestaurants(session
				.createCriteria(Restaurant.class)
				.createAlias("reviews", "rv")
				.add(Restrictions.ge("rv.rating", rating))
				.add(Restrictions.and(Restrictions.ilike("rv.reviewBy",
						reviewedBy))).list());
	}

	private static void showRestaurantsAboveAverage(Session session) {
		Query query = null;

		query = session
				.createQuery("select r.restaurantName from Restaurant r inner join r.reviews rv where rv.rating > (select avg(rating) from Review)");
		List<String> restaurantNames = query.list();
		for (String r : restaurantNames) {
			System.out.println(r);
		}
	}

	private static void showPopularRestaurants(Session session, int rating) {
		Query query = null;

		/*
		 * query = session .createQuery(
		 * "select r.restaurantName from Restaurant r left outer join r.reviews rv where (rv.rating > ? or rv.rating is NULL)"
		 * ); query.setParameter(0, rating); List<String> restaurantNames =
		 * query.list(); for (String r : restaurantNames) {
		 * System.out.println(r); }
		 */
		printRestaurants(session.createCriteria(Restaurant.class)
				.createAlias("reviews", "rv", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.ge("rv.rating", rating))
				.add(Restrictions.or(Restrictions.isNull("rv.rating"))).list());
	}

	private static void showAllRestaurantsWithReviews(Session session) {
		Query query = null;
		query = session
				.createQuery("select r.restaurantName, rv.comments from Restaurant r left outer join r.reviews rv");
		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			System.out.println("Name : " + row[0] + " comments : " + row[1]);
		}
	}

	private static void showRestaurantsWhichHasAtleatOneReivew(Session session) {
		Query query = null;

		query = session
				.createQuery("select r from Restaurant r inner join r.reviews");
		List<Restaurant> restaurants = query.list();
		printRestaurants(restaurants);
	}

	private static void showAllReviewsForRestaurantToday(Session session) {
		Query query = null;

		query = session
				.createQuery("select r.restaurantName, rv.comments from Restaurant r inner join r.reviews rv where rv.reviewDate = current_date()");
		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			System.out.println("Name : " + row[0] + " comments : " + row[1]);
		}
	}

	private static void showAllReviewsForRestaurant(Session session,
			String restaurantName) {
		Query query = null;

		/*
		 * query = session .createQuery(
		 * "select r.restaurantName, rv.comments, rv.rating from Restaurant r inner join r.reviews rv where lower(r.restaurantName) like ?"
		 * );
		 */
		query = session
				.createQuery("select r.reviews from Restaurant r where lower(r.restaurantName) = ?");

		query.setParameter(0, restaurantName.toLowerCase());
		List<Review> reviews = query.list();
		for (Review review : reviews) {
			System.out.println("comments : " + review.getComments());
		}
		/*
		 * List<Object[]> rows = query.list();
		 * 
		 * for (Object[] row : rows) { System.out.println("restaurantName : " +
		 * row[0] + " comments : " + row[1] + " rating : " + row[2]); }
		 */
	}

	private static void showRestaurantsByReviewedBy(Session session,
			String reviewedBy) {
		/*
		 * Query query = session .createQuery(
		 * "select r from Restaurant r inner join r.reviews as review where review.reviewBy = ?"
		 * ); query.setParameter(0, reviewedBy); List<Restaurant> restaurants =
		 * query.list(); printRestaurants(restaurants);
		 */
		Criteria c = session.createCriteria(Restaurant.class)
				.createAlias("reviews", "rv")
				.add(Restrictions.ilike("rv.reviewBy", reviewedBy));
		List<Restaurant> restaurants = c.list();
		printRestaurants(restaurants);

	}

	/**
	 * Get Restaurant Attributes as RestaurantBo
	 * 
	 * @param session
	 * @param type
	 */
	private static void showAllRestaurantBos(Session session, String type) {
		Query query = null;

		query = session
				.createQuery("select new com.otms.bo.RestaurantBo(r.restaurantName,r.location,r.contactNo) from Restaurant r where lower(r.restaurantType) = ?");
		query.setParameter(0, type.toLowerCase());
		List<RestaurantBo> restaurants = query.list();
		for (RestaurantBo bo : restaurants) {
			System.out.println(bo);
		}

	}

	private static void showNoOfRestaurantsByTypeAndLocation(Session session,
			String type, String location) {
		Query query = null;

		query = session
				.createQuery("select count(r) from Restaurant r where lower(r.restaurantType) like ? and lower(r.location) like ?");
		query.setParameter(0, "%" + type.toLowerCase() + "%");
		query.setParameter(1, "%" + location.toLowerCase() + "%");
		List<Integer> row = query.list();
		System.out.println("restaurants : " + row.get(0));
	}

	// Hql
	/**
	 * Gets all the restaurants
	 * 
	 * @param session
	 */
	public static void showRestaurants(Session session) {
		/*
		 * Query query = null;
		 * 
		 * query = session
		 * .createQuery("Select restaurant from Restaurant restaurant");
		 * List<Restaurant> restaurants = query.list(); for (Restaurant
		 * restaurant : restaurants) {
		 * System.out.println(restaurant.getRestaurantName()); }
		 */

		Criteria c = session.createCriteria(Restaurant.class);
		List<Restaurant> restaurants = c.list();
		printRestaurants(restaurants);
	}

	/**
	 * Partial restaurant attributes by location
	 * 
	 * @param session
	 * @param location
	 */
	private static void showRestaurantsByLocation(Session session,
			String location) {
		Query query = null;

		query = session
				.createQuery("select r.restaurantName, r.contactNo from Restaurant r where r.location=?");
		query.setParameter(0, location);
		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			System.out.println("name : " + row[0] + "- " + "contactNo : "
					+ row[1]);
		}
	}

	private static void showRestaurantsByType(Session session, String type) {
		/*
		 * Query query = null;
		 * 
		 * query = session
		 * .createQuery("from Restaurant as r where r.restaurantType=?");
		 * query.setParameter(0, type); List<Restaurant> restaurants =
		 * query.list();
		 */
		/*
		 * Criteria c = session.createCriteria(Restaurant.class).add(
		 * Restrictions.ilike("restaurantType", type)); List<Restaurant>
		 * restaurants = c.list(); printRestaurants(restaurants);
		 */
		Criteria c = session.createCriteria(Restaurant.class)
				.add(Restrictions.ilike("restaurantType", type))
				.setProjection(Projections.property("restaurantName"));
		List<String> restaurantNames = c.list();
		System.out.println(restaurantNames);
	}

	private static void showRestaurantsByName(Session session,
			String restaurantName) {
		Query query = null;
		String lRestaurantName = null;

		lRestaurantName = "%" + restaurantName.toLowerCase() + "%";
		query = session
				.createQuery("from Restaurant r where lower(r.restaurantName) like ?");
		query.setParameter(0, lRestaurantName);
		List<Restaurant> restaurants = query.list();
		printRestaurants(restaurants);

	}

	private static void printRestaurants(List<Restaurant> restaurants) {
		for (Restaurant restaurant : restaurants) {
			System.out.println(restaurant);
		}
	}

	private static void saveRestaurant(Session session) {
		Restaurant restaurant = null;
		Set<Review> reviews = null;
		Review review = null;
		Review review1 = null;

		reviews = new HashSet<Review>();
		review = new Review();
		review.setComments("Best place to eat");
		review.setRating(3);
		review.setReviewBy("Ramarao");
		review.setReviewDate(new Date());

		review1 = new Review();
		review1.setComments("Sensation");
		review1.setRating(4);
		review1.setReviewBy("Venkatrao");
		review1.setReviewDate(new Date());

		reviews.add(review);
		reviews.add(review1);

		restaurant = new Restaurant();
		restaurant.setRestaurantName("Grand Kakatiya");
		restaurant.setLocation("Ameerpet");
		restaurant.setContactNo("35393944");
		restaurant.setEmailAddress("gk@gmail.com");
		restaurant.setRestaurantType("Local");
		restaurant.setReviews(reviews);
		review1.setRestaurant(restaurant);
		review.setRestaurant(restaurant);
		session.persist(restaurant);

	}
}
