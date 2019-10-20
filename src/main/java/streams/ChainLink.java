package streams;

enum Side {NONE, LEFT, RIGHT}

public class ChainLink {
    private ChainLink left, right;
    private int COUNT_FOR_CHAIN_LOOP = -1;

    public static void main(String[] args) throws IllegalAccessException {
        ChainLink left = new ChainLink();
        ChainLink middle = new ChainLink();
        ChainLink right = new ChainLink();
        ChainLink right2 = new ChainLink();
        left.append(middle);
        middle.append(right);
        right.append(right2);
        System.out.println(left.longerSide());
    }

    public void append(ChainLink rightPart) throws IllegalAccessException {
        if (this.right != null) {
            throw new IllegalAccessException("Link is already connected");
        }

        this.right = rightPart;
        rightPart.left = this;
    }

    public Side longerSide() {
        int rightLinkCount = countRight();
        if (rightLinkCount == COUNT_FOR_CHAIN_LOOP) {
            return Side.NONE;
        }
        int leftLinkCount = countLeft();

        if (rightLinkCount > leftLinkCount) {
            return Side.RIGHT;
        } else if (rightLinkCount == leftLinkCount) {
            return Side.NONE;
        }
        return Side.LEFT;
    }

    private int countLeft() {
        ChainLink next = this.left;
        int count = 0;
        while (next != null) {
            if (next == this) {
                return COUNT_FOR_CHAIN_LOOP;
            } else {
                count++;
            }
            next = next.left;
        }
        return count;
    }

    private int countRight() {
        ChainLink next = this.right;
        int count = 0;
        while (next != null) {
            if (next == this) {
                return COUNT_FOR_CHAIN_LOOP;
            } else {
                count++;
            }
            next = next.right;
        }
        return count;
    }
}