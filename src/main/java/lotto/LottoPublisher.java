package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoPublisher {
    public List<Lotto> lottos = new ArrayList<>();
    public int lottoQuantity;
    private static int lottoPrice = 1000;

    LottoPublisher(int price) {
        this.lottoQuantity = price / lottoPrice;
        for (int i = 0; i < lottoQuantity; i++) {
            Lotto lotto = new Lotto(publishNumbers());
            this.lottos.add(lotto);
        }
    }

    private List<Integer> publishNumbers() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1,45,6);
        Collections.sort(numbers); // 오름차순 정렬
        return numbers;
    }
}
