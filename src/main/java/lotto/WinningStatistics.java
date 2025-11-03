package lotto;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WinningStatistics {
    private final Map<LottoWinners, Integer> winnerCounts;

    public WinningStatistics(){
        this.winnerCounts = new EnumMap<>(LottoWinners.class);
        for (LottoWinners rank : LottoWinners.values()) {
            winnerCounts.put(rank, 0);
        }
    }

    public Map<LottoWinners, Integer> calculateWinningStats(List<Integer> winningNumbers, int bonusNumber, List<Lotto> lottos){
        for (Lotto lotto : lottos) {
            LottoWinners rank = determineRank(lotto, winningNumbers, bonusNumber);
            if (rank != null){
                this.winnerCounts.put(rank, this.winnerCounts.get(rank) + 1);
            }
        }
        return this.winnerCounts;
    }

    private LottoWinners determineRank(Lotto lotto, List<Integer> winningNumbers, int bonusNumber){
        int matchCount = countMatches(lotto, winningNumbers);
        boolean hasBonus = lotto.getNumbers().contains(bonusNumber);

        return LottoWinners.valueOf(matchCount, hasBonus);
    }

    private int countMatches(Lotto lotto, List<Integer> winningNumbers){
        Set<Integer> lottoNumbers = new HashSet<>(lotto.getNumbers());
        int matches = 0;
        for (int winningNum : winningNumbers) {
            if (lottoNumbers.contains(winningNum)) {
                matches++;
            }
        }
        return matches;
    }

    public int calculatePrize(){
        int prize = 0;
        for (LottoWinners rank : LottoWinners.values()) {
            int count = this.winnerCounts.get(rank);
            int price = rank.getWinnerPrize();
            prize += count*price;
        }
        return prize;
    }
}
