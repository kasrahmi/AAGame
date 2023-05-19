package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.LoginMenu.LoginMenu;


public class AvatarOrganizer {
    public static String pathGenerator(String id) {
        return LoginMenu.class.getResource("/avatars/" + id +".png").toString();
    }
    public static String randomPathGenerator() {
        int x = (int) (Math.random() * 3 + 1);
        return LoginMenu.class.getResource("/avatars/" + x + ".png").toString();
    }

    public static ImageView getAvatar(User user){
        return new ImageView(
                new Image(user.getPathAvatar(), 80 ,80, false, false));
    }

    public static ImageView getAvatarByPath(String path) {
        return new ImageView(
                new Image(path, 80 ,80, false, false));
    }
}
