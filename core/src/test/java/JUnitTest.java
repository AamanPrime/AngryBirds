import com.sampleproject.model.UserManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class JUnitTest {
    private int initscore;
    private int score;
    UserManager userManager;
    @BeforeEach
    public void init() {
        userManager = new UserManager();
        userManager.addUser(new UserManager.User("default","default"));
    }
    @Test
    public void wrongUserName() {
        UserManager.User user = userManager.getUsers("defaul");
        Assertions.assertNull(user, "User Doesnt exist");
    }

    @Test
    public void updateMusicStatus() {
        UserManager.User user = userManager.getUsers("default");
        boolean musicStatus =  userManager.getSettings("music","default");
        userManager.setSetting("music","default");
        Assertions.assertNotEquals(musicStatus, userManager.getSettings("music","default"));
    }

    @Test
    public void updateSoundStatus() {
        UserManager.User user = userManager.getUsers("default");
        boolean soundStatus =  userManager.getSettings("sound","default");
        userManager.setSetting("sound","default");
        Assertions.assertNotEquals(soundStatus, userManager.getSettings("sound","default"));
    }

    @Test
    public void updateDifficultyStatus() {
        UserManager.User user = userManager.getUsers("default");
        boolean DifficultyStatus =  userManager.getSettings("difficulty","default");
        userManager.setSetting("difficulty","default");
        Assertions.assertNotEquals(DifficultyStatus, userManager.getSettings("difficulty","default"));
    }

    @Test
    public void updateUserName() {
        UserManager.User user = userManager.getUsers("default");
        String oldname = user.getUsername();
        userManager.changeUserName(oldname,"newName");
        user = userManager.getUsers("newName");
        Assertions.assertNotEquals(oldname, user.getUsername());
    }

}
