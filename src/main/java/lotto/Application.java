package lotto;

import camp.nextstep.edu.missionutils.Console;
import form.InputFormValidator;
import form.LottoPrinter;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        LottoPrinter lottoPrinter = new LottoPrinter();
        InputFormValidator inputFormValidator = new InputFormValidator();
        WinningStatistics winningStatistics = new WinningStatistics();

        lottoPrinter.priceGuide();
        // 로또 구매
        int price = inputFormValidator.checkPurchasePriceAndChange(Console.readLine());
        LottoPublisher lottoPublisher = new LottoPublisher(price);

        lottoPrinter.purchasedLottoGuide(lottoPublisher.lottoQuantity, lottoPublisher.lottos);

        lottoPrinter.winningNumberGuide();
        List<Integer> winningNumbers = inputFormValidator.checkWinningNumbersAndChange(Console.readLine());

        lottoPrinter.bonusNumberGuide();
        Integer bonusNumber =  inputFormValidator.checkBonusNumber(Console.readLine(), winningNumbers);

        lottoPrinter.winnerGuide(winningStatistics.calculateWinningStats(winningNumbers, bonusNumber, lottoPublisher.lottos));
        lottoPrinter.revenueGuide(winningStatistics.calculatePrize(), price);
    }
}
