/**
 * An event is made up of an Entity that is taking an
 * Action a specified time.
 */
public final class Event {
    private Action action;
    private double time;
    private Entity entity;

    public Event(Action action, double time, Entity entity) {
        this.action = action;
        this.time = time;
        this.entity = entity;
    }
    public Action action(){return this.action;}
    public double time(){return this.time;}
    public Entity entity(){return this.entity;}
}
