public class UserRepository {
    public User findUserByEmail(String email) {
        return new User(email, "password");
    }
}
