package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoPublisherTest {
    @Test
    @DisplayName("구입 금액만큼 로또를 발행한다")
    void 구입량에_따라_로또_발행하는지() {
        // given
        int price = 8000;
        int expectedQuantity = 8;

        // when
        LottoPublisher publisher = new LottoPublisher(price);

        // then
        assertThat(publisher.lottoQuantity).isEqualTo(expectedQuantity);
        assertThat(publisher.lottos.size()).isEqualTo(expectedQuantity);

        // 발행된 로또가 null이 아닌지, Lotto 객체인지 확인
        assertThat(publisher.lottos.get(0)).isInstanceOf(Lotto.class);
    }
}
