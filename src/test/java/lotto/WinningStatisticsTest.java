package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningStatisticsTest {
    private WinningStatistics statistics;
    private List<Integer> winningNumbers;
    private int bonusNumber;

    @BeforeEach
    void setUp() {
        // 테스트 실행 전 매번 statistics 객체를 새로 생성하여 초기화
        statistics = new WinningStatistics();
        winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        bonusNumber = 7;
    }

    @Test
    @DisplayName("초기화 시 모든 당첨 통계가 0이다")
    void checkInitialStatistics() {
        // when
        int prize = statistics.calculatePrize();
        Map<LottoWinners, Integer> stats = statistics.calculateWinningStats(winningNumbers, bonusNumber, List.of());

        // then
        assertThat(prize).isEqualTo(0);

        // 모든 등수의 카운트가 0인지 확인
        for (LottoWinners rank : LottoWinners.values()) {
            assertThat(stats.get(rank)).isEqualTo(0);
        }
    }

    @Test
    @DisplayName("1등, 3등, 5등 각 1개씩 당첨 통계를 정확히 계산한다")
    void calculateWinningStats_Correctly() {
        // given
        Lotto lotto1st = new Lotto(List.of(1, 2, 3, 4, 5, 6)); // 1등
        Lotto lotto3rd = new Lotto(List.of(1, 2, 3, 4, 5, 8)); // 3등 (보너스 7과 다름)
        Lotto lotto5th = new Lotto(List.of(1, 2, 3, 10, 11, 12)); // 5등
        Lotto lottoFail = new Lotto(List.of(10, 11, 12, 13, 14, 15)); // 꽝
        List<Lotto> lottos = List.of(lotto1st, lotto3rd, lotto5th, lottoFail);

        // when
        Map<LottoWinners, Integer> stats = statistics.calculateWinningStats(winningNumbers, bonusNumber, lottos);

        // then
        assertThat(stats.get(LottoWinners.FIRST)).isEqualTo(1);
        assertThat(stats.get(LottoWinners.SECOND)).isEqualTo(0);
        assertThat(stats.get(LottoWinners.THIRD)).isEqualTo(1);
        assertThat(stats.get(LottoWinners.FOURTH)).isEqualTo(0);
        assertThat(stats.get(LottoWinners.FIFTH)).isEqualTo(1);
    }

    @Test
    @DisplayName("2등 당첨을 정확히 계산한다")
    void calculateWinningStats_SecondPlace() {
        // given
        Lotto lotto2nd = new Lotto(List.of(1, 2, 3, 4, 5, 7)); // 2등 (보너스 7 일치)
        List<Lotto> lottos = List.of(lotto2nd);

        // when
        Map<LottoWinners, Integer> stats = statistics.calculateWinningStats(winningNumbers, bonusNumber, lottos);

        // then
        assertThat(stats.get(LottoWinners.SECOND)).isEqualTo(1);
        assertThat(stats.get(LottoWinners.THIRD)).isEqualTo(0); // 3등이 아님
    }

    @Test
    @DisplayName("총 당첨금을 정확히 계산한다")
    void calculatePrize_Correctly() {
        // given
        // 4등 1개 (50,000원), 5등 2개 (5,000 * 2 = 10,000원)
        Lotto lotto4th = new Lotto(List.of(1, 2, 3, 4, 10, 11));
        Lotto lotto5th_1 = new Lotto(List.of(1, 2, 3, 10, 11, 12));
        Lotto lotto5th_2 = new Lotto(List.of(4, 5, 6, 10, 11, 12));
        List<Lotto> lottos = List.of(lotto4th, lotto5th_1, lotto5th_2);

        int expectedPrize = 50000 + 5000 + 5000;

        // when
        statistics.calculateWinningStats(winningNumbers, bonusNumber, lottos);
        int prize = statistics.calculatePrize();

        // then
        assertThat(prize).isEqualTo(expectedPrize);
    }
}
