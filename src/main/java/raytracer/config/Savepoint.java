package raytracer.config;

public class Savepoint {
    private final String path;
    private final float time;
    private final String unit;
    private boolean saved;

    public Savepoint(String path, float time, String unit) {
        this.path = path;
        this.time = time;
        this.unit = unit;

        this.saved = false;
    }

    public String path() {
        return path;
    }

    public void markSaved() {
        saved = true;
    }

    public boolean readyToSave(float currentTime, int frameCount) {
        if (saved) {
            return false;
        }

        return switch (unit) {
            case "frames" -> frameCount >= time;
            case "seconds" -> currentTime >= time;
            case "minutes" -> currentTime >= time * 60;
            case "hours" -> currentTime >= time * 60 * 60;
            default -> throw new IllegalArgumentException("Invalid unit: " + unit);
        };
    }
}
