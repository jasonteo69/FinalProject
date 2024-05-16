import java.awt.*;

public class CollisionHandler {
    private Rectangle object;
    private Rectangle projectile;
    private boolean touching;

    public CollisionHandler() {
        object = new Rectangle();
        projectile = new Rectangle();
    }

    public boolean collided() {
        if (projectile.intersects(object)) {
            touching = true;
        } else {
            touching = false;
        }
        return touching;
    }

    public void setObject(Rectangle object) {
        this.object = object;
    }

    public void setProjectile(Rectangle projectile) {
        this.projectile = projectile;
    }

}
