public class BoxGeneric<T extends Item> {
    private T value;

    public void setValue(T value) {
        this.value = value;
    }
    public T getValue() {
        return value;
    }
    public String info() {
        return value.getItemInfo();
    }
}
