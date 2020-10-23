package bearmaps;
import java.util.ArrayList;
import java.util.HashMap;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{
    private int size;
    private ArrayList<PriorityNode> items;
    private HashMap<T, Integer> Map;

    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        items.add(null);
        Map = new HashMap<>();
        Map.put(null, 0);
        size = 0;
    }

    public void add(T item, double priority) {
        if (contains(item) || item == null) {
            throw new IllegalArgumentException();
        }
        items.add(new PriorityNode(item, priority));
        size += 1;
        Map.put(item, size);
        up(size);
    }
    @Override
    public T getSmallest() {
        if (size() == 0) {
            throw new IllegalArgumentException();
        }
        return items.get(1).item;
    }

    @Override
    public T removeSmallest() {
        if (size() == 0) {
            throw new IllegalArgumentException();
        }
        T Remove = items.get(1).getItem();
        swap(1, size);
        items.remove(Remove);
        size -= 1;
        Map.remove(size());
        down(1);
        return Remove;
    }

    @Override
    public boolean contains(T item) {
        if (!Map.containsKey(item)) {
            return false;
        }
        return true;
    }

    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new IllegalArgumentException();
        }
        int index = Map.get(item);
        double curpriority = items.get(index).getPriority();
        if (curpriority > priority) {
            up(index);
        } else {
            down(index);
        }
    }

    public void up(int index) {
        int Parent = index / 2;
        if(Parent == 0) {
            return;
        }
        if (index != 1 && items.get(index).compareTo(items.get(Parent)) < 0) {
            swap(index, Parent );
            up(Parent);
            Map.put(items.get(index).getItem(), Parent);
            Map.put(items.get(Parent).getItem(), index);
        }
    }

    public void down(int index) {
        int leftChild = leftChild(index);
        int rightChild = rightChild(index);
        while (leftChild <= size && rightChild <= size) {
            int Left = items.get(index).compareTo(items.get(leftChild));
            int Right = items.get(index).compareTo(items.get(rightChild));
            if (Left > 0 && Right > 0 && Left >= Right) {
                swap(index, leftChild);
                index = leftChild;
            } else if (Left > 0 && Right > 0 && Left < Right) {
                swap(index, rightChild);
                index = rightChild;
            } else if (Left > 0) {
                swap(index, leftChild);
                index = leftChild;
            } else if (Right > 0) {
                swap(index, rightChild);
                index = rightChild;
            }
            else if (Left <= 0 && Right <= 0) {
                break;
            }
        }
    }

    public void swap(int index1, int index2) {
        PriorityNode temp = items.get(index1);
        items.set(index1, items.get(index2));
        items.set(index2, temp);
        Map.put(items.get(index1).getItem(), index1);
        Map.put(items.get(index2).getItem(), index2);
    }

    private int parent(int index) {
        return index / 2;
    }

    private int leftChild(int index) {
        return index * 2;
    }

    private int rightChild(int index) {
        return index * 2 +1;
    }

    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((PriorityNode) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }
}

