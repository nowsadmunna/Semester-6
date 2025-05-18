import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AuthManagerTest {

    private UserRepository mockUserRepo;
    private Hash mockHash;
    private AuthManager authManager;
    private User mockUser;

    private final String email = "test@example.com";
    private final String rawPassword = "password123";
    private final String hashedPassword = "hashedpassword";
    private final String errorEmail = "error@example.com";

    @Before
    public void setUp() {
        mockUserRepo = mock(UserRepository.class);
        mockHash = mock(Hash.class);
        mockUser = mock(User.class);

        authManager = new AuthManager(mockUserRepo, mockHash);
        when(mockUserRepo.findUserByEmail(email)).thenReturn(mockUser);
        when(mockUser.getPassword()).thenReturn(hashedPassword);
        when(mockHash.hash(rawPassword)).thenReturn(hashedPassword);
        when(mockUserRepo.findUserByEmail(errorEmail)).thenThrow(new RuntimeException("User not found"));
    }

    @Test
    public void testLoginSuccess() {
        boolean result = authManager.login(email, rawPassword);
        assertTrue(result);
    }

    @Test
    public void testLoginIncorrectPassword() {
        when(mockHash.hash(rawPassword)).thenReturn("wronghashedpassword");

        boolean result = authManager.login(email, rawPassword);
        assertFalse(result);
    }

    @Test
    public void testLoginWrongEmail() {
        boolean result = authManager.login(errorEmail, rawPassword);
        assertFalse(result);
    }
}
