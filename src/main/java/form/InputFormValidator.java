package form;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputFormValidator {
    private static int LOTTO_PRICE = 1000;
    private static int LOTTO_HIGH = 45;
    private static int LOTTO_LOW = 1;

    public Integer checkPurchasePriceAndChange(String input){
        validateEmpty(input);
        Integer number = validatePositiveIntegerAndChange(input);
        validateLottoPrice(number);
        return number;
    }

    public Integer checkBonusNumber(String input, List<Integer> winningNumbers){
        int number = checkLotteryNumberAndChange(input);
        winningNumbers.add(number);
        validateDuplication(winningNumbers);
        return number;
    }

    public Integer checkLotteryNumberAndChange(String input){
        validateEmpty(input);
        int number = validatePositiveIntegerAndChange(input);
        validateRange(number);
        return number;
    }

    public List<Integer> checkWinningNumbersAndChange(String input){
        validateEmpty(input);
        return parseNumbers(input);
    }

    private List<Integer> parseNumbers(String input){
        String[] numbers = input.split(",");
        List<Integer> numberList = new ArrayList<Integer>();
        for (String number : numbers){
            numberList.add(checkLotteryNumberAndChange(number));
        }
        validateDuplication(numberList);
        return numberList;
    }

    private void validateEmpty(String input){
        if (input.isEmpty() || input == null) {
            throw new IllegalArgumentException("[ERROR] 값이 입력되지 않았습니다.");
        }
    }

    private int validatePositiveIntegerAndChange(String input) {
        int number;
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 입력값이 숫자 형식이 아닙니다.");
        }
        return number;
    }

    private void validateRange(int input){
        if (input > LOTTO_HIGH || input < LOTTO_LOW){
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private void validateLottoPrice(int input){
        if (input % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 로또를 살 수 없습니다. (입력값이 음수거나 1000으로 나누어 떨어지지 않습니다)");
        }
    }

    private void validateDuplication(List<Integer> numbers){
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numberSet.size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호에 중복이 존재합니다.");
        }
    }
}
