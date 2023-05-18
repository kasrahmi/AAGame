package model;

import javafx.scene.image.ImageView;


public class AvatarOrganizer {
    public static String pathGenerator(User user) {
        return "src/main/resources/avatars/" + user.getUsername() + ".jpg";
    }

    public static ImageView getAvatar(User user){
        return new ImageView(
                new javafx.scene.image.Image(user.getPathAvatar(), 800 ,600, false, false));
    }
}
