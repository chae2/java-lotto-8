package form;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lotto.Lotto;
import lotto.LottoWinners;

public class LottoPrinter {
//    static final int lotto_length = 6;
    public void priceGuide(){
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void purchasedLottoGuide(int lottoQuantity, List<Lotto> lottos){
        System.out.println(lottoQuantity + "개를 구매했습니다.");
        for (Lotto lotto : lottos){
            System.out.println(Arrays.toString(lotto.getNumbers().toArray()));
        }
    }

    public void winnerGuide(Map<LottoWinners, Integer> winners) {
        System.out.println("당첨 통계");
        System.out.println("---");

        LottoWinners[] ranks = {
                LottoWinners.FIFTH,
                LottoWinners.FOURTH,
                LottoWinners.THIRD,
                LottoWinners.SECOND,
                LottoWinners.FIRST
        };

        for (LottoWinners rank : ranks) {
            String inform = rank.printInform();
            int count = winners.get(rank);
            System.out.println(inform+" - "+count+"개");
        }
    }

    public void winningNumberGuide(){
        System.out.println("당첨 번호를 입력해 주세요.");
    }

    public void bonusNumberGuide(){
        System.out.println("보너스 번호를 입력해 주세요.");
    }

    public void revenueGuide(int prize, int purchase) {
        double revenue = (double) prize/purchase * 100.0;
        System.out.printf("총 수익률은 %.1f%%입니다.", revenue);
    }
}
