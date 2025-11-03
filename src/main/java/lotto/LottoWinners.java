package lotto;

public enum LottoWinners {
    FIRST(6,"6개 일치 (2,000,000,000원)", 2000000000),
    SECOND(5,"5개 일치, 보너스 볼 일치 (30,000,000원)", 30000000),
    THIRD(5,"5개 일치 (1,500,000원)", 1500000),
    FOURTH(4,"4개 일치 (50,000원)", 50000),
    FIFTH(3,"3개 일치 (5,000원)",5000);

    private final int matchNumber;
    private final String inform;
    private final int winnerPrize;

    LottoWinners(int matchNumbers, String inform, int winnerPrize){
        this.matchNumber = matchNumbers;
        this.inform = inform;
        this.winnerPrize = winnerPrize;
    }

    public String printInform(){
        return this.inform;
    }

    public int getMatchNumber(){
        return this.matchNumber;
    }

    public int getWinnerPrize(){
        return this.winnerPrize;
    }

    public static LottoWinners valueOf(int matchNumbers, boolean hasBonus) {
        if (matchNumbers == FIRST.getMatchNumber()) {
            return FIRST;
        }
        if (matchNumbers == SECOND.getMatchNumber() && hasBonus) {
            return SECOND;
        }
        if (matchNumbers == THIRD.getMatchNumber()) {
            return THIRD;
        }
        if (matchNumbers == FOURTH.getMatchNumber()) {
            return FOURTH;
        }
        if (matchNumbers == FIFTH.getMatchNumber()) {
            return FIFTH;
        }
        return null; // 꽝
    }
}