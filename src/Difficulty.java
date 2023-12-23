public enum Difficulty {
    EASY(43),
    MEDIUM(51),
    HARD(56),
    EXPERT(81);

    private int emptyCells;

    private Difficulty(int emptyCells) {
        this.emptyCells = emptyCells;
    }

    public int getEmptyCells() {
        return emptyCells;
    }
}
