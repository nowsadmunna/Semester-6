public class AuthManager {
    private UserRepository userRepository;
    private Hash hash;

    public AuthManager(UserRepository userRepository, Hash hash) {
        this.userRepository = userRepository;
        this.hash = hash;
    }

    public boolean login(String email, String password) {
        try {
            User user = userRepository.findUserByEmail(email);
            String hashedInputPassword = hash.hash(password);
            return hashedInputPassword.equals(user.getPassword());
        } catch (Exception e) {
            return false;
        }
    }
}
