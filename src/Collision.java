import java.awt.*;

public class Collision {
    private Rectangle object;
    private Rectangle projectile;
    private boolean touching;
    public Frame gamePanel;

    public Collision(Rectangle object, Rectangle projectile) {
        this.object = object;
        this.projectile = projectile;
    }
    public Collision(Frame gp) {
        gamePanel = gp;
    }
    public Collision() {
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
    public boolean isTouching() {
        return touching;
    }

    public Rectangle getObject() {
        return object;
    }

    public void setObject(Rectangle object) {
        this.object = object;
    }

    public Rectangle getProjectile() {
        return projectile;
    }

    public void setProjectile(Rectangle projectile) {
        this.projectile = projectile;
    }

    public void setTouching(boolean touching) {
        this.touching = touching;
    }
}
