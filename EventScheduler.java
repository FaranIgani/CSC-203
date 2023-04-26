import java.util.*;

/**
 * Keeps track of events that have been scheduled.
 */
public final class EventScheduler {
    private PriorityQueue<Event> eventQueue;
    private Map<Entity, List<Event>> pendingEvents;
    private double currentTime;


    public double currentTime(){return this.currentTime;}

    public EventScheduler() {
        this.eventQueue = new PriorityQueue<>(new EventComparator());
        this.pendingEvents = new HashMap<>();
        this.currentTime = 0;
    }
    public void scheduleEvent(Entity entity, Action action, double afterPeriod) {
        double time = this.currentTime + afterPeriod;

        Event event = new Event(action, time, entity);

        this.eventQueue.add(event);

        // update list of pending events for the given entity
        List<Event> pending = this.pendingEvents.getOrDefault(entity, new LinkedList<>());
        pending.add(event);
        this.pendingEvents.put(entity, pending);
    }
    public static void unscheduleAllEvents(EventScheduler scheduler, Entity entity) {
        List<Event> pending = scheduler.pendingEvents.remove(entity);

        if (pending != null) {
            for (Event event : pending) {
                scheduler.eventQueue.remove(event);
            }
        }
    }
    public void removePendingEvent( Event event) {
        List<Event> pending = this.pendingEvents.get(event.entity());

        if (pending != null) {
            pending.remove(event);
        }
    }

    public void updateOnTime( double time) {
        double stopTime = this.currentTime + time;
        while (!this.eventQueue.isEmpty() && this.eventQueue.peek().time() <= stopTime) {
            Event next = this.eventQueue.poll();
            this.removePendingEvent(next);
            this.currentTime = next.time();
            next.action().executeAction(this);
        }
        this.currentTime = stopTime;
    }
}
