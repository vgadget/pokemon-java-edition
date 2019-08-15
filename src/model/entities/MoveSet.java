package model.entities;

import model.entities.Movements.Move;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Adrian Vazquez
 */
public class MoveSet implements Entity<String> {

    private List<Move> moveSet;
    private int[] remainingPP;
    int selectedMove;

    public MoveSet() {
        moveSet = new LinkedList<>();
        remainingPP = new int[4];
        selectedMove = 0;
    }

    public void selectMove(int i) {

        selectedMove = i;

        if (selectedMove > 4 || selectedMove < 0) {
            selectedMove = 0;
        }
    }

    public void selectMove(Move m) {
        selectMove(this.moveSet.indexOf(m));
    }

    public Move getSelectedMove() {

        if (this.moveSet.size() > 0) {
            return this.moveSet.get(selectedMove);
        } else {
            return null;
        }
    }

    public Iterator<Move> getMoves() {
        return this.moveSet.iterator();
    }

    public int getRemainingPP(Move m) {

        if (moveSet.contains(m)) {
            return remainingPP[moveSet.indexOf(m)];
        }

        return -1;
    }

    public void setRemainingPP(Move m, int pp) {
        if (moveSet.contains(m)) {

            if (pp > m.getPp()) {
                pp = m.getPp();
            } else if (pp < 0) {
                pp = 0;
            }

            this.remainingPP[moveSet.indexOf(m)] = pp;
        }
    }

    public boolean add(Move m) {

        if (moveSet.size() < 4 && m != null && !moveSet.contains(m)) {
            boolean success = moveSet.add(m);

            if (success) {
                remainingPP[moveSet.indexOf(m)] = m.getPp();
            }

            return success;
        }

        return false;
    }

    public boolean remove(Move m) {
        if (moveSet.size() > 1) {
            return moveSet.remove(m);
        }
        return false;
    }

    public boolean replace(Move previousMove, Move newMove) {

        if (newMove == null || previousMove == null) {
            return false;
        }

        boolean removed = remove(previousMove);
        boolean added = false;

        if (removed) {
            added = add(newMove);
            if (!added) {
                add(previousMove);
            }
        }

        return removed && added;
    }

    public int availableMoves() {
        return this.moveSet.size();
    }

    @Override
    public String toString() {
        return "MoveSet{" + "moveSet=" + moveSet + ", remainingPP=" + Arrays.toString(remainingPP) + ", selectedMove=" + selectedMove + '}';
    }

    @Override
    public int compareTo(Entity o) {

        if (o instanceof MoveSet) {

            for (Move m : moveSet) {
                for (Move n : ((MoveSet) o).moveSet) {

                    if (m.compareTo(n) != 0) {
                        return m.compareTo(n);
                    }

                }
            }

            return 0;
        }

        return this.getClass().getName().compareTo(o.getClass().getName());
    }

    @Override
    public String getPK() {
        return this.toString();
    }

}
